package com.github.intellij.gno.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import com.github.intellij.gno.psi.*;

public class GoLiteralTypeExprImpl extends GoExpressionImpl implements GoLiteralTypeExpr {

    public GoLiteralTypeExprImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitLiteralTypeExpr(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GoVisitor) accept((GoVisitor)visitor);
        else super.accept(visitor);
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