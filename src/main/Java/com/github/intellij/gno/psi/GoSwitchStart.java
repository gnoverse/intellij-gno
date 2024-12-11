package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GoSwitchStart extends GoCompositeElement {

    @NotNull
    PsiElement getSwitch();

}