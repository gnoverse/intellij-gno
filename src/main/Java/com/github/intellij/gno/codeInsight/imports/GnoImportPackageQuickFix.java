package com.github.intellij.gno.codeInsight.imports;

import com.github.intellij.gno.language.GnoIcons;
import com.github.intellij.gno.completion.GnoCompletionUtil;
import com.github.intellij.gno.project.GnoVendoringUtil;
import com.github.intellij.gno.psi.GnoFile;
import com.github.intellij.gno.psi.GnoReferenceExpression;
import com.github.intellij.gno.psi.GnoTypeReferenceExpression;
import com.github.intellij.gno.psi.impl.GnoPsiImplUtil;
import com.github.intellij.gno.psi.impl.GnoReference;
import com.github.intellij.gno.psi.impl.GnoTypeReference;
import com.github.intellij.gno.runconfig.testing.GnoTestFinder;
import com.github.intellij.gno.stubs.index.GnoPackagesIndex;
import com.github.intellij.gno.util.GnoUtil;
import com.intellij.codeInsight.FileModificationService;
import com.intellij.codeInsight.daemon.impl.DaemonListeners;
import com.intellij.codeInsight.daemon.impl.ShowAutoImportPass;
import com.intellij.codeInsight.hint.HintManager;
import com.intellij.codeInsight.intention.HighPriorityAction;
import com.intellij.codeInspection.HintAction;
import com.intellij.codeInspection.LocalQuickFixAndIntentionActionOnPsiElement;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.impl.LaterInvocator;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.JBPopup;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.PopupChooserBuilder;
import com.intellij.openapi.util.Comparing;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiReference;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.stubs.StubIndex;
import com.intellij.ui.IdeBorderFactory;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBList;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.*;

import static com.intellij.util.containers.ContainerUtil.*;

public class GnoImportPackageQuickFix extends LocalQuickFixAndIntentionActionOnPsiElement implements HintAction, HighPriorityAction {
    @NotNull private final String myPackageName;
    @Nullable private List<String> myPackagesToImport;

    public GnoImportPackageQuickFix(@NotNull PsiElement element, @NotNull String importPath) {
        super(element);
        myPackageName = "";
        myPackagesToImport = Collections.singletonList(importPath);
    }

    public GnoImportPackageQuickFix(@NotNull PsiReference reference) {
        super(reference.getElement());
        myPackageName = reference.getCanonicalText();
    }

    @Nullable
    public PsiReference getReference(PsiElement element) {
        if (element != null && element.isValid()) {
            for (PsiReference reference : element.getReferences()) {
                if (isSupportedReference(reference)) {
                    return reference;
                }
            }
        }
        return null;
    }

    private static boolean isSupportedReference(@Nullable PsiReference reference) {
        return reference instanceof GnoReference || reference instanceof GnoTypeReference;
    }

    @Override
    public boolean showHint(@NotNull Editor editor) {
        return doAutoImportOrShowHint(editor, true);
    }

    @NotNull
    @Override
    public String getText() {
        PsiElement element = getStartElement();
        if (element != null) {
            return "Import " + getText(getImportPathVariantsToImport(element));
        }
        return "Import package";
    }

    @NotNull
    private static String getText(@NotNull Collection<String> packagesToImport) {
        return getFirstItem(packagesToImport, "") + "? " + (packagesToImport.size() > 1 ? "(multiple choices...) " : "");
    }

    @NotNull
    @Override
    public String getFamilyName() {
        return "Import package";
    }

    @Override
    public void invoke(@NotNull Project project, @NotNull PsiFile file, @Nullable("is null when called from inspection") Editor editor,
                       @NotNull PsiElement startElement, @NotNull PsiElement endElement) {
        if (!FileModificationService.getInstance().prepareFileForWrite(file)) return;
        perform(getImportPathVariantsToImport(startElement), file, editor);
    }

    @Override
    public boolean isAvailable(@NotNull Project project,
                               @NotNull PsiFile file,
                               @NotNull PsiElement startElement,
                               @NotNull PsiElement endElement) {
        PsiReference reference = getReference(startElement);
        return file instanceof GnoFile && file.getManager().isInProject(file)
                && reference != null && reference.resolve() == null
                && !getImportPathVariantsToImport(startElement).isEmpty() && notQualified(startElement);
    }

    private static boolean notQualified(@Nullable PsiElement startElement) {
        return startElement instanceof GnoReferenceExpression && ((GnoReferenceExpression)startElement).getQualifier() == null ||
                startElement instanceof GnoTypeReferenceExpression && ((GnoTypeReferenceExpression)startElement).getQualifier() == null;
    }

    @NotNull
    private List<String> getImportPathVariantsToImport(@NotNull PsiElement element) {
        if (myPackagesToImport == null) {
            myPackagesToImport = getImportPathVariantsToImport(myPackageName, element);
        }
        return myPackagesToImport;
    }

