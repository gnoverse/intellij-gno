package com.github.intellij.gno.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GnoArgumentList extends GnoCompositeElement {

    @NotNull
    List<GnoExpression> getExpressionList();

    @NotNull
    PsiElement getLparen();

    @Nullable
    PsiElement getRparen();

    @Nullable
    PsiElement getTripleDot();

}