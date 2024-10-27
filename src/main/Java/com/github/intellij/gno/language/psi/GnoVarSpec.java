package com.github.intellij.gno.language.psi;

import java.util.List;

import com.github.intellij.gno.language.stubs.GnoVarSpecStub;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.*;
import com.intellij.psi.StubBasedPsiElement;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;

public interface GnoVarSpec extends GnoCompositeElement, StubBasedPsiElement<GnoVarSpecStub> {

    @NotNull
    List<GnoExpression> getExpressionList();

    @Nullable
    GnoType getType();

    @NotNull
    List<GnoVarDefinition> getVarDefinitionList();

    @Nullable
    PsiElement getAssign();

    boolean processDeclarations(@NotNull PsiScopeProcessor processor, @NotNull ResolveState state, PsiElement lastParent, @NotNull PsiElement place);

    void deleteDefinition(GnoVarDefinition definitionToDelete);

    @NotNull
    List<GnoExpression> getRightExpressionsList();

}
