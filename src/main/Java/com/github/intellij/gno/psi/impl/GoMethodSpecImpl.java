package com.github.intellij.gno.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import static com.github.intellij.gno.GoTypes.*;
import com.github.intellij.gno.stubs.GoMethodSpecStub;
import com.github.intellij.gno.psi.*;
import com.intellij.psi.ResolveState;
import com.intellij.psi.stubs.IStubElementType;

public class GoMethodSpecImpl extends GoNamedElementImpl<GoMethodSpecStub> implements GoMethodSpec {

    public GoMethodSpecImpl(GoMethodSpecStub stub, IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public GoMethodSpecImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitMethodSpec(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GoVisitor) accept((GoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @Nullable
    public GoSignature getSignature() {
        return GoPsiTreeUtil.getStubChildOfType(this, GoSignature.class);
    }

    @Override
    @Nullable
    public GoTypeReferenceExpression getTypeReferenceExpression() {
        return GoPsiTreeUtil.getChildOfType(this, GoTypeReferenceExpression.class);
    }

    @Override
    @Nullable
    public PsiElement getIdentifier() {
        return findChildByType(IDENTIFIER);
    }

    @Nullable
    public GoType getGoTypeInner(ResolveState context) {
        return GoPsiImplUtil.getGoTypeInner(this, context);
    }

    @Nullable
    public String getName() {
        return GoPsiImplUtil.getName(this);
    }

}