package com.github.intellij.gno.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GnoPsiTreeUtil;
import static com.github.intellij.gno.GnoTypes.*;
import com.github.intellij.gno.psi.*;

public class GnoGoStatementImpl extends GnoStatementImpl implements GnoGoStatement {

  public GnoGoStatementImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull GnoVisitor visitor) {
    visitor.visitGnoStatement(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof GnoVisitor) accept((GnoVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public GnoExpression getExpression() {
    return GnoPsiTreeUtil.getChildOfType(this, GnoExpression.class);
  }

  @Override
  @NotNull
  public PsiElement getGno() {
    return notNullChild(findChildByType(GNO));
  }

}
