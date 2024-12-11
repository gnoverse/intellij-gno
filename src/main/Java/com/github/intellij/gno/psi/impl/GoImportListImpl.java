package com.github.intellij.gno.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import com.github.intellij.gno.psi.*;

public class GoImportListImpl extends GoCompositeElementImpl implements GoImportList {

    public GoImportListImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitImportList(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GoVisitor) accept((GoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @NotNull
    public List<GoImportDeclaration> getImportDeclarationList() {
        return GoPsiTreeUtil.getChildrenOfTypeAsList(this, GoImportDeclaration.class);
    }

    @NotNull
    public GoImportSpec addImport(String packagePath, String alias) {
        return GoPsiImplUtil.addImport(this, packagePath, alias);
    }

}