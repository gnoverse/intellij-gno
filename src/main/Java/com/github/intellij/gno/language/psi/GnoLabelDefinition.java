package com.github.intellij.gno.language.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import com.github.intellij.gno.language.stubs.GnoLabelDefinitionStub;

public interface GnoLabelDefinition extends GnoNamedElement, StubBasedPsiElement<GnoLabelDefinitionStub> {

    @NotNull
    PsiElement getIdentifier();

}