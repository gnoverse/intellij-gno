package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import com.github.intellij.gno.stubs.GoLabelDefinitionStub;

public interface GoLabelDefinition extends GoNamedElement, StubBasedPsiElement<GoLabelDefinitionStub> {

    @NotNull
    PsiElement getIdentifier();

}