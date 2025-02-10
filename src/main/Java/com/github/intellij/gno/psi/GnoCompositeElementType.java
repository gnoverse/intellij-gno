package com.github.intellij.gno.psi;

import com.github.intellij.gno.language.GnoLanguage;
import com.intellij.psi.tree.IElementType;

public class GnoCompositeElementType extends IElementType {
    public GnoCompositeElementType(String expr) {
        super(expr, GnoLanguage.INSTANCE);
    }
}
