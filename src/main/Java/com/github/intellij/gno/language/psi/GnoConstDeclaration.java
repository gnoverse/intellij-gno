package com.github.intellij.gno.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GnoConstDeclaration extends GoTopLevelDeclaration {

    @NotNull
    List<GoConstSpec> getConstSpecList();

    @Nullable
    PsiElement getLparen();

    @Nullable
    PsiElement getRparen();

    @NotNull
    PsiElement getConst();

    @NotNull
    GoConstSpec addSpec(String name, String type, String value, GoConstSpec specAnchor);

    void deleteSpec(GoConstSpec specToDelete);

}