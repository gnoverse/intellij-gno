package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GoIfStatement extends GoStatement {

    @Nullable
    GoElseStatement getElseStatement();

    @Nullable
    GoExpression getExpression();

    @Nullable
    GoStatement getStatement();

    @Nullable
    PsiElement getSemicolon();

    @NotNull
    PsiElement getIf();

}