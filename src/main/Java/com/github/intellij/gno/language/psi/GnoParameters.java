package com.github.intellij.gno.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import com.github.intellij.gno.language.stubs.GnoParametersStub;

public interface GnoParameters extends GnoCompositeElement, StubBasedPsiElement<GnoParametersStub> {

    @NotNull
    List<GnoParameterDeclaration> getParameterDeclarationList();

    @Nullable
    GnoType getType();

    @NotNull
    PsiElement getLparen();

    @Nullable
    PsiElement getRparen();

}
