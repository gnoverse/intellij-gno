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

public class GoRecvStatementImpl extends GoVarSpecImpl implements GoRecvStatement {

    public GoRecvStatementImpl(GoVarSpecStub stub, IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public GoRecvStatementImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitRecvStatement(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GoVisitor) accept((GoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @NotNull
    public List<GoExpression> getExpressionList() {
        return GoPsiTreeUtil.getChildrenOfTypeAsList(this, GoExpression.class);
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

    @Nullable
    public GoExpression getRecvExpression() {
        return GoPsiImplUtil.getRecvExpression(this);
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