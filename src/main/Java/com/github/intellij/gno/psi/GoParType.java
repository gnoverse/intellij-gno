package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GoParType extends GoType {

    @NotNull
    GoType getType();

    @NotNull
    PsiElement getLparen();

    @NotNull
    PsiElement getRparen();

    @NotNull
    GoType getActualType();

}