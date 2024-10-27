package com.github.intellij.gno.language.psi;


import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;

public interface GnoTypeReferenceExpression extends GnoReferenceExpressionBase {

    @NotNull
    PsiElement getIdentifier();

    @NotNull
    PsiReference getReference();

    @Nullable
    GnoTypeReferenceExpression getQualifier();

    @Nullable
    PsiElement resolve();

    @Nullable
    GnoType resolveType();

}