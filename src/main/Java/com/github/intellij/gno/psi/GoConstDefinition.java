package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import com.github.intellij.gno.stubs.GoConstDefinitionStub;
import com.intellij.psi.ResolveState;

public interface GoConstDefinition extends GoNamedElement, StubBasedPsiElement<GoConstDefinitionStub> {

    @NotNull
    PsiElement getIdentifier();

    @Nullable
    GoType getGoTypeInner(ResolveState context);

    @Nullable
    GoExpression getValue();

}