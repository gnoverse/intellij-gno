package com.github.intellij.gno.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface GnoStatement extends PsiElement {
    boolean processDeclarations(@NotNull PsiScopeProcessor var1, @NotNull ResolveState var2, @Nullable PsiElement var3, @NotNull PsiElement var4);
}
