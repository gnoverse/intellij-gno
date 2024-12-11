package com.github.intellij.gno.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import static com.github.intellij.gno.GoTypes.*;
import com.github.intellij.gno.stubs.GoFunctionDeclarationStub;
import com.github.intellij.gno.psi.*;
import com.intellij.psi.stubs.IStubElementType;

public class GoFunctionDeclarationImpl extends GoFunctionOrMethodDeclarationImpl<GoFunctionDeclarationStub> implements GoFunctionDeclaration {

    public GoFunctionDeclarationImpl(GoFunctionDeclarationStub stub, IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public GoFunctionDeclarationImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitFunctionDeclaration(this);
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
    public GoSignature getSignature() {
        return GoPsiTreeUtil.getStubChildOfType(this, GoSignature.class);
    }

    @Override
    @NotNull
    public PsiElement getFunc() {
        return notNullChild(findChildByType(FUNC));
    }

    @Override
    @NotNull
    public PsiElement getIdentifier() {
        return notNullChild(findChildByType(IDENTIFIER));
    }

}