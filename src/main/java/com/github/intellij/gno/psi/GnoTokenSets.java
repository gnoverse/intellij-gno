package com.github.intellij.gno.psi;

import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

public class GnoTokenSets {
    public static final @NotNull TokenSet IDENTIFIERS = TokenSet.create(GnoTypes.IDENTIFIER);
    public static final @NotNull TokenSet COMMENTS = TokenSet.create(GnoTypes.COMMENT);
    public static final @NotNull TokenSet WHITESPACES = TokenSet.create(GnoTypes.WHITE_SPACE, GnoTypes.EOL);
    public static final @NotNull TokenSet ANY_TOKENS = TokenSet.create(
            GnoTypes.IDENTIFIER, GnoTypes.WHITE_SPACE, GnoTypes.EOL,
            GnoTypes.COMMENT, GnoTypes.DOT, GnoTypes.ANY_CHAR
    );
}