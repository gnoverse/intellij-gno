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

public class GnoStructTypeImpl extends GnoTypeImpl implements GnoStructType {

    public GnoStructTypeImpl(GnoTypeStub stub, IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public GnoStructTypeImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GnoVisitor visitor) {
        visitor.visitStructType(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GnoVisitor) accept((GnoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @NotNull
    public List<GnoFieldDeclaration> getFieldDeclarationList() {
        return GnoPsiTreeUtil.getChildrenOfTypeAsList(this, GnoFieldDeclaration.class);
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
    public PsiElement getStruct() {
        return notNullChild(findChildByType(STRUCT));
    }

}