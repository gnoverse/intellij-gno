package com.github.intellij.gno.psi.impl;

import com.github.intellij.gno.psi.GnoTypes;
import com.github.intellij.gno.psi.GnoNamedElementImpl;
import com.github.intellij.gno.psi.GnoVarDefinition;
import com.github.intellij.gno.psi.GnoVisitor;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;

public class GnoVarDefinitionImpl extends GnoNamedElementImpl implements GnoVarDefinition {
    public GnoVarDefinitionImpl(@NotNull ASTNode node) {

        super(node);
    }

    public void accept(@NotNull GnoVisitor visitor) {

        visitor.visitVarDefinition(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {

        if (visitor instanceof GnoVisitor) {
            this.accept((GnoVisitor)visitor);
        } else {
            super.accept(visitor);
        }

    }

    public @NotNull PsiElement getVariable() {

        return this.findNotNullChildByType(GnoTypes.VARIABLE);
    }
}
