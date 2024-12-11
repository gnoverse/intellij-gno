package com.github.intellij.gno.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import static com.github.intellij.gno.GoTypes.*;
import com.github.intellij.gno.psi.*;

public class GoLabeledStatementImpl extends GoStatementImpl implements GoLabeledStatement {

    public GoLabeledStatementImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitLabeledStatement(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GoVisitor) accept((GoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @NotNull
    public GoLabelDefinition getLabelDefinition() {
        return notNullChild(GoPsiTreeUtil.getChildOfType(this, GoLabelDefinition.class));
    }

    @Override
    @Nullable
    public GoStatement getStatement() {
        return GoPsiTreeUtil.getChildOfType(this, GoStatement.class);
    }

    @Override
    @NotNull
    public PsiElement getColon() {
        return notNullChild(findChildByType(COLON));
    }

}