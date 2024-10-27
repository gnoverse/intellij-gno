package com.github.intellij.gno.language.psi;

import com.intellij.navigation.NavigationItem;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface GnoNamedElement extends GnoCompositeElement, GnoTypeOwner, PsiNameIdentifierOwner, NavigationItem {
    boolean isPublic();

    @Nullable
    PsiElement getIdentifier();

    @Nullable
    String getQualifiedName();

    @Override
    @NotNull
    GnoFile getContainingFile();

    @Nullable
    GnoType findSiblingType();

    boolean isBlank();
}