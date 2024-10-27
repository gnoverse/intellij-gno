package com.github.intellij.gno.language.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface GnoFunctionOrMethodDeclaration extends GnoTopLevelDeclaration, GnoNamedSignatureOwner {

    @Nullable
    GnoBlock getBlock();

    @Override
    @Nullable
    GnoSignature getSignature();

    @NotNull
    PsiElement getFunc();

    @Override
    @Nullable
    PsiElement getIdentifier();
}