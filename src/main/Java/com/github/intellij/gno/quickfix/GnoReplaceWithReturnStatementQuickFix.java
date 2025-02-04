package com.github.intellij.gno.quickfix;

import com.github.intellij.gno.psi.GnoStatement;
import com.github.intellij.gno.psi.impl.GnoElementFactory;
import com.intellij.codeInspection.LocalQuickFixAndIntentionActionOnPsiElement;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GnoReplaceWithReturnStatementQuickFix extends LocalQuickFixAndIntentionActionOnPsiElement {
    public static final String QUICK_FIX_NAME = "Replace with 'return'";

    public GnoReplaceWithReturnStatementQuickFix(@Nullable PsiElement element) {
        super(element);
    }

    @NotNull
    @Override
    public String getText() {
        return QUICK_FIX_NAME;
    }

    @Nls
    @NotNull
    @Override
    public String getFamilyName() {
        return QUICK_FIX_NAME;
    }

    @Override
    public void invoke(@NotNull Project project,
                       @NotNull PsiFile file,
                       @Nullable("is null when called from inspection") Editor editor,
                       @NotNull PsiElement startElement,
                       @NotNull PsiElement endElement) {
        WriteCommandAction.runWriteCommandAction(project, () -> {
            if (startElement instanceof GnoStatement) {
                startElement.replace(GnoElementFactory.createReturnStatement(project));
            }
        });
    }
}