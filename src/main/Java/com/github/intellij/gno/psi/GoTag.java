package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;

public interface GoTag extends GoCompositeElement {

    @NotNull
    GoStringLiteral getStringLiteral();

}