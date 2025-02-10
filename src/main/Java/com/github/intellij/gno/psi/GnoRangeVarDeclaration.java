package com.github.intellij.gno.psi;


import com.intellij.psi.PsiElement;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface GnoRangeVarDeclaration extends GnoVarDeclaration {
    @NotNull GnoPipeline getPipeline();

    @NotNull List<GnoVarDefinition> getVarDefinitionList();

    @Nullable PsiElement getComma();
}
