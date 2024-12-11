package com.github.intellij.gno.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import static com.github.intellij.gno.GoTypes.*;
import com.github.intellij.gno.psi.*;
import com.github.intellij.gno.stubs.GoTypeStub;
import com.intellij.psi.stubs.IStubElementType;

public class GoArrayOrSliceTypeImpl extends GoTypeImpl implements GoArrayOrSliceType {

    public GoArrayOrSliceTypeImpl(GoTypeStub stub, IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public GoArrayOrSliceTypeImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitArrayOrSliceType(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GoVisitor) accept((GoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @Nullable
    public GoExpression getExpression() {
        return GoPsiTreeUtil.getChildOfType(this, GoExpression.class);
    }

    @Override
    @Nullable
    public GoType getType() {
        return GoPsiTreeUtil.getStubChildOfType(this, GoType.class);
    }

    @Override
    @NotNull
    public PsiElement getLbrack() {
        return notNullChild(findChildByType(LBRACK));
    }

    @Override
    @Nullable
    public PsiElement getRbrack() {
        return findChildByType(RBRACK);
    }

    @Override
    @Nullable
    public PsiElement getTripleDot() {
        return findChildByType(TRIPLE_DOT);
    }

}