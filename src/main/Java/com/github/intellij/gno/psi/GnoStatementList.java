package com.github.intellij.gno.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface GnoStatementList extends PsiElement {
    @NotNull List<GnoStatement> getStatementList();

    boolean processDeclarations(@NotNull PsiScopeProcessor var1, @NotNull ResolveState var2, @Nullable PsiElement var3, @NotNull PsiElement var4);
}
