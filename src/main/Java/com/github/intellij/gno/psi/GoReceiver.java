package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import com.github.intellij.gno.stubs.GoReceiverStub;
import com.intellij.psi.ResolveState;

public interface GoReceiver extends GoNamedElement, StubBasedPsiElement<GoReceiverStub> {

    @Nullable
    GoType getType();

    @Nullable
    PsiElement getComma();

    @NotNull
    PsiElement getLparen();

    @Nullable
    PsiElement getRparen();

    @Nullable
    PsiElement getIdentifier();

    @Nullable
    GoType getGoTypeInner(ResolveState context);

}
