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
        if (node == null) {
            $$$reportNull$$$0(0);
        }

        super(node);
    }

    public void accept(@NotNull GnoVisitor visitor) {
        if (visitor == null) {
            $$$reportNull$$$0(1);
        }

        visitor.visitStringLiteral(this);
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

    public @Nullable PsiElement getRawString() {
        return this.findChildByType(GnoTypes.RAW_STRING);
    }

    public @Nullable PsiElement getString() {
        return this.findChildByType(GnoTypes.STRING);
    }

    public PsiReference @NotNull [] getReferences() {
        PsiReference[] var10000 = GnoImplUtil.getReferences(this);
        if (var10000 == null) {
            $$$reportNull$$$0(3);
        }

        return var10000;
    }

    public @NotNull GnoStringLiteral updateText(@NotNull String text) {
        if (text == null) {
            $$$reportNull$$$0(4);
        }

        GnoStringLiteral var10000 = GnoImplUtil.updateText(this, text);
        if (var10000 == null) {
            $$$reportNull$$$0(5);
        }

        return var10000;
    }
}
