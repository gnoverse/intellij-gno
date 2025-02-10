package com.github.intellij.gno.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface GnoDefineStatement extends GnoStatement {
    @Nullable GnoEndStatement getEndStatement();

    @Nullable GnoStatementList getStatementList();

    @Nullable GnoStringLiteral getStringLiteral();

    @NotNull PsiElement getDefine();

    @NotNull PsiElement getLdoubleBrace();

    @Nullable PsiElement getRdoubleBrace();
}
