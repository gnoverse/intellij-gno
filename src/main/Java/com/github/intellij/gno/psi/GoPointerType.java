package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GoPointerType extends GoType {

    @Nullable
    GoType getType();

    @NotNull
    PsiElement getMul();

}