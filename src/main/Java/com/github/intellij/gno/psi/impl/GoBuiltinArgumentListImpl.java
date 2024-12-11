package com.github.intellij.gno.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import com.github.intellij.gno.psi.*;

public class GoBuiltinArgumentListImpl extends GoArgumentListImpl implements GoBuiltinArgumentList {

    public GoBuiltinArgumentListImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitBuiltinArgumentList(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GoVisitor) accept((GoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @Nullable
    public GoType getType() {
        return GoPsiTreeUtil.getChildOfType(this, GoType.class);
    }

}