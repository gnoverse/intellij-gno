package com.github.intellij.gno.psi;

import java.util.List;
import org.jetbrains.annotations.*;

public interface GoImportList extends GoCompositeElement {

    @NotNull
    List<GoImportDeclaration> getImportDeclarationList();

    @NotNull
    GoImportSpec addImport(String packagePath, String alias);

}
