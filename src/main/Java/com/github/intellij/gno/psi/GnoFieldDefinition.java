package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import com.github.intellij.gno.stubs.GnoFieldDefinitionStub;

public interface GnoFieldDefinition extends GnoNamedElement, StubBasedPsiElement<GnoFieldDefinitionStub> {

  @NotNull
  PsiElement getIdentifier();

}
