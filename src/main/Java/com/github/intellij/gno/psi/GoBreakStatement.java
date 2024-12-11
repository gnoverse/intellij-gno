package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GoBreakStatement extends GoStatement {

    @Nullable
    GoLabelRef getLabelRef();

    @NotNull
    PsiElement getBreak();

}