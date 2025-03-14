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

public class GnoStatementImpl extends ASTWrapperPsiElement implements GnoStatement {

  public GnoStatementImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull GnoVisitor visitor) {
    visitor.visitStatement(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof GnoVisitor) accept((GnoVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public GnoAssignment getAssignment() {
    return findChildByClass(GnoAssignment.class);
  }

  @Override
  @Nullable
  public GnoDeclaration getDeclaration() {
    return findChildByClass(GnoDeclaration.class);
  }

  @Override
  @Nullable
  public GnoExpression getExpression() {
    return findChildByClass(GnoExpression.class);
  }

}
