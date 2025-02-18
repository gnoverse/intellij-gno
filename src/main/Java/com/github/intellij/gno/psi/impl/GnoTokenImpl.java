// This is a generated file. Not intended for manual editing.
package com.github.intellij.gno.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.intellij.gno.psi.GnoTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.github.intellij.gno.psi.*;

public class GnoTokenImpl extends ASTWrapperPsiElement implements GnoToken {

  public GnoTokenImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull GnoVisitor visitor) {
    visitor.visitToken(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof GnoVisitor) accept((GnoVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public GnoIdentifier getIdentifier() {
    return findChildByClass(GnoIdentifier.class);
  }

  @Override
  @Nullable
  public PsiElement getAnyChar() {
    return findChildByType(ANY_CHAR);
  }

  @Override
  @Nullable
  public PsiElement getComment() {
    return findChildByType(COMMENT);
  }

  @Override
  @Nullable
  public PsiElement getEol() {
    return findChildByType(EOL);
  }

  @Override
  @Nullable
  public PsiElement getWhiteSpace() {
    return findChildByType(WHITE_SPACE);
  }

}
