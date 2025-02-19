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

public class GnoPrimaryExprImpl extends ASTWrapperPsiElement implements GnoPrimaryExpr {

  public GnoPrimaryExprImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull GnoVisitor visitor) {
    visitor.visitPrimaryExpr(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof GnoVisitor) accept((GnoVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<GnoCallSuffix> getCallSuffixList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, GnoCallSuffix.class);
  }

  @Override
  @Nullable
  public GnoCompositeLiteral getCompositeLiteral() {
    return findChildByClass(GnoCompositeLiteral.class);
  }

  @Override
  @Nullable
  public GnoExpression getExpression() {
    return findChildByClass(GnoExpression.class);
  }

  @Override
  @Nullable
  public GnoLiteral getLiteral() {
    return findChildByClass(GnoLiteral.class);
  }

  @Override
  @NotNull
  public List<GnoSelectorSuffix> getSelectorSuffixList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, GnoSelectorSuffix.class);
  }

  @Override
  @NotNull
  public List<GnoTypeAssertionSuffix> getTypeAssertionSuffixList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, GnoTypeAssertionSuffix.class);
  }

  @Override
  @Nullable
  public GnoTypeConversion getTypeConversion() {
    return findChildByClass(GnoTypeConversion.class);
  }

  @Override
  @Nullable
  public PsiElement getIdentifier() {
    return findChildByType(IDENTIFIER);
  }

  @Override
  @Nullable
  public PsiElement getLparen() {
    return findChildByType(LPAREN);
  }

  @Override
  @Nullable
  public PsiElement getRparen() {
    return findChildByType(RPAREN);
  }

}
