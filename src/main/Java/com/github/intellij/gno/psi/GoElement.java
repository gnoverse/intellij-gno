package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GoElement extends GoCompositeElement {

    @Nullable
    GoKey getKey();

    @Nullable
    GoValue getValue();

    @Nullable
    PsiElement getColon();

}