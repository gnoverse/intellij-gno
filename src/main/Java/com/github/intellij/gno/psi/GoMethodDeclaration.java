package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import com.github.intellij.gno.stubs.GoMethodDeclarationStub;

public interface GoMethodDeclaration extends GoFunctionOrMethodDeclaration, StubBasedPsiElement<GoMethodDeclarationStub> {

    @Nullable
    GoBlock getBlock();

    @Nullable
    GoReceiver getReceiver();

    @Nullable
    GoSignature getSignature();

    @NotNull
    PsiElement getFunc();

    @Nullable
    PsiElement getIdentifier();

    @Nullable
    GoType getReceiverType();

}
