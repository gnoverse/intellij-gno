package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GoAddExpr extends GoBinaryExpr {

    @Nullable
    PsiElement getBitOr();

    @Nullable
    PsiElement getBitXor();

    @Nullable
    PsiElement getMinus();

    @Nullable
    PsiElement getPlus();

}