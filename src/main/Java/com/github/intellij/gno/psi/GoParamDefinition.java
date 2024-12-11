package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import com.github.intellij.gno.stubs.GoParamDefinitionStub;

public interface GoParamDefinition extends GoNamedElement, StubBasedPsiElement<GoParamDefinitionStub> {

    @NotNull
    PsiElement getIdentifier();

    boolean isVariadic();

}