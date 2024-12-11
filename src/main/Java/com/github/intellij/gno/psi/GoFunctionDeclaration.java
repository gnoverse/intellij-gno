package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import com.github.intellij.gno.stubs.GoFunctionDeclarationStub;

public interface GoFunctionDeclaration extends GoFunctionOrMethodDeclaration, StubBasedPsiElement<GoFunctionDeclarationStub> {

    @Nullable
    GoBlock getBlock();

    @Nullable
    GoSignature getSignature();

    @NotNull
    PsiElement getFunc();

    @NotNull
    PsiElement getIdentifier();

}
