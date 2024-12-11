package com.github.intellij.gno.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GoMapType extends GoType {

    @NotNull
    List<GoType> getTypeList();

    @Nullable
    PsiElement getLbrack();

    @Nullable
    PsiElement getRbrack();

    @NotNull
    PsiElement getMap();

    @Nullable
    GoType getKeyType();

    @Nullable
    GoType getValueType();

}