package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;

public interface GoFunctionLit extends GoExpression, GoSignatureOwner {

    @Nullable
    GoBlock getBlock();

    @Nullable
    GoSignature getSignature();

    @NotNull
    PsiElement getFunc();

    boolean processDeclarations(PsiScopeProcessor processor, ResolveState state, PsiElement lastParent, PsiElement place);

}