package com.github.intellij.gno.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import static com.github.intellij.gno.GoTypes.*;
import com.github.intellij.gno.stubs.GoParamDefinitionStub;
import com.github.intellij.gno.psi.*;
import com.intellij.psi.stubs.IStubElementType;

public class GoParamDefinitionImpl extends GoNamedElementImpl<GoParamDefinitionStub> implements GoParamDefinition {

    public GoParamDefinitionImpl(GoParamDefinitionStub stub, IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public GoParamDefinitionImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitParamDefinition(this);
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

    public boolean isVariadic() {
        return GoPsiImplUtil.isVariadic(this);
    }

}