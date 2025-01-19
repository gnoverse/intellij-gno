// This is a generated file. Not intended for manual editing.
package com.github.intellij.gno.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GnoCallExpression extends PsiElement {

  @Nullable
  GnoArguments getArguments();

  @NotNull
  PsiElement getIdentifier();

  @NotNull
  PsiElement getLparen();

  @Nullable
  PsiElement getRparen();

}
