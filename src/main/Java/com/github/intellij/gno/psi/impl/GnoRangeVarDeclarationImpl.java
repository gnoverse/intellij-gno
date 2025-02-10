package com.github.intellij.gno.psi.impl;

import com.github.intellij.gno.psi.GnoTypes;
import com.github.intellij.gno.psi.GnoPipeline;
import com.github.intellij.gno.psi.GnoRangeVarDeclaration;
import com.github.intellij.gno.psi.GnoVarDefinition;
import com.github.intellij.gno.psi.GnoVisitor;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GnoRangeVarDeclarationImpl extends GnoVarDeclarationImpl implements GnoRangeVarDeclaration {
    public GnoRangeVarDeclarationImpl(@NotNull ASTNode node) {

        super(node);
    }

    public void accept(@NotNull GnoVisitor visitor) {

        visitor.visitRangeVarDeclaration(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {

        if (visitor instanceof GnoVisitor) {
            this.accept((GnoVisitor)visitor);
        } else {
            super.accept(visitor);
        }

    }

    public @NotNull GnoPipeline getPipeline() {

        return this.findNotNullChildByClass(GnoPipeline.class);
    }

    public @NotNull List<GnoVarDefinition> getVarDefinitionList() {

        return PsiTreeUtil.getChildrenOfTypeAsList(this, GnoVarDefinition.class);
    }

    public @Nullable PsiElement getComma() {
        return this.findChildByType(GnoTypes.COMMA);
    }
}
