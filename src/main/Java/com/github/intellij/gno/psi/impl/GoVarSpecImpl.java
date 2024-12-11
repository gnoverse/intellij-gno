package com.github.intellij.gno.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import static com.github.intellij.gno.GoTypes.*;
import com.github.intellij.gno.stubs.GoVarSpecStub;
import com.github.intellij.gno.psi.*;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.psi.stubs.IStubElementType;

public class GoVarSpecImpl extends GoStubbedElementImpl<GoVarSpecStub> implements GoVarSpec {

    public GoVarSpecImpl(GoVarSpecStub stub, IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public GoVarSpecImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitVarSpec(this);
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
    @Nullable
    public GoType getType() {
        return GoPsiTreeUtil.getStubChildOfType(this, GoType.class);
    }

    @Override
    @NotNull
    public List<GoVarDefinition> getVarDefinitionList() {
        return GoPsiTreeUtil.getStubChildrenOfTypeAsList(this, GoVarDefinition.class);
    }

    @Override
    @Nullable
    public PsiElement getAssign() {
        return findChildByType(ASSIGN);
    }

    public boolean processDeclarations(@NotNull PsiScopeProcessor processor, @NotNull ResolveState state, PsiElement lastParent, @NotNull PsiElement place) {
        return GoPsiImplUtil.processDeclarations(this, processor, state, lastParent, place);
    }

    public void deleteDefinition(GoVarDefinition definitionToDelete) {
        GoPsiImplUtil.deleteDefinition(this, definitionToDelete);
    }

    @NotNull
    public List<GoExpression> getRightExpressionsList() {
        return GoPsiImplUtil.getRightExpressionsList(this);
    }

}