// This is a generated file. Not intended for manual editing.
package com.github.intellij.gno.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GnoMapType extends PsiElement {

  @NotNull
  List<GnoTypeBody> getTypeBodyList();

  @Nullable
  PsiElement getLbrack();

  @NotNull
  PsiElement getMap();

  @Nullable
  PsiElement getRbrack();

}
