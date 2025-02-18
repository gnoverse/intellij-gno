// This is a generated file. Not intended for manual editing.
package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class GnoVisitor extends PsiElementVisitor {

  public void visitIdentifier(@NotNull GnoIdentifier o) {
    visitPsiElement(o);
  }

  public void visitToken(@NotNull GnoToken o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
