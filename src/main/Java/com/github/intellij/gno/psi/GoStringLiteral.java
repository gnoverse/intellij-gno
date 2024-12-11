package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLanguageInjectionHost;
import com.github.intellij.gno.psi.impl.GoStringLiteralImpl;
import com.github.intellij.gno.util.GoStringLiteralEscaper;

public interface GoStringLiteral extends GoExpression, PsiLanguageInjectionHost {

    @Nullable
    PsiElement getRawString();

    @Nullable
    PsiElement getString();

    boolean isValidHost();

    @NotNull
    GoStringLiteralImpl updateText(String text);

    @NotNull
    GoStringLiteralEscaper createLiteralTextEscaper();

    @NotNull
    String getDecodedText();

}
