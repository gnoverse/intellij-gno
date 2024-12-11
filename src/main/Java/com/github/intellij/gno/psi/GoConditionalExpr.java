package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GoConditionalExpr extends GoBinaryExpr {

    @Nullable
    PsiElement getEq();

    @Nullable
    PsiElement getGreater();

    @Nullable
    PsiElement getGreaterOrEqual();

    @Nullable
    PsiElement getLess();

    @Nullable
    PsiElement getLessOrEqual();

    @Nullable
    PsiElement getNotEq();

}