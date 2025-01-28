// This is a generated file. Not intended for manual editing.
package com.github.intellij.gno.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GnoPrimaryExpr extends PsiElement {

  @NotNull
  List<GnoCallSuffix> getCallSuffixList();

  @Nullable
  GnoCompositeLiteral getCompositeLiteral();

  @Nullable
  GnoExpression getExpression();

  @Nullable
  GnoLiteral getLiteral();

  @NotNull
  List<GnoSelectorSuffix> getSelectorSuffixList();

  @NotNull
  List<GnoTypeAssertionSuffix> getTypeAssertionSuffixList();

  @Nullable
  GnoTypeConversion getTypeConversion();

  @Nullable
  PsiElement getIdentifier();

  @Nullable
  PsiElement getLparen();

  @Nullable
  PsiElement getRparen();

}
