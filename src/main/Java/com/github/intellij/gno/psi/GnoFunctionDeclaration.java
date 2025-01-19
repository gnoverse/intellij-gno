// This is a generated file. Not intended for manual editing.
package com.github.intellij.gno.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GnoFunctionDeclaration extends PsiElement {

  @NotNull
  GnoBlock getBlock();

  @Nullable
  GnoReceiver getReceiver();

  @NotNull
  GnoSignature getSignature();

  @NotNull
  PsiElement getFunc();

  @NotNull
  PsiElement getIdentifier();

}
