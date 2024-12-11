package com.github.intellij.gno.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GoBinaryExpr extends GoExpression {

    @NotNull
    List<GoExpression> getExpressionList();

    @NotNull
    GoExpression getLeft();

    @Nullable
    GoExpression getRight();

    @Nullable
    PsiElement getOperator();

}