package com.github.intellij.gno.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GoExprCaseClause extends GoCaseClause {

    @NotNull
    List<GoExpression> getExpressionList();

    @NotNull
    List<GoStatement> getStatementList();

    @Nullable
    PsiElement getColon();

    @Nullable
    PsiElement getCase();

    @Nullable
    PsiElement getDefault();

}