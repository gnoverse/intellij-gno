package com.github.intellij.gno.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GoArgumentList extends GoCompositeElement {

    @NotNull
    List<GoExpression> getExpressionList();

    @NotNull
    PsiElement getLparen();

    @Nullable
    PsiElement getRparen();

    @Nullable
    PsiElement getTripleDot();

}