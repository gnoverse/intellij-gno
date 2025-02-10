package com.github.intellij.gno.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface GnoStringLiteral extends PsiElement {
    @Nullable PsiElement getRawString();

    @Nullable PsiElement getString();

    PsiReference @NotNull [] getReferences();

    @NotNull GnoStringLiteral updateText(@NotNull String var1);
}
