package com.github.intellij.gno.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import com.github.intellij.gno.psi.*;

public class GoTagImpl extends GoCompositeElementImpl implements GoTag {

    public GoTagImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitTag(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GoVisitor) accept((GoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @NotNull
    public GoStringLiteral getStringLiteral() {
        return notNullChild(GoPsiTreeUtil.getChildOfType(this, GoStringLiteral.class));
    }

}