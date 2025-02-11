package com.github.intellij.gno.psi.impl;

import com.github.intellij.gno.psi.GnoLiteral;
import com.github.intellij.gno.psi.GnoLiteralExpr;
import com.github.intellij.gno.psi.GnoVisitor;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;

public class GnoLiteralExprImpl extends GnoExpressionImpl implements GnoLiteralExpr {
    public GnoLiteralExprImpl(@NotNull ASTNode node) {

        super(node);
    }

    public void accept(@NotNull GnoVisitor visitor) {

        visitor.visitLiteralExpr(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {

        if (visitor instanceof GnoVisitor) {
            this.accept((GnoVisitor)visitor);
        } else {
            super.accept(visitor);
        }

    }

    public @NotNull GnoLiteral getLiteral() {

        return this.findNotNullChildByClass(GnoLiteral.class);
    }
}
