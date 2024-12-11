package com.github.intellij.gno.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import com.github.intellij.gno.stubs.GoConstSpecStub;

public interface GoConstSpec extends GoCompositeElement, StubBasedPsiElement<GoConstSpecStub> {

    @NotNull
    List<GoConstDefinition> getConstDefinitionList();

    @NotNull
    List<GoExpression> getExpressionList();

    @Nullable
    GoType getType();

    @Nullable
    PsiElement getAssign();

    void deleteDefinition(GoConstDefinition definitionToDelete);

}