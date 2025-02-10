package com.github.intellij.gno.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface GnoTemplateStatement extends GnoStatement, GnoDeclarationOwner {
    @Nullable GnoPipeline getPipeline();

    @Nullable GnoStringLiteral getStringLiteral();

    @Nullable GnoVarDeclaration getVarDeclaration();

    @NotNull PsiElement getLdoubleBrace();

    @Nullable PsiElement getRdoubleBrace();

    @NotNull PsiElement getTemplate();
}
