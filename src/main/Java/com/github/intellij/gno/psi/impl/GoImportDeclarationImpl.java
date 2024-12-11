package com.github.intellij.gno.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import static com.github.intellij.gno.GoTypes.*;
import com.github.intellij.gno.psi.*;

public class GoImportDeclarationImpl extends GoCompositeElementImpl implements GoImportDeclaration {

    public GoImportDeclarationImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitImportDeclaration(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GoVisitor) accept((GoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @NotNull
    public List<GoImportSpec> getImportSpecList() {
        return GoPsiTreeUtil.getChildrenOfTypeAsList(this, GoImportSpec.class);
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
    public PsiElement getImport() {
        return notNullChild(findChildByType(IMPORT));
    }

    @NotNull
    public GoImportSpec addImportSpec(String packagePath, String alias) {
        return GoPsiImplUtil.addImportSpec(this, packagePath, alias);
    }

}