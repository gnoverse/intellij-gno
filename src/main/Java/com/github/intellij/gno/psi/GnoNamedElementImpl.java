package com.github.intellij.gno.psi;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GnoNamedElementImpl extends ASTWrapperPsiElement implements GnoNamedElement {
    public GnoNamedElementImpl(@NotNull ASTNode node) {

        super(node);
    }

    public @Nullable PsiElement getNameIdentifier() {
        return this.findChildByType(GnoTypes.VARIABLE);
    }

    public String getName() {
        PsiElement identifier = this.getNameIdentifier();
        return identifier != null ? identifier.getText() : null;
    }

    public PsiElement setName(@NotNull String name) throws IncorrectOperationException {

        PsiElement element = this.getNameIdentifier();
        if (element != null) {
            element.replace(GnoElementFactory.createVar(this.getProject(), name));
        }

        return this;
    }
}
