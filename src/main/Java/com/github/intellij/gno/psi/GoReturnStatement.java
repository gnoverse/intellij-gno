package com.github.intellij.gno.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GoReturnStatement extends GoStatement {

    @NotNull
    List<GoExpression> getExpressionList();

    @NotNull
    PsiElement getReturn();

}