package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;


public interface GoBuiltinCallExpr extends GoExpression {

    @Nullable
    GoBuiltinArgumentList getBuiltinArgumentList();

    @NotNull
    GoReferenceExpression getReferenceExpression();

}