package com.github.intellij.gno.psi.impl;

import com.github.intellij.gno.psi.GnoExpression;
import com.github.intellij.gno.psi.GnoVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;

public abstract class GnoExpressionImpl extends ASTWrapperPsiElement implements GnoExpression {
    public GnoExpressionImpl(@NotNull ASTNode node) {

        super(node);
    }

    public void accept(@NotNull GnoVisitor visitor) {

        visitor.visitExpression(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {

        if (visitor instanceof GnoVisitor) {
            this.accept((GnoVisitor)visitor);
        } else {
            super.accept(visitor);
        }

    }
}
