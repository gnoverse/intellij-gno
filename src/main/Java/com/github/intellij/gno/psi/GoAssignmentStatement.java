package com.github.intellij.gno.psi;

import java.util.List;
import org.jetbrains.annotations.*;

public interface GoAssignmentStatement extends GoStatement {

    @NotNull
    List<GoExpression> getExpressionList();

    @NotNull
    GoLeftHandExprList getLeftHandExprList();

    @NotNull
    GoAssignOp getAssignOp();

}