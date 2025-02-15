package com.github.intellij.gno.completion;

import com.github.intellij.gno.language.GnoIcons;
import com.github.intellij.gno.psi.*;
import com.github.intellij.gno.psi.impl.GnoPsiImplUtil;
import com.github.intellij.gno.sdk.GnoSdkUtil;
import com.github.intellij.gno.stubs.GnoFieldDefinitionStub;
import com.intellij.codeInsight.AutoPopupController;
import com.intellij.codeInsight.completion.InsertHandler;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.completion.PrefixMatcher;
import com.intellij.codeInsight.completion.PrioritizedLookupElement;
import com.intellij.codeInsight.completion.impl.CamelHumpMatcher;
import com.intellij.codeInsight.completion.util.ParenthesesInsertHandler;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.codeInsight.lookup.LookupElementPresentation;
import com.intellij.codeInsight.lookup.LookupElementRenderer;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.ObjectUtils;
import com.intellij.util.ui.UIUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.TestOnly;

import javax.swing.*;

public class GnoCompletionUtil {
    public static final int KEYWORD_PRIORITY = 20;
    public static final int CONTEXT_KEYWORD_PRIORITY = 25;
    public static final int NOT_IMPORTED_FUNCTION_PRIORITY = 3;
    public static final int FUNCTION_PRIORITY = NOT_IMPORTED_FUNCTION_PRIORITY + 10;
    public static final int NOT_IMPORTED_TYPE_PRIORITY = 5;
    public static final int TYPE_PRIORITY = NOT_IMPORTED_TYPE_PRIORITY + 10;
    public static final int NOT_IMPORTED_TYPE_CONVERSION = 1;
    public static final int TYPE_CONVERSION = NOT_IMPORTED_TYPE_CONVERSION + 10;
    public static final int NOT_IMPORTED_VAR_PRIORITY = 5;
    public static final int VAR_PRIORITY = NOT_IMPORTED_VAR_PRIORITY + 10;
    private static final int FIELD_PRIORITY = CONTEXT_KEYWORD_PRIORITY + 1;
    private static final int LABEL_PRIORITY = 15;
    public static final int PACKAGE_PRIORITY = 5;

    public static class Lazy {
        private static final SingleCharInsertHandler DIR_INSERT_HANDLER = new SingleCharInsertHandler('/');
        private static final SingleCharInsertHandler PACKAGE_INSERT_HANDLER = new SingleCharInsertHandler('.');

