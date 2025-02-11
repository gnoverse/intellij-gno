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

        super(node);
    }

    public void accept(@NotNull GnoVisitor visitor) {

        visitor.visitVariableExpr(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {

        if (visitor instanceof GnoVisitor) {
            this.accept((GnoVisitor)visitor);
        } else {
            super.accept(visitor);
        }

    }

    public @NotNull PsiElement getVariable() {

        return this.findNotNullChildByType(GnoTypes.VARIABLE);
    }

    public @NotNull PsiReference getReference() {

        return GnoImplUtil.getReference(this);
    }
}
