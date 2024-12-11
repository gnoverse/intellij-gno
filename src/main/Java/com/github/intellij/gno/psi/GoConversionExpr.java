package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GoConversionExpr extends GoBinaryExpr {

    @Nullable
    GoExpression getExpression();

    @NotNull
    GoType getType();

    @Nullable
    PsiElement getComma();

    @NotNull
    PsiElement getLparen();

    @Nullable
    PsiElement getRparen();

}