package com.github.intellij.gno.psi;

import java.util.List;
import org.jetbrains.annotations.*;

public interface GoFieldDeclaration extends GoCompositeElement {

    @Nullable
    GoAnonymousFieldDefinition getAnonymousFieldDefinition();

    @NotNull
    List<GoFieldDefinition> getFieldDefinitionList();

    @Nullable
    GoTag getTag();

    @Nullable
    GoType getType();

}