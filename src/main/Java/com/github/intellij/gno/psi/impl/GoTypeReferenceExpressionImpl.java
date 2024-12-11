package com.github.intellij.gno.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import static com.github.intellij.gno.GoTypes.*;
import com.github.intellij.gno.psi.*;
import com.intellij.psi.PsiReference;

public class GoTypeReferenceExpressionImpl extends GoCompositeElementImpl implements GoTypeReferenceExpression {

    public GoTypeReferenceExpressionImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitTypeReferenceExpression(this);
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

    @NotNull
    public PsiReference getReference() {
        return GoPsiImplUtil.getReference(this);
    }

    @Nullable
    public GoTypeReferenceExpression getQualifier() {
        return GoPsiImplUtil.getQualifier(this);
    }

    @Nullable
    public PsiElement resolve() {
        return GoPsiImplUtil.resolve(this);
    }

    @Nullable
    public GoType resolveType() {
        return GoPsiImplUtil.resolveType(this);
    }

}