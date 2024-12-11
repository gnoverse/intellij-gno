package com.github.intellij.gno.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import static com.github.intellij.gno.GoTypes.*;
import com.github.intellij.gno.psi.*;

public class GoTypeDeclarationImpl extends GoCompositeElementImpl implements GoTypeDeclaration {

    public GoTypeDeclarationImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitTypeDeclaration(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GoVisitor) accept((GoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @NotNull
    public List<GoTypeSpec> getTypeSpecList() {
        return GoPsiTreeUtil.getChildrenOfTypeAsList(this, GoTypeSpec.class);
    }

    @Override
    @Nullable
    public PsiElement getLparen() {
        return findChildByType(LPAREN);
    }

    @Override
    @Nullable
    public PsiElement getRparen() {
        return findChildByType(RPAREN);
    }

    @Override
    @NotNull
    public PsiElement getType_() {
        return notNullChild(findChildByType(TYPE_));
    }

}