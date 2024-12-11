package com.github.intellij.gno.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import static com.github.intellij.gno.GoTypes.*;
import com.github.intellij.gno.psi.*;

public class GoBreakStatementImpl extends GoStatementImpl implements GoBreakStatement {

    public GoBreakStatementImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitBreakStatement(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GoVisitor) accept((GoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @Nullable
    public GoLabelRef getLabelRef() {
        return GoPsiTreeUtil.getChildOfType(this, GoLabelRef.class);
    }

    @Override
    @NotNull
    public PsiElement getBreak() {
        return notNullChild(findChildByType(BREAK));
    }

}