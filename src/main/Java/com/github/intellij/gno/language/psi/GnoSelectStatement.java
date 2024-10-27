package com.github.intellij.gno.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GnoSelectStatement extends GnoStatement {

    @NotNull
    List<GnoCommClause> getCommClauseList();

    @Nullable
    PsiElement getLbrace();

    @Nullable
    PsiElement getRbrace();

    @NotNull
    PsiElement getSelect();

}