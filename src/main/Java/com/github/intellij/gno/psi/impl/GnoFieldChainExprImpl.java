package com.github.intellij.gno.psi.impl;

import com.github.intellij.gno.psi.GnoImplUtil;
import com.github.intellij.gno.psi.GnoExpression;
import com.github.intellij.gno.psi.GnoFieldChainExpr;
import com.github.intellij.gno.psi.GnoVisitor;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GnoFieldChainExprImpl extends GnoExpressionImpl implements GnoFieldChainExpr {
    public GnoFieldChainExprImpl(@NotNull ASTNode node) {

        super(node);
    }

    public void accept(@NotNull GnoVisitor visitor) {

        visitor.visitFieldChainExpr(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {

        if (visitor instanceof GnoVisitor) {
            this.accept((GnoVisitor)visitor);
        } else {
            super.accept(visitor);
        }

    }

    public @NotNull List<GnoFieldChainExpr> getFieldChainExprList() {

        return PsiTreeUtil.getChildrenOfTypeAsList(this, GnoFieldChainExpr.class);
    }

    public @Nullable GnoExpression getQualifier() {
        return GnoImplUtil.getQualifier(this);
    }

    public @NotNull PsiElement getDot() {

        return GnoImplUtil.getDot(this);
    }

    public @NotNull PsiElement getIdentifier() {

        return GnoImplUtil.getIdentifier(this);
    }
}
