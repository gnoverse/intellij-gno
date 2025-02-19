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

public class GnoSimpleStatementImpl extends ASTWrapperPsiElement implements GnoSimpleStatement {

  public GnoSimpleStatementImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull GnoVisitor visitor) {
    visitor.visitSimpleStatement(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof GnoVisitor) accept((GnoVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public GnoAssignStatement getAssignStatement() {
    return findChildByClass(GnoAssignStatement.class);
  }

  @Override
  @Nullable
  public GnoExpressionStatement getExpressionStatement() {
    return findChildByClass(GnoExpressionStatement.class);
  }

  @Override
  @Nullable
  public GnoIncDecStatement getIncDecStatement() {
    return findChildByClass(GnoIncDecStatement.class);
  }

  @Override
  @Nullable
  public GnoShortVarDeclaration getShortVarDeclaration() {
    return findChildByClass(GnoShortVarDeclaration.class);
  }

}
