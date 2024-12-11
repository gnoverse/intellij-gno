package com.github.intellij.gno.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import com.github.intellij.gno.psi.*;

public class GoCallExprImpl extends GoExpressionImpl implements GoCallExpr {

    public GoCallExprImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitCallExpr(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GoVisitor) accept((GoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @NotNull
    public GoArgumentList getArgumentList() {
        return notNullChild(GoPsiTreeUtil.getChildOfType(this, GoArgumentList.class));
    }

    @Override
    @NotNull
    public GoExpression getExpression() {
        return notNullChild(GoPsiTreeUtil.getChildOfType(this, GoExpression.class));
    }

}