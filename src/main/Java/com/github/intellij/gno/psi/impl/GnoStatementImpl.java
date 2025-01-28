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
  public GnoBlock getBlock() {
    return findChildByClass(GnoBlock.class);
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
  public GnoSimpleStatement getSimpleStatement() {
    return findChildByClass(GnoSimpleStatement.class);
  }

  @Override
  @Nullable
  public GnoSwitchStatement getSwitchStatement() {
    return findChildByClass(GnoSwitchStatement.class);
  }

  @Override
  @Nullable
  public PsiElement getEol() {
    return findChildByType(EOL);
  }

}
