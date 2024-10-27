package com.github.intellij.gno.language.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GnoAddExpr extends GnoBinaryExpr {

    @Nullable
    PsiElement getBitOr();

    @Nullable
    PsiElement getBitXor();

    @Nullable
    PsiElement getMinus();

    @Nullable
    PsiElement getPlus();

}