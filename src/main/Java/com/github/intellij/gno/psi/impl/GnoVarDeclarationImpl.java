package com.github.intellij.gno.psi.impl;

import com.github.intellij.gno.psi.GnoTypes;
import com.github.intellij.gno.psi.GnoPipeline;
import com.github.intellij.gno.psi.GnoVarDeclaration;
import com.github.intellij.gno.psi.GnoVarDefinition;
import com.github.intellij.gno.psi.GnoVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GnoVarDeclarationImpl extends ASTWrapperPsiElement implements GnoVarDeclaration {
    public GnoVarDeclarationImpl(@NotNull ASTNode node) {

        super(node);
    }

    public void accept(@NotNull GnoVisitor visitor) {

        visitor.visitVarDeclaration(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {

        if (visitor instanceof GnoVisitor) {
            this.accept((GnoVisitor)visitor);
        } else {
            super.accept(visitor);
        }

    }

    public @Nullable GnoPipeline getPipeline() {
        return this.findChildByClass(GnoPipeline.class);
    }

    public @NotNull GnoVarDefinition getVarDefinition() {

        return this.findNotNullChildByClass(GnoVarDefinition.class);
    }

    public @NotNull PsiElement getVarAssign() {

        return this.findNotNullChildByType(GnoTypes.VAR_ASSIGN);
    }
}
