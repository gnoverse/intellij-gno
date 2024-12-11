package com.github.intellij.gno.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import static com.github.intellij.gno.GoTypes.*;
import com.github.intellij.gno.psi.*;
import com.intellij.codeInsight.highlighting.ReadWriteAccessDetector.Access;

public class GoReferenceExpressionImpl extends GoExpressionImpl implements GoReferenceExpression {

    public GoReferenceExpressionImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitReferenceExpression(this);
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
    public GoReference getReference() {
        return GoPsiImplUtil.getReference(this);
    }

    @Nullable
    public GoReferenceExpression getQualifier() {
        return GoPsiImplUtil.getQualifier(this);
    }

    @Nullable
    public PsiElement resolve() {
        return GoPsiImplUtil.resolve(this);
    }

    @NotNull
    public Access getReadWriteAccess() {
        return GoPsiImplUtil.getReadWriteAccess(this);
    }

}