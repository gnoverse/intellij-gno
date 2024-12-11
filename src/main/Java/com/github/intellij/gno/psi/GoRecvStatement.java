package com.github.intellij.gno.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GoRecvStatement extends GoVarSpec {

    @NotNull
    List<GoExpression> getExpressionList();

    @NotNull
    List<GoVarDefinition> getVarDefinitionList();

    @Nullable
    PsiElement getVarAssign();

    @Nullable
    GoExpression getRecvExpression();

    @NotNull
    List<GoExpression> getLeftExpressionsList();

    @NotNull
    List<GoExpression> getRightExpressionsList();

}