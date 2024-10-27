package com.github.intellij.gno.language.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GnoArrayOrSliceType extends GnoType {

    @Nullable
    GnoExpression getExpression();

    @Nullable
    GnoType getType();

    @NotNull
    PsiElement getLbrack();

    @Nullable
    PsiElement getRbrack();

    @Nullable
    PsiElement getTripleDot();

}