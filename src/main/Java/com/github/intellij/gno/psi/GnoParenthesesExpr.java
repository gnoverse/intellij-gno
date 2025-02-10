package com.github.intellij.gno.psi;


import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface GnoParenthesesExpr extends GnoExpression {
    @Nullable GnoPipeline getPipeline();

    @NotNull PsiElement getLparen();

    @Nullable PsiElement getRparen();
}
