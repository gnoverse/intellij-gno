package com.github.intellij.gno.psi;

import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

public class GnoTokenSets {
    public static final @NotNull TokenSet IDENTIFIERS = TokenSet.create(GnoTypes.IDENTIFIER);
    public static final @NotNull TokenSet COMMENTS = TokenSet.create(GnoTypes.COMMENT);
    public static final @NotNull TokenSet STRING_LITERALS = TokenSet.create(GnoTypes.STRING);
    public static final @NotNull TokenSet KEYWORDS = TokenSet.create(
            GnoTypes.IMPORT,
            GnoTypes.CONST,
            GnoTypes.TYPE,
            GnoTypes.FUNC,
            GnoTypes.VAR,
            GnoTypes.SWITCH,
            GnoTypes.CASE,
            GnoTypes.SELECT,
            GnoTypes.FOR,
            GnoTypes.IF,
            GnoTypes.ELSE,
            GnoTypes.RETURN,
            GnoTypes.MAP,
            GnoTypes.INTERFACE,
            GnoTypes.STRUCT
    );
}
