package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;

public interface GoTypeReferenceExpression extends GoReferenceExpressionBase {

    @NotNull
    PsiElement getIdentifier();

    @NotNull
    PsiReference getReference();

    @Nullable
    GoTypeReferenceExpression getQualifier();

    @Nullable
    PsiElement resolve();

    @Nullable
    GoType resolveType();

}