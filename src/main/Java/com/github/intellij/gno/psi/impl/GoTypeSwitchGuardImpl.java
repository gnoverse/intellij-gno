package com.github.intellij.gno.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import static com.github.intellij.gno.GoTypes.*;
import com.github.intellij.gno.psi.*;

public class GoTypeSwitchGuardImpl extends GoCompositeElementImpl implements GoTypeSwitchGuard {

    public GoTypeSwitchGuardImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitTypeSwitchGuard(this);
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
    public GoTypeGuard getTypeGuard() {
        return notNullChild(GoPsiTreeUtil.getChildOfType(this, GoTypeGuard.class));
    }

    @Override
    @Nullable
    public GoVarDefinition getVarDefinition() {
        return GoPsiTreeUtil.getChildOfType(this, GoVarDefinition.class);
    }

    @Override
    @NotNull
    public PsiElement getDot() {
        return notNullChild(findChildByType(DOT));
    }

    @Override
    @Nullable
    public PsiElement getVarAssign() {
        return findChildByType(VAR_ASSIGN);
    }

}