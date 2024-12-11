package com.github.intellij.gno.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import static com.github.intellij.gno.GoTypes.*;
import com.github.intellij.gno.stubs.GoImportSpecStub;
import com.github.intellij.gno.psi.*;
import com.intellij.psi.stubs.IStubElementType;

public class GoImportSpecImpl extends GoNamedElementImpl<GoImportSpecStub> implements GoImportSpec {

    public GoImportSpecImpl(GoImportSpecStub stub, IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public GoImportSpecImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitImportSpec(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GoVisitor) accept((GoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @NotNull
    public GoImportString getImportString() {
        return notNullChild(GoPsiTreeUtil.getChildOfType(this, GoImportString.class));
    }

    @Override
    @Nullable
    public PsiElement getDot() {
        return findChildByType(DOT);
    }

    @Override
    @Nullable
    public PsiElement getIdentifier() {
        return findChildByType(IDENTIFIER);
    }

    public String getAlias() {
        return GoPsiImplUtil.getAlias(this);
    }

    public String getLocalPackageName() {
        return GoPsiImplUtil.getLocalPackageName(this);
    }

    public boolean shouldGoDeeper() {
        return GoPsiImplUtil.shouldGoDeeper(this);
    }

    public boolean isForSideEffects() {
        return GoPsiImplUtil.isForSideEffects(this);
    }

    public boolean isDot() {
        return GoPsiImplUtil.isDot(this);
    }

    @NotNull
    public String getPath() {
        return GoPsiImplUtil.getPath(this);
    }

    public String getName() {
        return GoPsiImplUtil.getName(this);
    }

    public boolean isCImport() {
        return GoPsiImplUtil.isCImport(this);
    }

}
