package com.github.intellij.gno.quickfix;

import com.intellij.codeInspection.LocalQuickFixAndIntentionActionOnPsiElement;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GnoDeleteRangeQuickFix extends LocalQuickFixAndIntentionActionOnPsiElement {
    private static final Logger LOG = Logger.getInstance(GnoDeleteRangeQuickFix.class);
    private final String myName;

    public GnoDeleteRangeQuickFix(@NotNull PsiElement startElement, @NotNull PsiElement endElement, @NotNull String name) {
        super(startElement, endElement);
        if (!startElement.getParent().equals(endElement.getParent())) {
            LOG.error("Cannot delete range of elements with different parents");
        }
        myName = name;
    }

    @NotNull
    @Override
    public String getText() {
        return myName;
    }

    @Nls
    @NotNull
    @Override
    public String getFamilyName() {
        return "Delete elements";
    }

    @Override
    public void invoke(@NotNull Project project,
                       @NotNull PsiFile file,
                       @Nullable("is null when called from inspection") Editor editor,
                       @NotNull PsiElement start,
                       @NotNull PsiElement end) {
        if (start.isValid() && end.isValid()) {
            PsiElement parent = start.getParent();
            if (parent != null && parent.equals(end.getParent())) {
                parent.getNode().removeRange(start.getNode(), end.getNode().getTreeNext());
            }
        }
    }
}