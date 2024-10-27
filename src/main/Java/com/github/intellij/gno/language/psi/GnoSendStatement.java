package com.github.intellij.gno.language.psi;
import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GnoSendStatement extends GnoStatement {

    @NotNull
    List<GnoExpression> getExpressionList();

    @Nullable
    GnoLeftHandExprList getLeftHandExprList();

    @NotNull
    PsiElement getSendChannel();

    @Nullable
    GnoExpression getSendExpression();

}