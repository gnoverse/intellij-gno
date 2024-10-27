package com.github.intellij.gno.language.psi;

import com.github.intellij.gno.language.util.GnoStringLiteralEscaper;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLanguageInjectionHost;

public interface GnoStringLiteral extends GnoExpression, PsiLanguageInjectionHost {

    @Nullable
    PsiElement getRawString();

    @Nullable
    PsiElement getString();

    boolean isValidHost();

    @NotNull
    GnoStringLiteralImpl updateText(String text);

    @NotNull
    GnoStringLiteralEscaper createLiteralTextEscaper();

    @NotNull
    String getDecodedText();

}
