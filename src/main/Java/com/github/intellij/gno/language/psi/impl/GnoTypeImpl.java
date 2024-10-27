package com.github.intellij.gno.language.psi.impl;


import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.language.psi.GnoPsiTreeUtil;
import com.github.intellij.gno.language.stubs.GnoTypeStub;
import com.github.intellij.gno.language.psi.*;
import com.intellij.psi.stubs.IStubElementType;

public class GnoTypeImpl extends GnoStubbedElementImpl<GnoTypeStub> implements GnoType {

    public GnoTypeImpl(GnoTypeStub stub, IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public GnoTypeImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GnoVisitor visitor) {
        visitor.visitType(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GnoVisitor) accept((GnoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @Nullable
    public GnoTypeReferenceExpression getTypeReferenceExpression() {
        return GnoPsiTreeUtil.getChildOfType(this, GnoTypeReferenceExpression.class);
    }

    @NotNull
    public GnoType getUnderlyingType() {
        return GnoPsiImplUtil.getUnderlyingType(this);
    }

    @Override
    public boolean shouldGoDeeper() {
        return false;
    }

    public boolean shouldGnoDeeper() {
        return GnoPsiImplUtil.shouldGnoDeeper(this);
    }
}