package com.github.intellij.gno.psi.impl;

import com.github.intellij.gno.psi.GnoImplUtil;
import com.github.intellij.gno.psi.GnoExpression;
import com.github.intellij.gno.psi.GnoPipeline;
import com.github.intellij.gno.psi.GnoVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public abstract class GnoPipelineImpl extends ASTWrapperPsiElement implements GnoPipeline {
    public GnoPipelineImpl(@NotNull ASTNode node) {

        super(node);
    }

    public void accept(@NotNull GnoVisitor visitor) {

        visitor.visitPipeline(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {

        if (visitor instanceof GnoVisitor) {
            this.accept((GnoVisitor)visitor);
        } else {
            super.accept(visitor);
        }

    }

    public @NotNull List<GnoExpression> getExpressionList() {

        return GnoImplUtil.getExpressionList(this);
    }
}
