package com.github.intellij.gno.language.psi;

import com.github.intellij.gno.services.GnoLanguage;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

public class GnoCompositeElementType extends IElementType {
    public GnoCompositeElementType(@NotNull String debug) {
        super(debug, GnoLanguage.INSTANCE);
    }
}