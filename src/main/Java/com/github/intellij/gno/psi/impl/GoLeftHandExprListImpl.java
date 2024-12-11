package com.github.intellij.gno.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import com.github.intellij.gno.psi.*;

public class GoLeftHandExprListImpl extends GoCompositeElementImpl implements GoLeftHandExprList {

    public GoLeftHandExprListImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitLeftHandExprList(this);
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

}