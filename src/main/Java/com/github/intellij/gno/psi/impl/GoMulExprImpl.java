package com.github.intellij.gno.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import static com.github.intellij.gno.GoTypes.*;
import com.github.intellij.gno.psi.*;

public class GoMulExprImpl extends GoBinaryExprImpl implements GoMulExpr {

    public GoMulExprImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitMulExpr(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GoVisitor) accept((GoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @Nullable
    public PsiElement getBitAnd() {
        return findChildByType(BIT_AND);
    }

    @Override
    @Nullable
    public PsiElement getBitClear() {
        return findChildByType(BIT_CLEAR);
    }

    @Override
    @Nullable
    public PsiElement getMul() {
        return findChildByType(MUL);
    }

    @Override
    @Nullable
    public PsiElement getQuotient() {
        return findChildByType(QUOTIENT);
    }

    @Override
    @Nullable
    public PsiElement getRemainder() {
        return findChildByType(REMAINDER);
    }

    @Override
    @Nullable
    public PsiElement getShiftLeft() {
        return findChildByType(SHIFT_LEFT);
    }

    @Override
    @Nullable
    public PsiElement getShiftRight() {
        return findChildByType(SHIFT_RIGHT);
    }

}