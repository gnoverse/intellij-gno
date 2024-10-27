package com.github.intellij.gno.language.psi;

import com.github.intellij.gno.services.GnoLanguage;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

public class GnoTokenType extends IElementType {
    public GnoTokenType(@NotNull String debug) {
        super(debug, GnoLanguage.INSTANCE);
    }
}
