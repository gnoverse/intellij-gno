package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GoElseStatement extends GoStatement {

    @Nullable
    GoIfStatement getIfStatement();

    @NotNull
    PsiElement getElse();

}