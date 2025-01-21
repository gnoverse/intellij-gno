// This is a generated file. Not intended for manual editing.
package com.github.intellij.gno.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GnoVarSpec extends PsiElement {

  @Nullable
  GnoExpression getExpression();

  @NotNull
  GnoTypeBody getTypeBody();

  @Nullable
  PsiElement getAssign();

  @NotNull
  PsiElement getIdentifier();

}
