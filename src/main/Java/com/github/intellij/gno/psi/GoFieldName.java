package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;

public interface GoFieldName extends GoReferenceExpressionBase {

    @NotNull
    PsiElement getIdentifier();

    @NotNull
    PsiReference getReference();

    @Nullable
    GoReferenceExpression getQualifier();

    @Nullable
    PsiElement resolve();

}