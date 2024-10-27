package com.github.intellij.gno.language.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import com.github.intellij.gno.language.stubs.GnoTypeStub;

public interface GnoSpecType extends GnoType, StubBasedPsiElement<GnoTypeStub> {

    @Nullable
    GnoType getType();

    @NotNull
    PsiElement getIdentifier();

}