package com.github.intellij.gno.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import static com.github.intellij.gno.GoTypes.*;
import com.github.intellij.gno.stubs.GoConstSpecStub;
import com.github.intellij.gno.psi.*;
import com.intellij.psi.stubs.IStubElementType;

public class GoConstSpecImpl extends GoStubbedElementImpl<GoConstSpecStub> implements GoConstSpec {

    public GoConstSpecImpl(GoConstSpecStub stub, IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public GoConstSpecImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitConstSpec(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GoVisitor) accept((GoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @NotNull
    public List<GoConstDefinition> getConstDefinitionList() {
        return GoPsiTreeUtil.getStubChildrenOfTypeAsList(this, GoConstDefinition.class);
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
    @Nullable
    public PsiElement getAssign() {
        return findChildByType(ASSIGN);
    }

    public void deleteDefinition(GoConstDefinition definitionToDelete) {
        GoPsiImplUtil.deleteDefinition(this, definitionToDelete);
    }

}