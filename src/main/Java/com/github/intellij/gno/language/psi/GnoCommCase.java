package com.github.intellij.gno.language.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GnoCommCase extends GnoCompositeElement {

    @Nullable
    GnoRecvStatement getRecvStatement();

    @Nullable
    GnoSendStatement getSendStatement();

    @Nullable
    PsiElement getCase();

    @Nullable
    PsiElement getDefault();

}