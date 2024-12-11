package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GoOrExpr extends GoBinaryExpr {

    @NotNull
    PsiElement getCondOr();

}