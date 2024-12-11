package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GoGoStatement extends GoStatement {

    @Nullable
    GoExpression getExpression();

    @NotNull
    PsiElement getGo();

}