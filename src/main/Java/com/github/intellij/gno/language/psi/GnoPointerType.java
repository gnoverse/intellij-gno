package com.github.intellij.gno.language.psi;


import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GnoPointerType extends GnoType {

    @Nullable
    GnoType getType();

    @NotNull
    PsiElement getMul();

}