package com.github.intellij.gno.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GoSelectStatement extends GoStatement {

    @NotNull
    List<GoCommClause> getCommClauseList();

    @Nullable
    PsiElement getLbrace();

    @Nullable
    PsiElement getRbrace();

    @NotNull
    PsiElement getSelect();

}