package com.github.intellij.gno.language.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GnoConversionExpr extends GnoBinaryExpr {

    @Nullable
    GnoExpression getExpression();

    @NotNull
    GnoType getType();

    @Nullable
    PsiElement getComma();

    @NotNull
    PsiElement getLparen();

    @Nullable
    PsiElement getRparen();

}