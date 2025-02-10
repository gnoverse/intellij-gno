package com.github.intellij.gno.psi;

import org.jetbrains.annotations.NotNull;

public interface GnoLiteralExpr extends GnoExpression {
    @NotNull GnoLiteral getLiteral();
}
