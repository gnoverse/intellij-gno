package com.github.intellij.gno.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import static com.github.intellij.gno.GoTypes.*;
import com.github.intellij.gno.stubs.GoConstDefinitionStub;
import com.github.intellij.gno.psi.*;
import com.intellij.psi.ResolveState;
import com.intellij.psi.stubs.IStubElementType;

public class GoConstDefinitionImpl extends GoNamedElementImpl<GoConstDefinitionStub> implements GoConstDefinition {

    public GoConstDefinitionImpl(GoConstDefinitionStub stub, IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public GoConstDefinitionImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitConstDefinition(this);
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
    public GoExpression getValue() {
        return GoPsiImplUtil.getValue(this);
    }

}