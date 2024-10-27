package com.github.intellij.gno.language.psi;

import org.jetbrains.annotations.*;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiReference;

public interface GnoImportString extends GnoCompositeElement {

    @NotNull
    GnoStringLiteral getStringLiteral();

    @NotNull
    PsiReference @NotNull [] getReferences();

    @Nullable
    PsiDirectory resolve();

    @NotNull
    String getPath();

    @NotNull
    TextRange getPathTextRange();

}