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
        if (node == null) {
            $$$reportNull$$$0(0);
        }

        super(node);
    }

    public void accept(@NotNull GnoVisitor visitor) {
        if (visitor == null) {
            $$$reportNull$$$0(1);
        }

        visitor.visitFieldChainExpr(this);
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

    public @NotNull List<GnoFieldChainExpr> getFieldChainExprList() {
        List var10000 = PsiTreeUtil.getChildrenOfTypeAsList(this, GnoFieldChainExpr.class);
        if (var10000 == null) {
            $$$reportNull$$$0(3);
        }

        return var10000;
    }

    public @Nullable GnoExpression getQualifier() {
        return GnoImplUtil.getQualifier(this);
    }

    public @NotNull PsiElement getDot() {
        PsiElement var10000 = GnoImplUtil.getDot(this);
        if (var10000 == null) {
            $$$reportNull$$$0(4);
        }

        return var10000;
    }

    public @NotNull PsiElement getIdentifier() {
        PsiElement var10000 = GnoImplUtil.getIdentifier(this);
        if (var10000 == null) {
            $$$reportNull$$$0(5);
        }

        return var10000;
    }
}
