package com.github.intellij.gno.psi.impl;

import com.github.intellij.gno.psi.GnoExpression;
import com.github.intellij.gno.psi.GnoSimplePipeline;
import com.github.intellij.gno.psi.GnoVisitor;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class GnoSimplePipelineImpl extends GnoPipelineImpl implements GnoSimplePipeline {
    public GnoSimplePipelineImpl(@NotNull ASTNode node) {
        if (node == null) {
            $$$reportNull$$$0(0);
        }

        super(node);
    }

    public void accept(@NotNull GnoVisitor visitor) {
        if (visitor == null) {
            $$$reportNull$$$0(1);
        }

        visitor.visitSimplePipeline(this);
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

    public @NotNull List<GnoExpression> getExpressionList() {
        List var10000 = PsiTreeUtil.getChildrenOfTypeAsList(this, GnoExpression.class);
        if (var10000 == null) {
            $$$reportNull$$$0(3);
        }

        return var10000;
    }
}
