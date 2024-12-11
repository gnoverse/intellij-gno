package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import com.github.intellij.gno.stubs.GoVarDefinitionStub;
import com.intellij.psi.PsiReference;
import com.intellij.psi.ResolveState;

public interface GoVarDefinition extends GoNamedElement, StubBasedPsiElement<GoVarDefinitionStub> {

    @NotNull
    PsiElement getIdentifier();

    @Nullable
    GoType getGoTypeInner(ResolveState context);

    @Nullable
    PsiReference getReference();

    @Nullable
    GoExpression getValue();

}