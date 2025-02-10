package com.github.intellij.gno.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface GnoElseStatement extends GnoStatement {
    @Nullable GnoEndStatement getEndStatement();

    @Nullable GnoStatementList getStatementList();

    @NotNull PsiElement getElse();

    @NotNull PsiElement getLdoubleBrace();

    @Nullable PsiElement getRdoubleBrace();
}
