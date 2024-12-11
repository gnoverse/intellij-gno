package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;

public interface GoCompositeLit extends GoExpression {

    @Nullable
    GoLiteralValue getLiteralValue();

    @Nullable
    GoType getType();

    @Nullable
    GoTypeReferenceExpression getTypeReferenceExpression();

}