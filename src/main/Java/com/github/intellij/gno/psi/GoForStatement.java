package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GoForStatement extends GoStatement {

    @Nullable
    GoExpression getExpression();

    @Nullable
    GoForClause getForClause();

    @Nullable
    GoRangeClause getRangeClause();

    @NotNull
    PsiElement getFor();

}