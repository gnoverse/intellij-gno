package com.github.intellij.gno.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import com.github.intellij.gno.psi.*;

public class GoValueImpl extends GoCompositeElementImpl implements GoValue {

    public GoValueImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitValue(this);
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
    public GoLiteralValue getLiteralValue() {
        return GoPsiTreeUtil.getChildOfType(this, GoLiteralValue.class);
    }

}