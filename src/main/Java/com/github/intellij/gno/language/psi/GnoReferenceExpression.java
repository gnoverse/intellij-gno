package com.github.intellij.gno.language.psi;


import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.goide.psi.impl.GnoReference;
import com.intellij.codeInsight.highlighting.ReadWriteAccessDetector.Access;

public interface GnoReferenceExpression extends GnoExpression, GnoReferenceExpressionBase {

    @NotNull
    PsiElement getIdentifier();

    @NotNull
    GnoReference getReference();

    @Nullable
    GnoReferenceExpression getQualifier();

    @Nullable
    PsiElement resolve();

    @NotNull
    Access getReadWriteAccess();

}