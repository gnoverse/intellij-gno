package com.github.intellij.gno.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface GnoWithStatement extends GnoStatement, GnoDeclarationOwner {
    @Nullable GnoPipeline getPipeline();

    @Nullable GnoStatement getStatement();

    @Nullable GnoStatementList getStatementList();

    @Nullable GnoVarDeclaration getVarDeclaration();

    @NotNull PsiElement getLdoubleBrace();

    @Nullable PsiElement getRdoubleBrace();

    @NotNull PsiElement getWith();
}
