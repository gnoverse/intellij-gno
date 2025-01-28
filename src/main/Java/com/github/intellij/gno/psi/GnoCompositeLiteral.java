// This is a generated file. Not intended for manual editing.
package com.github.intellij.gno.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GnoCompositeLiteral extends PsiElement {

  @Nullable
  GnoFieldLiteralList getFieldLiteralList();

  @NotNull
  GnoTypeName getTypeName();

  @Nullable
  PsiElement getEol();

  @Nullable
  PsiElement getLbrace();

  @Nullable
  PsiElement getRbrace();

}
