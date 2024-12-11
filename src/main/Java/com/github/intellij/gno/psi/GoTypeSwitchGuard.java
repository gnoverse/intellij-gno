package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GoTypeSwitchGuard extends GoCompositeElement {

    @NotNull
    GoExpression getExpression();

    @NotNull
    GoTypeGuard getTypeGuard();

    @Nullable
    GoVarDefinition getVarDefinition();

    @NotNull
    PsiElement getDot();

    @Nullable
    PsiElement getVarAssign();

}