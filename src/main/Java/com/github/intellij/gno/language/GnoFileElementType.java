package com.github.intellij.gno.language;

import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.IStubFileElementType;

public final class GnoFileElementType extends IStubFileElementType {
    public static final IFileElementType INSTANCE = new GnoFileElementType();

    private GnoFileElementType() {
        super("GNO_TEMPLATE_FILE", GnoLanguage.INSTANCE);
    }
}
