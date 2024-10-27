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

public class GnoArrayOrSliceTypeImpl extends GnoTypeImpl implements GnoArrayOrSliceType {

    public GnoArrayOrSliceTypeImpl(GnoTypeStub stub, IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public GnoArrayOrSliceTypeImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GnoVisitor visitor) {
        visitor.visitArrayOrSliceType(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GnoVisitor) accept((GnoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @Nullable
    public GnoExpression getExpression() {
        return GnoPsiTreeUtil.getChildOfType(this, GnoExpression.class);
    }

    @Override
    @Nullable
    public GnoType getType() {
        return GnoPsiTreeUtil.getStubChildOfType(this, GnoType.class);
    }

    @Override
    @NotNull
    public PsiElement getLbrack() {
        return notNullChild(findChildByType(LBRACK));
    }

    @Override
    @Nullable
    public PsiElement getRbrack() {
        return findChildByType(RBRACK);
    }

    @Override
    @Nullable
    public PsiElement getTripleDot() {
        return findChildByType(TRIPLE_DOT);
    }
}
