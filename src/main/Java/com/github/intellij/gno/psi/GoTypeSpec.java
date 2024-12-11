package com.github.intellij.gno.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import com.github.intellij.gno.stubs.GoTypeSpecStub;
import com.intellij.psi.ResolveState;

public interface GoTypeSpec extends GoNamedElement, StubBasedPsiElement<GoTypeSpecStub> {

    @NotNull
    GoSpecType getSpecType();

    @Nullable
    GoType getGoTypeInner(ResolveState context);

    @NotNull
    List<GoMethodDeclaration> getMethods();

    boolean shouldGoDeeper();

    @NotNull
    PsiElement getIdentifier();

}