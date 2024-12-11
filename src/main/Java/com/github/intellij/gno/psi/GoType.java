package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.StubBasedPsiElement;
import com.github.intellij.gno.stubs.GoTypeStub;

public interface GoType extends GoCompositeElement, StubBasedPsiElement<GoTypeStub> {

    @Nullable
    GoTypeReferenceExpression getTypeReferenceExpression();

    @NotNull
    GoType getUnderlyingType();

    boolean shouldGoDeeper();

}
