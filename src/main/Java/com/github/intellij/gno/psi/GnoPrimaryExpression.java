// This is a generated file. Not intended for manual editing.
package com.github.intellij.gno.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GnoPrimaryExpression extends PsiElement {

  @NotNull
  List<GnoCallSuffix> getCallSuffixList();

  @Nullable
  GnoLiteral getLiteral();

  @NotNull
  List<GnoSelectorSuffix> getSelectorSuffixList();

  @Nullable
  PsiElement getIdentifier();

}
