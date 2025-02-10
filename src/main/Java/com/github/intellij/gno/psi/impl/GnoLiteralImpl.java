package com.github.intellij.gno.psi.impl;

import com.github.intellij.gno.psi.GnoTypes;
import com.github.intellij.gno.psi.GnoLiteral;
import com.github.intellij.gno.psi.GnoStringLiteral;
import com.github.intellij.gno.psi.GnoVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GnoLiteralImpl extends ASTWrapperPsiElement implements GnoLiteral {
    public GnoLiteralImpl(@NotNull ASTNode node) {
        if (node == null) {
            $$$reportNull$$$0(0);
        }

        super(node);
    }

    public void accept(@NotNull GnoVisitor visitor) {
        if (visitor == null) {
            $$$reportNull$$$0(1);
        }

        visitor.visitLiteral(this);
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

    public @Nullable GnoStringLiteral getStringLiteral() {
        return (GnoStringLiteral)this.findChildByClass(GnoStringLiteral.class);
    }

    public @Nullable PsiElement getAt() {
        return this.findChildByType(GnoTypes.AT);
    }

    public @Nullable PsiElement getChar() {
        return this.findChildByType(GnoTypes.CHAR);
    }

    public @Nullable PsiElement getDot() {
        return this.findChildByType(GnoTypes.DOT);
    }

    public @Nullable PsiElement getFalse() {
        return this.findChildByType(GnoTypes.FALSE);
    }

    public @Nullable PsiElement getHash() {
        return this.findChildByType(GnoTypes.HASH);
    }

    public @Nullable PsiElement getIdentifier() {
        return this.findChildByType(GnoTypes.IDENTIFIER);
    }

    public @Nullable PsiElement getNil() {
        return this.findChildByType(GnoTypes.NIL);
    }

    public @Nullable PsiElement getNumber() {
        return this.findChildByType(GnoTypes.NUMBER);
    }

    public @Nullable PsiElement getPercent() {
        return this.findChildByType(GnoTypes.PERCENT);
    }

    public @Nullable PsiElement getTrue() {
        return this.findChildByType(GnoTypes.TRUE);
    }
}
