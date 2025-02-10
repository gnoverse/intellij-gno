package com.github.intellij.gno.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import org.jetbrains.annotations.NotNull;

public interface GnoVariableExpr extends GnoExpression {
    @NotNull PsiElement getVariable();

    @NotNull PsiReference getReference();
}
