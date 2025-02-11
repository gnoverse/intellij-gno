package com.github.intellij.gno.psi.impl;

import com.github.intellij.gno.psi.GnoTypes;
import com.github.intellij.gno.psi.GnoElseIfStatement;
import com.github.intellij.gno.psi.GnoPipeline;
import com.github.intellij.gno.psi.GnoStatement;
import com.github.intellij.gno.psi.GnoStatementList;
import com.github.intellij.gno.psi.GnoVarDeclaration;
import com.github.intellij.gno.psi.GnoVisitor;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GnoElseIfStatementImpl extends GnoStatementImpl implements GnoElseIfStatement {
    public GnoElseIfStatementImpl(@NotNull ASTNode node) {

        super(node);
    }

    public void accept(@NotNull GnoVisitor visitor) {

        visitor.visitElseIfStatement(this);
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

    public @Nullable GnoStatement getStatement() {
        return this.findChildByClass(GnoStatement.class);
    }

    public @Nullable GnoStatementList getStatementList() {
        return this.findChildByClass(GnoStatementList.class);
    }

    public @Nullable GnoVarDeclaration getVarDeclaration() {
        return this.findChildByClass(GnoVarDeclaration.class);
    }

    public @NotNull PsiElement getElse() {

        return this.findNotNullChildByType(GnoTypes.ELSE);
    }

    public @NotNull PsiElement getIf() {

        return this.findNotNullChildByType(GnoTypes.IF);
    }

    public @NotNull PsiElement getLdoubleBrace() {

        return this.findNotNullChildByType(GnoTypes.LDOUBLE_BRACE);
    }

    public @Nullable PsiElement getRdoubleBrace() {
        return this.findChildByType(GnoTypes.RDOUBLE_BRACE);
    }
}
