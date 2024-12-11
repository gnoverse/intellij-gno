package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;

public interface GoCallExpr extends GoExpression {

    @NotNull
    GoArgumentList getArgumentList();

    @NotNull
    GoExpression getExpression();

}