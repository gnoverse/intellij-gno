package com.github.intellij.gno.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import static com.github.intellij.gno.GoTypes.*;
import com.github.intellij.gno.psi.*;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;

public class GoFunctionLitImpl extends GoExpressionImpl implements GoFunctionLit {

    public GoFunctionLitImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitFunctionLit(this);
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
        return GoPsiTreeUtil.getChildOfType(this, GoSignature.class);
    }

    @Override
    @NotNull
    public PsiElement getFunc() {
        return notNullChild(findChildByType(FUNC));
    }

    public boolean processDeclarations(@NotNull PsiScopeProcessor processor,@NotNull ResolveState state, PsiElement lastParent,@NotNull PsiElement place) {
        return GoPsiImplUtil.processDeclarations(this, processor, state, lastParent, place);
    }

}