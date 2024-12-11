package com.github.intellij.gno.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GoTypeDeclaration extends GoTopLevelDeclaration {

    @NotNull
    List<GoTypeSpec> getTypeSpecList();

    @Nullable
    PsiElement getLparen();

    @Nullable
    PsiElement getRparen();

    @NotNull
    PsiElement getType_();

}