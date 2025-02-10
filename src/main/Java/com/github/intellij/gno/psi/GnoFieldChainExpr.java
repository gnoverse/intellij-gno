package com.github.intellij.gno.psi;

import com.intellij.psi.PsiElement;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface GnoFieldChainExpr extends GnoExpression {
    @NotNull List<GnoFieldChainExpr> getFieldChainExprList();

    @Nullable GnoExpression getQualifier();

    @NotNull PsiElement getDot();

    @NotNull PsiElement getIdentifier();
}
