package com.github.intellij.gno.psi.impl;

import com.github.intellij.gno.psi.GnoImplUtil;
import com.github.intellij.gno.psi.GnoStatement;
import com.github.intellij.gno.psi.GnoVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GnoStatementImpl extends ASTWrapperPsiElement implements GnoStatement {
    public GnoStatementImpl(@NotNull ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GnoVisitor visitor) {
        visitor.visitStatement(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GnoVisitor) {
            this.accept((GnoVisitor)visitor);
        } else {
            super.accept(visitor);
        }

    }

    public boolean processDeclarations(@NotNull PsiScopeProcessor processor, @NotNull ResolveState state, @Nullable PsiElement lastParent, @NotNull PsiElement place) {
        return GnoImplUtil.processDeclarations(this, processor, state, lastParent, place);
    }
}
