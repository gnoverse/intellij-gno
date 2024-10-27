package com.github.intellij.gno.language.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GnoAndExpr extends GnoBinaryExpr {

    @NotNull
    PsiElement getCondAnd();

}
