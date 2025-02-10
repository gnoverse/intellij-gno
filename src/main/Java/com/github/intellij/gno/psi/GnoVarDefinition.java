package com.github.intellij.gno.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public interface GnoVarDefinition extends GnoNamedElement {
    @NotNull PsiElement getVariable();
}
