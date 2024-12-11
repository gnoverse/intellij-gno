package com.github.intellij.gno.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import static com.github.intellij.gno.GoTypes.*;
import com.github.intellij.gno.psi.*;

public class GoTypeSwitchStatementImpl extends GoSwitchStatementImpl implements GoTypeSwitchStatement {

    public GoTypeSwitchStatementImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitTypeSwitchStatement(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GoVisitor) accept((GoVisitor)visitor);
        else super.accept(visitor);
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
    public List<GoTypeCaseClause> getTypeCaseClauseList() {
        return GoPsiTreeUtil.getChildrenOfTypeAsList(this, GoTypeCaseClause.class);
    }

    @Override
    @NotNull
    public GoTypeSwitchGuard getTypeSwitchGuard() {
        return notNullChild(GoPsiTreeUtil.getChildOfType(this, GoTypeSwitchGuard.class));
    }

    @Override
    @Nullable
    public PsiElement getLbrace() {
        return findChildByType(LBRACE);
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