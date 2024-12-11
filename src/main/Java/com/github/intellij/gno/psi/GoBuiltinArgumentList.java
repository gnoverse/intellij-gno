package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;

public interface GoBuiltinArgumentList extends GoArgumentList {

    @Nullable
    GoType getType();

}