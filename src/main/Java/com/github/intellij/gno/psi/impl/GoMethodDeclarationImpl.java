package com.github.intellij.gno.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import static com.github.intellij.gno.GoTypes.*;
import com.github.intellij.gno.stubs.GoMethodDeclarationStub;
import com.github.intellij.gno.psi.*;
import com.intellij.psi.stubs.IStubElementType;

public class GoMethodDeclarationImpl extends GoFunctionOrMethodDeclarationImpl<GoMethodDeclarationStub> implements GoMethodDeclaration {

    public GoMethodDeclarationImpl(GoMethodDeclarationStub stub, IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public GoMethodDeclarationImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitMethodDeclaration(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GoVisitor) accept((GoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @Nullable
    public GoBlock getBlock() {
        return GoPsiTreeUtil.getChildOfType(this, GoBlock.class);
    }

    @Override
    @Nullable
    public GoReceiver getReceiver() {
        return GoPsiTreeUtil.getStubChildOfType(this, GoReceiver.class);
    }

    @Override
    @Nullable
    public GoSignature getSignature() {
        return GoPsiTreeUtil.getStubChildOfType(this, GoSignature.class);
    }

    @Override
    @NotNull
    public PsiElement getFunc() {
        return notNullChild(findChildByType(FUNC));
    }

    @Override
    @Nullable
    public PsiElement getIdentifier() {
        return findChildByType(IDENTIFIER);
    }

    @Nullable
    public GoType getReceiverType() {
        return GoPsiImplUtil.getReceiverType(this);
    }

}