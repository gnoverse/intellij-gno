package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiReference;

public interface GoImportString extends GoCompositeElement {

    @NotNull
    GoStringLiteral getStringLiteral();

    @NotNull
    PsiReference[] getReferences();

    @Nullable
    PsiDirectory resolve();

    @NotNull
    String getPath();

    @NotNull
    TextRange getPathTextRange();

}
