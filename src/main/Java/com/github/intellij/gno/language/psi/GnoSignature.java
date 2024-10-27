package com.github.intellij.gno.language.psi;


import org.jetbrains.annotations.*;
import com.intellij.psi.StubBasedPsiElement;
import com.github.intellij.gno.language.stubs.GnoSignatureStub;

public interface GnoSignature extends GnoCompositeElement, StubBasedPsiElement<GnoSignatureStub> {

    @NotNull
    GnoParameters getParameters();

    @Nullable
    GnoResult getResult();

}