package com.github.intellij.gno.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface GnoAssignStatement extends GnoStatement {
    @Nullable GnoPipeline getPipeline();

    @NotNull GnoVariableExpr getVariableExpr();

    @NotNull PsiElement getAssign();

    @NotNull PsiElement getLdoubleBrace();

    @Nullable PsiElement getRdoubleBrace();
}
