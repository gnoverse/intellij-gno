package com.github.intellij.gno.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import com.github.intellij.gno.stubs.GoParameterDeclarationStub;

public interface GoParameterDeclaration extends GoCompositeElement, StubBasedPsiElement<GoParameterDeclarationStub> {

    @NotNull
    List<GoParamDefinition> getParamDefinitionList();

    @NotNull
    GoType getType();

    @Nullable
    PsiElement getTripleDot();

    boolean isVariadic();

}