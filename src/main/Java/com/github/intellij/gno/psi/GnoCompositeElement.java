package com.github.intellij.gno.psi;

import com.intellij.psi.PsiElement;

public interface GnoCompositeElement extends PsiElement {
  boolean shouldGnoDeeper();
}
