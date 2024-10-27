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

public class GnoMapTypeImpl extends GnoTypeImpl implements GnoMapType {

    public GnoMapTypeImpl(GnoTypeStub stub, IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public GnoMapTypeImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GnoVisitor visitor) {
        visitor.visitMapType(this);
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

    @Override
    @Nullable
    public PsiElement getLbrack() {
        return findChildByType(LBRACK);
    }

    @Override
    @Nullable
    public PsiElement getRbrack() {
        return findChildByType(RBRACK);
    }

    @Override
    @NotNull
    public PsiElement getMap() {
        return notNullChild(findChildByType(MAP));
    }

    @Override
    @Nullable
    public GnoType getKeyType() {
        List<GnoType> p1 = getTypeList();
        return p1.isEmpty() ? null : p1.get(0);
    }

    @Override
    @Nullable
    public GnoType getValueType() {
        List<GnoType> p1 = getTypeList();
        return p1.size() < 2 ? null : p1.get(1);
    }

}
