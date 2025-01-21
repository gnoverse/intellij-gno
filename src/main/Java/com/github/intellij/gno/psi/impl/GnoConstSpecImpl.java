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

public class GnoConstSpecImpl extends ASTWrapperPsiElement implements GnoConstSpec {

  public GnoConstSpecImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull GnoVisitor visitor) {
    visitor.visitConstSpec(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof GnoVisitor) accept((GnoVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public GnoExpression getExpression() {
    return findNotNullChildByClass(GnoExpression.class);
  }

  @Override
  @Nullable
  public GnoTypeAnnotation getTypeAnnotation() {
    return findChildByClass(GnoTypeAnnotation.class);
  }

  @Override
  @NotNull
  public PsiElement getAssign() {
    return findNotNullChildByType(ASSIGN);
  }

  @Override
  @NotNull
  public PsiElement getIdentifier() {
    return findNotNullChildByType(IDENTIFIER);
  }

}
