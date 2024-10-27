package com.github.intellij.gno.language.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import com.github.intellij.gno.language.stubs.GnoParamDefinitionStub;

public interface GnoParamDefinition extends GnoNamedElement, StubBasedPsiElement<GnoParamDefinitionStub> {

    @NotNull
    PsiElement getIdentifier();

    boolean isVariadic();

}