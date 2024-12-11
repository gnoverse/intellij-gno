package com.github.intellij.gno.psi;

import java.util.List;
import org.jetbrains.annotations.*;

public interface GoLeftHandExprList extends GoCompositeElement {

    @NotNull
    List<GoExpression> getExpressionList();

}