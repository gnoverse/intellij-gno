package com.github.intellij.gno.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;

public interface GnoBlock extends GnoCompositeElement {

    @NotNull
    List<GnoStatement> getStatementList();

    @NotNull
    PsiElement getLbrace();

    @Nullable
    PsiElement getRbrace();

    boolean processDeclarations(@NotNull PsiScopeProcessor processor, @NotNull ResolveState state, PsiElement lastParent, @NotNull PsiElement place);

}