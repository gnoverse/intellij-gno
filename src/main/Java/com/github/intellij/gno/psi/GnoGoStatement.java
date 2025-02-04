package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GnoGoStatement extends GnoStatement {

  @Nullable
  GnoExpression getExpression();

  @NotNull
  PsiElement getGno();

}
