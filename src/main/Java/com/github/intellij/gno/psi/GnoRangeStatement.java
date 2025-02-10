package com.github.intellij.gno.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface GnoRangeStatement extends GnoStatement {
    @Nullable GnoPipeline getPipeline();

    @Nullable GnoRangeVarDeclaration getRangeVarDeclaration();

    @Nullable GnoStatement getStatement();

    @Nullable GnoStatementList getStatementList();

    @NotNull PsiElement getLdoubleBrace();

    @NotNull PsiElement getRange();

    @Nullable PsiElement getRdoubleBrace();
}
