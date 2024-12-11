package com.github.intellij.gno.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import com.github.intellij.gno.stubs.GoTypeSpecStub;
import com.github.intellij.gno.psi.*;
import com.intellij.psi.ResolveState;
import com.intellij.psi.stubs.IStubElementType;

public class GoTypeSpecImpl extends GoNamedElementImpl<GoTypeSpecStub> implements GoTypeSpec {

    public GoTypeSpecImpl(GoTypeSpecStub stub, IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public GoTypeSpecImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitTypeSpec(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GoVisitor) accept((GoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @NotNull
    public GoSpecType getSpecType() {
        return notNullChild(GoPsiTreeUtil.getStubChildOfType(this, GoSpecType.class));
    }

    @Nullable
    public GoType getGoTypeInner(ResolveState context) {
        return GoPsiImplUtil.getGoTypeInner(this, context);
    }

    @NotNull
    public List<GoMethodDeclaration> getMethods() {
        return GoPsiImplUtil.getMethods(this);
    }

    public boolean shouldGoDeeper() {
        return GoPsiImplUtil.shouldGoDeeper(this);
    }

    @Override
    @NotNull
    public PsiElement getIdentifier() {
        GoSpecType p1 = getSpecType();
        return p1.getIdentifier();
    }

}