package com.github.intellij.gno.psi;

import com.github.intellij.gno.language.GnoLanguage;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

public class GnoTokenType extends IElementType {
    public GnoTokenType(@NotNull String debugName) {
        super(debugName, GnoLanguage.INSTANCE);
    }
}
