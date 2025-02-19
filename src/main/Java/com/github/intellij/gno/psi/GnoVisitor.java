// This is a generated file. Not intended for manual editing.
package com.github.intellij.gno.psi;

import com.github.intellij.gno.psi.impl.GnoIdentifierImpl;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class GnoVisitor extends PsiElementVisitor {

  public void visitPackageDecl(@NotNull GnoPackageDecl o) {
    visitPsiElement(o);
  }

  public void visitToken(@NotNull GnoToken o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

    public void visitIdentifier(GnoIdentifierImpl gnoIdentifier) {
    visitPsiElement(gnoIdentifier);
    }
}
