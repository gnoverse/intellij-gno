package com.github.intellij.gno.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GoRangeClause extends GoVarSpec {

    @NotNull
    List<GoVarDefinition> getVarDefinitionList();

    @Nullable
    PsiElement getVarAssign();

    @Nullable
    PsiElement getRange();

    @Nullable
    GoExpression getRangeExpression();

    @NotNull
    List<GoExpression> getLeftExpressionsList();

    @NotNull
    List<GoExpression> getRightExpressionsList();

}