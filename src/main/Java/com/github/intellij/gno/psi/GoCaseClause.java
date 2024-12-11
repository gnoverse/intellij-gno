package com.github.intellij.gno.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface GoCaseClause extends GoCompositeElement {
  @Nullable
  PsiElement getColon();

  @NotNull
  List<GoStatement> getStatementList();

  @Nullable
  PsiElement getDefault();
}
