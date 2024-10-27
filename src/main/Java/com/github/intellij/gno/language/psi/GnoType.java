package com.github.intellij.gno.language.psi;


import com.github.intellij.gno.language.stubs.GnoTypeStub;
import org.jetbrains.annotations.*;
import com.intellij.psi.StubBasedPsiElement;

public interface GnoType extends GnoCompositeElement, StubBasedPsiElement<GnoTypeStub> {

    @Nullable
    GnoTypeReferenceExpression getTypeReferenceExpression();

    @NotNull
    GnoType getUnderlyingType();


    boolean shouldGoDeeper();
}