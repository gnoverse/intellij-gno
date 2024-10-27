package com.github.intellij.gno.language.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GnoIfStatement extends GnoStatement {

    @Nullable
    GnoElseStatement getElseStatement();

    @Nullable
    GnoExpression getExpression();

    @Nullable
    GnoStatement getStatement();

    @Nullable
    PsiElement getSemicolon();

    @NotNull
    PsiElement getIf();

}