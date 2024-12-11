package com.github.intellij.gno.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GoExprSwitchStatement extends GoSwitchStatement {

    @NotNull
    List<GoExprCaseClause> getExprCaseClauseList();

    @Nullable
    GoExpression getExpression();

    @Nullable
    GoStatement getStatement();

    @NotNull
    GoSwitchStart getSwitchStart();

    @NotNull
    PsiElement getLbrace();

    @Nullable
    PsiElement getRbrace();

    @Nullable
    PsiElement getSemicolon();

}