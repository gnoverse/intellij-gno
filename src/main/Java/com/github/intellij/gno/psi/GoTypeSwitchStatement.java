package com.github.intellij.gno.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GoTypeSwitchStatement extends GoSwitchStatement {

    @Nullable
    GoStatement getStatement();

    @NotNull
    GoSwitchStart getSwitchStart();

    @NotNull
    List<GoTypeCaseClause> getTypeCaseClauseList();

    @NotNull
    GoTypeSwitchGuard getTypeSwitchGuard();

    @Nullable
    PsiElement getLbrace();

    @Nullable
    PsiElement getRbrace();

    @Nullable
    PsiElement getSemicolon();

}