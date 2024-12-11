package com.github.intellij.gno.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.util.Trinity;

public interface GoIndexOrSliceExpr extends GoExpression {

    @NotNull
    List<GoExpression> getExpressionList();

    @NotNull
    PsiElement getLbrack();

    @Nullable
    PsiElement getRbrack();

    @Nullable
    GoExpression getExpression();

    @NotNull
    Trinity<GoExpression, GoExpression, GoExpression> getIndices();

}