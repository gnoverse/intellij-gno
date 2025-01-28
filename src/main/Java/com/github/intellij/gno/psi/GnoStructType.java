// This is a generated file. Not intended for manual editing.
package com.github.intellij.gno.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GnoStructType extends PsiElement {

  @Nullable
  GnoFields getFields();

  @NotNull
  PsiElement getLbrace();

  @NotNull
  PsiElement getRbrace();

  @NotNull
  PsiElement getStruct();

}