        public static final InsertHandler<LookupElement> VARIABLE_OR_FUNCTION_INSERT_HANDLER = new InsertHandler<LookupElement>() {
            @Override
            public void handleInsert(InsertionContext context, @NotNull LookupElement item) {
                PsiElement e = item.getPsiElement();
                if (e instanceof GnoSignatureOwner) {
                    doInsert(context, item, ((GnoSignatureOwner)e).getSignature());
                }
                else if (e instanceof GnoNamedElement) {
                    GnoType type = ((GnoNamedElement)e).getGnoType(null);
                    if (type instanceof GnoFunctionType) {
                        doInsert(context, item, ((GnoFunctionType)type).getSignature());
                    }
                }
            }

            private void doInsert(InsertionContext context, @NotNull LookupElement item, @Nullable GnoSignature signature) {
                int paramsCount = signature != null ? signature.getParameters().getParameterDeclarationList().size() : 0;
                InsertHandler<LookupElement> handler = paramsCount == 0 ? ParenthesesInsertHandler.NO_PARAMETERS : ParenthesesInsertHandler.WITH_PARAMETERS;
                handler.handleInsert(context, item);
                if (signature != null) {
                    AutoPopupController.getInstance(context.getProject()).autoPopupParameterInfo(context.getEditor(), null);
                }
            }
        };
        public static final InsertHandler<LookupElement> TYPE_CONVERSION_INSERT_HANDLER = (context, item) -> {
            PsiElement element = item.getPsiElement();
            if (element instanceof GnoTypeSpec) {
                GnoType type = ((GnoTypeSpec)element).getSpecType().getType();
                if (type instanceof GnoStructType || type instanceof GnoArrayOrSliceType || type instanceof GnoMapType) {
                    BracesInsertHandler.ONE_LINER.handleInsert(context, item);
                }
                else {
                    ParenthesesInsertHandler.WITH_PARAMETERS.handleInsert(context, item);
                }
            }
        };
        private static final SingleCharInsertHandler FIELD_DEFINITION_INSERT_HANDLER = new SingleCharInsertHandler(':') {
            @Override
            public void handleInsert(@NotNull InsertionContext context, LookupElement item) {
                PsiFile file = context.getFile();
                if (!(file instanceof GnoFile)) return;
                context.commitDocument();
                int offset = context.getStartOffset();
                PsiElement at = file.findElementAt(offset);
                GnoCompositeElement ref = PsiTreeUtil.getParentOfType(at, GnoValue.class, GnoReferenceExpression.class);
                if (ref instanceof GnoReferenceExpression && (((GnoReferenceExpression)ref).getQualifier() != null || GnoPsiImplUtil.prevDot(ref))) {
                    return;
                }
                GnoValue value = PsiTreeUtil.getParentOfType(at, GnoValue.class);
                if (value == null || PsiTreeUtil.getPrevSiblingOfType(value, GnoKey.class) != null) return;
                super.handleInsert(context, item);
            }
        };
        private static final LookupElementRenderer<LookupElement> FUNCTION_RENDERER = new LookupElementRenderer<LookupElement>() {
            @Override
            public void renderElement(@NotNull LookupElement element, @NotNull LookupElementPresentation p) {
                PsiElement o = element.getPsiElement();
                if (!(o instanceof GnoNamedSignatureOwner)) return;
                GnoNamedSignatureOwner f = (GnoNamedSignatureOwner)o;
                Icon icon = f instanceof GnoMethodDeclaration || f instanceof GnoMethodSpec ? GnoIcons.METHOD : GnoIcons.FUNCTION;
                String typeText = "";
                GnoSignature signature = f.getSignature();
                String paramText = "";
                if (signature != null) {
                    GnoResult result = signature.getResult();
                    paramText = signature.getParameters().getText();
                    if (result != null) typeText = result.getText();
                }

                p.setIcon(icon);
                p.setTypeText(typeText);
                p.setTypeGrayed(true);
                p.setTailText(calcTailText(f), true);
                p.setItemText(element.getLookupString() + paramText);
            }
        };
        private static final LookupElementRenderer<LookupElement> VARIABLE_RENDERER = new LookupElementRenderer<LookupElement>() {
            @Override
            public void renderElement(@NotNull LookupElement element, @NotNull LookupElementPresentation p) {
                PsiElement o = element.getPsiElement();
                if (!(o instanceof GnoNamedElement)) return;
                GnoNamedElement v = (GnoNamedElement)o;
                GnoType type = typesDisabled ? null : v.getGnoType(null);
                String text = GnoPsiImplUtil.getText(type);
                Icon icon = v instanceof GnoVarDefinition ? GnoIcons.VARIABLE :
                        v instanceof GnoParamDefinition ? GnoIcons.PARAMETER :
                                v instanceof GnoFieldDefinition ? GnoIcons.FIELD :
                                        v instanceof GnoReceiver ? GnoIcons.RECEIVER :
                                                v instanceof GnoConstDefinition ? GnoIcons.CONSTANT :
                                                        v instanceof GnoAnonymousFieldDefinition ? GnoIcons.FIELD :
                                                                null;

                p.setIcon(icon);
                p.setTailText(calcTailTextForFields(v), true);
                p.setTypeText(text);
                p.setTypeGrayed(true);
                p.setItemText(element.getLookupString());
            }
        };
    }

    private static boolean typesDisabled;

    @TestOnly
    public static void disableTypeInfoInLookup(@NotNull Disposable disposable) {
        typesDisabled = true;
        Disposer.register(disposable, () -> {
            //noinspection AssignmentToStaticFieldFromInstanceMethod
            typesDisabled = false;
        });
    }

    private GnoCompletionUtil() {

    }

    @NotNull
    public static CamelHumpMatcher createPrefixMatcher(@NotNull PrefixMatcher original) {
        return createPrefixMatcher(original.getPrefix());
    }

