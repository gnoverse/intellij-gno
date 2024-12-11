package com.github.intellij.gno.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import static com.github.intellij.gno.GoTypes.*;
import com.github.intellij.gno.psi.*;

public class GoConstDeclarationImpl extends GoCompositeElementImpl implements GoConstDeclaration {

    public GoConstDeclarationImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitConstDeclaration(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GoVisitor) accept((GoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @NotNull
    public List<GoConstSpec> getConstSpecList() {
        return GoPsiTreeUtil.getChildrenOfTypeAsList(this, GoConstSpec.class);
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
    public PsiElement getConst() {
        return notNullChild(findChildByType(CONST));
    }

    @NotNull
    public GoConstSpec addSpec(String name, String type, String value, GoConstSpec specAnchor) {
        return GoPsiImplUtil.addSpec(this, name, type, value, specAnchor);
    }

    public void deleteSpec(GoConstSpec specToDelete) {
        GoPsiImplUtil.deleteSpec(this, specToDelete);
    }

}