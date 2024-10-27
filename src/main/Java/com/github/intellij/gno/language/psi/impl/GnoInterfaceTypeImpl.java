package com.github.intellij.gno.language.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.language.psi.GnoPsiTreeUtil;
import static com.github.intellij.gno.language.GnoTypes.*;
import com.github.intellij.gno.language.psi.*;
import com.github.intellij.gno.language.stubs.GnoTypeStub;
import com.intellij.psi.stubs.IStubElementType;

public class GnoInterfaceTypeImpl extends GnoTypeImpl implements GnoInterfaceType {

    public GnoInterfaceTypeImpl(GnoTypeStub stub, IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public GnoInterfaceTypeImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GnoVisitor visitor) {
        visitor.visitInterfaceType(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GnoVisitor) accept((GnoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @NotNull
    public List<GnoMethodSpec> getMethodSpecList() {
        return GnoPsiTreeUtil.getStubChildrenOfTypeAsList(this, GnoMethodSpec.class);
    }

    @Override
    @Nullable
    public PsiElement getLbrace() {
        return findChildByType(LBRACE);
    }

    @Override
    @Nullable
    public PsiElement getRbrace() {
        return findChildByType(RBRACE);
    }

    @Override
    @NotNull
    public PsiElement getInterface() {
        return notNullChild(findChildByType(INTERFACE));
    }

    @NotNull
    public List<GnoMethodSpec> getMethods() {
        return GnoPsiImplUtil.getMethods(this);
    }

    @NotNull
    public List<GnoTypeReferenceExpression> getBaseTypesReferences() {
        return GnoPsiImplUtil.getBaseTypesReferences(this);
    }

}