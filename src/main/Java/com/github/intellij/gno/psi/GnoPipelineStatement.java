package com.github.intellij.gno.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface GnoPipelineStatement extends GnoStatement {
    @NotNull GnoPipeline getPipeline();

    @NotNull PsiElement getLdoubleBrace();

    @Nullable PsiElement getRdoubleBrace();
}
