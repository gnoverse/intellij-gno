package com.github.intellij.gno.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import com.github.intellij.gno.psi.*;

public class GoBuiltinCallExprImpl extends GoExpressionImpl implements GoBuiltinCallExpr {

    public GoBuiltinCallExprImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitBuiltinCallExpr(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GoVisitor) accept((GoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @Nullable
    public GoBuiltinArgumentList getBuiltinArgumentList() {
        return GoPsiTreeUtil.getChildOfType(this, GoBuiltinArgumentList.class);
    }

    @Override
    @NotNull
    public GoReferenceExpression getReferenceExpression() {
        return notNullChild(GoPsiTreeUtil.getChildOfType(this, GoReferenceExpression.class));
    }

}