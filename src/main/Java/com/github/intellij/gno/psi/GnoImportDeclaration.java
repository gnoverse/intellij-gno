// This is a generated file. Not intended for manual editing.
package com.github.intellij.gno.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GnoImportDeclaration extends PsiElement {

  @Nullable
  GnoImportSpec getImportSpec();

  @Nullable
  GnoImportSpecList getImportSpecList();

  @NotNull
  PsiElement getImport();

  @Nullable
  PsiElement getLparen();

  @Nullable
  PsiElement getRparen();

}
