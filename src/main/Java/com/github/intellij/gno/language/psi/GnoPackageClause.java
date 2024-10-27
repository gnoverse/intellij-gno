package com.github.intellij.gno.language.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import com.github.intellij.gno.language.stubs.GnoPackageClauseStub;

public interface GnoPackageClause extends GnoCompositeElement, StubBasedPsiElement<GnoPackageClauseStub> {

    @Nullable
    PsiElement getIdentifier();

    @NotNull
    PsiElement getPackage();

    @Nullable
    String getName();

}
