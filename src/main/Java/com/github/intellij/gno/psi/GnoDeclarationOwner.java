package com.github.intellij.gno.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;

public interface GnoDeclarationOwner extends PsiElement {
    @Nullable GnoVarDeclaration getVarDeclaration();
}
