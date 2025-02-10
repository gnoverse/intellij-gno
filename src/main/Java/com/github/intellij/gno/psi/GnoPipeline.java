package com.github.intellij.gno.psi;

import com.intellij.psi.PsiElement;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public interface GnoPipeline extends PsiElement {
    @NotNull List<GnoExpression> getExpressionList();
}
