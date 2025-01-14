// This is a generated file. Not intended for manual editing.
package com.github.intellij.gno.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GnoBlock extends PsiElement {

  @NotNull
  List<GnoStatement> getStatementList();

  @NotNull
  PsiElement getLbrace();

  @NotNull
  PsiElement getRbrace();

}
