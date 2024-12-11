package com.github.intellij.gno.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import com.github.intellij.gno.psi.*;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiReference;

public class GoImportStringImpl extends GoCompositeElementImpl implements GoImportString {

    public GoImportStringImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitImportString(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GoVisitor) accept((GoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @NotNull
    public GoStringLiteral getStringLiteral() {
        return notNullChild(GoPsiTreeUtil.getChildOfType(this, GoStringLiteral.class));
    }

    @NotNull
    public PsiReference @NotNull [] getReferences() {
        return GoPsiImplUtil.getReferences(this);
    }

    @Nullable
    public PsiDirectory resolve() {
        return GoPsiImplUtil.resolve(this);
    }

    @NotNull
    public String getPath() {
        return GoPsiImplUtil.getPath(this);
    }

    @NotNull
    public TextRange getPathTextRange() {
        return GoPsiImplUtil.getPathTextRange(this);
    }

}