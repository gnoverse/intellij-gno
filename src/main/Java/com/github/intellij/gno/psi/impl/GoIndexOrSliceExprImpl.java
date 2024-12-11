package com.github.intellij.gno.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import static com.github.intellij.gno.GoTypes.*;
import com.github.intellij.gno.psi.*;
import com.intellij.openapi.util.Trinity;

public class GoIndexOrSliceExprImpl extends GoExpressionImpl implements GoIndexOrSliceExpr {

    public GoIndexOrSliceExprImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitIndexOrSliceExpr(this);
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
    public PsiElement getLbrack() {
        return notNullChild(findChildByType(LBRACK));
    }

    @Override
    @Nullable
    public PsiElement getRbrack() {
        return findChildByType(RBRACK);
    }

    @Nullable
    public GoExpression getExpression() {
        return GoPsiImplUtil.getExpression(this);
    }

    @NotNull
    public Trinity<GoExpression, GoExpression, GoExpression> getIndices() {
        return GoPsiImplUtil.getIndices(this);
    }

}