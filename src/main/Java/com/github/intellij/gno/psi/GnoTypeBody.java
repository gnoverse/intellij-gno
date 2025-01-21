// This is a generated file. Not intended for manual editing.
package com.github.intellij.gno.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GnoTypeBody extends PsiElement {

  @Nullable
  GnoArrayType getArrayType();

  @Nullable
  GnoFunctionType getFunctionType();

  @Nullable
  GnoMapType getMapType();

  @Nullable
  GnoPointerType getPointerType();

  @Nullable
  GnoQualifiedIdentifier getQualifiedIdentifier();

  @Nullable
  GnoStructType getStructType();

  @Nullable
  GnoTypeBody getTypeBody();

  @Nullable
  GnoTypeName getTypeName();

  @Nullable
  PsiElement getLparen();

  @Nullable
  PsiElement getRparen();

}
