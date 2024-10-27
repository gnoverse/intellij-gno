package com.github.intellij.gno.language.psi;


import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GnoChannelType extends GnoType {

    @Nullable
    GnoType getType();

    @Nullable
    PsiElement getSendChannel();

    @Nullable
    PsiElement getChan();

}