package com.github.intellij.gno.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GoCommClause extends GoCompositeElement {

    @NotNull
    GoCommCase getCommCase();

    @NotNull
    List<GoStatement> getStatementList();

    @Nullable
    PsiElement getColon();

}