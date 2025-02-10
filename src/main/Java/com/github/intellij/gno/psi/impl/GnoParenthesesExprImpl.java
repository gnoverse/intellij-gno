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
        if (node == null) {
            $$$reportNull$$$0(0);
        }

        super(node);
    }

    public void accept(@NotNull GnoVisitor visitor) {
        if (visitor == null) {
            $$$reportNull$$$0(1);
        }

        visitor.visitParenthesesExpr(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor == null) {
            $$$reportNull$$$0(2);
        }

        if (visitor instanceof GnoVisitor) {
            this.accept((GnoVisitor)visitor);
        } else {
            super.accept(visitor);
        }

    }

    public @Nullable GnoPipeline getPipeline() {
        return (GnoPipeline)this.findChildByClass(GnoPipeline.class);
    }

    public @NotNull PsiElement getLparen() {
        PsiElement var10000 = this.findNotNullChildByType(GnoTypes.LPAREN);
        if (var10000 == null) {
            $$$reportNull$$$0(3);
        }

        return var10000;
    }

    public @Nullable PsiElement getRparen() {
        return this.findChildByType(GnoTypes.RPAREN);
    }
}
