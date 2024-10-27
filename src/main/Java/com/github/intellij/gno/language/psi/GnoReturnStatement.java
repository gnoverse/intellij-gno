package com.github.intellij.gno.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GnoReturnStatement extends GnoStatement {

    @NotNull
    List<GnoExpression> getExpressionList();

    @NotNull
    PsiElement getReturn();

}