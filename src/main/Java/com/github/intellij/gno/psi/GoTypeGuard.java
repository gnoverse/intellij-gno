package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GoTypeGuard extends GoCompositeElement {

    @NotNull
    PsiElement getLparen();

    @Nullable
    PsiElement getRparen();

    @NotNull
    PsiElement getType_();

}