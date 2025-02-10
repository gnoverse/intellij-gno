package com.github.intellij.gno.psi.impl;

import com.github.intellij.gno.psi.GnoImplUtil;
import com.github.intellij.gno.psi.GnoTypes;
import com.github.intellij.gno.psi.GnoVariableExpr;
import com.github.intellij.gno.psi.GnoVisitor;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiReference;
import org.jetbrains.annotations.NotNull;

public class GnoVariableExprImpl extends GnoExpressionImpl implements GnoVariableExpr {
    public GnoVariableExprImpl(@NotNull ASTNode node) {
        if (node == null) {
            $$$reportNull$$$0(0);
        }

        super(node);
    }

    public void accept(@NotNull GnoVisitor visitor) {
        if (visitor == null) {
            $$$reportNull$$$0(1);
        }

        visitor.visitVariableExpr(this);
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

    public @NotNull PsiElement getVariable() {
        PsiElement var10000 = this.findNotNullChildByType(GnoTypes.VARIABLE);
        if (var10000 == null) {
            $$$reportNull$$$0(3);
        }

        return var10000;
    }

    public @NotNull PsiReference getReference() {
        PsiReference var10000 = GnoImplUtil.getReference(this);
        if (var10000 == null) {
            $$$reportNull$$$0(4);
        }

        return var10000;
    }
}
