package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GoSelectorExpr extends GoBinaryExpr {

    @NotNull
    PsiElement getDot();

}