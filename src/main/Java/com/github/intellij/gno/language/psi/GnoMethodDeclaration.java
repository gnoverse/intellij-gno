package com.github.intellij.gno.language.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import com.github.intellij.gno.language.stubs.GnoMethodDeclarationStub;

public interface GnoMethodDeclaration extends GnoFunctionOrMethodDeclaration, StubBasedPsiElement<GnoMethodDeclarationStub> {

    @Nullable
    GnoBlock getBlock();

    @Nullable
    GnoReceiver getReceiver();

    @Nullable
    GnoSignature getSignature();

    @NotNull
    PsiElement getFunc();

    @Nullable
    PsiElement getIdentifier();

    @Nullable
    GnoType getReceiverType();

}