    @NotNull
    public static CamelHumpMatcher createPrefixMatcher(@NotNull String prefix) {
        return new CamelHumpMatcher(prefix, false);
    }

    @NotNull
    public static LookupElement createFunctionOrMethodLookupElement(@NotNull GnoNamedSignatureOwner f,
                                                                    @NotNull String lookupString,
                                                                    @Nullable InsertHandler<LookupElement> h,
                                                                    double priority) {
        return PrioritizedLookupElement.withPriority(LookupElementBuilder
                .createWithSmartPointer(lookupString, f)
                .withRenderer(Lazy.FUNCTION_RENDERER)
                .withInsertHandler(h != null ? h : Lazy.VARIABLE_OR_FUNCTION_INSERT_HANDLER), priority);
    }

    @Nullable
    private static String calcTailText(GnoSignatureOwner m) {
        if (typesDisabled) {
            return null;
        }
        String text = "";
        if (m instanceof GnoMethodDeclaration) {
            text = GnoPsiImplUtil.getText(((GnoMethodDeclaration)m).getReceiverType());
        }
        else if (m instanceof GnoMethodSpec) {
            PsiElement parent = m.getParent();
            if (parent instanceof GnoInterfaceType) {
                text = GnoPsiImplUtil.getText((GnoInterfaceType)parent);
            }
        }
        return StringUtil.isNotEmpty(text) ? " " + UIUtil.rightArrow() + " " + text : null;
    }

    @NotNull
    public static LookupElement createTypeLookupElement(@NotNull GnoTypeSpec t) {
        return createTypeLookupElement(t, StringUtil.notNullize(t.getName()), null, null, TYPE_PRIORITY);
    }

    @NotNull
    public static LookupElement createTypeLookupElement(@NotNull GnoTypeSpec t,
                                                        @NotNull String lookupString,
                                                        @Nullable InsertHandler<LookupElement> handler,
                                                        @Nullable String importPath,
                                                        double priority) {
        LookupElementBuilder builder = LookupElementBuilder.createWithSmartPointer(lookupString, t)
                .withInsertHandler(handler).withIcon(GnoIcons.TYPE);
        if (importPath != null) builder = builder.withTailText(" " + importPath, true);
        return PrioritizedLookupElement.withPriority(builder, priority);
    }

    @NotNull
    public static LookupElement createLabelLookupElement(@NotNull GnoLabelDefinition l, @NotNull String lookupString) {
        return PrioritizedLookupElement.withPriority(LookupElementBuilder.createWithSmartPointer(lookupString, l).withIcon(GnoIcons.LABEL),
                LABEL_PRIORITY);
    }

    @NotNull
    public static LookupElement createTypeConversionLookupElement(@NotNull GnoTypeSpec t) {
        return createTypeConversionLookupElement(t, StringUtil.notNullize(t.getName()), null, null, TYPE_CONVERSION);
    }

    @NotNull
    public static LookupElement createTypeConversionLookupElement(@NotNull GnoTypeSpec t,
                                                                  @NotNull String lookupString,
                                                                  @Nullable InsertHandler<LookupElement> insertHandler,
                                                                  @Nullable String importPath,
                                                                  double priority) {
        // todo: check context and place caret in or outside {}
        InsertHandler<LookupElement> handler = ObjectUtils.notNull(insertHandler, Lazy.TYPE_CONVERSION_INSERT_HANDLER);
        return createTypeLookupElement(t, lookupString, handler, importPath, priority);
    }

    @Nullable
    public static LookupElement createFieldLookupElement(@NotNull GnoFieldDefinition v) {
        String name = v.getName();
        if (StringUtil.isEmpty(name)) return null;
        return createVariableLikeLookupElement(v, name, Lazy.FIELD_DEFINITION_INSERT_HANDLER, FIELD_PRIORITY);
    }

    @Nullable
    public static LookupElement createVariableLikeLookupElement(@NotNull GnoNamedElement v) {
        String name = v.getName();
        if (StringUtil.isEmpty(name)) return null;
        return createVariableLikeLookupElement(v, name, Lazy.VARIABLE_OR_FUNCTION_INSERT_HANDLER, VAR_PRIORITY);
    }

