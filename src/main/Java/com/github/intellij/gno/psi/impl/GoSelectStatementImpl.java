package com.github.intellij.gno.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import static com.github.intellij.gno.GoTypes.*;
import com.github.intellij.gno.psi.*;

public class GoSelectStatementImpl extends GoStatementImpl implements GoSelectStatement {

    public GoSelectStatementImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitSelectStatement(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GoVisitor) accept((GoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @NotNull
    public List<GoCommClause> getCommClauseList() {
        return GoPsiTreeUtil.getChildrenOfTypeAsList(this, GoCommClause.class);
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
    @NotNull
    public PsiElement getSelect() {
        return notNullChild(findChildByType(SELECT));
    }

}