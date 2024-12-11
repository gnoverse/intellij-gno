package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GoParenthesesExpr extends GoExpression {

    @Nullable
    GoExpression getExpression();

    @NotNull
    PsiElement getLparen();

    @Nullable
    PsiElement getRparen();

}