    @NotNull
    public static List<String> getImportPathVariantsToImport(@NotNull String packageName, @NotNull PsiElement context) {
        PsiFile contextFile = context.getContainingFile();
        Set<String> imported = contextFile instanceof GnoFile
                ? ((GnoFile)contextFile).getImportedPackagesMap().keySet() : Collections.emptySet();
        Project project = context.getProject();
        PsiDirectory parentDirectory = contextFile != null ? contextFile.getParent() : null;
        String testTargetPackage = GnoTestFinder.getTestTargetPackage(contextFile);
        Module module = contextFile != null ? ModuleUtilCore.findModuleForPsiElement(contextFile) : null;
        boolean vendoringEnabled = GnoVendoringUtil.isVendoringEnabled(module);
        GlobalSearchScope scope = GnoUtil.goPathResolveScope(context);
        Collection<GnoFile> packages = StubIndex.getElements(GnoPackagesIndex.KEY, packageName, project, scope, GnoFile.class);
        return sorted(skipNulls(map2Set(
                packages,
                file -> {
                    if (parentDirectory != null && parentDirectory.isEquivalentTo(file.getParent())) {
                        if (testTargetPackage == null || !testTargetPackage.equals(file.getPackageName())) {
                            return null;
                        }
                    }
                    if (!GnoPsiImplUtil.canBeAutoImported(file, false, module)) {
                        return null;
                    }
                    String importPath = file.getImportPath(vendoringEnabled);
                    return !imported.contains(importPath) ? importPath : null;
                }
        )), new MyImportsComparator(context, vendoringEnabled));
    }

    public boolean doAutoImportOrShowHint(@NotNull Editor editor, boolean showHint) {
        PsiElement element = getStartElement();
        if (element == null || !element.isValid()) return false;

        PsiReference reference = getReference(element);
        if (reference == null || reference.resolve() != null) return false;

        List<String> packagesToImport = getImportPathVariantsToImport(element);
        if (packagesToImport.isEmpty()) {
            return false;
        }

        PsiFile file = element.getContainingFile();
        String firstPackageToImport = getFirstItem(packagesToImport);

        // autoimport on trying to fix
        if (packagesToImport.size() == 1) {
            if (GnoCodeInsightSettings.getInstance().isAddUnambiguousImportsOnTheFly() && !LaterInvocator.isInModalContext() &&
                    (ApplicationManager.getApplication().isUnitTestMode())) {
                CommandProcessor.getInstance().runUndoTransparentAction(() -> perform(file, firstPackageToImport));
                return true;
            }
        }

        // show hint on failed autoimport
        if (showHint) {
            if (ApplicationManager.getApplication().isUnitTestMode()) return false;
            if (HintManager.getInstance().hasShownHintsThatWillHideByOtherHint(true)) return false;
            if (!GnoCodeInsightSettings.getInstance().isShowImportPopup()) return false;
            TextRange referenceRange = reference.getRangeInElement().shiftRight(element.getTextRange().getStartOffset());
            HintManager.getInstance().showQuestionHint(
                    editor,
                    ShowAutoImportPass.getMessage(packagesToImport.size() > 1, getFirstItem(packagesToImport)),
                    referenceRange.getStartOffset(),
                    referenceRange.getEndOffset(),
                    () -> {
                        if (file.isValid() && !editor.isDisposed()) {
                            perform(packagesToImport, file, editor);
                        }
                        return true;
                    }
            );
            return true;
        }
        return false;
    }

    private void perform(@NotNull List<String> packagesToImport, @NotNull PsiFile file, @Nullable Editor editor) {
        LOG.assertTrue(editor != null || packagesToImport.size() == 1, "Cannot invoke fix with ambiguous imports on null editor");
        if (packagesToImport.size() > 1 && editor != null) {
            JBList list = new JBList(packagesToImport);
            list.installCellRenderer(o -> {
                JBLabel label = new JBLabel(o.toString(), GnoIcons.PACKAGE, SwingConstants.LEFT);
                label.setBorder(IdeBorderFactory.createEmptyBorder(2, 4, 2, 4));
                return label;
            });
            PopupChooserBuilder builder = JBPopupFactory.getInstance().createListPopupBuilder(list).setRequestFocus(true)
                    .setTitle("Package to import")
                    .setItemChoosenCallback(
                            () -> {
                                int i = list.getSelectedIndex();
                                if (i < 0) return;
                                perform(file, packagesToImport.get(i));
                            })
                    .setFilteringEnabled(o -> o instanceof String ? (String)o : o.toString());
            JBPopup popup = builder.createPopup();
            builder.getScrollPane().setBorder(null);
            builder.getScrollPane().setViewportBorder(null);
            popup.showInBestPositionFor(editor);
        }
        else if (packagesToImport.size() == 1) {
            perform(file, getFirstItem(packagesToImport));
        }
        else {
            String packages = StringUtil.join(packagesToImport, ",");
            throw new IncorrectOperationException("Cannot invoke fix with ambiguous imports on editor ()" + editor + ". Packages: " + packages);
        }
    }

    private void perform(@NotNull PsiFile file, @Nullable String pathToImport) {
        if (file instanceof GnoFile && pathToImport != null) {
            Project project = file.getProject();
            CommandProcessor.getInstance().executeCommand(project, () -> ApplicationManager.getApplication().runWriteAction(() -> {
                if (!isAvailable()) return;
                if (((GnoFile)file).getImportedPackagesMap().containsKey(pathToImport)) return;
                ((GnoFile)file).addImport(pathToImport, null);
            }), "Add import", null);
        }
    }

    private static class MyImportsComparator implements Comparator<String> {
        @Nullable
        private final String myContextImportPath;

        public MyImportsComparator(@Nullable PsiElement context, boolean vendoringEnabled) {
            myContextImportPath = GnoCompletionUtil.getContextImportPath(context, vendoringEnabled);
        }

        @Override
        public int compare(@NotNull String s1, @NotNull String s2) {
            int result = Comparing.compare(GnoCompletionUtil.calculatePackagePriority(s2, myContextImportPath),
                    GnoCompletionUtil.calculatePackagePriority(s1, myContextImportPath));
            return result != 0 ? result : Comparing.compare(s1, s2);
        }
    }
}
