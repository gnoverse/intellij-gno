package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GoLabeledStatement extends GoStatement {

    @NotNull
    GoLabelDefinition getLabelDefinition();

    @Nullable
    GoStatement getStatement();

    @NotNull
    PsiElement getColon();

}