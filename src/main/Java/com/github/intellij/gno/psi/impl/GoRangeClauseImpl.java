package com.github.intellij.gno.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import static com.github.intellij.gno.GoTypes.*;
import com.github.intellij.gno.psi.*;
import com.github.intellij.gno.stubs.GoVarSpecStub;
import com.intellij.psi.stubs.IStubElementType;

public class GoRangeClauseImpl extends GoVarSpecImpl implements GoRangeClause {

    public GoRangeClauseImpl(GoVarSpecStub stub, IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public GoRangeClauseImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitRangeClause(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GoVisitor) accept((GoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @NotNull
    public List<GoVarDefinition> getVarDefinitionList() {
        return GoPsiTreeUtil.getStubChildrenOfTypeAsList(this, GoVarDefinition.class);
    }

    @Override
    @Nullable
    public PsiElement getVarAssign() {
        return findChildByType(VAR_ASSIGN);
    }

    @Override
    @Nullable
    public PsiElement getRange() {
        return findChildByType(RANGE);
    }

    @Nullable
    public GoExpression getRangeExpression() {
        return GoPsiImplUtil.getRangeExpression(this);
    }

    @NotNull
    public List<GoExpression> getLeftExpressionsList() {
        return GoPsiImplUtil.getLeftExpressionsList(this);
    }

    @NotNull
    public List<GoExpression> getRightExpressionsList() {
        return GoPsiImplUtil.getRightExpressionsList(this);
    }

}