package com.github.intellij.gno.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import static com.github.intellij.gno.GoTypes.*;
import com.github.intellij.gno.psi.*;

public class GoAddExprImpl extends GoBinaryExprImpl implements GoAddExpr {

    public GoAddExprImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitAddExpr(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GoVisitor) accept((GoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @Nullable
    public PsiElement getBitOr() {
        return findChildByType(BIT_OR);
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
    public PsiElement getPlus() {
        return findChildByType(PLUS);
    }

}