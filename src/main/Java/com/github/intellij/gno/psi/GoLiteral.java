package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GoLiteral extends GoExpression {

    @Nullable
    PsiElement getChar();

    @Nullable
    PsiElement getDecimali();

    @Nullable
    PsiElement getFloat();

    @Nullable
    PsiElement getFloati();

    @Nullable
    PsiElement getHex();

    @Nullable
    PsiElement getInt();

    @Nullable
    PsiElement getOct();

}