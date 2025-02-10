package com.github.intellij.gno.psi.impl;

import com.github.intellij.gno.psi.GnoLiteral;
import com.github.intellij.gno.psi.GnoLiteralExpr;
import com.github.intellij.gno.psi.GnoVisitor;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;

public class GnoLiteralExprImpl extends GnoExpressionImpl implements GnoLiteralExpr {
    public GnoLiteralExprImpl(@NotNull ASTNode node) {
        if (node == null) {
            $$$reportNull$$$0(0);
        }

        super(node);
    }

    public void accept(@NotNull GnoVisitor visitor) {
        if (visitor == null) {
            $$$reportNull$$$0(1);
        }

        visitor.visitLiteralExpr(this);
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

    public @NotNull GnoLiteral getLiteral() {
        GnoLiteral var10000 = (GnoLiteral)this.findNotNullChildByClass(GnoLiteral.class);
        if (var10000 == null) {
            $$$reportNull$$$0(3);
        }

        return var10000;
    }
}
