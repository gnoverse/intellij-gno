package com.github.intellij.gno.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import static com.github.intellij.gno.GoTypes.*;
import com.github.intellij.gno.psi.*;

public class GoTypeCaseClauseImpl extends GoCompositeElementImpl implements GoTypeCaseClause {

    public GoTypeCaseClauseImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitTypeCaseClause(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GoVisitor) accept((GoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @NotNull
    public List<GoStatement> getStatementList() {
        return GoPsiTreeUtil.getChildrenOfTypeAsList(this, GoStatement.class);
    }

    @Override
    @Nullable
    public GoType getType() {
        return GoPsiTreeUtil.getChildOfType(this, GoType.class);
    }

    @Override
    @Nullable
    public PsiElement getColon() {
        return findChildByType(COLON);
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