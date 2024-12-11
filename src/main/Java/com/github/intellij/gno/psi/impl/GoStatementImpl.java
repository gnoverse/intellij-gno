package com.github.intellij.gno.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import com.github.intellij.gno.psi.*;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;

public class GoStatementImpl extends GoCompositeElementImpl implements GoStatement {

    public GoStatementImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitStatement(this);
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
    public GoConstDeclaration getConstDeclaration() {
        return GoPsiTreeUtil.getChildOfType(this, GoConstDeclaration.class);
    }

    @Override
    @Nullable
    public GoTypeDeclaration getTypeDeclaration() {
        return GoPsiTreeUtil.getChildOfType(this, GoTypeDeclaration.class);
    }

    @Override
    @Nullable
    public GoVarDeclaration getVarDeclaration() {
        return GoPsiTreeUtil.getChildOfType(this, GoVarDeclaration.class);
    }

    public boolean processDeclarations(@NotNull PsiScopeProcessor processor, @NotNull ResolveState state, PsiElement lastParent, @NotNull PsiElement place) {
        return GoPsiImplUtil.processDeclarations(this, processor, state, lastParent, place);
    }

}