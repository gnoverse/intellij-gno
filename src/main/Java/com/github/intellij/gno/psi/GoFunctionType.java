package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GoFunctionType extends GoType, GoSignatureOwner {

    @Nullable
    GoSignature getSignature();

    @NotNull
    PsiElement getFunc();

}