package com.github.intellij.gno.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GoSendStatement extends GoStatement {

    @NotNull
    List<GoExpression> getExpressionList();

    @Nullable
    GoLeftHandExprList getLeftHandExprList();

    @NotNull
    PsiElement getSendChannel();

    @Nullable
    GoExpression getSendExpression();

}