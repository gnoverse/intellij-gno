package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;

public interface GoSimpleStatement extends GoStatement {

    @Nullable
    GoLeftHandExprList getLeftHandExprList();

    @Nullable
    GoShortVarDeclaration getShortVarDeclaration();

    @Nullable
    GoStatement getStatement();

}