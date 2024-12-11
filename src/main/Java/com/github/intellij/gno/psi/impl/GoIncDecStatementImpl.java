package com.github.intellij.gno.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import static com.github.intellij.gno.GoTypes.*;
import com.github.intellij.gno.psi.*;

public class GoIncDecStatementImpl extends GoStatementImpl implements GoIncDecStatement {

    public GoIncDecStatementImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitIncDecStatement(this);
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
    @Nullable
    public PsiElement getMinusMinus() {
        return findChildByType(MINUS_MINUS);
    }

    @Override
    @Nullable
    public PsiElement getPlusPlus() {
        return findChildByType(PLUS_PLUS);
    }

}