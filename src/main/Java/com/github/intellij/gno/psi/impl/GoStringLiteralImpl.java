package com.github.intellij.gno.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import static com.github.intellij.gno.GoTypes.*;
import com.github.intellij.gno.psi.*;
import com.github.intellij.gno.util.GoStringLiteralEscaper;

public class GoStringLiteralImpl extends GoExpressionImpl implements GoStringLiteral {

    public GoStringLiteralImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitStringLiteral(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GoVisitor) accept((GoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @Nullable
    public PsiElement getRawString() {
        return findChildByType(RAW_STRING);
    }

    @Override
    @Nullable
    public PsiElement getString() {
        return findChildByType(STRING);
    }

    public boolean isValidHost() {
        return GoPsiImplUtil.isValidHost(this);
    }

    @NotNull
    public GoStringLiteralImpl updateText(String text) {
        return GoPsiImplUtil.updateText(this, text);
    }

    @NotNull
    public GoStringLiteralEscaper createLiteralTextEscaper() {
        return GoPsiImplUtil.createLiteralTextEscaper(this);
    }

    @NotNull
    public String getDecodedText() {
        return GoPsiImplUtil.getDecodedText(this);
    }

}
