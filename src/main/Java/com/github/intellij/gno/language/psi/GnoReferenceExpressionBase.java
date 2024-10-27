package com.github.intellij.gno.language.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface GnoReferenceExpressionBase extends GnoCompositeElement {
    @NotNull
    PsiElement getIdentifier();

    @Nullable
    GnoReferenceExpressionBase getQualifier();
}