// This is a generated file. Not intended for manual editing.
package com.github.intellij.gno.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GnoStatement extends PsiElement {

  @Nullable
  GnoAssignment getAssignment();

  @Nullable
  GnoDeclaration getDeclaration();

  @Nullable
  GnoExpression getExpression();

}
