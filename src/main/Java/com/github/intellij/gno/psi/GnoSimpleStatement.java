// This is a generated file. Not intended for manual editing.
package com.github.intellij.gno.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GnoSimpleStatement extends PsiElement {

  @Nullable
  GnoAssignStatement getAssignStatement();

  @Nullable
  GnoExpressionStatement getExpressionStatement();

  @Nullable
  GnoIncDecStatement getIncDecStatement();

  @Nullable
  GnoShortVarDeclaration getShortVarDeclaration();

}
