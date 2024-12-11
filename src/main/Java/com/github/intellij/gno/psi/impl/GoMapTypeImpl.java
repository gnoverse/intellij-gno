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

public class GoMapTypeImpl extends GoTypeImpl implements GoMapType {

    public GoMapTypeImpl(GoTypeStub stub, IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public GoMapTypeImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitMapType(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GoVisitor) accept((GoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @NotNull
    public List<GoType> getTypeList() {
        return GoPsiTreeUtil.getStubChildrenOfTypeAsList(this, GoType.class);
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
    public GoType getKeyType() {
        List<GoType> p1 = getTypeList();
        return p1.isEmpty() ? null : p1.get(0);
    }

    @Override
    @Nullable
    public GoType getValueType() {
        List<GoType> p1 = getTypeList();
        return p1.size() < 2 ? null : p1.get(1);
    }

}