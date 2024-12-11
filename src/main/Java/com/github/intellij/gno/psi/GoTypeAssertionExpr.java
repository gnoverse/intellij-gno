package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GoTypeAssertionExpr extends GoExpression {

    @NotNull
    GoExpression getExpression();

    @NotNull
    GoType getType();

    @NotNull
    PsiElement getDot();

    @NotNull
    PsiElement getLparen();

    @NotNull
    PsiElement getRparen();

}