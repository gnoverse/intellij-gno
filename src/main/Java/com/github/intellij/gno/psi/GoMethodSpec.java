package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import com.github.intellij.gno.stubs.GoMethodSpecStub;
import com.intellij.psi.ResolveState;

public interface GoMethodSpec extends GoNamedSignatureOwner, StubBasedPsiElement<GoMethodSpecStub> {

    @Nullable
    GoSignature getSignature();

    @Nullable
    GoTypeReferenceExpression getTypeReferenceExpression();

    @Nullable
    PsiElement getIdentifier();

    @Nullable
    GoType getGoTypeInner(ResolveState context);

    @Nullable
    String getName();

}