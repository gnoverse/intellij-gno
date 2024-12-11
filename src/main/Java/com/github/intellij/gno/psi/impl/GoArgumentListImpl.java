package com.github.intellij.gno.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import static com.github.intellij.gno.GoTypes.*;
import com.github.intellij.gno.psi.*;

public class GoArgumentListImpl extends GoCompositeElementImpl implements GoArgumentList {

    public GoArgumentListImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitArgumentList(this);
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
    public PsiElement getLparen() {
        return notNullChild(findChildByType(LPAREN));
    }

    @Override
    @Nullable
    public PsiElement getRparen() {
        return findChildByType(RPAREN);
    }

    @Override
    @Nullable
    public PsiElement getTripleDot() {
        return findChildByType(TRIPLE_DOT);
    }

}