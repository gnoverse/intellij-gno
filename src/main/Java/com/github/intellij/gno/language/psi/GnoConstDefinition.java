package com.github.intellij.gno.language.psi;


import com.github.intellij.gno.language.stubs.GnoConstDefinitionStub;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import com.intellij.psi.ResolveState;

public interface GnoConstDefinition extends GnoNamedElement, StubBasedPsiElement<GnoConstDefinitionStub> {

    @NotNull
    PsiElement getIdentifier();

    @Nullable
    GnoType getGoTypeInner(ResolveState context);

    @Nullable
    GnoExpression getValue();

}