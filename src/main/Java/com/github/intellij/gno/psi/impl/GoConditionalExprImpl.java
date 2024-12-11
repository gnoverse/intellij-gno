package com.github.intellij.gno.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import static com.github.intellij.gno.GoTypes.*;
import com.github.intellij.gno.psi.*;

public class GoConditionalExprImpl extends GoBinaryExprImpl implements GoConditionalExpr {

    public GoConditionalExprImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitConditionalExpr(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GoVisitor) accept((GoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @Nullable
    public PsiElement getEq() {
        return findChildByType(EQ);
    }

    @Override
    @Nullable
    public PsiElement getGreater() {
        return findChildByType(GREATER);
    }

    @Override
    @Nullable
    public PsiElement getGreaterOrEqual() {
        return findChildByType(GREATER_OR_EQUAL);
    }

    @Override
    @Nullable
    public PsiElement getLess() {
        return findChildByType(LESS);
    }

    @Override
    @Nullable
    public PsiElement getLessOrEqual() {
        return findChildByType(LESS_OR_EQUAL);
    }

    @Override
    @Nullable
    public PsiElement getNotEq() {
        return findChildByType(NOT_EQ);
    }

}