package com.github.intellij.gno.language.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import static com.github.intellij.gno.language.GnoTypes.*;
import com.github.intellij.gno.language.stubs.GnoConstDefinitionStub;
import com.github.intellij.gno.language.psi.*;
import com.intellij.psi.ResolveState;
import com.intellij.psi.stubs.IStubElementType;

public class GnoConstDefinitionImpl extends GnoNamedElementImpl<GnoConstDefinitionStub> implements GnoConstDefinition {

    public GnoConstDefinitionImpl(GnoConstDefinitionStub stub, IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public GnoConstDefinitionImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GnoVisitor visitor) {
        visitor.visitConstDefinition(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GnoVisitor) accept((GnoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @NotNull
    public PsiElement getIdentifier() {
        return notNullChild(findChildByType(IDENTIFIER));
    }

    @Override
    public @Nullable GnoType getGoTypeInner(ResolveState context) {
        return null;
    }

    @Nullable
    public GnoType getGnoTypeInner(ResolveState context) {
        return GnoPsiImplUtil.getGnoTypeInner(this, context);
    }

    @Nullable
    public GnoExpression getValue() {
        return GnoPsiImplUtil.getValue(this);
    }

}