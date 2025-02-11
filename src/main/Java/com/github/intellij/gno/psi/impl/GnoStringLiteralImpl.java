package com.github.intellij.gno.psi.impl;

import com.github.intellij.gno.psi.GnoImplUtil;
import com.github.intellij.gno.psi.GnoTypes;
import com.github.intellij.gno.psi.GnoStringLiteral;
import com.github.intellij.gno.psi.GnoVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiReference;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GnoStringLiteralImpl extends ASTWrapperPsiElement implements GnoStringLiteral {
    public GnoStringLiteralImpl(@NotNull ASTNode node) {

        super(node);
    }

    public void accept(@NotNull GnoVisitor visitor) {

        visitor.visitStringLiteral(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {

        if (visitor instanceof GnoVisitor) {
            this.accept((GnoVisitor)visitor);
        } else {
            super.accept(visitor);
        }

    }

    public @Nullable PsiElement getRawString() {
        return this.findChildByType(GnoTypes.RAW_STRING);
    }

    public @Nullable PsiElement getString() {
        return this.findChildByType(GnoTypes.STRING);
    }

    public PsiReference @NotNull [] getReferences() {

        return GnoImplUtil.getReferences(this);
    }

    public @NotNull GnoStringLiteral updateText(@NotNull String text) {

        return GnoImplUtil.updateText(this, text);
    }
}
