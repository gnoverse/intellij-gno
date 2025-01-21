// This is a generated file. Not intended for manual editing.
package com.github.intellij.gno.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.navigation.ItemPresentation;

public interface GnoPropertyDeclaration extends GnoNamedElement {

  @NotNull
  GnoExpression getExpression();

  @NotNull
  PsiElement getIdentifier();

  @NotNull
  PsiElement getVarAssign();

  String getKey();

  String getValue();

  String getName();

  PsiElement setName(String newName);

  PsiElement getNameIdentifier();

  ItemPresentation getPresentation();

}
