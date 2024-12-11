package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.StubBasedPsiElement;
import com.github.intellij.gno.stubs.GoSignatureStub;

public interface GoSignature extends GoCompositeElement, StubBasedPsiElement<GoSignatureStub> {

    @NotNull
    GoParameters getParameters();

    @Nullable
    GoResult getResult();

}