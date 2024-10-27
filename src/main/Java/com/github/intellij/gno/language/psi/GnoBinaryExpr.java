package com.github.intellij.gno.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GnoBinaryExpr extends GnoExpression {

    @NotNull
    List<GnoExpression> getExpressionList();

    @NotNull
    GnoExpression getLeft();

    @Nullable
    GnoExpression getRight();

    @Nullable
    PsiElement getOperator();

}