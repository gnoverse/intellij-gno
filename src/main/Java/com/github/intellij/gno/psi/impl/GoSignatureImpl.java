package com.github.intellij.gno.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import com.github.intellij.gno.stubs.GoSignatureStub;
import com.github.intellij.gno.psi.*;
import com.intellij.psi.stubs.IStubElementType;

public class GoSignatureImpl extends GoStubbedElementImpl<GoSignatureStub> implements GoSignature {

    public GoSignatureImpl(GoSignatureStub stub, IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public GoSignatureImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitSignature(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GoVisitor) accept((GoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @NotNull
    public GoParameters getParameters() {
        return notNullChild(GoPsiTreeUtil.getStubChildOfType(this, GoParameters.class));
    }

    @Override
    @Nullable
    public GoResult getResult() {
        return GoPsiTreeUtil.getStubChildOfType(this, GoResult.class);
    }

}