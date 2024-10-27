package com.github.intellij.gno.language.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import com.github.intellij.gno.language.stubs.GnoMethodSpecStub;
import com.intellij.psi.ResolveState;

public interface GnoMethodSpec extends GnoNamedSignatureOwner, StubBasedPsiElement<GnoMethodSpecStub> {

    @Nullable
    GnoSignature getSignature();

    @Nullable
    GnoTypeReferenceExpression getTypeReferenceExpression();

    @Nullable
    PsiElement getIdentifier();

    @Nullable
    GnoType getGnoTypeInner(ResolveState context);

    @Nullable
    String getName();

}