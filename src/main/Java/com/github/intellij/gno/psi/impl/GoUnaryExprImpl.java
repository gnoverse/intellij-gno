package com.github.intellij.gno.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import static com.github.intellij.gno.GoTypes.*;
import com.github.intellij.gno.psi.*;

public class GoUnaryExprImpl extends GoExpressionImpl implements GoUnaryExpr {

    public GoUnaryExprImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitUnaryExpr(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GoVisitor) accept((GoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @Nullable
    public GoExpression getExpression() {
        return GoPsiTreeUtil.getChildOfType(this, GoExpression.class);
    }

    @Override
    @Nullable
    public PsiElement getBitAnd() {
        return findChildByType(BIT_AND);
    }

    @Override
    @Nullable
    public PsiElement getBitXor() {
        return findChildByType(BIT_XOR);
    }

    @Override
    @Nullable
    public PsiElement getMinus() {
        return findChildByType(MINUS);
    }

    @Override
    @Nullable
    public PsiElement getMul() {
        return findChildByType(MUL);
    }

    @Override
    @Nullable
    public PsiElement getNot() {
        return findChildByType(NOT);
    }

    @Override
    @Nullable
    public PsiElement getPlus() {
        return findChildByType(PLUS);
    }

    @Override
    @Nullable
    public PsiElement getSendChannel() {
        return findChildByType(SEND_CHANNEL);
    }

    @Nullable
    public PsiElement getOperator() {
        return GoPsiImplUtil.getOperator(this);
    }

}
