package com.github.intellij.gno.psi;

import java.util.List;
import org.jetbrains.annotations.*;

public interface GoTypeList extends GoType {

    @NotNull
    List<GoType> getTypeList();

}