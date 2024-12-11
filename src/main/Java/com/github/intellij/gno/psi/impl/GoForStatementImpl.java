package com.github.intellij.gno.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import static com.github.intellij.gno.GoTypes.*;
import com.github.intellij.gno.psi.*;

public class GoForStatementImpl extends GoStatementImpl implements GoForStatement {

    public GoForStatementImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitForStatement(this);
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
    public GoForClause getForClause() {
        return GoPsiTreeUtil.getChildOfType(this, GoForClause.class);
    }

    @Override
    @Nullable
    public GoRangeClause getRangeClause() {
        return GoPsiTreeUtil.getChildOfType(this, GoRangeClause.class);
    }

    @Override
    @NotNull
    public PsiElement getFor() {
        return notNullChild(findChildByType(FOR));
    }

}