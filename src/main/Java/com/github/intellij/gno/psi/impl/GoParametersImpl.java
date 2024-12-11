package com.github.intellij.gno.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import static com.github.intellij.gno.GoTypes.*;
import com.github.intellij.gno.stubs.GoParametersStub;
import com.github.intellij.gno.psi.*;
import com.intellij.psi.stubs.IStubElementType;

public class GoParametersImpl extends GoStubbedElementImpl<GoParametersStub> implements GoParameters {

    public GoParametersImpl(GoParametersStub stub, IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public GoParametersImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitParameters(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GoVisitor) accept((GoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @NotNull
    public List<GoParameterDeclaration> getParameterDeclarationList() {
        return GoPsiTreeUtil.getStubChildrenOfTypeAsList(this, GoParameterDeclaration.class);
    }

    @Override
    @Nullable
    public GoType getType() {
        return GoPsiTreeUtil.getStubChildOfType(this, GoType.class);
    }

    @Override
    @NotNull
    public PsiElement getLparen() {
        return notNullChild(findChildByType(LPAREN));
    }

    @Override
    @Nullable
    public PsiElement getRparen() {
        return findChildByType(RPAREN);
    }

}