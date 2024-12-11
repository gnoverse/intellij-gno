package com.github.intellij.gno.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import com.github.intellij.gno.psi.*;

public class GoSimpleStatementImpl extends GoStatementImpl implements GoSimpleStatement {

    public GoSimpleStatementImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitSimpleStatement(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GoVisitor) accept((GoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @Nullable
    public GoLeftHandExprList getLeftHandExprList() {
        return GoPsiTreeUtil.getChildOfType(this, GoLeftHandExprList.class);
    }

    @Override
    @Nullable
    public GoShortVarDeclaration getShortVarDeclaration() {
        return GoPsiTreeUtil.getChildOfType(this, GoShortVarDeclaration.class);
    }

    @Override
    @Nullable
    public GoStatement getStatement() {
        return GoPsiTreeUtil.getChildOfType(this, GoStatement.class);
    }

}