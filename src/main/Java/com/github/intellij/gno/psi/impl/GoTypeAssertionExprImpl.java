package com.github.intellij.gno.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import static com.github.intellij.gno.GoTypes.*;
import com.github.intellij.gno.psi.*;

public class GoTypeAssertionExprImpl extends GoExpressionImpl implements GoTypeAssertionExpr {

    public GoTypeAssertionExprImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitTypeAssertionExpr(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GoVisitor) accept((GoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @NotNull
    public GoExpression getExpression() {
        return notNullChild(GoPsiTreeUtil.getChildOfType(this, GoExpression.class));
    }

    @Override
    @NotNull
    public GoType getType() {
        return notNullChild(GoPsiTreeUtil.getChildOfType(this, GoType.class));
    }

    @Override
    @NotNull
    public PsiElement getDot() {
        return notNullChild(findChildByType(DOT));
    }

    @Override
    @NotNull
    public PsiElement getLparen() {
        return notNullChild(findChildByType(LPAREN));
    }

    @Override
    @NotNull
    public PsiElement getRparen() {
        return notNullChild(findChildByType(RPAREN));
    }

}