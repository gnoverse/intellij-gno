// This is a generated file. Not intended for manual editing.
package com.github.intellij.gno.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GnoPropertyDeclaration extends PsiElement {

  @NotNull
  GnoExpression getExpression();

  @NotNull
  PsiElement getIdentifier();

  @NotNull
  PsiElement getVarAssign();

  String getKey();

  String getValue();

  String getName();

}
