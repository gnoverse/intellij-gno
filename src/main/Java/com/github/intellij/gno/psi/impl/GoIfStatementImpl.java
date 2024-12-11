package com.github.intellij.gno.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import static com.github.intellij.gno.GoTypes.*;
import com.github.intellij.gno.psi.*;

public class GoIfStatementImpl extends GoStatementImpl implements GoIfStatement {

    public GoIfStatementImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitIfStatement(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GoVisitor) accept((GoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @Nullable
    public GoElseStatement getElseStatement() {
        return GoPsiTreeUtil.getChildOfType(this, GoElseStatement.class);
    }

    @Override
    @Nullable
    public GoExpression getExpression() {
        return GoPsiTreeUtil.getChildOfType(this, GoExpression.class);
    }

    @Override
    @Nullable
    public GoStatement getStatement() {
        return GoPsiTreeUtil.getChildOfType(this, GoStatement.class);
    }

    @Override
    @Nullable
    public PsiElement getSemicolon() {
        return findChildByType(SEMICOLON);
    }

    @Override
    @NotNull
    public PsiElement getIf() {
        return notNullChild(findChildByType(IF));
    }

}