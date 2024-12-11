package com.github.intellij.gno.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import com.github.intellij.gno.stubs.GoTypeStub;
import com.github.intellij.gno.psi.*;
import com.intellij.psi.stubs.IStubElementType;

public class GoTypeImpl extends GoStubbedElementImpl<GoTypeStub> implements GoType {

    public GoTypeImpl(GoTypeStub stub, IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public GoTypeImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitType(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GoVisitor) accept((GoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @Nullable
    public GoTypeReferenceExpression getTypeReferenceExpression() {
        return GoPsiTreeUtil.getChildOfType(this, GoTypeReferenceExpression.class);
    }

    @NotNull
    public GoType getUnderlyingType() {
        return GoPsiImplUtil.getUnderlyingType(this);
    }

    public boolean shouldGoDeeper() {
        return GoPsiImplUtil.shouldGoDeeper(this);
    }

}