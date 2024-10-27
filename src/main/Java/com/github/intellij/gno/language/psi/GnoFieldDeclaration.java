package com.github.intellij.gno.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;

public interface GnoFieldDeclaration extends GnoCompositeElement {

    @Nullable
    GnoAnonymousFieldDefinition getAnonymousFieldDefinition();

    @NotNull
    List<GnoFieldDefinition> getFieldDefinitionList();

    @Nullable
    GnoTag getTag();

    @Nullable
    GnoType getType();

}