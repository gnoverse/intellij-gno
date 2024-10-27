package com.github.intellij.gno.language.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.ResolveState;

public interface GnoExpression extends GnoTypeOwner {

    @Nullable
    GnoType getGnoType(ResolveState context);
}