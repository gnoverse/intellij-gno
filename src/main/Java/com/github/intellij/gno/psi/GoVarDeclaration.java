package com.github.intellij.gno.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GoVarDeclaration extends GoTopLevelDeclaration {

    @NotNull
    List<GoVarSpec> getVarSpecList();

    @Nullable
    PsiElement getLparen();

    @Nullable
    PsiElement getRparen();

    @NotNull
    PsiElement getVar();

    @NotNull
    GoVarSpec addSpec(String name, String type, String value, GoVarSpec specAnchor);

    void deleteSpec(GoVarSpec specToDelete);

}