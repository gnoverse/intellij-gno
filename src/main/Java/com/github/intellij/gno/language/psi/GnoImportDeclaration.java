package com.github.intellij.gno.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GnoImportDeclaration extends GnoCompositeElement {

    @NotNull
    List<GnoImportSpec> getImportSpecList();

    @Nullable
    PsiElement getLparen();

    @Nullable
    PsiElement getRparen();

    @NotNull
    PsiElement getImport();

    @NotNull
    GnoImportSpec addImportSpec(String packagePath, String alias);

}