package com.github.intellij.gno.language.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GnoFunctionType extends GnoType, GnoSignatureOwner {

    @Nullable
    GnoSignature getSignature();

    @NotNull
    PsiElement getFunc();

}