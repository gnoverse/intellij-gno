package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;

public interface GoLiteralTypeExpr extends GoExpression {

    @Nullable
    GoType getType();

    @Nullable
    GoTypeReferenceExpression getTypeReferenceExpression();

}