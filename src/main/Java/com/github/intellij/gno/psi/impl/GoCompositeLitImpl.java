package com.github.intellij.gno.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import com.github.intellij.gno.psi.*;

public class GoCompositeLitImpl extends GoExpressionImpl implements GoCompositeLit {

    public GoCompositeLitImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitCompositeLit(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GoVisitor) accept((GoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @Nullable
    public GoLiteralValue getLiteralValue() {
        return GoPsiTreeUtil.getChildOfType(this, GoLiteralValue.class);
    }

    @Override
    @Nullable
    public GoType getType() {
        return GoPsiTreeUtil.getChildOfType(this, GoType.class);
    }

    @Override
    @Nullable
    public GoTypeReferenceExpression getTypeReferenceExpression() {
        return GoPsiTreeUtil.getChildOfType(this, GoTypeReferenceExpression.class);
    }

}