package com.github.intellij.gno.language.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.language.psi.GnoPsiTreeUtil;
import com.github.intellij.gno.language.psi.*;
import com.github.intellij.gno.language.stubs.GnoTypeStub;
import com.intellij.psi.stubs.IStubElementType;

public class GnoTypeListImpl extends GnoTypeImpl implements GnoTypeList  {

    public GnoTypeListImpl(GnoTypeStub stub, IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public GnoTypeListImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GnoVisitor visitor) {
        visitor.visitTypeList(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GnoVisitor) accept((GnoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @NotNull
    public List<GnoType> getTypeList() {
        return GnoPsiTreeUtil.getStubChildrenOfTypeAsList(this, GnoType.class);
    }

}