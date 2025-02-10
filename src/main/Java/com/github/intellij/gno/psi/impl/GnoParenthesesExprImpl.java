package com.github.intellij.gno.psi.impl;

import com.github.intellij.gno.psi.GnoTypes;
import com.github.intellij.gno.psi.GnoParenthesesExpr;
import com.github.intellij.gno.psi.GnoPipeline;
import com.github.intellij.gno.psi.GnoVisitor;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GnoParenthesesExprImpl extends GnoExpressionImpl implements GnoParenthesesExpr {
    public GnoParenthesesExprImpl(@NotNull ASTNode node) {

        super(node);
    }

    public void accept(@NotNull GnoVisitor visitor) {

        visitor.visitParenthesesExpr(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {

        if (visitor instanceof GnoVisitor) {
            this.accept((GnoVisitor)visitor);
        } else {
            super.accept(visitor);
        }

    }

    public @Nullable GnoPipeline getPipeline() {
        return this.findChildByClass(GnoPipeline.class);
    }

    public @NotNull PsiElement getLparen() {

        return this.findNotNullChildByType(GnoTypes.LPAREN);
    }

    public @Nullable PsiElement getRparen() {
        return this.findChildByType(GnoTypes.RPAREN);
    }
}
