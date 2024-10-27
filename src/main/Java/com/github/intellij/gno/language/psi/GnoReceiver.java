package com.github.intellij.gno.language.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import com.github.intellij.gno.language.stubs.GnoReceiverStub;
import com.intellij.psi.ResolveState;

public interface GnoReceiver extends GnoNamedElement, StubBasedPsiElement<GnoReceiverStub> {

    @Nullable
    GnoType getType();

    @Nullable
    PsiElement getComma();

    @NotNull
    PsiElement getLparen();

    @Nullable
    PsiElement getRparen();

    @Nullable
    PsiElement getIdentifier();

    @Nullable
    GnoType getGnoTypeInner(ResolveState context);

}