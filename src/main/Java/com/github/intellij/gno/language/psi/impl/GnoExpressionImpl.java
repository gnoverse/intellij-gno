package com.github.intellij.gno.language.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.language.psi.*;
import com.intellij.psi.ResolveState;

public class GnoExpressionImpl extends GnoCompositeElementImpl implements GnoExpression {

    public GnoExpressionImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GnoVisitor visitor) {
        visitor.visitExpression(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GnoVisitor) accept((GnoVisitor)visitor);
        else super.accept(visitor);
    }

    @Nullable
    public GnoType getGnoType(ResolveState context) {
        return GnoPsiImplUtil.getGnoType(this, context);
    }

}