package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GoArrayOrSliceType extends GoType {

    @Nullable
    GoExpression getExpression();

    @Nullable
    GoType getType();

    @NotNull
    PsiElement getLbrack();

    @Nullable
    PsiElement getRbrack();

    @Nullable
    PsiElement getTripleDot();

}