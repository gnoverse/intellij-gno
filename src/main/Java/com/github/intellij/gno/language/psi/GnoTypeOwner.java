package com.github.intellij.gno.language.psi;

import com.intellij.psi.ResolveState;
import org.jetbrains.annotations.Nullable;

public interface GnoTypeOwner extends GnoCompositeElement {
    @Nullable
    GnoType getGnoType(@Nullable ResolveState context);
}