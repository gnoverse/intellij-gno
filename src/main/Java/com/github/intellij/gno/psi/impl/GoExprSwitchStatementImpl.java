package com.github.intellij.gno.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import static com.github.intellij.gno.GoTypes.*;
import com.github.intellij.gno.psi.*;

public class GoExprSwitchStatementImpl extends GoSwitchStatementImpl implements GoExprSwitchStatement {

    public GoExprSwitchStatementImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitExprSwitchStatement(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GoVisitor) accept((GoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @NotNull
    public List<GoExprCaseClause> getExprCaseClauseList() {
        return GoPsiTreeUtil.getChildrenOfTypeAsList(this, GoExprCaseClause.class);
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
    @NotNull
    public GoSwitchStart getSwitchStart() {
        return notNullChild(GoPsiTreeUtil.getChildOfType(this, GoSwitchStart.class));
    }

    @Override
    @NotNull
    public PsiElement getLbrace() {
        return notNullChild(findChildByType(LBRACE));
    }

    @Override
    @Nullable
    public PsiElement getRbrace() {
        return findChildByType(RBRACE);
    }

    @Override
    @Nullable
    public PsiElement getSemicolon() {
        return findChildByType(SEMICOLON);
    }

}
