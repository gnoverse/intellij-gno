// This is a generated file. Not intended for manual editing.
package com.github.intellij.gno.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.intellij.gno.psi.GnoTypes.*;
import com.github.intellij.gno.psi.*;
import com.intellij.navigation.ItemPresentation;

public class GnoPropertyDeclarationImpl extends GnoNamedElementImpl implements GnoPropertyDeclaration {

  public GnoPropertyDeclarationImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull GnoVisitor visitor) {
    visitor.visitPropertyDeclaration(this);
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
  @NotNull
  public PsiElement getIdentifier() {
    return findNotNullChildByType(IDENTIFIER);
  }

  @Override
  @NotNull
  public PsiElement getVarAssign() {
    return findNotNullChildByType(VAR_ASSIGN);
  }

  @Override
  public String getKey() {
    return GnoPsiImplUtil.getKey(this);
  }

  @Override
  public String getValue() {
    return GnoPsiImplUtil.getValue(this);
  }

  @Override
  public String getName() {
    return GnoPsiImplUtil.getName(this);
  }

  @Override
  public PsiElement setName(String newName) {
    return GnoPsiImplUtil.setName(this, newName);
  }

  @Override
  public PsiElement getNameIdentifier() {
    return GnoPsiImplUtil.getNameIdentifier(this);
  }

  @Override
  public ItemPresentation getPresentation() {
    return GnoPsiImplUtil.getPresentation(this);
  }

}
