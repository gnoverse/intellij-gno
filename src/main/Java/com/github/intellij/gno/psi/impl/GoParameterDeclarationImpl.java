package com.github.intellij.gno.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import static com.github.intellij.gno.GoTypes.*;
import com.github.intellij.gno.stubs.GoParameterDeclarationStub;
import com.github.intellij.gno.psi.*;
import com.intellij.psi.stubs.IStubElementType;

public class GoParameterDeclarationImpl extends GoStubbedElementImpl<GoParameterDeclarationStub> implements GoParameterDeclaration {

    public GoParameterDeclarationImpl(GoParameterDeclarationStub stub, IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public GoParameterDeclarationImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitParameterDeclaration(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GoVisitor) accept((GoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @NotNull
    public List<GoParamDefinition> getParamDefinitionList() {
        return GoPsiTreeUtil.getStubChildrenOfTypeAsList(this, GoParamDefinition.class);
    }

    @Override
    @NotNull
    public GoType getType() {
        return notNullChild(GoPsiTreeUtil.getStubChildOfType(this, GoType.class));
    }

    @Override
    @Nullable
    public PsiElement getTripleDot() {
        return findChildByType(TRIPLE_DOT);
    }

    public boolean isVariadic() {
        return GoPsiImplUtil.isVariadic(this);
    }

}