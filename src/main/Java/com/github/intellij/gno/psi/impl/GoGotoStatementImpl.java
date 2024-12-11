package com.github.intellij.gno.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import static com.github.intellij.gno.GoTypes.*;
import com.github.intellij.gno.psi.*;

public class GoGotoStatementImpl extends GoStatementImpl implements GoGotoStatement {

    public GoGotoStatementImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitGotoStatement(this);
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
    public PsiElement getGoto() {
        return notNullChild(findChildByType(GOTO));
    }

}