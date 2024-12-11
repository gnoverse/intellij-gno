package com.github.intellij.gno.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import static com.github.intellij.gno.GoTypes.*;
import com.github.intellij.gno.stubs.GoLabelDefinitionStub;
import com.github.intellij.gno.psi.*;
import com.intellij.psi.stubs.IStubElementType;

public class GoLabelDefinitionImpl extends GoNamedElementImpl<GoLabelDefinitionStub> implements GoLabelDefinition {

    public GoLabelDefinitionImpl(GoLabelDefinitionStub stub, IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public GoLabelDefinitionImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitLabelDefinition(this);
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

}