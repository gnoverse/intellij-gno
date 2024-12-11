package com.github.intellij.gno.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import static com.github.intellij.gno.GoTypes.*;
import com.github.intellij.gno.stubs.GoPackageClauseStub;
import com.github.intellij.gno.psi.*;
import com.intellij.psi.stubs.IStubElementType;

public class GoPackageClauseImpl extends GoStubbedElementImpl<GoPackageClauseStub> implements GoPackageClause {

    public GoPackageClauseImpl(GoPackageClauseStub stub, IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public GoPackageClauseImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitPackageClause(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GoVisitor) accept((GoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @Nullable
    public PsiElement getIdentifier() {
        return findChildByType(IDENTIFIER);
    }

    @Override
    @NotNull
    public PsiElement getPackage() {
        return notNullChild(findChildByType(PACKAGE));
    }

    @Nullable
    public String getName() {
        return GoPsiImplUtil.getName(this);
    }

}