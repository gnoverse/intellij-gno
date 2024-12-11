package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.ResolveState;

public interface GoExpression extends GoTypeOwner {

    @Nullable
    GoType getGoType(ResolveState context);
}
