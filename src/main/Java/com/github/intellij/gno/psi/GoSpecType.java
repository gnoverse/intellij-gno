package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import com.github.intellij.gno.stubs.GoTypeStub;

public interface GoSpecType extends GoType, StubBasedPsiElement<GoTypeStub> {

    @Nullable
    GoType getType();

    @NotNull
    PsiElement getIdentifier();

}