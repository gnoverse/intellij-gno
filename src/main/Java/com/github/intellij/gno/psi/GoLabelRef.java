package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;

public interface GoLabelRef extends GoCompositeElement {

    @NotNull
    PsiElement getIdentifier();

    @NotNull
    PsiReference getReference();

}