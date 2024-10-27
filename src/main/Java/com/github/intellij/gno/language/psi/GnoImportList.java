package com.github.intellij.gno.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;

public interface GnoImportList extends GnoCompositeElement {

    @NotNull
    List<GnoImportDeclaration> getImportDeclarationList();

    @NotNull
    GnoImportSpec addImport(String packagePath, String alias);

}
