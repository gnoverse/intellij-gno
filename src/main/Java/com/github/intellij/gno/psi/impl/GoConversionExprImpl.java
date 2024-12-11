package com.github.intellij.gno.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import static com.github.intellij.gno.GoTypes.*;
import com.github.intellij.gno.psi.*;

public class GoConversionExprImpl extends GoBinaryExprImpl implements GoConversionExpr {

    public GoConversionExprImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitConversionExpr(this);
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
    @NotNull
    public GoType getType() {
        return notNullChild(GoPsiTreeUtil.getChildOfType(this, GoType.class));
    }

    @Override
    @Nullable
    public PsiElement getComma() {
        return findChildByType(COMMA);
    }

    @Override
    @NotNull
    public PsiElement getLparen() {
        return notNullChild(findChildByType(LPAREN));
    }

    @Override
    @Nullable
    public PsiElement getRparen() {
        return findChildByType(RPAREN);
    }

}
