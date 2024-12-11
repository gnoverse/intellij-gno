package com.github.intellij.gno.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import com.github.intellij.gno.psi.*;

public class GoSwitchStatementImpl extends GoStatementImpl implements GoSwitchStatement {

    public GoSwitchStatementImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitSwitchStatement(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GoVisitor) accept((GoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @Nullable
    public GoSwitchStart getSwitchStart() {
        return GoPsiTreeUtil.getChildOfType(this, GoSwitchStart.class);
    }

    @Override
    @Nullable
    public GoSwitchStatement getSwitchStatement() {
        return GoPsiTreeUtil.getChildOfType(this, GoSwitchStatement.class);
    }

}