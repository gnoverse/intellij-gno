package com.github.intellij.gno.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import com.github.intellij.gno.psi.*;

public class GoBinaryExprImpl extends GoExpressionImpl implements GoBinaryExpr {

    public GoBinaryExprImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitBinaryExpr(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GoVisitor) accept((GoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @NotNull
    public List<GoExpression> getExpressionList() {
        return GoPsiTreeUtil.getChildrenOfTypeAsList(this, GoExpression.class);
    }

    @Override
    @NotNull
    public GoExpression getLeft() {
        List<GoExpression> p1 = getExpressionList();
        return p1.get(0);
    }

    @Override
    @Nullable
    public GoExpression getRight() {
        List<GoExpression> p1 = getExpressionList();
        return p1.size() < 2 ? null : p1.get(1);
    }

    @Nullable
    public PsiElement getOperator() {
        return GoPsiImplUtil.getOperator(this);
    }

}