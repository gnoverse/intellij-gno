package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import com.github.intellij.gno.stubs.GoAnonymousFieldDefinitionStub;
import com.intellij.psi.ResolveState;

public interface GoAnonymousFieldDefinition extends GoNamedElement, StubBasedPsiElement<GoAnonymousFieldDefinitionStub> {

    @NotNull
    GoType getType();

    @Nullable
    PsiElement getIdentifier();

    @Nullable
    String getName();

    @Nullable
    GoTypeReferenceExpression getTypeReferenceExpression();

    @Nullable
    GoType getGoTypeInner(ResolveState context);

}