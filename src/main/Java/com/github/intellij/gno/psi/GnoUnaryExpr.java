// This is a generated file. Not intended for manual editing.
package com.github.intellij.gno.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GnoUnaryExpr extends PsiElement {

  @Nullable
  GnoPrimaryExpr getPrimaryExpr();

  @NotNull
  List<GnoUnaryOp> getUnaryOpList();

}
