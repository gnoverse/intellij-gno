package com.github.intellij.gno.quickfix;

import com.github.intellij.gno.psi.GnoFunctionDeclaration;
import com.github.intellij.gno.psi.GnoSignature;
import com.github.intellij.gno.psi.impl.GnoElementFactory;
import com.intellij.codeInspection.LocalQuickFixAndIntentionActionOnPsiElement;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.ObjectUtils;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GnoEmptySignatureQuickFix extends LocalQuickFixAndIntentionActionOnPsiElement {
    public static final String QUICK_FIX_NAME = "Fix signature";

    public GnoEmptySignatureQuickFix(@NotNull GnoFunctionDeclaration functionDeclaration) {
        super(functionDeclaration);
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
        GnoFunctionDeclaration function = ObjectUtils.tryCast(startElement, GnoFunctionDeclaration.class);
        GnoSignature signature = function != null ? function.getSignature() : null;
        if (signature == null) return;
        signature.replace(GnoElementFactory.createFunctionSignatureFromText(project, ""));
    }
}