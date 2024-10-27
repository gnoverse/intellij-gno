package com.github.intellij.gno.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;

public interface GnoTypeList extends GnoType {

    @NotNull
    List<GnoType> getTypeList();

}