package com.github.intellij.gno.language.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GnoParType extends GnoType {

    @NotNull
    GnoType getType();

    @NotNull
    PsiElement getLparen();

    @NotNull
    PsiElement getRparen();

    @NotNull
    GnoType getActualType();

}
