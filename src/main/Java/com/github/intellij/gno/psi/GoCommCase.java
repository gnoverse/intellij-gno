package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GoCommCase extends GoCompositeElement {

    @Nullable
    GoRecvStatement getRecvStatement();

    @Nullable
    GoSendStatement getSendStatement();

    @Nullable
    PsiElement getCase();

    @Nullable
    PsiElement getDefault();

}