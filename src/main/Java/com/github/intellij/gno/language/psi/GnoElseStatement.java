package com.github.intellij.gno.language.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GnoElseStatement extends GnoStatement {

    @Nullable
    GnoIfStatement getIfStatement();

    @NotNull
    PsiElement getElse();

}