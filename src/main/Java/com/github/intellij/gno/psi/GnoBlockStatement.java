package com.github.intellij.gno.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface GnoBlockStatement extends GnoStatement, GnoDeclarationOwner {
    @Nullable GnoEndStatement getEndStatement();

    @Nullable GnoPipeline getPipeline();

    @Nullable GnoStatementList getStatementList();

    @Nullable GnoStringLiteral getStringLiteral();

    @Nullable GnoVarDeclaration getVarDeclaration();

    @NotNull PsiElement getBlock();

    @NotNull PsiElement getLdoubleBrace();

    @Nullable PsiElement getRdoubleBrace();
}
