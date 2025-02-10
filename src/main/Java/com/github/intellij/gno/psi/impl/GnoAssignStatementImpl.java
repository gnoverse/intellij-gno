package com.github.intellij.gno.psi.impl;

import com.github.intellij.gno.psi.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GnoAssignStatementImpl extends GnoStatementImpl implements GnoAssignStatement {
    public GnoAssignStatementImpl(@NotNull ASTNode node) {

        super(node);
    }

    public void accept(@NotNull GnoVisitor visitor) {

        visitor.visitAssignStatement(this);
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

    public @NotNull GnoVariableExpr getVariableExpr() {

        return this.findNotNullChildByClass(GnoVariableExpr.class);
    }

    public @NotNull PsiElement getAssign() {

        return this.findNotNullChildByType(GnoTypes.ASSIGN);
    }

    public @NotNull PsiElement getLdoubleBrace() {

        return this.findNotNullChildByType(GnoTypes.LDOUBLE_BRACE);
    }

    public @Nullable PsiElement getRdoubleBrace() {
        return this.findChildByType(GnoTypes.RDOUBLE_BRACE);
    }
}
