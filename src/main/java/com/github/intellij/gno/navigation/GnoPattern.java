package com.github.intellij.gno.navigation;

import com.intellij.patterns.PlatformPatterns;
import com.intellij.patterns.PsiElementPattern;
import com.intellij.psi.PsiElement;

public class GnoPattern {
    public static PsiElementPattern.Capture<PsiElement> identifierPattern() {
        return PlatformPatterns.psiElement().withText(PlatformPatterns.string().matches("\\w+"));
    }
}
