package com.github.intellij.gno.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import static com.github.intellij.gno.GoTypes.*;
import com.github.intellij.gno.psi.*;

public class GoVarDeclarationImpl extends GoCompositeElementImpl implements GoVarDeclaration {

    public GoVarDeclarationImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitVarDeclaration(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GoVisitor) accept((GoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @NotNull
    public List<GoVarSpec> getVarSpecList() {
        return GoPsiTreeUtil.getChildrenOfTypeAsList(this, GoVarSpec.class);
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
    public PsiElement getVar() {
        return notNullChild(findChildByType(VAR));
    }

    @NotNull
    public GoVarSpec addSpec(String name, String type, String value, GoVarSpec specAnchor) {
        return GoPsiImplUtil.addSpec(this, name, type, value, specAnchor);
    }

    public void deleteSpec(GoVarSpec specToDelete) {
        GoPsiImplUtil.deleteSpec(this, specToDelete);
    }

}