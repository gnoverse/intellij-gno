package com.github.intellij.gno.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import static com.github.intellij.gno.GoTypes.*;
import com.github.intellij.gno.psi.*;
import com.github.intellij.gno.stubs.GoTypeStub;
import com.intellij.psi.stubs.IStubElementType;

public class GoInterfaceTypeImpl extends GoTypeImpl implements GoInterfaceType {

    public GoInterfaceTypeImpl(GoTypeStub stub, IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public GoInterfaceTypeImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitInterfaceType(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GoVisitor) accept((GoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @NotNull
    public List<GoMethodSpec> getMethodSpecList() {
        return GoPsiTreeUtil.getStubChildrenOfTypeAsList(this, GoMethodSpec.class);
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
    public List<GoMethodSpec> getMethods() {
        return GoPsiImplUtil.getMethods(this);
    }

    @NotNull
    public List<GoTypeReferenceExpression> getBaseTypesReferences() {
        return GoPsiImplUtil.getBaseTypesReferences(this);
    }

}
