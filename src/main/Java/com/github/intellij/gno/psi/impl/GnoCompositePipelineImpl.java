package com.github.intellij.gno.psi.impl;

import com.github.intellij.gno.psi.GnoCompositePipeline;
import com.github.intellij.gno.psi.GnoExpression;
import com.github.intellij.gno.psi.GnoPipeline;
import com.github.intellij.gno.psi.GnoVisitor;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class GnoCompositePipelineImpl extends GnoPipelineImpl implements GnoCompositePipeline {
    public GnoCompositePipelineImpl(@NotNull ASTNode node) {

        super(node);
    }

    public void accept(@NotNull GnoVisitor visitor) {

        visitor.visitCompositePipeline(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {

        if (visitor instanceof GnoVisitor) {
            this.accept((GnoVisitor)visitor);
        } else {
            super.accept(visitor);
        }

    }

    public @NotNull List<GnoExpression> getExpressionList() {

        return PsiTreeUtil.getChildrenOfTypeAsList(this, GnoExpression.class);
    }

    public @NotNull GnoPipeline getPipeline() {

        return this.findNotNullChildByClass(GnoPipeline.class);
    }
}
