// This is a generated file. Not intended for manual editing.
package com.github.intellij.gno.psi;

import com.github.intellij.gno.psi.impl.GnoWsImpl;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class GnoVisitor extends PsiElementVisitor {

  public void visitAssignment(@NotNull GnoAssignment o) {
    visitPsiElement(o);
  }

  public void visitDeclaration(@NotNull GnoDeclaration o) {
    visitPsiElement(o);
  }

  public void visitExpression(@NotNull GnoExpression o) {
    visitPsiElement(o);
  }

  public void visitFunctionCall(@NotNull GnoFunctionCall o) {
    visitPsiElement(o);
  }

  public void visitImportDecl(@NotNull GnoImportDecl o) {
    visitPsiElement(o);
  }

  public void visitImportSpec(@NotNull GnoImportSpec o) {
    visitPsiElement(o);
  }

  public void visitPackageDecl(@NotNull GnoPackageDecl o) {
    visitPsiElement(o);
  }

  public void visitStatement(@NotNull GnoStatement o) {
    visitPsiElement(o);
  }

  public void visitStringLiteral(@NotNull GnoStringLiteral o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

  public void visitWs(GnoWsImpl gnoWs) {
    visitPsiElement(gnoWs);
  }
}
