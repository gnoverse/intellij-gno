/*
 * Copyright 2013-2016 Sergey Ignatov, Alexander Zolotov, Florin Patan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.intellij.gno.psi.impl;

import com.github.intellij.gno.psi.GnoConstants;
import com.github.intellij.gno.GnoTypes;
import com.github.intellij.gno.psi.*;
import com.github.intellij.gno.psi.impl.imports.GnoImportReferenceSet;
import com.github.intellij.gno.runconfig.testing.GnoTestFinder;
import com.github.intellij.gno.sdk.GnoPackageUtil;
import com.github.intellij.gno.sdk.GnoSdkUtil;
import com.github.intellij.gno.stubs.*;
import com.github.intellij.gno.stubs.index.GnoIdFilter;
import com.github.intellij.gno.stubs.index.GnoMethodIndex;
import com.github.intellij.gno.util.GnoStringLiteralEscaper;
import com.github.intellij.gno.util.GnoUtil;
import com.intellij.codeInsight.highlighting.ReadWriteAccessDetector;
import com.intellij.diagnostic.AttachmentFactory;
import com.intellij.lang.ASTNode;
import com.intellij.lang.parser.GeneratedParserUtilBase;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.*;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.impl.source.resolve.reference.impl.PsiMultiReference;
import com.intellij.psi.impl.source.resolve.reference.impl.providers.FileReferenceOwner;
import com.intellij.psi.impl.source.resolve.reference.impl.providers.PsiFileReference;
import com.intellij.psi.impl.source.tree.LeafElement;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.tree.TokenSet;
import com.intellij.psi.util.CachedValueProvider;
import com.intellij.psi.util.CachedValuesManager;
import com.intellij.psi.util.PsiModificationTracker;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.ObjectUtils;
import com.intellij.util.PathUtil;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.github.intellij.gno.psi.impl.GnoLightType.*;
import static com.intellij.codeInsight.highlighting.ReadWriteAccessDetector.Access.*;
import static com.intellij.openapi.util.Conditions.equalTo;
import static java.lang.Boolean.TRUE;

public class GnoPsiImplUtil {
  private static final Logger LOG = Logger.getInstance(GnoPsiImplUtil.class);
  private static final Key<SmartPsiElementPointer<PsiElement>> CONTEXT = Key.create("CONTEXT");

  @NotNull
  public static SyntaxTraverser<PsiElement> goTraverser() {
    return SyntaxTraverser.psiTraverser().forceDisregardTypes(equalTo(GeneratedParserUtilBase.DUMMY_BLOCK));
  }

  public static boolean builtin(@Nullable PsiElement resolve) {
    return resolve != null && isBuiltinFile(resolve.getContainingFile());
  }

  public static boolean isConversionExpression(@Nullable GnoExpression expression) {
    if (expression instanceof GnoConversionExpr) {
      return true;
    }
    GnoReferenceExpression referenceExpression = null;
    if (expression instanceof GnoCallExpr) {
      referenceExpression = ObjectUtils.tryCast(((GnoCallExpr)expression).getExpression(), GnoReferenceExpression.class);
    }
    else if (expression instanceof GnoBuiltinCallExpr) {
      referenceExpression = ((GnoBuiltinCallExpr)expression).getReferenceExpression();
    }
    return referenceExpression != null && referenceExpression.resolve() instanceof GnoTypeSpec;
  }

  public static boolean isPanic(@NotNull GnoCallExpr o) {
    return stdLibCall(o, "panic");
  }

  public static boolean isRecover(@NotNull GnoCallExpr o) {
    return stdLibCall(o, "recover");
  }

  private static boolean stdLibCall(@NotNull GnoCallExpr o, @NotNull String name) {
    GnoExpression e = o.getExpression();
    if (e.textMatches(name) && e instanceof GnoReferenceExpression) {
      PsiReference reference = e.getReference();
      PsiElement resolve = reference != null ? reference.resolve() : null;
      if (!(resolve instanceof GnoFunctionDeclaration)) return false;
      return isBuiltinFile(resolve.getContainingFile());
    }
    return false;
  }

  public static boolean isBuiltinFile(@Nullable PsiFile file) {
    return file instanceof GnoFile
           && GnoConstants.BUILTIN_PACKAGE_NAME.equals(((GnoFile)file).getPackageName())
           && GnoConstants.BUILTIN_PACKAGE_NAME.equals(((GnoFile)file).getImportPath(false))
           && GnoConstants.BUILTIN_FILE_NAME.equals(file.getName());
  }

  @Nullable
  public static GnoTopLevelDeclaration getTopLevelDeclaration(@Nullable PsiElement startElement) {
    GnoTopLevelDeclaration declaration = PsiTreeUtil.getTopmostParentOfType(startElement, GnoTopLevelDeclaration.class);
    if (declaration == null || !(declaration.getParent() instanceof GnoFile)) return null;
    return declaration;
  }

  public static int getArity(@Nullable GnoSignature s) {
    return s == null ? -1 : s.getParameters().getParameterDeclarationList().size();
  }

  @Nullable
  public static PsiElement getContextElement(@Nullable ResolveState state) {
    SmartPsiElementPointer<PsiElement> context = state != null ? state.get(CONTEXT) : null;
    return context != null ? context.getElement() : null;
  }

  @NotNull
  public static ResolveState createContextOnElement(@NotNull PsiElement element) {
    return ResolveState.initial().put(CONTEXT, SmartPointerManager.getInstance(element.getProject()).createSmartPsiElementPointer(element));
  }

  @Nullable
  public static GnoTypeReferenceExpression getQualifier(@NotNull GnoTypeReferenceExpression o) {
    return PsiTreeUtil.getChildOfType(o, GnoTypeReferenceExpression.class);
  }

  @Nullable
  public static PsiDirectory resolve(@NotNull GnoImportString importString) {
    PsiReference[] references = importString.getReferences();
    for (PsiReference reference : references) {
      if (reference instanceof FileReferenceOwner) {
        PsiFileReference lastFileReference = ((FileReferenceOwner)reference).getLastFileReference();
        PsiElement result = lastFileReference != null ? lastFileReference.resolve() : null;
        return result instanceof PsiDirectory ? (PsiDirectory)result : null;
      }
    }
    return null;
  }

  @NotNull
  public static PsiReference getReference(@NotNull GnoTypeReferenceExpression o) {
    return new GnoTypeReference(o);
  }

  @NotNull
  public static PsiReference getReference(@NotNull GnoLabelRef o) {
    return new GnoLabelReference(o);
  }

  @Nullable
  public static PsiReference getReference(@NotNull GnoVarDefinition o) {
    GnoShortVarDeclaration shortDeclaration = PsiTreeUtil.getParentOfType(o, GnoShortVarDeclaration.class);
    boolean createRef = PsiTreeUtil.getParentOfType(shortDeclaration, GnoBlock.class, GnoForStatement.class, GnoIfStatement.class,
                                                    GnoSwitchStatement.class, GnoSelectStatement.class) instanceof GnoBlock;
    return createRef ? new GnoVarReference(o) : null;
  }

  @NotNull
  public static GnoReference getReference(@NotNull GnoReferenceExpression o) {
    return new GnoReference(o);
  }

  @NotNull
  public static PsiReference getReference(@NotNull GnoFieldName o) {
    GnoFieldNameReference field = new GnoFieldNameReference(o);
    GnoReference ordinal = new GnoReference(o);
    return new PsiMultiReference(new PsiReference[]{field, ordinal}, o) {
      @Override
      public PsiElement resolve() {
        PsiElement resolve = field.resolve();
        return resolve != null ? resolve : field.inStructTypeKey() ? null : ordinal.resolve();
      }

      @Override
      public boolean isReferenceTo(PsiElement element) {
        return GnoUtil.couldBeReferenceTo(element, getElement()) && getElement().getManager().areElementsEquivalent(resolve(), element);
      }
    };
  }

  @Nullable
  public static GnoReferenceExpression getQualifier(@SuppressWarnings("UnusedParameters") @NotNull GnoFieldName o) {
    return null;
  }

  @NotNull
  public static PsiReference[] getReferences(@NotNull GnoImportString o) {
    if (o.getTextLength() < 2) return PsiReference.EMPTY_ARRAY;
    return new GnoImportReferenceSet(o).getAllReferences();
  }

  @Nullable
  public static GnoReferenceExpression getQualifier(@NotNull GnoReferenceExpression o) {
    return PsiTreeUtil.getChildOfType(o, GnoReferenceExpression.class);
  }

  public static boolean processDeclarations(@NotNull GnoCompositeElement o,
                                            @NotNull PsiScopeProcessor processor,
                                            @NotNull ResolveState state,
                                            PsiElement lastParent,
                                            @NotNull PsiElement place) {
    boolean isAncestor = PsiTreeUtil.isAncestor(o, place, false);
    if (o instanceof GnoVarSpec) {
      return isAncestor || GnoCompositeElementImpl.processDeclarationsDefault(o, processor, state, lastParent, place);
    }

    if (isAncestor) return GnoCompositeElementImpl.processDeclarationsDefault(o, processor, state, lastParent, place);

    if (o instanceof GnoBlock ||
        o instanceof GnoIfStatement ||
        o instanceof GnoForStatement ||
        o instanceof GnoCommClause ||
        o instanceof GnoFunctionLit ||
        o instanceof GnoTypeCaseClause ||
        o instanceof GnoExprCaseClause) {
      return processor.execute(o, state);
    }
    return GnoCompositeElementImpl.processDeclarationsDefault(o, processor, state, lastParent, place);
  }

  @Nullable
  public static GnoType getGnoTypeInner(@NotNull GnoReceiver o, @SuppressWarnings("UnusedParameters") @Nullable ResolveState context) {
    return o.getType();
  }

  @Nullable
  public static PsiElement getIdentifier(@SuppressWarnings("UnusedParameters") @NotNull GnoAnonymousFieldDefinition o) {
    GnoTypeReferenceExpression expression = o.getTypeReferenceExpression();
    return expression != null ? expression.getIdentifier() : null;
  }

  @Nullable
  public static String getName(@NotNull GnoPackageClause packageClause) {
    GnoPackageClauseStub stub = packageClause.getStub();
    if (stub != null) return stub.getName();
    PsiElement packageIdentifier = packageClause.getIdentifier();
    return packageIdentifier != null ? packageIdentifier.getText().trim() : null;
  }

  @Nullable
  public static String getName(@NotNull GnoAnonymousFieldDefinition o) {
    PsiElement identifier = o.getIdentifier();
    return identifier != null ? identifier.getText() : null;
  }

  @Nullable
  public static GnoTypeReferenceExpression getTypeReferenceExpression(@NotNull GnoAnonymousFieldDefinition o) {
    return getTypeRefExpression(o.getType());
  }

  @Nullable
  public static GnoType getGnoTypeInner(@NotNull GnoAnonymousFieldDefinition o,
                                      @SuppressWarnings("UnusedParameters") @Nullable ResolveState context) {
    return o.getType();
  }

  @Nullable
  public static String getName(@NotNull GnoMethodSpec o) {
    GnoNamedStub<GnoMethodSpec> stub = o.getStub();
    if (stub != null) {
      return stub.getName();
    }
    PsiElement identifier = o.getIdentifier();
    if (identifier != null) return identifier.getText();
    GnoTypeReferenceExpression typeRef = o.getTypeReferenceExpression();
    return typeRef != null ? typeRef.getIdentifier().getText() : null;
  }

  @Nullable
  public static GnoType getReceiverType(@NotNull GnoMethodDeclaration o) {
    GnoReceiver receiver = o.getReceiver();
    return receiver == null ? null : receiver.getType();
  }

  // todo: merge with {@link this#getTypeRefExpression}
  @Nullable
  public static GnoTypeReferenceExpression getTypeReference(@Nullable GnoType o) {
    if (o instanceof GnoPointerType) {
      return PsiTreeUtil.findChildOfAnyType(o, GnoTypeReferenceExpression.class);
    }
    return o != null ? o.getTypeReferenceExpression() : null;
  }

  // todo: merge with {@link this#getTypeReference}
  @Nullable
  public static GnoTypeReferenceExpression getTypeRefExpression(@Nullable GnoType type) {
    GnoType unwrap = unwrapPointerIfNeeded(type);
    return unwrap != null ? unwrap.getTypeReferenceExpression() : null;
  }

  @Nullable
  public static GnoType getGnoTypeInner(@NotNull GnoConstDefinition o, @Nullable ResolveState context) {
    GnoType fromSpec = findTypeInConstSpec(o);
    if (fromSpec != null) return fromSpec;
    // todo: stubs 
    return RecursionManager.doPreventingRecursion(o, true, (NullableComputable<GnoType>)() -> {
      GnoConstSpec prev = PsiTreeUtil.getPrevSiblingOfType(o.getParent(), GnoConstSpec.class);
      while (prev != null) {
        GnoType type = prev.getType();
        if (type != null) return type;
        GnoExpression expr = ContainerUtil.getFirstItem(prev.getExpressionList()); // not sure about first
        if (expr != null) return expr.getGnoType(context);
        prev = PsiTreeUtil.getPrevSiblingOfType(prev, GnoConstSpec.class);
      }
      return null;
    });
  }

  @Nullable
  private static GnoType findTypeInConstSpec(@NotNull GnoConstDefinition o) {
    GnoConstDefinitionStub stub = o.getStub();
    PsiElement parent = PsiTreeUtil.getStubOrPsiParent(o);
    if (!(parent instanceof GnoConstSpec)) return null;
    GnoConstSpec spec = (GnoConstSpec)parent;
    GnoType commonType = spec.getType();
    if (commonType != null) return commonType;
    List<GnoConstDefinition> varList = spec.getConstDefinitionList();
    int i = Math.max(varList.indexOf(o), 0);
    if (stub != null) return null;
    GnoConstSpecStub specStub = spec.getStub();
    List<GnoExpression> es = specStub != null ? specStub.getExpressionList() : spec.getExpressionList(); // todo: move to constant spec
    if (es.size() <= i) return null;
    return es.get(i).getGnoType(null);
  }

  @Nullable
  private static GnoType unwrapParType(@NotNull GnoExpression o, @Nullable ResolveState c) {
    GnoType inner = getGnoTypeInner(o, c);
    return inner instanceof GnoParType ? ((GnoParType)inner).getActualType() : inner;
  }

  @Nullable
  public static GnoType getGnoType(@NotNull GnoExpression o, @Nullable ResolveState context) {
    return RecursionManager.doPreventingRecursion(o, true, () -> {
      if (context != null) return unwrapParType(o, context);
      return CachedValuesManager.getCachedValue(o, () -> CachedValueProvider.Result
        .create(unwrapParType(o, createContextOnElement(o)), PsiModificationTracker.MODIFICATION_COUNT));
    });
  }

  @Nullable
  private static GnoType getGnoTypeInner(@NotNull GnoExpression o, @Nullable ResolveState context) {
    if (o instanceof GnoUnaryExpr) {
      GnoUnaryExpr u = (GnoUnaryExpr)o;
      GnoExpression e = u.getExpression();
      if (e == null) return null;
      GnoType type = e.getGnoType(context);
      GnoType base = type == null || type.getTypeReferenceExpression() == null ? type : type.getUnderlyingType();
      if (u.getBitAnd() != null) return type != null ? new LightPointerType(type) : null;
      if (u.getSendChannel() != null) return base instanceof GnoChannelType ? ((GnoChannelType)base).getType() : type;
      if (u.getMul() != null) return base instanceof GnoPointerType ? ((GnoPointerType)base).getType() : type;
      return type;
    }
    if (o instanceof GnoAddExpr) {
      return ((GnoAddExpr)o).getLeft().getGnoType(context);
    }
    if (o instanceof GnoMulExpr) {
      GnoExpression left = ((GnoMulExpr)o).getLeft();
      if (!(left instanceof GnoLiteral)) return left.getGnoType(context);
      GnoExpression right = ((GnoBinaryExpr)o).getRight();
      if (right != null) return right.getGnoType(context);
    }
    else if (o instanceof GnoCompositeLit) {
      GnoType type = ((GnoCompositeLit)o).getType();
      if (type != null) return type;
      GnoTypeReferenceExpression expression = ((GnoCompositeLit)o).getTypeReferenceExpression();
      return expression != null ? expression.resolveType() : null;
    }
    else if (o instanceof GnoFunctionLit) {
      return new LightFunctionType((GnoFunctionLit)o);
    }
    else if (o instanceof GnoBuiltinCallExpr) {
      String text = ((GnoBuiltinCallExpr)o).getReferenceExpression().getText();
      boolean isNew = "new".equals(text);
      boolean isMake = "make".equals(text);
      if (isNew || isMake) {
        GnoBuiltinArgumentList args = ((GnoBuiltinCallExpr)o).getBuiltinArgumentList();
        GnoType type = args != null ? args.getType() : null;
        return isNew ? type == null ? null : new LightPointerType(type) : type;
      }
    }
    else if (o instanceof GnoCallExpr) {
      GnoExpression e = ((GnoCallExpr)o).getExpression();
      if (e instanceof GnoReferenceExpression) { // todo: unify Type processing
        PsiReference ref = e.getReference();
        PsiElement resolve = ref != null ? ref.resolve() : null;
        if (((GnoReferenceExpression)e).getQualifier() == null && "append".equals(((GnoReferenceExpression)e).getIdentifier().getText())) {
          if (resolve instanceof GnoFunctionDeclaration && isBuiltinFile(resolve.getContainingFile())) {
            List<GnoExpression> l = ((GnoCallExpr)o).getArgumentList().getExpressionList();
            GnoExpression f = ContainerUtil.getFirstItem(l);
            return f == null ? null : getGnoType(f, context);
          }
        }
        else if (resolve == e) { // C.call()
          return new GnoCType(e);
        }
      }
      GnoType type = ((GnoCallExpr)o).getExpression().getGnoType(context);
      if (type instanceof GnoFunctionType) {
        return funcType(type);
      }
      GnoType byRef = type != null && type.getTypeReferenceExpression() != null ? type.getUnderlyingType() : null;
      if (byRef instanceof GnoFunctionType) {
        return funcType(byRef);
      }
      return type;
    }
    else if (o instanceof GnoReferenceExpression) {
      PsiReference reference = o.getReference();
      PsiElement resolve = reference != null ? reference.resolve() : null;
      if (resolve instanceof GnoTypeOwner) return typeOrParameterType((GnoTypeOwner)resolve, context);
    }
    else if (o instanceof GnoParenthesesExpr) {
      GnoExpression expression = ((GnoParenthesesExpr)o).getExpression();
      return expression != null ? expression.getGnoType(context) : null;
    }
    else if (o instanceof GnoSelectorExpr) {
      GnoExpression item = ContainerUtil.getLastItem(((GnoSelectorExpr)o).getExpressionList());
      return item != null ? item.getGnoType(context) : null;
    }
    else if (o instanceof GnoIndexOrSliceExpr) {
      GnoType referenceType = getIndexedExpressionReferenceType((GnoIndexOrSliceExpr)o, context);
      if (o.getNode().findChildByType(GnoTypes.COLON) != null) return referenceType; // means slice expression, todo: extract if needed
      GnoType type = referenceType != null ? referenceType.getUnderlyingType() : null;
      if (type instanceof GnoMapType) {
        List<GnoType> list = ((GnoMapType)type).getTypeList();
        if (list.size() == 2) {
          return list.get(1);
        }
      }
      else if (type instanceof GnoArrayOrSliceType) {
        return ((GnoArrayOrSliceType)type).getType();
      }
      else if (GnoTypeUtil.isString(type)) {
        return getBuiltinType("byte", o);
      }
    }
    else if (o instanceof GnoTypeAssertionExpr) {
      return ((GnoTypeAssertionExpr)o).getType();
    }
    else if (o instanceof GnoConversionExpr) {
      return ((GnoConversionExpr)o).getType();
    }
    else if (o instanceof GnoStringLiteral) {
      return getBuiltinType("string", o);
    }
    else if (o instanceof GnoLiteral) {
      GnoLiteral l = (GnoLiteral)o;
      if (l.getChar() != null) return getBuiltinType("rune", o);
      if (l.getInt() != null || l.getHex() != null || ((GnoLiteral)o).getOct() != null) return getBuiltinType("int", o);
      if (l.getFloat() != null) return getBuiltinType("float64", o);
      if (l.getFloati() != null) return getBuiltinType("complex64", o);
      if (l.getDecimali() != null) return getBuiltinType("complex128", o);
    }
    else if (o instanceof GnoConditionalExpr) {
      return getBuiltinType("bool", o);
    }
    return null;
  }

  @Nullable
  public static GnoType getIndexedExpressionReferenceType(@NotNull GnoIndexOrSliceExpr o, @Nullable ResolveState context) {
    GnoExpression first = ContainerUtil.getFirstItem(o.getExpressionList());
    // todo: calculate type for indexed expressions only
    // https://golang.org/ref/spec#Index_expressions – a[x] is shorthand for (*a)[x]
    return unwrapPointerIfNeeded(first != null ? first.getGnoType(context) : null);
  }

  @Nullable
  public static GnoType unwrapPointerIfNeeded(@Nullable GnoType type) {
    return type instanceof GnoPointerType ? ((GnoPointerType)type).getType() : type;
  }

  @Nullable
  public static GnoType getBuiltinType(@NotNull String name, @NotNull PsiElement context) {
    GnoFile builtin = GnoSdkUtil.findBuiltinFile(context);
    if (builtin != null) {
      GnoTypeSpec spec = ContainerUtil.find(builtin.getTypes(), spec1 -> name.equals(spec1.getName()));
      if (spec != null) {
        return spec.getSpecType().getType(); // todo
      }
    }
    return null;
  }

  @Nullable
  private static GnoType typeFromRefOrType(@Nullable GnoType t) {
    if (t == null) return null;
    GnoTypeReferenceExpression tr = t.getTypeReferenceExpression();
    return tr != null ? tr.resolveType() : t;
  }

  @Nullable
  public static GnoType typeOrParameterType(@NotNull GnoTypeOwner resolve, @Nullable ResolveState context) {
    GnoType type = resolve.getGnoType(context);
    if (resolve instanceof GnoParamDefinition && ((GnoParamDefinition)resolve).isVariadic()) {
      return type == null ? null : new LightArrayType(type);
    }
    if (resolve instanceof GnoSignatureOwner) {
      return new LightFunctionType((GnoSignatureOwner)resolve);
    }
    return type;
  }

  @Nullable
  public static PsiElement resolve(@NotNull GnoReferenceExpression o) { // todo: replace with default method in GnoReferenceExpressionBase
    return o.getReference().resolve();
  }

  @Nullable
  public static PsiElement resolve(@NotNull GnoTypeReferenceExpression o) { // todo: replace with default method in GnoReferenceExpressionBase
    return o.getReference().resolve();
  }

  @Nullable
  public static PsiElement resolve(@NotNull GnoFieldName o) { // todo: replace with default method in GnoReferenceExpressionBase
    return o.getReference().resolve();
  }

  @Nullable
  public static GnoType getLiteralType(@Nullable PsiElement context, boolean considerLiteralValue) {
    GnoCompositeLit lit = PsiTreeUtil.getNonStrictParentOfType(context, GnoCompositeLit.class);
    if (lit == null) {
      return null;
    }
    GnoType type = lit.getType();
    if (type == null) {
      GnoTypeReferenceExpression ref = lit.getTypeReferenceExpression();
      GnoType resolve = ref != null ? ref.resolveType() : null;
      type = resolve != null ? resolve.getUnderlyingType() : null;
    }
    if (!considerLiteralValue) {
      return type;
    }
    GnoValue parentGnoValue = getParentGnoValue(context);
    PsiElement literalValue = PsiTreeUtil.getParentOfType(context, GnoLiteralValue.class);
    while (literalValue != null) {
      if (literalValue == lit) break;
      if (literalValue instanceof GnoLiteralValue) {
        type = calcLiteralType(parentGnoValue, type);
      }
      literalValue = literalValue.getParent();
    }
    return type;
  }

  @Nullable
  public static GnoValue getParentGnoValue(@NotNull PsiElement element) {
    PsiElement place = element;
    while ((place = PsiTreeUtil.getParentOfType(place, GnoLiteralValue.class)) != null) {
      if (place.getParent() instanceof GnoValue) {
        return (GnoValue)place.getParent();
      }
    }
    return null;
  }

  // todo: rethink and unify this algorithm
  @Nullable
  private static GnoType calcLiteralType(@Nullable GnoValue parentGnoValue, @Nullable GnoType type) {
    if (type == null) return null;
    type = findLiteralType(parentGnoValue, type);

    if (type instanceof GnoParType) {
      type = ((GnoParType)type).getActualType();
    }

    if (type != null && type.getTypeReferenceExpression() != null) {
      type = type.getUnderlyingType();
    }

    if (type instanceof GnoPointerType) {
      GnoType inner = ((GnoPointerType)type).getType();
      if (inner != null && inner.getTypeReferenceExpression() != null) {
        type = inner.getUnderlyingType();
      }
    }

    return type instanceof GnoSpecType ? ((GnoSpecType)type).getType() : type;
  }

  private static GnoType findLiteralType(@Nullable GnoValue parentGnoValue, @Nullable GnoType type) {
    boolean inValue = parentGnoValue != null;
    if (inValue && type instanceof GnoArrayOrSliceType) {
      type = ((GnoArrayOrSliceType)type).getType();
    }
    else if (type instanceof GnoMapType) {
      type = inValue ? ((GnoMapType)type).getValueType() : ((GnoMapType)type).getKeyType();
    }
    else if (inValue && type instanceof GnoSpecType) {
      GnoType inner = ((GnoSpecType)type).getType();
      if (inner instanceof GnoArrayOrSliceType) {
        type = ((GnoArrayOrSliceType)inner).getType();
      }
      else if (inner instanceof GnoStructType) {
        GnoKey key = PsiTreeUtil.getPrevSiblingOfType(parentGnoValue, GnoKey.class);
        GnoFieldName field = key != null ? key.getFieldName() : null;
        PsiElement resolve = field != null ? field.resolve() : null;
        if (resolve instanceof GnoFieldDefinition) {
          type = PsiTreeUtil.getNextSiblingOfType(resolve, GnoType.class);
        }
      }
    }
    return type;
  }

  @Nullable
  public static GnoType resolveType(@NotNull GnoTypeReferenceExpression expression) {
    PsiElement resolve = expression.resolve();
    if (resolve instanceof GnoTypeSpec) return ((GnoTypeSpec)resolve).getSpecType();
    // hacky C resolve
    return resolve == expression ? new GnoCType(expression) : null;
  }

  public static boolean isVariadic(@NotNull GnoParamDefinition o) {
    PsiElement parent = o.getParent();
    return parent instanceof GnoParameterDeclaration && ((GnoParameterDeclaration)parent).isVariadic();
  }

  public static boolean isVariadic(@NotNull GnoParameterDeclaration o) {
    GnoParameterDeclarationStub stub = o.getStub();
    return stub != null ? stub.isVariadic() : o.getTripleDot() != null;
  }

  public static boolean hasVariadic(@NotNull GnoArgumentList argumentList) {
    return argumentList.getTripleDot() != null;
  }

  @Nullable
  public static GnoType getGnoTypeInner(@NotNull GnoTypeSpec o, @SuppressWarnings("UnusedParameters") @Nullable ResolveState context) {
    return o.getSpecType();
  }

  @Nullable
  public static GnoType getGnoTypeInner(@NotNull GnoVarDefinition o, @Nullable ResolveState context) {
    // see http://golang.org/ref/spec#RangeClause
    PsiElement parent = PsiTreeUtil.getStubOrPsiParent(o);
    if (parent instanceof GnoRangeClause) {
      return processRangeClause(o, (GnoRangeClause)parent, context);
    }
    if (parent instanceof GnoVarSpec) {
      return findTypeInVarSpec(o, context);
    }
    GnoCompositeLit literal = PsiTreeUtil.getNextSiblingOfType(o, GnoCompositeLit.class);
    if (literal != null) {
      return literal.getType();
    }
    GnoType siblingType = o.findSiblingType();
    if (siblingType != null) return siblingType;

    if (parent instanceof GnoTypeSwitchGuard) {
      GnoTypeSwitchStatement switchStatement = ObjectUtils.tryCast(parent.getParent(), GnoTypeSwitchStatement.class);
      if (switchStatement != null) {
        GnoTypeCaseClause typeCase = getTypeCaseClause(getContextElement(context), switchStatement);
        if (typeCase != null) {
          return typeCase.getDefault() != null ? ((GnoTypeSwitchGuard)parent).getExpression().getGnoType(context) : typeCase.getType();
        }
        return ((GnoTypeSwitchGuard)parent).getExpression().getGnoType(null);
      }
    }
    return null;
  }

  public static boolean isVoid(@NotNull GnoResult result) {
    GnoType type = result.getType();
    if (type != null) return false;
    GnoParameters parameters = result.getParameters();
    return parameters == null || parameters.getParameterDeclarationList().isEmpty();
  }

  @Nullable
  private static GnoTypeCaseClause getTypeCaseClause(@Nullable PsiElement context, @NotNull GnoTypeSwitchStatement switchStatement) {
    return SyntaxTraverser.psiApi().parents(context).takeWhile(Conditions.notEqualTo(switchStatement))
      .filter(GnoTypeCaseClause.class).last();
  }

  @Nullable
  private static GnoType findTypeInVarSpec(@NotNull GnoVarDefinition o, @Nullable ResolveState context) {
    GnoVarSpec parent = (GnoVarSpec)PsiTreeUtil.getStubOrPsiParent(o);
    if (parent == null) return null;
    GnoType commonType = parent.getType();
    if (commonType != null) return commonType;
    List<GnoVarDefinition> varList = parent.getVarDefinitionList();
    int i = varList.indexOf(o);
    i = i == -1 ? 0 : i;
    List<GnoExpression> exprs = parent.getRightExpressionsList();
    if (exprs.size() == 1 && exprs.get(0) instanceof GnoCallExpr) {
      GnoExpression call = exprs.get(0);
      GnoType fromCall = call.getGnoType(context);
      boolean canDecouple = varList.size() > 1;
      GnoType underlyingType = canDecouple && fromCall instanceof GnoSpecType ? ((GnoSpecType)fromCall).getType() : fromCall;
      GnoType byRef = typeFromRefOrType(underlyingType);
      GnoType type = funcType(canDecouple && byRef instanceof GnoSpecType ? ((GnoSpecType)byRef).getType() : byRef);
      if (type == null) return fromCall;
      if (type instanceof GnoTypeList) {
        if (((GnoTypeList)type).getTypeList().size() > i) {
          return ((GnoTypeList)type).getTypeList().get(i);
        }
      }
      return type;
    }
    if (exprs.size() <= i) return null;
    return exprs.get(i).getGnoType(context);
  }

  @Nullable
  private static GnoType funcType(@Nullable GnoType type) {
    if (type instanceof GnoFunctionType) {
      GnoSignature signature = ((GnoFunctionType)type).getSignature();
      GnoResult result = signature != null ? signature.getResult() : null;
      if (result != null) {
        GnoType rt = result.getType();
        if (rt != null) return rt;
        GnoParameters parameters = result.getParameters();
        if (parameters != null) {
          List<GnoParameterDeclaration> list = parameters.getParameterDeclarationList();
          List<GnoType> types = ContainerUtil.newArrayListWithCapacity(list.size());
          for (GnoParameterDeclaration declaration : list) {
            GnoType declarationType = declaration.getType();
            for (GnoParamDefinition ignored : declaration.getParamDefinitionList()) {
              types.add(declarationType);
            }
          }
          if (!types.isEmpty()) {
            return types.size() == 1 ? types.get(0) : new LightTypeList(parameters, types);
          }
        }
      }
      return null;
    }
    return type;
  }

  /**
   * https://golang.org/ref/spec#RangeClause
   */
  @Nullable
  private static GnoType processRangeClause(@NotNull GnoVarDefinition o, @NotNull GnoRangeClause parent, @Nullable ResolveState context) {
    GnoExpression rangeExpression = parent.getRangeExpression();
    if (rangeExpression != null) {
      List<GnoVarDefinition> varList = parent.getVarDefinitionList();
      GnoType type = unwrapIfNeeded(rangeExpression.getGnoType(context));
      if (type instanceof GnoChannelType) return ((GnoChannelType)type).getType();
      int i = varList.indexOf(o);
      i = i == -1 ? 0 : i;
      if (type instanceof GnoArrayOrSliceType && i == 1) return ((GnoArrayOrSliceType)type).getType();
      if (type instanceof GnoMapType) {
        List<GnoType> list = ((GnoMapType)type).getTypeList();
        if (i == 0) return ContainerUtil.getFirstItem(list);
        if (i == 1) return ContainerUtil.getLastItem(list);
      }
      if (GnoTypeUtil.isString(type)) {
        return getBuiltinType("int32", o);
      }
    }
    return null;
  }

  @Nullable
  private static GnoType unwrapIfNeeded(@Nullable GnoType type) {
    type = unwrapPointerIfNeeded(type);
    return type != null ? type.getUnderlyingType() : null;
  }

  @NotNull
  public static GnoType getActualType(@NotNull GnoParType o) {
    return ObjectUtils.notNull(SyntaxTraverser.psiTraverser(o).filter(Conditions.notInstanceOf(GnoParType.class))
                                 .filter(GnoType.class).first(), o.getType());
  }

  @NotNull
  public static String getText(@Nullable GnoType o) {
    if (o == null) return "";
    if (o instanceof GnoPointerType && ((GnoPointerType)o).getType() instanceof GnoSpecType) {
      return "*" + getText(((GnoPointerType)o).getType());
    }
    if (o instanceof GnoSpecType) {
      String fqn = getFqn(getTypeSpecSafe(o));
      if (fqn != null) {
        return fqn;
      }
    }
    else if (o instanceof GnoStructType) {
      return ((GnoStructType)o).getFieldDeclarationList().isEmpty() ? "struct{}" : "struct {...}";
    }
    else if (o instanceof GnoInterfaceType) {
      return ((GnoInterfaceType)o).getMethodSpecList().isEmpty() ? "interface{}" : "interface {...}";
    }
    String text = o.getText();
    if (text == null) return "";
    return text.replaceAll("\\s+", " ");
  }

  @Nullable
  private static String getFqn(@Nullable GnoTypeSpec typeSpec) {
    if (typeSpec == null) return null;
    String name = typeSpec.getName();
    GnoFile file = typeSpec.getContainingFile();
    String packageName = file.getPackageName();
    if (name != null) {
      return !isBuiltinFile(file) ? getFqn(packageName, name) : name;
    }
    return null;
  }

  public static String getFqn(@Nullable String packageName, @NotNull String elementName) {
    return StringUtil.isNotEmpty(packageName) ? packageName + "." + elementName : elementName;
  }

  @NotNull
  public static List<GnoMethodSpec> getMethods(@NotNull GnoInterfaceType o) {
    return ContainerUtil.filter(o.getMethodSpecList(), spec -> spec.getIdentifier() != null);
  }

  @NotNull
  public static List<GnoTypeReferenceExpression> getBaseTypesReferences(@NotNull GnoInterfaceType o) {
    List<GnoTypeReferenceExpression> refs = ContainerUtil.newArrayList();
    o.accept(new GnoRecursiveVisitor() {
      @Override
      public void visitMethodSpec(@NotNull GnoMethodSpec o) {
        ContainerUtil.addIfNotNull(refs, o.getTypeReferenceExpression());
      }
    });
    return refs;
  }

  @NotNull
  public static List<GnoMethodDeclaration> getMethods(@NotNull GnoTypeSpec o) {
    return CachedValuesManager.getCachedValue(o, () -> {
      // todo[zolotov]: implement package modification tracker
      return CachedValueProvider.Result.create(calcMethods(o), PsiModificationTracker.MODIFICATION_COUNT);
    });
  }

  public static boolean allowed(@NotNull PsiFile declarationFile, @Nullable PsiFile referenceFile, @Nullable Module contextModule) {
    if (!(declarationFile instanceof GnoFile)) {
      return false;
    }
    VirtualFile referenceVirtualFile = referenceFile != null ? referenceFile.getOriginalFile().getVirtualFile() : null;
    if (!allowed(declarationFile.getVirtualFile(), referenceVirtualFile)) {
      return false;
    }
    if (GnoConstants.DOCUMENTATION.equals(((GnoFile)declarationFile).getPackageName())) {
      return false;
    }
    return GnoUtil.matchedForModuleBuildTarget(declarationFile, contextModule);
  }

  public static boolean allowed(@Nullable VirtualFile declarationFile, @Nullable VirtualFile referenceFile) {
    if (declarationFile == null) {
      return true;
    }
    if (GnoUtil.fileToIgnore(declarationFile.getName())) {
      return false;
    }
    // it's not a test or context file is also test from the same package
    return referenceFile == null
           || !GnoTestFinder.isTestFile(declarationFile)
           || GnoTestFinder.isTestFile(referenceFile) && Comparing.equal(referenceFile.getParent(), declarationFile.getParent());
  }

  static boolean processNamedElements(@NotNull PsiScopeProcessor processor,
                                      @NotNull ResolveState state,
                                      @NotNull Collection<? extends GnoNamedElement> elements,
                                      boolean localResolve) {
    //noinspection unchecked
    return processNamedElements(processor, state, elements, localResolve, false);
  }

  static boolean processNamedElements(@NotNull PsiScopeProcessor processor,
                                      @NotNull ResolveState state,
                                      @NotNull Collection<? extends GnoNamedElement> elements,
                                      boolean localResolve,
                                      boolean checkContainingFile) {
    return processNamedElements(processor, state, elements, x -> TRUE, localResolve, checkContainingFile);
  }

  static boolean processNamedElements(@NotNull PsiScopeProcessor processor,
                                      @NotNull ResolveState state,
                                      @NotNull Collection<? extends GnoNamedElement> elements,
                                      @NotNull Condition<GnoNamedElement> condition,
                                      boolean localResolve,
                                      boolean checkContainingFile) {
    PsiFile contextFile = checkContainingFile ? GnoReference.getContextFile(state) : null;
    Module module = contextFile != null ? ModuleUtilCore.findModuleForPsiElement(contextFile) : null;
    for (GnoNamedElement definition : elements) {
      if (!condition.value(definition)) continue;
      if (!definition.isValid() || checkContainingFile && !allowed(definition.getContainingFile(), contextFile, module)) continue;
      if ((localResolve || definition.isPublic()) && !processor.execute(definition, state)) return false;
    }
    return true;
  }

  public static boolean processSignatureOwner(@NotNull GnoSignatureOwner o, @NotNull GnoScopeProcessorBase processor) {
    GnoSignature signature = o.getSignature();
    if (signature == null) return true;
    if (!processParameters(processor, signature.getParameters())) return false;
    GnoResult result = signature.getResult();
    GnoParameters resultParameters = result != null ? result.getParameters() : null;
    return resultParameters == null || processParameters(processor, resultParameters);
  }

  private static boolean processParameters(@NotNull GnoScopeProcessorBase processor, @NotNull GnoParameters parameters) {
    for (GnoParameterDeclaration declaration : parameters.getParameterDeclarationList()) {
      if (!processNamedElements(processor, ResolveState.initial(), declaration.getParamDefinitionList(), true)) return false;
    }
    return true;
  }

  @NotNull
  public static String joinPsiElementText(List<? extends PsiElement> items) {
    return StringUtil.join(items, PsiElement::getText, ", ");
  }

  @Nullable
  public static PsiElement getBreakStatementOwner(@NotNull PsiElement breakStatement) {
    GnoCompositeElement owner = PsiTreeUtil.getParentOfType(breakStatement, GnoSwitchStatement.class, GnoForStatement.class,
                                                           GnoSelectStatement.class, GnoFunctionLit.class);
    return owner instanceof GnoFunctionLit ? null : owner;
  }

  @NotNull
  private static List<GnoMethodDeclaration> calcMethods(@NotNull GnoTypeSpec o) {
    PsiFile file = o.getContainingFile().getOriginalFile();
    if (file instanceof GnoFile) {
      String packageName = ((GnoFile)file).getPackageName();
      String typeName = o.getName();
      if (StringUtil.isEmpty(packageName) || StringUtil.isEmpty(typeName)) return Collections.emptyList();
      String key = packageName + "." + typeName;
      Project project = ((GnoFile)file).getProject();
      GlobalSearchScope scope = GnoPackageUtil.packageScope((GnoFile)file);
      Collection<GnoMethodDeclaration> declarations = GnoMethodIndex.find(key, project, scope, GnoIdFilter.getFilesFilter(scope));
      return ContainerUtil.newArrayList(declarations);
    }
    return Collections.emptyList();
  }

  @NotNull
  public static GnoType getUnderlyingType(@NotNull GnoType o) {
    GnoType type = RecursionManager.doPreventingRecursion(o, true, () -> getTypeInner(o));
    return ObjectUtils.notNull(type, o);
  }

  @NotNull
  private static GnoType getTypeInner(@NotNull GnoType o) {
    if (o instanceof GnoArrayOrSliceType
        | o instanceof GnoStructType
        | o instanceof GnoPointerType
        | o instanceof GnoFunctionType
        | o instanceof GnoInterfaceType
        | o instanceof GnoMapType
        | o instanceof GnoChannelType) {
      return o;
    }

    if (o instanceof GnoParType) return ((GnoParType)o).getActualType();

    if (o instanceof GnoSpecType) {
      GnoType type = ((GnoSpecType)o).getType();
      return type != null ? type.getUnderlyingType() : o;
    }

    if (builtin(o)) return o;

    GnoTypeReferenceExpression e = o.getTypeReferenceExpression();
    GnoType byRef = e == null ? null : e.resolveType();
    if (byRef != null) {
      return byRef.getUnderlyingType();
    }

    return o;
  }

  @Nullable
  public static GnoType getGnoTypeInner(@NotNull GnoSignatureOwner o, @SuppressWarnings("UnusedParameters") @Nullable ResolveState context) {
    GnoSignature signature = o.getSignature();
    GnoResult result = signature != null ? signature.getResult() : null;
    if (result != null) {
      GnoType type = result.getType();
      if (type instanceof GnoTypeList && ((GnoTypeList)type).getTypeList().size() == 1) {
        return ((GnoTypeList)type).getTypeList().get(0);
      }
      if (type != null) return type;
      GnoParameters parameters = result.getParameters();
      if (parameters != null) {
        GnoType parametersType = parameters.getType();
        if (parametersType != null) return parametersType;
        List<GnoType> composite = ContainerUtil.newArrayList();
        for (GnoParameterDeclaration p : parameters.getParameterDeclarationList()) {
          for (GnoParamDefinition definition : p.getParamDefinitionList()) {
            composite.add(definition.getGnoType(context));
          }
        }
        if (composite.size() == 1) return composite.get(0);
        return new LightTypeList(parameters, composite);
      }
    }
    return null;
  }

  @NotNull
  public static GnoImportSpec addImport(@NotNull GnoImportList importList, @NotNull String packagePath, @Nullable String alias) {
    Project project = importList.getProject();
    GnoImportDeclaration newDeclaration = GnoElementFactory.createImportDeclaration(project, packagePath, alias, false);
    List<GnoImportDeclaration> existingImports = importList.getImportDeclarationList();
    for (int i = existingImports.size() - 1; i >= 0; i--) {
      GnoImportDeclaration existingImport = existingImports.get(i);
      List<GnoImportSpec> importSpecList = existingImport.getImportSpecList();
      if (importSpecList.isEmpty()) {
        continue;
      }
      if (existingImport.getRparen() == null && importSpecList.get(0).isCImport()) {
        continue;
      }
      return existingImport.addImportSpec(packagePath, alias);
    }
    return addImportDeclaration(importList, newDeclaration);
  }

  @NotNull
  private static GnoImportSpec addImportDeclaration(@NotNull GnoImportList importList, @NotNull GnoImportDeclaration newImportDeclaration) {
    GnoImportDeclaration lastImport = ContainerUtil.getLastItem(importList.getImportDeclarationList());
    GnoImportDeclaration importDeclaration = (GnoImportDeclaration)importList.addAfter(newImportDeclaration, lastImport);
    PsiElement importListNextSibling = importList.getNextSibling();
    if (!(importListNextSibling instanceof PsiWhiteSpace)) {
      importList.addAfter(GnoElementFactory.createNewLine(importList.getProject()), importDeclaration);
      if (importListNextSibling != null) {
        // double new line if there is something valuable after import list
        importList.addAfter(GnoElementFactory.createNewLine(importList.getProject()), importDeclaration);
      }
    }
    importList.addBefore(GnoElementFactory.createNewLine(importList.getProject()), importDeclaration);
    GnoImportSpec result = ContainerUtil.getFirstItem(importDeclaration.getImportSpecList());
    assert result != null;
    return result;
  }

  @NotNull
  public static GnoImportSpec addImportSpec(@NotNull GnoImportDeclaration declaration, @NotNull String packagePath, @Nullable String alias) {
    PsiElement rParen = declaration.getRparen();
    if (rParen == null) {
      GnoImportDeclaration newDeclaration = GnoElementFactory.createEmptyImportDeclaration(declaration.getProject());
      for (GnoImportSpec spec : declaration.getImportSpecList()) {
        newDeclaration.addImportSpec(spec.getPath(), spec.getAlias());
      }
      declaration = (GnoImportDeclaration)declaration.replace(newDeclaration);
      LOG.assertTrue(declaration.getRparen() != null);
      return declaration.addImportSpec(packagePath, alias);
    }
    declaration.addBefore(GnoElementFactory.createNewLine(declaration.getProject()), rParen);
    GnoImportSpec newImportSpace = GnoElementFactory.createImportSpec(declaration.getProject(), packagePath, alias);
    GnoImportSpec spec = (GnoImportSpec)declaration.addBefore(newImportSpace, rParen);
    declaration.addBefore(GnoElementFactory.createNewLine(declaration.getProject()), rParen);
    return spec;
  }

  public static String getLocalPackageName(@NotNull String importPath) {
    String fileName = !StringUtil.endsWithChar(importPath, '/') && !StringUtil.endsWithChar(importPath, '\\')
                      ? PathUtil.getFileName(importPath)
                      : "";
    StringBuilder name = null;
    for (int i = 0; i < fileName.length(); i++) {
      char c = fileName.charAt(i);
      if (!(Character.isLetter(c) || c == '_' || i != 0 && Character.isDigit(c))) {
        if (name == null) {
          name = new StringBuilder(fileName.length());
          name.append(fileName, 0, i);
        }
        name.append('_');
      }
      else if (name != null) {
        name.append(c);
      }
    }
    return name == null ? fileName : name.toString();
  }

  public static String getLocalPackageName(@NotNull GnoImportSpec importSpec) {
    return getLocalPackageName(importSpec.getPath());
  }

  public static boolean isCImport(@NotNull GnoImportSpec importSpec) {
    return GnoConstants.C_PATH.equals(importSpec.getPath());
  }

  public static boolean isDot(@NotNull GnoImportSpec importSpec) {
    GnoImportSpecStub stub = importSpec.getStub();
    return stub != null ? stub.isDot() : importSpec.getDot() != null;
  }

  @NotNull
  public static String getPath(@NotNull GnoImportSpec importSpec) {
    GnoImportSpecStub stub = importSpec.getStub();
    return stub != null ? stub.getPath() : importSpec.getImportString().getPath();
  }

  public static String getName(@NotNull GnoImportSpec importSpec) {
    return getAlias(importSpec);
  }

  public static String getAlias(@NotNull GnoImportSpec importSpec) {
    GnoImportSpecStub stub = importSpec.getStub();
    if (stub != null) {
      return stub.getAlias();
    }

    PsiElement identifier = importSpec.getIdentifier();
    if (identifier != null) {
      return identifier.getText();
    }
    return importSpec.isDot() ? "." : null;
  }

  @Nullable
  public static String getImportQualifierToUseInFile(@Nullable GnoImportSpec importSpec, @Nullable String defaultValue) {
    if (importSpec == null || importSpec.isForSideEffects()) {
      return null;
    }
    if (importSpec.isDot()) {
      return "";
    }
    String alias = importSpec.getAlias();
    if (alias != null) {
      return alias;
    }
    return defaultValue != null ? defaultValue : importSpec.getLocalPackageName();
  }

  public static boolean shouldGnoDeeper(@SuppressWarnings("UnusedParameters") GnoImportSpec o) {
    return false;
  }

  public static boolean shouldGnoDeeper(@SuppressWarnings("UnusedParameters") GnoTypeSpec o) {
    return false;
  }

  public static boolean shouldGnoDeeper(@NotNull GnoType o) {
    return o instanceof GnoInterfaceType || o instanceof GnoStructType;
  }

  public static boolean isForSideEffects(@NotNull GnoImportSpec o) {
    return "_".equals(o.getAlias());
  }

  @NotNull
  public static String getPath(@NotNull GnoImportString o) {
    return o.getStringLiteral().getDecodedText();
  }

  @NotNull
  public static String unquote(@Nullable String s) {
    if (StringUtil.isEmpty(s)) return "";
    char quote = s.charAt(0);
    int startOffset = isQuote(quote) ? 1 : 0;
    int endOffset = s.length();
    if (s.length() > 1) {
      char lastChar = s.charAt(s.length() - 1);
      if (isQuote(quote) && lastChar == quote) {
        endOffset = s.length() - 1;
      }
      if (!isQuote(quote) && isQuote(lastChar)) {
        endOffset = s.length() - 1;
      }
    }
    return s.substring(startOffset, endOffset);
  }

  @NotNull
  public static TextRange getPathTextRange(@NotNull GnoImportString importString) {
    String text = importString.getText();
    return !text.isEmpty() && isQuote(text.charAt(0)) ? TextRange.create(1, text.length() - 1) : TextRange.EMPTY_RANGE;
  }

  public static boolean isQuotedImportString(@NotNull String s) {
    return s.length() > 1 && isQuote(s.charAt(0)) && s.charAt(0) == s.charAt(s.length() - 1);
  }

  private static boolean isQuote(char ch) {
    return ch == '"' || ch == '\'' || ch == '`';
  }

  public static boolean isValidHost(@NotNull GnoStringLiteral o) {
    return PsiTreeUtil.getParentOfType(o, GnoImportString.class) == null;
  }

  @NotNull
  public static GnoStringLiteralImpl updateText(@NotNull GnoStringLiteral o, @NotNull String text) {
    if (text.length() > 2) {
      if (o.getString() != null) {
        StringBuilder outChars = new StringBuilder();
        GnoStringLiteralEscaper.escapeString(text.substring(1, text.length() - 1), outChars);
        outChars.insert(0, '"');
        outChars.append('"');
        text = outChars.toString();
      }
    }

    ASTNode valueNode = o.getNode().getFirstChildNode();
    assert valueNode instanceof LeafElement;

    ((LeafElement)valueNode).replaceWithText(text);
    return (GnoStringLiteralImpl)o;
  }

  @NotNull
  public static GnoStringLiteralEscaper createLiteralTextEscaper(@NotNull GnoStringLiteral o) {
    return new GnoStringLiteralEscaper(o);
  }

  public static boolean prevDot(@Nullable PsiElement e) {
    PsiElement prev = e == null ? null : PsiTreeUtil.prevVisibleLeaf(e);
    return prev instanceof LeafElement && ((LeafElement)prev).getElementType() == GnoTypes.DOT;
  }

  @Nullable
  public static GnoSignatureOwner resolveCall(@Nullable GnoExpression call) {
    return ObjectUtils.tryCast(resolveCallRaw(call), GnoSignatureOwner.class);
  }

  public static PsiElement resolveCallRaw(@Nullable GnoExpression call) {
    if (!(call instanceof GnoCallExpr)) return null;
    GnoExpression e = ((GnoCallExpr)call).getExpression();
    if (e instanceof GnoSelectorExpr) {
      GnoExpression right = ((GnoSelectorExpr)e).getRight();
      PsiReference reference = right instanceof GnoReferenceExpression ? right.getReference() : null;
      return reference != null ? reference.resolve() : null;
    }
    if (e instanceof GnoCallExpr) {
      GnoSignatureOwner resolve = resolveCall(e);
      if (resolve != null) {
        GnoSignature signature = resolve.getSignature();
        GnoResult result = signature != null ? signature.getResult() : null;
        return result != null ? result.getType() : null;
      }
      return null;
    }
    if (e instanceof GnoFunctionLit) {
      return e;
    }
    GnoReferenceExpression r = e instanceof GnoReferenceExpression
                              ? (GnoReferenceExpression)e
                              : PsiTreeUtil.getChildOfType(e, GnoReferenceExpression.class);
    PsiReference reference = (r != null ? r : e).getReference();
    return reference != null ? reference.resolve() : null;
  }

  public static boolean isUnaryBitAndExpression(@Nullable PsiElement parent) {
    PsiElement grandParent = parent != null ? parent.getParent() : null;
    return grandParent instanceof GnoUnaryExpr && ((GnoUnaryExpr)grandParent).getBitAnd() != null;
  }

  @NotNull
  public static GnoVarSpec addSpec(@NotNull GnoVarDeclaration declaration,
                                  @NotNull String name,
                                  @Nullable String type,
                                  @Nullable String value,
                                  @Nullable GnoVarSpec specAnchor) {
    Project project = declaration.getProject();
    GnoVarSpec newSpec = GnoElementFactory.createVarSpec(project, name, type, value);
    PsiElement rParen = declaration.getRparen();
    if (rParen == null) {
      GnoVarSpec item = ContainerUtil.getFirstItem(declaration.getVarSpecList());
      assert item != null;
      boolean updateAnchor = specAnchor == item;
      declaration = (GnoVarDeclaration)declaration.replace(GnoElementFactory.createVarDeclaration(project, "(" + item.getText() + ")"));
      rParen = declaration.getRparen();
      if (updateAnchor) {
        specAnchor = ContainerUtil.getFirstItem(declaration.getVarSpecList());
      }
    }

    assert rParen != null;
    PsiElement anchor = ObjectUtils.notNull(specAnchor, rParen);
    if (!hasNewLineBefore(anchor)) {
      declaration.addBefore(GnoElementFactory.createNewLine(declaration.getProject()), anchor);
    }
    GnoVarSpec spec = (GnoVarSpec)declaration.addBefore(newSpec, anchor);
    declaration.addBefore(GnoElementFactory.createNewLine(declaration.getProject()), anchor);
    return spec;
  }

  @NotNull
  public static GnoConstSpec addSpec(@NotNull GnoConstDeclaration declaration,
                                    @NotNull String name,
                                    @Nullable String type,
                                    @Nullable String value,
                                    @Nullable GnoConstSpec specAnchor) {
    Project project = declaration.getProject();
    GnoConstSpec newSpec = GnoElementFactory.createConstSpec(project, name, type, value);
    PsiElement rParen = declaration.getRparen();
    if (rParen == null) {
      GnoConstSpec item = ContainerUtil.getFirstItem(declaration.getConstSpecList());
      assert item != null;
      boolean updateAnchor = specAnchor == item;
      declaration = (GnoConstDeclaration)declaration.replace(GnoElementFactory.createConstDeclaration(project, "(" + item.getText() + ")"));
      rParen = declaration.getRparen();
      if (updateAnchor) {
        specAnchor = ContainerUtil.getFirstItem(declaration.getConstSpecList());
      }
    }

    assert rParen != null;
    PsiElement anchor = ObjectUtils.notNull(specAnchor, rParen);
    if (!hasNewLineBefore(anchor)) {
      declaration.addBefore(GnoElementFactory.createNewLine(declaration.getProject()), anchor);
    }
    GnoConstSpec spec = (GnoConstSpec)declaration.addBefore(newSpec, anchor);
    declaration.addBefore(GnoElementFactory.createNewLine(declaration.getProject()), anchor);
    return spec;
  }

  public static void deleteSpec(@NotNull GnoVarDeclaration declaration, @NotNull GnoVarSpec specToDelete) {
    List<GnoVarSpec> specList = declaration.getVarSpecList();
    int index = specList.indexOf(specToDelete);
    assert index >= 0;
    if (specList.size() == 1) {
      declaration.delete();
      return;
    }
    specToDelete.delete();
  }

  public static void deleteSpec(@NotNull GnoConstDeclaration declaration, @NotNull GnoConstSpec specToDelete) {
    List<GnoConstSpec> specList = declaration.getConstSpecList();
    int index = specList.indexOf(specToDelete);
    assert index >= 0;
    if (specList.size() == 1) {
      declaration.delete();
      return;
    }
    specToDelete.delete();
  }

  public static void deleteExpressionFromAssignment(@NotNull GnoAssignmentStatement assignment,
                                                    @NotNull GnoExpression expressionToDelete) {
    GnoExpression expressionValue = getRightExpression(assignment, expressionToDelete);
    if (expressionValue != null) {
      if (assignment.getExpressionList().size() == 1) {
        assignment.delete();
      }
      else {
        deleteElementFromCommaSeparatedList(expressionToDelete);
        deleteElementFromCommaSeparatedList(expressionValue);
      }
    }
  }

  public static void deleteDefinition(@NotNull GnoVarSpec spec, @NotNull GnoVarDefinition definitionToDelete) {
    List<GnoVarDefinition> definitionList = spec.getVarDefinitionList();
    int index = definitionList.indexOf(definitionToDelete);
    assert index >= 0;
    if (definitionList.size() == 1) {
      PsiElement parent = spec.getParent();
      if (parent instanceof GnoVarDeclaration) {
        ((GnoVarDeclaration)parent).deleteSpec(spec);
      }
      else {
        spec.delete();
      }
      return;
    }

    GnoExpression value = definitionToDelete.getValue();
    if (value != null && spec.getRightExpressionsList().size() <= 1) {
      PsiElement assign = spec.getAssign();
      if (assign != null) {
        assign.delete();
      }
    }
    deleteElementFromCommaSeparatedList(value);
    deleteElementFromCommaSeparatedList(definitionToDelete);
  }

  public static void deleteDefinition(@NotNull GnoConstSpec spec, @NotNull GnoConstDefinition definitionToDelete) {
    List<GnoConstDefinition> definitionList = spec.getConstDefinitionList();
    int index = definitionList.indexOf(definitionToDelete);
    assert index >= 0;
    if (definitionList.size() == 1) {
      PsiElement parent = spec.getParent();
      if (parent instanceof GnoConstDeclaration) {
        ((GnoConstDeclaration)parent).deleteSpec(spec);
      }
      else {
        spec.delete();
      }
      return;
    }
    GnoExpression value = definitionToDelete.getValue();
    if (value != null && spec.getExpressionList().size() <= 1) {
      PsiElement assign = spec.getAssign();
      if (assign != null) {
        assign.delete();
      }
    }
    deleteElementFromCommaSeparatedList(value);
    deleteElementFromCommaSeparatedList(definitionToDelete);
  }

  private static void deleteElementFromCommaSeparatedList(@Nullable PsiElement element) {
    if (element == null) {
      return;
    }
    PsiElement prevVisibleLeaf = PsiTreeUtil.prevVisibleLeaf(element);
    PsiElement nextVisibleLeaf = PsiTreeUtil.nextVisibleLeaf(element);
    if (prevVisibleLeaf != null && prevVisibleLeaf.textMatches(",")) {
      prevVisibleLeaf.delete();
    }
    else if (nextVisibleLeaf != null && nextVisibleLeaf.textMatches(",")) {
      nextVisibleLeaf.delete();
    }
    element.delete();
  }

  private static boolean hasNewLineBefore(@NotNull PsiElement anchor) {
    PsiElement prevSibling = anchor.getPrevSibling();
    while (prevSibling instanceof PsiWhiteSpace) {
      if (prevSibling.textContains('\n')) {
        return true;
      }
      prevSibling = prevSibling.getPrevSibling();
    }
    return false;
  }

  @Nullable
  public static GnoExpression getValue(@NotNull GnoVarDefinition definition) {
    PsiElement parent = definition.getParent();
    if (parent instanceof GnoVarSpec) {
      int index = ((GnoVarSpec)parent).getVarDefinitionList().indexOf(definition);
      return getByIndex(((GnoVarSpec)parent).getRightExpressionsList(), index);
    }
    if (parent instanceof GnoTypeSwitchGuard) {
      return ((GnoTypeSwitchGuard)parent).getExpression();
    }
    LOG.error("Cannot find value for variable definition: " + definition.getText(),
              AttachmentFactory.createAttachment(definition.getContainingFile().getVirtualFile()));
    return null;
  }

  @Nullable
  public static GnoExpression getValue(@NotNull GnoConstDefinition definition) {
    PsiElement parent = definition.getParent();
    assert parent instanceof GnoConstSpec;
    int index = ((GnoConstSpec)parent).getConstDefinitionList().indexOf(definition);
    return getByIndex(((GnoConstSpec)parent).getExpressionList(), index);
  }

  private static <T> T getByIndex(@NotNull List<T> list, int index) {
    return 0 <= index && index < list.size() ? list.get(index) : null;
  }

  @Nullable
  public static GnoTypeSpec getTypeSpecSafe(@NotNull GnoType type) {
    GnoTypeStub stub = type.getStub();
    PsiElement parent = stub == null ? type.getParent() : stub.getParentStub().getPsi();
    return ObjectUtils.tryCast(parent, GnoTypeSpec.class);
  }

  public static boolean canBeAutoImported(@NotNull GnoFile file, boolean allowMain, @Nullable Module module) {
    if (isBuiltinFile(file) || !allowMain && StringUtil.equals(file.getPackageName(), GnoConstants.MAIN)) {
      return false;
    }
    return allowed(file, null, module) && !GnoUtil.isExcludedFile(file);
  }

  @Nullable
  @Contract("null, _ -> null")
  public static <T extends PsiElement> T getNonStrictTopmostParentOfType(@Nullable PsiElement element, @NotNull Class<T> aClass) {
    T first = PsiTreeUtil.getNonStrictParentOfType(element, aClass);
    T topMost = PsiTreeUtil.getTopmostParentOfType(first, aClass);
    return ObjectUtils.chooseNotNull(topMost, first);
  }

  @Nullable
  public static GnoExpression getExpression(@NotNull GnoIndexOrSliceExpr slice) {
    return ContainerUtil.getFirstItem(getExpressionsBefore(slice.getExpressionList(), slice.getLbrack()));
  }

  @NotNull
  public static List<GnoExpression> getLeftExpressionsList(@NotNull GnoRangeClause rangeClause) {
    return getExpressionsBefore(rangeClause.getExpressionList(), rangeClause.getRange());
  }

  @NotNull
  public static List<GnoExpression> getLeftExpressionsList(@NotNull GnoRecvStatement recvStatement) {
    return getExpressionsBefore(recvStatement.getExpressionList(),
                                ObjectUtils.chooseNotNull(recvStatement.getAssign(), recvStatement.getVarAssign()));
  }

  @NotNull
  public static Trinity<GnoExpression, GnoExpression, GnoExpression> getIndices(@NotNull GnoIndexOrSliceExpr slice) {
    GnoExpression start;
    GnoExpression end = null;
    GnoExpression max = null;
    ASTNode[] colons = slice.getNode().getChildren(TokenSet.create(GnoTypes.COLON));
    List<GnoExpression> exprList = slice.getExpressionList();

    start = ContainerUtil.getFirstItem(getExpressionsInRange(exprList, slice.getLbrack(), colons.length > 0 ? colons[0].getPsi() : null));
    if (colons.length == 1) {
      end = ContainerUtil.getFirstItem(getExpressionsInRange(exprList, colons[0].getPsi(), slice.getRbrack()));
    }
    if (colons.length == 2) {
      end = ContainerUtil.getFirstItem(getExpressionsInRange(exprList, colons[0].getPsi(), colons[1].getPsi()));
      max = ContainerUtil.getFirstItem(getExpressionsInRange(exprList, colons[1].getPsi(), slice.getRbrack()));
    }

    return Trinity.create(start, end, max);
  }

  @NotNull
  public static List<GnoExpression> getRightExpressionsList(@NotNull GnoVarSpec varSpec) {
    return varSpec.getExpressionList();
  }

  @NotNull
  public static List<GnoExpression> getRightExpressionsList(@NotNull GnoRangeClause rangeClause) {
    return ContainerUtil.createMaybeSingletonList(rangeClause.getRangeExpression());
  }

  @NotNull
  public static List<GnoExpression> getRightExpressionsList(@NotNull GnoRecvStatement recvStatement) {
    return ContainerUtil.createMaybeSingletonList(recvStatement.getRecvExpression());
  }

  @Nullable
  public static GnoExpression getRangeExpression(@NotNull GnoRangeClause rangeClause) {
    return getLastExpressionAfter(rangeClause.getExpressionList(), rangeClause.getRange());
  }

  @Nullable
  public static GnoExpression getRecvExpression(@NotNull GnoRecvStatement recvStatement) {
    return getLastExpressionAfter(recvStatement.getExpressionList(),
                                  ObjectUtils.chooseNotNull(recvStatement.getAssign(), recvStatement.getVarAssign()));
  }

  @Nullable
  public static GnoExpression getSendExpression(@NotNull GnoSendStatement sendStatement) {
    return getLastExpressionAfter(sendStatement.getExpressionList(), sendStatement.getSendChannel());
  }

  @Nullable
  private static GnoExpression getLastExpressionAfter(@NotNull List<GnoExpression> list, @Nullable PsiElement anchor) {
    if (anchor == null) return null;
    GnoExpression last = ContainerUtil.getLastItem(list);
    return last != null && last.getTextRange().getStartOffset() >= anchor.getTextRange().getEndOffset() ? last : null;
  }

  @NotNull
  private static List<GnoExpression> getExpressionsInRange(@NotNull List<GnoExpression> list,
                                                          @Nullable PsiElement start,
                                                          @Nullable PsiElement end) {
    if (start == null && end == null) {
      return list;
    }
    return ContainerUtil.filter(list, expression -> (end == null || expression.getTextRange().getEndOffset() <= end.getTextRange().getStartOffset()) &&
                                                (start == null || expression.getTextRange().getStartOffset() >= start.getTextRange().getEndOffset()));
  }

  @NotNull
  private static List<GnoExpression> getExpressionsBefore(@NotNull List<GnoExpression> list, @Nullable PsiElement anchor) {
    return getExpressionsInRange(list, null, anchor);
  }

  @NotNull
  public static ReadWriteAccessDetector.Access getReadWriteAccess(@NotNull GnoReferenceExpression referenceExpression) {
    GnoExpression expression = getConsiderableExpression(referenceExpression);
    PsiElement parent = expression.getParent();
    if (parent instanceof GnoSelectorExpr) {
      if (expression.equals(((GnoSelectorExpr)parent).getRight())) {
        expression = getConsiderableExpression((GnoSelectorExpr)parent);
        parent = expression.getParent();
      }
      else {
        return Read;
      }
    }
    if (parent instanceof GnoIncDecStatement) {
      return Write;
    }
    if (parent instanceof GnoLeftHandExprList) {
      PsiElement grandParent = parent.getParent();
      if (grandParent instanceof GnoAssignmentStatement) {
        return ((GnoAssignmentStatement)grandParent).getAssignOp().getAssign() == null ? ReadWrite : Write;
      }
      if (grandParent instanceof GnoSendStatement) {
        return Write;
      }
      return Read;
    }
    if (parent instanceof GnoSendStatement && parent.getParent() instanceof GnoCommCase) {
      return expression.equals(((GnoSendStatement)parent).getSendExpression()) ? Read : ReadWrite;
    }
    if (parent instanceof GnoRangeClause) {
      return expression.equals(((GnoRangeClause)parent).getRangeExpression()) ? Read : Write;
    }
    if (parent instanceof GnoRecvStatement) {
      return expression.equals(((GnoRecvStatement)parent).getRecvExpression()) ? Read : Write;
    }
    return Read;
  }

  @NotNull
  private static GnoExpression getConsiderableExpression(@NotNull GnoExpression element) {
    GnoExpression result = element;
    while (true) {
      PsiElement parent = result.getParent();
      if (parent == null) {
        return result;
      }
      if (parent instanceof GnoParenthesesExpr) {
        result = (GnoParenthesesExpr)parent;
        continue;
      }
      if (parent instanceof GnoUnaryExpr) {
        GnoUnaryExpr unaryExpr = (GnoUnaryExpr)parent;
        if (unaryExpr.getMul() != null || unaryExpr.getBitAnd() != null || unaryExpr.getSendChannel() != null) {
          result = (GnoUnaryExpr)parent;
          continue;
        }
      }
      return result;
    }
  }

  @NotNull
  public static String getDecodedText(@NotNull GnoStringLiteral o) {
    StringBuilder builder = new StringBuilder();
    TextRange range = ElementManipulators.getManipulator(o).getRangeInElement(o);
    o.createLiteralTextEscaper().decode(range, builder);
    return builder.toString();
  }

  @Nullable
  public static PsiElement getOperator(@NotNull GnoUnaryExpr o) {
    return getNotNullElement(o.getNot(), o.getMinus(), o.getPlus(), o.getBitAnd(), o.getBitXor(), o.getMul(), o.getSendChannel());
  }

  @Nullable
  public static PsiElement getOperator(@NotNull GnoBinaryExpr o) {
    if (o instanceof GnoAndExpr) return ((GnoAndExpr)o).getCondAnd();
    if (o instanceof GnoOrExpr) return ((GnoOrExpr)o).getCondOr();
    if (o instanceof GnoSelectorExpr) return ((GnoSelectorExpr)o).getDot();
    if (o instanceof GnoConversionExpr) return ((GnoConversionExpr)o).getComma();

    if (o instanceof GnoMulExpr) {
      GnoMulExpr m = (GnoMulExpr)o;
      return getNotNullElement(m.getMul(), m.getQuotient(), m.getRemainder(), m.getShiftRight(), m.getShiftLeft(), m.getBitAnd(),
                               m.getBitClear());
    }
    if (o instanceof GnoAddExpr) {
      GnoAddExpr a = (GnoAddExpr)o;
      return getNotNullElement(a.getBitXor(), a.getBitOr(), a.getMinus(), a.getPlus());
    }
    if (o instanceof GnoConditionalExpr) {
      GnoConditionalExpr c = (GnoConditionalExpr)o;
      return getNotNullElement(c.getEq(), c.getNotEq(), c.getGreater(), c.getGreaterOrEqual(), c.getLess(), c.getLessOrEqual());
    }
    return null;
  }

  @Nullable
  private static PsiElement getNotNullElement(@Nullable PsiElement... elements) {
    if (elements == null) return null;
    for (PsiElement e : elements) {
      if (e != null) return e;
    }
    return null;
  }

  public static boolean isSingleCharLiteral(@NotNull GnoStringLiteral literal) {
    return literal.getDecodedText().length() == 1;
  }

  @Nullable
  public static GnoExpression getRightExpression(@NotNull GnoAssignmentStatement assignment, @NotNull GnoExpression leftExpression) {
    int fieldIndex = assignment.getLeftHandExprList().getExpressionList().indexOf(leftExpression);
    return getByIndex(assignment.getExpressionList(), fieldIndex);
  }
}
