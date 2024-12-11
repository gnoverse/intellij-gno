package com.github.intellij.gno.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import static com.github.intellij.gno.GoTypes.*;
import com.github.intellij.gno.stubs.GoReceiverStub;
import com.github.intellij.gno.psi.*;
import com.intellij.psi.ResolveState;
import com.intellij.psi.stubs.IStubElementType;

public class GoReceiverImpl extends GoNamedElementImpl<GoReceiverStub> implements GoReceiver {

    public GoReceiverImpl(GoReceiverStub stub, IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public GoReceiverImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitReceiver(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GoVisitor) accept((GoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @Nullable
    public GoType getType() {
        return GoPsiTreeUtil.getStubChildOfType(this, GoType.class);
    }

    @Override
    @Nullable
    public PsiElement getComma() {
        return findChildByType(COMMA);
    }

    @Override
    @NotNull
    public PsiElement getLparen() {
        return notNullChild(findChildByType(LPAREN));
    }

    @Override
    @Nullable
    public PsiElement getRparen() {
        return findChildByType(RPAREN);
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

}
