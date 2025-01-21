// This is a generated file. Not intended for manual editing.
package com.github.intellij.gno.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GnoMulExpr extends PsiElement {

  @NotNull
  List<GnoMulOp> getMulOpList();

  @NotNull
  List<GnoUnaryExpr> getUnaryExprList();

}
