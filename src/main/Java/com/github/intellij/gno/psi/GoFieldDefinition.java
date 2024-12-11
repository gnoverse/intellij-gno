package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import com.github.intellij.gno.stubs.GoFieldDefinitionStub;

public interface GoFieldDefinition extends GoNamedElement, StubBasedPsiElement<GoFieldDefinitionStub> {

    @NotNull
    PsiElement getIdentifier();

}