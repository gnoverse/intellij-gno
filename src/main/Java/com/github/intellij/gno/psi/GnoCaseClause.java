// This is a generated file. Not intended for manual editing.
package com.github.intellij.gno.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GnoCaseClause extends PsiElement {

  @NotNull
  GnoExpression getExpression();

  @NotNull
  List<GnoStatement> getStatementList();

  @NotNull
  PsiElement getCase();

  @NotNull
  PsiElement getColon();

}
