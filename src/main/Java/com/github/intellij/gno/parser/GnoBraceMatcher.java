package com.github.intellij.gno.parser;

import com.intellij.lang.BracePair;
import com.github.intellij.gno.psi.GnoTypes;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class GnoBraceMatcher implements PairedBraceMatcher, GnoTypes {
    public static final BracePair[] PAIRS;

    public GnoBraceMatcher() {
    }

    public BracePair @NotNull [] getPairs() {
        return PAIRS;
    }

    public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType lbraceType, @Nullable IElementType contextType) {
        return true;
    }

    public int getCodeConstructStart(PsiFile file, int openingBraceOffset) {
        return openingBraceOffset;
    }

    static {
        PAIRS = new BracePair[]{new BracePair(LDOUBLE_BRACE, RDOUBLE_BRACE, false), new BracePair(LPAREN, RPAREN, true)};
    }
}
