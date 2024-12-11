package com.github.intellij.gno.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import static com.github.intellij.gno.GoTypes.*;
import com.github.intellij.gno.psi.*;
import com.github.intellij.gno.stubs.GoVarSpecStub;
import com.intellij.psi.stubs.IStubElementType;

public class GoShortVarDeclarationImpl extends GoVarSpecImpl implements GoShortVarDeclaration {

    public GoShortVarDeclarationImpl(GoVarSpecStub stub, IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public GoShortVarDeclarationImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitShortVarDeclaration(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GoVisitor) accept((GoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @NotNull
    public PsiElement getVarAssign() {
        return notNullChild(findChildByType(VAR_ASSIGN));
    }

}