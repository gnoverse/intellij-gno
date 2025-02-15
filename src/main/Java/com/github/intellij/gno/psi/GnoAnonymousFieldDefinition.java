package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import com.github.intellij.gno.stubs.GnoAnonymousFieldDefinitionStub;
import com.intellij.psi.ResolveState;

public interface GnoAnonymousFieldDefinition extends GnoNamedElement, StubBasedPsiElement<GnoAnonymousFieldDefinitionStub> {

  @NotNull
  GnoType getType();

  @Nullable
  PsiElement getIdentifier();

  @Nullable
  String getName();

  @Nullable
  GnoTypeReferenceExpression getTypeReferenceExpression();

  @Nullable
  GnoType getGnoTypeInner(ResolveState context);

}
