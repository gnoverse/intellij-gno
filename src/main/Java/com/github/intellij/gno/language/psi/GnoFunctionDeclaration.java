package com.github.intellij.gno.language.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import com.github.intellij.gno.language.stubs.GnoFunctionDeclarationStub;

public interface GnoFunctionDeclaration extends GnoFunctionOrMethodDeclaration, StubBasedPsiElement<GnoFunctionDeclarationStub> {

    @Nullable
    GnoBlock getBlock();

    @Nullable
    GnoSignature getSignature();

    @NotNull
    PsiElement getFunc();

    @NotNull
    PsiElement getIdentifier();

}