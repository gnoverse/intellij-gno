package com.github.intellij.gno.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;

public interface GnoNamedElement extends PsiNameIdentifierOwner {

    String getKey(); //IDENTIFIER

    String getValue(); //EXPRESSION

    @Override
    String getName();

    @Override
    PsiElement setName(String newName);

    @Override
    PsiElement getNameIdentifier();
}