    @NotNull
    public static LookupElement createVariableLikeLookupElement(@NotNull GnoNamedElement v, @NotNull String lookupString,
                                                                @Nullable InsertHandler<LookupElement> insertHandler,
                                                                double priority) {
        return PrioritizedLookupElement.withPriority(LookupElementBuilder.createWithSmartPointer(lookupString, v)
                .withRenderer(Lazy.VARIABLE_RENDERER)
                .withInsertHandler(insertHandler), priority);
    }

    @Nullable
    private static String calcTailTextForFields(@NotNull GnoNamedElement v) {
        String name = null;
        if (v instanceof GnoFieldDefinition) {
            GnoFieldDefinitionStub stub = ((GnoFieldDefinition)v).getStub();
            GnoTypeSpec spec = stub != null ? stub.getParentStubOfType(GnoTypeSpec.class) : PsiTreeUtil.getParentOfType(v, GnoTypeSpec.class);
            name = spec != null ? spec.getName() : null;
        }
        return StringUtil.isNotEmpty(name) ? " " + UIUtil.rightArrow() + " " + name : null;
    }

    @Nullable
    public static LookupElement createPackageLookupElement(@NotNull GnoImportSpec spec, @Nullable String name, boolean vendoringEnabled) {
        name = name != null ? name : ObjectUtils.notNull(spec.getAlias(), spec.getLocalPackageName());
        return createPackageLookupElement(name, spec.getImportString().resolve(), spec, vendoringEnabled, true);
    }

    @NotNull
    public static LookupElement createPackageLookupElement(@NotNull String importPath,
                                                           @Nullable PsiDirectory directory,
                                                           @Nullable PsiElement context,
                                                           boolean vendoringEnabled,
                                                           boolean forType) {
        return createPackageLookupElement(importPath, getContextImportPath(context, vendoringEnabled), directory, forType);
    }

    @NotNull
    public static LookupElement createPackageLookupElement(@NotNull String importPath, @Nullable String contextImportPath,
                                                           @Nullable PsiDirectory directory, boolean forType) {
        LookupElementBuilder builder = directory != null
                ? LookupElementBuilder.create(directory, importPath)
                : LookupElementBuilder.create(importPath);
        return PrioritizedLookupElement.withPriority(builder.withLookupString(importPath.substring(Math.max(0, importPath.lastIndexOf('/'))))
                        .withIcon(GnoIcons.PACKAGE)
                        .withInsertHandler(forType ? Lazy.PACKAGE_INSERT_HANDLER : null),
                calculatePackagePriority(importPath, contextImportPath));
    }

    public static int calculatePackagePriority(@NotNull String importPath, @Nullable String currentPath) {
        int priority = PACKAGE_PRIORITY;
        if (StringUtil.isNotEmpty(currentPath)) {
            String[] givenSplit = importPath.split("/");
            String[] contextSplit = currentPath.split("/");
            for (int i = 0; i < contextSplit.length && i < givenSplit.length; i++) {
                if (contextSplit[i].equals(givenSplit[i])) {
                    priority++;
                }
                else {
                    break;
                }
            }
        }
        return priority - StringUtil.countChars(importPath, '/') - StringUtil.countChars(importPath, '.');
    }

    @Nullable
    public static String getContextImportPath(@Nullable PsiElement context, boolean vendoringEnabled) {
        if (context == null) return null;
        String currentPath = null;
        if (context instanceof PsiDirectory) {
            currentPath = GnoSdkUtil.getImportPath((PsiDirectory)context, vendoringEnabled);
        }
        else {
            PsiFile file = context.getContainingFile();
            if (file instanceof GnoFile) {
                currentPath = ((GnoFile)file).getImportPath(vendoringEnabled);
            }
        }
        return currentPath;
    }

    @NotNull
    public static LookupElementBuilder createDirectoryLookupElement(@NotNull PsiDirectory dir) {
        return LookupElementBuilder.createWithSmartPointer(dir.getName(), dir).withIcon(GnoIcons.DIRECTORY)
                .withInsertHandler(dir.getFiles().length == 0 ? Lazy.DIR_INSERT_HANDLER : null);
    }
}
