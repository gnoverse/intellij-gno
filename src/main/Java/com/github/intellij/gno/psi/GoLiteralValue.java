package com.github.intellij.gno.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GoLiteralValue extends GoCompositeElement {

    @NotNull
    List<GoElement> getElementList();

    @NotNull
    PsiElement getLbrace();

    @Nullable
    PsiElement getRbrace();

}