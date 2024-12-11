package com.github.intellij.gno.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import com.github.intellij.gno.stubs.GoAnonymousFieldDefinitionStub;
import com.github.intellij.gno.psi.*;
import com.intellij.psi.ResolveState;
import com.intellij.psi.stubs.IStubElementType;

public class GoAnonymousFieldDefinitionImpl extends GoNamedElementImpl<GoAnonymousFieldDefinitionStub> implements GoAnonymousFieldDefinition {

    public GoAnonymousFieldDefinitionImpl(GoAnonymousFieldDefinitionStub stub, IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public GoAnonymousFieldDefinitionImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitAnonymousFieldDefinition(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GoVisitor) accept((GoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @NotNull
    public GoType getType() {
        return notNullChild(GoPsiTreeUtil.getStubChildOfType(this, GoType.class));
    }

    @Nullable
    public PsiElement getIdentifier() {
        return GoPsiImplUtil.getIdentifier(this);
    }

    @Nullable
    public String getName() {
        return GoPsiImplUtil.getName(this);
    }

    @Nullable
    public GoTypeReferenceExpression getTypeReferenceExpression() {
        return GoPsiImplUtil.getTypeReferenceExpression(this);
    }

    @Nullable
    public GoType getGoTypeInner(ResolveState context) {
        return GoPsiImplUtil.getGoTypeInner(this, context);
    }

}