package com.github.intellij.gno.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GnoLeftHandExprList extends GnoCompositeElement {

    @NotNull
    List<GnoExpression> getExpressionList();

}
