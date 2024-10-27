package com.github.intellij.gno.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import com.github.intellij.gno.language.stubs.GnoParameterDeclarationStub;

public interface GnoParameterDeclaration extends GnoCompositeElement, StubBasedPsiElement<GnoParameterDeclarationStub> {

    @NotNull
    List<GnoParamDefinition> getParamDefinitionList();

    @NotNull
    GnoType getType();

    @Nullable
    PsiElement getTripleDot();

    boolean isVariadic();

}