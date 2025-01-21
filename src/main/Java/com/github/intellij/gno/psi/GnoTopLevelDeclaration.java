// This is a generated file. Not intended for manual editing.
package com.github.intellij.gno.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GnoTopLevelDeclaration extends PsiElement {

  @Nullable
  GnoConstDeclaration getConstDeclaration();

  @Nullable
  GnoFunctionDeclaration getFunctionDeclaration();

  @Nullable
  GnoPropertyDeclaration getPropertyDeclaration();

  @Nullable
  GnoTypeDeclaration getTypeDeclaration();

  @Nullable
  GnoVarDeclaration getVarDeclaration();

}
