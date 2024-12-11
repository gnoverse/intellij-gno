package com.github.intellij.gno.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import com.github.intellij.gno.stubs.GoParametersStub;

public interface GoParameters extends GoCompositeElement, StubBasedPsiElement<GoParametersStub> {

    @NotNull
    List<GoParameterDeclaration> getParameterDeclarationList();

    @Nullable
    GoType getType();

    @NotNull
    PsiElement getLparen();

    @Nullable
    PsiElement getRparen();

}