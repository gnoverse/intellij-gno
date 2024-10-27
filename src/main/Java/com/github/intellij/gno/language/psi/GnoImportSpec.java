package com.github.intellij.gno.language.psi;

import com.github.intellij.gno.language.stubs.GnoImportSpecStub;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;

public interface GnoImportSpec extends GnoNamedElement, StubBasedPsiElement<GnoImportSpecStub> {

    @NotNull
    GnoImportString getImportString();

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