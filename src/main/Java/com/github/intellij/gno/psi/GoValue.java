package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;

public interface GoValue extends GoCompositeElement {

    @Nullable
    GoExpression getExpression();

    @Nullable
    GoLiteralValue getLiteralValue();

}