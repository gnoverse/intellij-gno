package com.github.intellij.gno.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import static com.github.intellij.gno.GoTypes.*;
import com.github.intellij.gno.psi.*;
import com.github.intellij.gno.stubs.GoTypeStub;
import com.intellij.psi.stubs.IStubElementType;

public class GoPointerTypeImpl extends GoTypeImpl implements GoPointerType {

    public GoPointerTypeImpl(GoTypeStub stub, IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public GoPointerTypeImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitPointerType(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GoVisitor) accept((GoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @Nullable
    public GoType getType() {
        return GoPsiTreeUtil.getStubChildOfType(this, GoType.class);
    }

    @Override
    @NotNull
    public PsiElement getMul() {
        return notNullChild(findChildByType(MUL));
    }

}