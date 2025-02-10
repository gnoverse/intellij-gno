package com.github.intellij.gno.psi.impl;

import com.github.intellij.gno.psi.GnoTypes;
import com.github.intellij.gno.psi.GnoDefineStatement;
import com.github.intellij.gno.psi.GnoEndStatement;
import com.github.intellij.gno.psi.GnoStatementList;
import com.github.intellij.gno.psi.GnoStringLiteral;
import com.github.intellij.gno.psi.GnoVisitor;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GnoDefineStatementImpl extends GnoStatementImpl implements GnoDefineStatement {
    public GnoDefineStatementImpl(@NotNull ASTNode node) {

        super(node);
    }

    public void accept(@NotNull GnoVisitor visitor) {

        visitor.visitDefineStatement(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {

        if (visitor instanceof GnoVisitor) {
            this.accept((GnoVisitor)visitor);
        } else {
            super.accept(visitor);
        }

    }

    public @Nullable GnoEndStatement getEndStatement() {
        return this.findChildByClass(GnoEndStatement.class);
    }

    public @Nullable GnoStatementList getStatementList() {
        return this.findChildByClass(GnoStatementList.class);
    }

    public @Nullable GnoStringLiteral getStringLiteral() {
        return this.findChildByClass(GnoStringLiteral.class);
    }

    public @NotNull PsiElement getDefine() {

        return this.findNotNullChildByType(GnoTypes.DEFINE);
    }

    public @NotNull PsiElement getLdoubleBrace() {

        return this.findNotNullChildByType(GnoTypes.LDOUBLE_BRACE);
    }

    public @Nullable PsiElement getRdoubleBrace() {
        return this.findChildByType(GnoTypes.RDOUBLE_BRACE);
    }
}
