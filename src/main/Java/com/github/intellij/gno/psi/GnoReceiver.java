// This is a generated file. Not intended for manual editing.
package com.github.intellij.gno.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GnoReceiver extends PsiElement {

  @NotNull
  GnoReceiverParameter getReceiverParameter();

  @NotNull
  PsiElement getLparen();

  @NotNull
  PsiElement getRparen();

}
