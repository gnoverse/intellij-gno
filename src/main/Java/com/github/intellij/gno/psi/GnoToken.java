// This is a generated file. Not intended for manual editing.
package com.github.intellij.gno.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GnoToken extends PsiElement {

  @Nullable
  GnoIdentifier getIdentifier();

  @Nullable
  PsiElement getAnyChar();

  @Nullable
  PsiElement getComment();

  @Nullable
  PsiElement getEol();

  @Nullable
  PsiElement getWhiteSpace();

}
