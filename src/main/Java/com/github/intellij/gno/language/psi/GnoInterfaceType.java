package com.github.intellij.gno.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GnoInterfaceType extends GnoType {

    @NotNull
    List<GnoMethodSpec> getMethodSpecList();

    @Nullable
    PsiElement getLbrace();

    @Nullable
    PsiElement getRbrace();

    @NotNull
    PsiElement getInterface();

    @NotNull
    List<GnoMethodSpec> getMethods();

    @NotNull
    List<GnoTypeReferenceExpression> getBaseTypesReferences();

}