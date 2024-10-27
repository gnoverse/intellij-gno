package com.github.intellij.gno.language.psi;

import org.jetbrains.annotations.*;

public interface GnoTag extends GnoCompositeElement {

    @NotNull
    GnoStringLiteral getStringLiteral();

}