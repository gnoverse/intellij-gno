package com.github.intellij.gno.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import static com.github.intellij.gno.GoTypes.*;
import com.github.intellij.gno.psi.*;

public class GoCommCaseImpl extends GoCompositeElementImpl implements GoCommCase {

    public GoCommCaseImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitCommCase(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GoVisitor) accept((GoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @Nullable
    public GoRecvStatement getRecvStatement() {
        return GoPsiTreeUtil.getChildOfType(this, GoRecvStatement.class);
    }

    @Override
    @Nullable
    public GoSendStatement getSendStatement() {
        return GoPsiTreeUtil.getChildOfType(this, GoSendStatement.class);
    }

    @Override
    @Nullable
    public PsiElement getCase() {
        return findChildByType(CASE);
    }

    @Override
    @Nullable
    public PsiElement getDefault() {
        return findChildByType(DEFAULT);
    }

}
