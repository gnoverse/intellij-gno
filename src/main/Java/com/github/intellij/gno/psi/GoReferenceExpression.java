package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.github.intellij.gno.psi.impl.GoReference;
import com.intellij.codeInsight.highlighting.ReadWriteAccessDetector.Access;

public interface GoReferenceExpression extends GoExpression, GoReferenceExpressionBase {

    @NotNull
    PsiElement getIdentifier();

    @NotNull
    GoReference getReference();

    @Nullable
    GoReferenceExpression getQualifier();

    @Nullable
    PsiElement resolve();

    @NotNull
    Access getReadWriteAccess();

}