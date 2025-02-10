package com.github.intellij.gno.psi.impl;

import com.github.intellij.gno.psi.GnoTypes;
import com.github.intellij.gno.psi.GnoPipeline;
import com.github.intellij.gno.psi.GnoStatement;
import com.github.intellij.gno.psi.GnoStatementList;
import com.github.intellij.gno.psi.GnoVarDeclaration;
import com.github.intellij.gno.psi.GnoVisitor;
import com.github.intellij.gno.psi.GnoWithStatement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GnoWithStatementImpl extends GnoStatementImpl implements GnoWithStatement {
    public GnoWithStatementImpl(@NotNull ASTNode node) {
        if (node == null) {
            $$$reportNull$$$0(0);
        }

        super(node);
    }

    public void accept(@NotNull GnoVisitor visitor) {
        if (visitor == null) {
            $$$reportNull$$$0(1);
        }

        visitor.visitWithStatement(this);
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

    public @Nullable GnoStatement getStatement() {
        return (GnoStatement)this.findChildByClass(GnoStatement.class);
    }

    public @Nullable GnoStatementList getStatementList() {
        return (GnoStatementList)this.findChildByClass(GnoStatementList.class);
    }

    public @Nullable GnoVarDeclaration getVarDeclaration() {
        return (GnoVarDeclaration)this.findChildByClass(GnoVarDeclaration.class);
    }

    public @NotNull PsiElement getLdoubleBrace() {
        PsiElement var10000 = this.findNotNullChildByType(GnoTypes.LDOUBLE_BRACE);
        if (var10000 == null) {
            $$$reportNull$$$0(3);
        }

        return var10000;
    }

    public @Nullable PsiElement getRdoubleBrace() {
        return this.findChildByType(GnoTypes.RDOUBLE_BRACE);
    }

    public @NotNull PsiElement getWith() {
        PsiElement var10000 = this.findNotNullChildByType(GnoTypes.WITH);
        if (var10000 == null) {
            $$$reportNull$$$0(4);
        }

        return var10000;
    }
}
