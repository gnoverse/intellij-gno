package com.github.intellij.gno.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import com.github.intellij.gno.language.stubs.GnoTypeSpecStub;
import com.intellij.psi.ResolveState;

public interface GnoTypeSpec extends GnoNamedElement, StubBasedPsiElement<GnoTypeSpecStub> {

    @NotNull
    GnoSpecType getSpecType();

    @Nullable
    GnoType getGnoTypeInner(ResolveState context);

    @NotNull
    List<GnoMethodDeclaration> getMethods();

    boolean shouldGnoDeeper();

    @NotNull
    PsiElement getIdentifier();

}