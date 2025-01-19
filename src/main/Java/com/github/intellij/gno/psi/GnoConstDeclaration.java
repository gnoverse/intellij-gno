// This is a generated file. Not intended for manual editing.
package com.github.intellij.gno.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GnoConstDeclaration extends PsiElement {

  @Nullable
  GnoExpression getExpression();

  @Nullable
  PsiElement getAssign();

  @NotNull
  PsiElement getConst();

  @Nullable
  PsiElement getIdentifier();

}
