package com.github.intellij.gno.psi.impl;

import com.github.intellij.gno.psi.GnoTypes;
import com.github.intellij.gno.psi.GnoVarDeclaration;
import com.github.intellij.gno.psi.GnoVarDeclarationStatement;
import com.github.intellij.gno.psi.GnoVisitor;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GnoVarDeclarationStatementImpl extends GnoStatementImpl implements GnoVarDeclarationStatement {
    public GnoVarDeclarationStatementImpl(@NotNull ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GnoVisitor visitor) {
        visitor.visitVarDeclarationStatement(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GnoVisitor) {
            this.accept((GnoVisitor)visitor);
        } else {
            super.accept(visitor);
        }

    }

    public @NotNull GnoVarDeclaration getVarDeclaration() {
        return this.findNotNullChildByClass(GnoVarDeclaration.class);
    }

    public @NotNull PsiElement getLdoubleBrace() {
        return this.findNotNullChildByType(GnoTypes.LDOUBLE_BRACE);
    }

    public @Nullable PsiElement getRdoubleBrace() {
        return this.findChildByType(GnoTypes.RDOUBLE_BRACE);
    }
}
