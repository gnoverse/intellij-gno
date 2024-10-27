package com.github.intellij.gno.language.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.language.psi.GnoPsiTreeUtil;
import static com.github.intellij.gno.language.GnoTypes.*;
import com.github.intellij.gno.language.psi.*;
import com.github.intellij.gno.language.stubs.GnoTypeStub;
import com.intellij.psi.stubs.IStubElementType;

public class GnoSpecTypeImpl extends GnoTypeImpl implements GnoSpecType {

    public GnoSpecTypeImpl(GnoTypeStub stub, IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public GnoSpecTypeImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GnoVisitor visitor) {
        visitor.visitSpecType(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GnoVisitor) accept((GnoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @Nullable
    public GnoType getType() {
        return GnoPsiTreeUtil.getStubChildOfType(this, GnoType.class);
    }

    @Override
    @NotNull
    public PsiElement getIdentifier() {
        return notNullChild(findChildByType(IDENTIFIER));
    }

}