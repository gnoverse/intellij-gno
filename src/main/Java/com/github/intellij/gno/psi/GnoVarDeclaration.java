// This is a generated file. Not intended for manual editing.
package com.github.intellij.gno.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GnoVarDeclaration extends PsiElement {

  @Nullable
  GnoExpression getExpression();

  @Nullable
  GnoTypeBody getTypeBody();

  @Nullable
  PsiElement getAssign();

  @Nullable
  PsiElement getIdentifier();

  @NotNull
  PsiElement getVar();

}
