package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import com.github.intellij.gno.stubs.GoPackageClauseStub;

public interface GoPackageClause extends GoCompositeElement, StubBasedPsiElement<GoPackageClauseStub> {

    @Nullable
    PsiElement getIdentifier();

    @NotNull
    PsiElement getPackage();

    @Nullable
    String getName();

}
