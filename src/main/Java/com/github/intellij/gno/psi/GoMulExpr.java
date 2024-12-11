package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GoMulExpr extends GoBinaryExpr {

    @Nullable
    PsiElement getBitAnd();

    @Nullable
    PsiElement getBitClear();

    @Nullable
    PsiElement getMul();

    @Nullable
    PsiElement getQuotient();

    @Nullable
    PsiElement getRemainder();

    @Nullable
    PsiElement getShiftLeft();

    @Nullable
    PsiElement getShiftRight();

}