// This is a generated file. Not intended for manual editing.
package com.github.intellij.gno.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GnoResult extends PsiElement {

  @Nullable
  GnoParameters getParameters();

  @Nullable
  GnoTypeBody getTypeBody();

  @Nullable
  PsiElement getLparen();

  @Nullable
  PsiElement getRparen();

}
