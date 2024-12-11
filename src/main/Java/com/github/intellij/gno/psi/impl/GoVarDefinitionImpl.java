package com.github.intellij.gno.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import static com.github.intellij.gno.GoTypes.*;
import com.github.intellij.gno.stubs.GoVarDefinitionStub;
import com.github.intellij.gno.psi.*;
import com.intellij.psi.PsiReference;
import com.intellij.psi.ResolveState;
import com.intellij.psi.stubs.IStubElementType;

public class GoVarDefinitionImpl extends GoNamedElementImpl<GoVarDefinitionStub> implements GoVarDefinition {

    public GoVarDefinitionImpl(GoVarDefinitionStub stub, IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public GoVarDefinitionImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitVarDefinition(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GoVisitor) accept((GoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @NotNull
    public PsiElement getIdentifier() {
        return notNullChild(findChildByType(IDENTIFIER));
    }

    @Nullable
    public GoType getGoTypeInner(ResolveState context) {
        return GoPsiImplUtil.getGoTypeInner(this, context);
    }

    @Nullable
    public PsiReference getReference() {
        return GoPsiImplUtil.getReference(this);
    }

    @Nullable
    public GoExpression getValue() {
        return GoPsiImplUtil.getValue(this);
    }

}