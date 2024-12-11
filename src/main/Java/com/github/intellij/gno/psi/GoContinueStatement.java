package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GoContinueStatement extends GoStatement {

    @Nullable
    GoLabelRef getLabelRef();

    @NotNull
    PsiElement getContinue();

}