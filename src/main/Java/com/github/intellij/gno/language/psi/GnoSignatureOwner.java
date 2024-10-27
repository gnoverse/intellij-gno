package com.github.intellij.gno.language.psi;

import org.jetbrains.annotations.Nullable;

public interface GnoSignatureOwner extends GnoCompositeElement {
    @Nullable
    GnoSignature getSignature();
}