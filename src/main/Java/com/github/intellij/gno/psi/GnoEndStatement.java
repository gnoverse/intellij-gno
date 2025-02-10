package com.github.intellij.gno.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface GnoEndStatement extends GnoStatement {
    @NotNull PsiElement getEnd();

    @NotNull PsiElement getLdoubleBrace();

    @Nullable PsiElement getRdoubleBrace();
}
