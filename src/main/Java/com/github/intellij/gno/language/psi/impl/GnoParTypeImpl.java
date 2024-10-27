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

public class GnoParTypeImpl extends GnoTypeImpl implements GnoParType {

    public GnoParTypeImpl(GnoTypeStub stub, IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public GnoParTypeImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GnoVisitor visitor) {
        visitor.visitParType(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GnoVisitor) accept((GnoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @NotNull
    public GnoType getType() {
        return notNullChild(GnoPsiTreeUtil.getStubChildOfType(this, GnoType.class));
    }

    @Override
    @NotNull
    public PsiElement getLparen() {
        return notNullChild(findChildByType(LPAREN));
    }

    @Override
    @NotNull
    public PsiElement getRparen() {
        return notNullChild(findChildByType(RPAREN));
    }

    @NotNull
    public GnoType getActualType() {
        return GnoPsiImplUtil.getActualType(this);
    }

}