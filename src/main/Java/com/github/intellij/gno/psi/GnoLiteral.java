package com.github.intellij.gno.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;

public interface GnoLiteral extends PsiElement {
    @Nullable GnoStringLiteral getStringLiteral();

    @Nullable PsiElement getAt();

    @Nullable PsiElement getChar();

    @Nullable PsiElement getDot();

    @Nullable PsiElement getFalse();

    @Nullable PsiElement getHash();

    @Nullable PsiElement getIdentifier();

    @Nullable PsiElement getNil();

    @Nullable PsiElement getNumber();

    @Nullable PsiElement getPercent();

    @Nullable PsiElement getTrue();
}
