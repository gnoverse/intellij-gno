package com.github.intellij.gno.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import com.github.intellij.gno.psi.*;

public class GoFieldDeclarationImpl extends GoCompositeElementImpl implements GoFieldDeclaration {

    public GoFieldDeclarationImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitFieldDeclaration(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GoVisitor) accept((GoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @Nullable
    public GoAnonymousFieldDefinition getAnonymousFieldDefinition() {
        return GoPsiTreeUtil.getChildOfType(this, GoAnonymousFieldDefinition.class);
    }

    @Override
    @NotNull
    public List<GoFieldDefinition> getFieldDefinitionList() {
        return GoPsiTreeUtil.getChildrenOfTypeAsList(this, GoFieldDefinition.class);
    }

    @Override
    @Nullable
    public GoTag getTag() {
        return GoPsiTreeUtil.getChildOfType(this, GoTag.class);
    }

    @Override
    @Nullable
    public GoType getType() {
        return GoPsiTreeUtil.getChildOfType(this, GoType.class);
    }

}