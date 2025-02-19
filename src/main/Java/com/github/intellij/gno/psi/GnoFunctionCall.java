// This is a generated file. Not intended for manual editing.
package com.github.intellij.gno.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GnoFunctionCall extends PsiElement {

  @NotNull
  List<GnoExpression> getExpressionList();

  @NotNull
  PsiElement getIdentifier();

}
