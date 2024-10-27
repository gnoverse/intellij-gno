package com.github.intellij.gno.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GnoCommClause extends GnoCompositeElement {

    @NotNull
    GnoCommCase getCommCase();

    @NotNull
    List<GnoStatement> getStatementList();

    @Nullable
    PsiElement getColon();

}