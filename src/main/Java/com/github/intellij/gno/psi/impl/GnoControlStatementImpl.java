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

public class GnoControlStatementImpl extends ASTWrapperPsiElement implements GnoControlStatement {

  public GnoControlStatementImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull GnoVisitor visitor) {
    visitor.visitControlStatement(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof GnoVisitor) accept((GnoVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public GnoForStatement getForStatement() {
    return findChildByClass(GnoForStatement.class);
  }

  @Override
  @Nullable
  public GnoIfStatement getIfStatement() {
    return findChildByClass(GnoIfStatement.class);
  }

  @Override
  @Nullable
  public GnoReturnStatement getReturnStatement() {
    return findChildByClass(GnoReturnStatement.class);
  }

  @Override
  @Nullable
  public GnoSwitchStatement getSwitchStatement() {
    return findChildByClass(GnoSwitchStatement.class);
  }

}
