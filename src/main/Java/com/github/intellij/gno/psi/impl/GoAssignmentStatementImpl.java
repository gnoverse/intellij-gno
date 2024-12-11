package com.github.intellij.gno.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import com.github.intellij.gno.psi.*;

public class GoAssignmentStatementImpl extends GoStatementImpl implements GoAssignmentStatement {

    public GoAssignmentStatementImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitAssignmentStatement(this);
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
    public GoLeftHandExprList getLeftHandExprList() {
        return notNullChild(GoPsiTreeUtil.getChildOfType(this, GoLeftHandExprList.class));
    }

    @Override
    @NotNull
    public GoAssignOp getAssignOp() {
        return notNullChild(GoPsiTreeUtil.getChildOfType(this, GoAssignOp.class));
    }

}