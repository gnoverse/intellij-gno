package com.github.intellij.gno.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GnoRecvStatement extends GnoVarSpec {

    @NotNull
    List<GnoExpression> getExpressionList();

    @NotNull
    List<GnoVarDefinition> getVarDefinitionList();

    @Nullable
    PsiElement getVarAssign();

    @Nullable
    GnoExpression getRecvExpression();

    @NotNull
    List<GnoExpression> getLeftExpressionsList();

    @NotNull
    List<GnoExpression> getRightExpressionsList();

}
