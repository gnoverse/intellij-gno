package com.github.intellij.gno.psi;

import com.github.intellij.gno.language.GnoLanguage;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class GnoTokenType extends IElementType {
    public GnoTokenType(@NotNull @NonNls String debugName) {
        super(debugName, GnoLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "GnoTokenType." + super.toString();
    }
}