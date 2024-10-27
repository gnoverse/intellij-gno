package com.github.intellij.gno.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GnoMapType extends GnoType {

    @NotNull
    List<GnoType> getTypeList();

    @Nullable
    PsiElement getLbrack();

    @Nullable
    PsiElement getRbrack();

    @NotNull
    PsiElement getMap();

    @Nullable
    GnoType getKeyType();

    @Nullable
    GnoType getValueType();

}