package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;

public interface GoKey extends GoCompositeElement {

    @Nullable
    GoExpression getExpression();

    @Nullable
    GoFieldName getFieldName();

}