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

public class GnoTypeBodyImpl extends ASTWrapperPsiElement implements GnoTypeBody {

  public GnoTypeBodyImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull GnoVisitor visitor) {
    visitor.visitTypeBody(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof GnoVisitor) accept((GnoVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public GnoArrayType getArrayType() {
    return findChildByClass(GnoArrayType.class);
  }

  @Override
  @Nullable
  public GnoFunctionType getFunctionType() {
    return findChildByClass(GnoFunctionType.class);
  }

  @Override
  @Nullable
  public GnoMapType getMapType() {
    return findChildByClass(GnoMapType.class);
  }

  @Override
  @Nullable
  public GnoPointerType getPointerType() {
    return findChildByClass(GnoPointerType.class);
  }

  @Override
  @Nullable
  public GnoStructType getStructType() {
    return findChildByClass(GnoStructType.class);
  }

  @Override
  @Nullable
  public GnoTypeName getTypeName() {
    return findChildByClass(GnoTypeName.class);
  }

}
