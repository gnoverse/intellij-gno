package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import com.github.intellij.gno.stubs.GoImportSpecStub;

public interface GoImportSpec extends GoNamedElement, StubBasedPsiElement<GoImportSpecStub> {

    @NotNull
    GoImportString getImportString();

    @Nullable
    PsiElement getDot();

    @Nullable
    PsiElement getIdentifier();

    String getAlias();

    String getLocalPackageName();

    boolean shouldGoDeeper();

    boolean isForSideEffects();

    boolean isDot();

    @NotNull
    String getPath();

    String getName();

    boolean isCImport();

}
