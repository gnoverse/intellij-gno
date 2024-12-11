package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GoUnaryExpr extends GoExpression {

    @Nullable
    GoExpression getExpression();

    @Nullable
    PsiElement getBitAnd();

    @Nullable
    PsiElement getBitXor();

    @Nullable
    PsiElement getMinus();

    @Nullable
    PsiElement getMul();

    @Nullable
    PsiElement getNot();

    @Nullable
    PsiElement getPlus();

    @Nullable
    PsiElement getSendChannel();

    @Nullable
    PsiElement getOperator();

}