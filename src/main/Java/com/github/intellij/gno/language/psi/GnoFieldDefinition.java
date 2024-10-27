package com.github.intellij.gno.language.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import com.github.intellij.gno.language.stubs.GnoFieldDefinitionStub;

public interface GnoFieldDefinition extends GnoNamedElement, StubBasedPsiElement<GnoFieldDefinitionStub> {

    @NotNull
    PsiElement getIdentifier();

}