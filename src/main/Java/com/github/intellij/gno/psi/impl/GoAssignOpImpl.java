package com.github.intellij.gno.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import static com.github.intellij.gno.GoTypes.*;
import com.github.intellij.gno.psi.*;

public class GoAssignOpImpl extends GoCompositeElementImpl implements GoAssignOp {

    public GoAssignOpImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitAssignOp(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GoVisitor) accept((GoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @Nullable
    public PsiElement getAssign() {
        return findChildByType(ASSIGN);
    }

    @Override
    @Nullable
    public PsiElement getBitAndAssign() {
        return findChildByType(BIT_AND_ASSIGN);
    }

    @Override
    @Nullable
    public PsiElement getBitClearAssign() {
        return findChildByType(BIT_CLEAR_ASSIGN);
    }

    @Override
    @Nullable
    public PsiElement getBitOrAssign() {
        return findChildByType(BIT_OR_ASSIGN);
    }

    @Override
    @Nullable
    public PsiElement getBitXorAssign() {
        return findChildByType(BIT_XOR_ASSIGN);
    }

    @Override
    @Nullable
    public PsiElement getMinusAssign() {
        return findChildByType(MINUS_ASSIGN);
    }

    @Override
    @Nullable
    public PsiElement getMulAssign() {
        return findChildByType(MUL_ASSIGN);
    }

    @Override
    @Nullable
    public PsiElement getPlusAssign() {
        return findChildByType(PLUS_ASSIGN);
    }

    @Override
    @Nullable
    public PsiElement getQuotientAssign() {
        return findChildByType(QUOTIENT_ASSIGN);
    }

    @Override
    @Nullable
    public PsiElement getRemainderAssign() {
        return findChildByType(REMAINDER_ASSIGN);
    }

    @Override
    @Nullable
    public PsiElement getShiftLeftAssign() {
        return findChildByType(SHIFT_LEFT_ASSIGN);
    }

    @Override
    @Nullable
    public PsiElement getShiftRightAssign() {
        return findChildByType(SHIFT_RIGHT_ASSIGN);
    }

}
