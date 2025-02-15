package com.github.intellij.gno.quickfix;

import com.github.intellij.gno.psi.GnoFile;
import com.github.intellij.gno.psi.GnoImportSpec;
import com.intellij.codeInspection.LocalQuickFixBase;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;

public class GnoDeleteImportQuickFix extends LocalQuickFixBase {
    public static final String QUICK_FIX_NAME = "Delete import";

    public GnoDeleteImportQuickFix() {
        super(QUICK_FIX_NAME);
    }

    @Override
    public void applyFix(@NotNull Project project, @NotNull ProblemDescriptor descriptor) {
        PsiElement element = PsiTreeUtil.getNonStrictParentOfType(descriptor.getPsiElement(), GnoImportSpec.class);
        PsiFile file = element != null ? element.getContainingFile() : null;
        if (!(file instanceof GnoFile)) return;

        WriteCommandAction.runWriteCommandAction(project, () -> ((GnoFile)file).deleteImport((GnoImportSpec)element));
    }
}
