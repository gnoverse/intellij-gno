package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import com.github.intellij.gno.stubs.GoResultStub;

public interface GoResult extends GoCompositeElement, StubBasedPsiElement<GoResultStub> {

    @Nullable
    GoParameters getParameters();

    @Nullable
    GoType getType();

    @Nullable
    PsiElement getLparen();

    @Nullable
    PsiElement getRparen();

    boolean isVoid();

}
