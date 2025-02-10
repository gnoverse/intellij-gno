package com.github.intellij.gno.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface GnoVarDeclaration extends PsiElement {
    @Nullable GnoPipeline getPipeline();

    @NotNull GnoVarDefinition getVarDefinition();

    @NotNull PsiElement getVarAssign();
}
