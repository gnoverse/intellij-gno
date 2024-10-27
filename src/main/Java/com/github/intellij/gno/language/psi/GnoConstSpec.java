package com.github.intellij.gno.language.psi;

import java.util.List;

import com.github.intellij.gno.language.stubs.GnoConstSpecStub;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;

public interface GnoConstSpec extends GnoCompositeElement, StubBasedPsiElement<GnoConstSpecStub> {

    @NotNull
    List<GnoConstDefinition> getConstDefinitionList();

    @NotNull
    List<GnoExpression> getExpressionList();

    @Nullable
    GnoType getType();

    @Nullable
    PsiElement getAssign();

    void deleteDefinition(GnoConstDefinition definitionToDelete);

}
