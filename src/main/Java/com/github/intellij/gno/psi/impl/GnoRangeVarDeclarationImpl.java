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
        if (node == null) {
            $$$reportNull$$$0(0);
        }

        super(node);
    }

    public void accept(@NotNull GnoVisitor visitor) {
        if (visitor == null) {
            $$$reportNull$$$0(1);
        }

        visitor.visitRangeVarDeclaration(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor == null) {
            $$$reportNull$$$0(2);
        }

        if (visitor instanceof GnoVisitor) {
            this.accept((GnoVisitor)visitor);
        } else {
            super.accept(visitor);
        }

    }

    public @NotNull GnoPipeline getPipeline() {
        GnoPipeline var10000 = (GnoPipeline)this.findNotNullChildByClass(GnoPipeline.class);
        if (var10000 == null) {
            $$$reportNull$$$0(3);
        }

        return var10000;
    }

    public @NotNull List<GnoVarDefinition> getVarDefinitionList() {
        List var10000 = PsiTreeUtil.getChildrenOfTypeAsList(this, GnoVarDefinition.class);
        if (var10000 == null) {
            $$$reportNull$$$0(4);
        }

        return var10000;
    }

    public @Nullable PsiElement getComma() {
        return this.findChildByType(GnoTypes.COMMA);
    }
}
