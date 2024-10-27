package com.github.intellij.gno.language.psi.impl;

import com.github.intellij.gno.language.psi.GnoPackageClause;
import com.github.intellij.gno.language.stubs.GnoPackageClauseStub;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import static com.github.intellij.gno.language.GnoTypes.*;
import com.github.intellij.gno.language.psi.*;
import com.intellij.psi.stubs.IStubElementType;

public class GnoPackageClauseImpl extends GnoStubbedElementImpl<GnoPackageClauseStub> implements GnoPackageClause {

    public GnoPackageClauseImpl(GnoPackageClauseStub stub, IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public GnoPackageClauseImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GnoVisitor visitor) {
        visitor.visitPackageClause(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GnoVisitor) accept((GnoVisitor)visitor);
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
        return GnoPsiImplUtil.getName(this);
    }

}
