package com.github.intellij.gno.language.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import com.github.intellij.gno.language.stubs.GnoResultStub;

public interface GnoResult extends GnoCompositeElement, StubBasedPsiElement<GnoResultStub> {

    @Nullable
    GnoParameters getParameters();

    @Nullable
    GnoType getType();

    @Nullable
    PsiElement getLparen();

    @Nullable
    PsiElement getRparen();

    boolean isVoid();

}