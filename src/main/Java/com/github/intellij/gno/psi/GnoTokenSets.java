package com.github.intellij.gno.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;

public final class GnoTokenSets {
    public static final GnoTokenType COMMENT = new GnoTokenType("GO_TEMPLATE_COMMENT");
    public static final TokenSet WHITESPACES;
    public static final TokenSet COMMENTS;
    public static final TokenSet KEYWORDS;
    public static final TokenSet PUNCTUATION;
    public static final TokenSet OPERATORS;
    public static final TokenSet STRINGS;
    public static final TokenSet LITERALS;
    public static final TokenSet IDENTIFIERS;

    private GnoTokenSets() {
    }

    static {
        WHITESPACES = TokenSet.WHITE_SPACE;
        COMMENTS = TokenSet.create(new IElementType[]{COMMENT});
        KEYWORDS = TokenSet.create(new IElementType[]{GnoTypes.BLOCK, GnoTypes.DEFINE, GnoTypes.ELSE, GnoTypes.END, GnoTypes.IF, GnoTypes.RANGE, GnoTypes.TEMPLATE, GnoTypes.WITH, GnoTypes.NIL, GnoTypes.TRUE, GnoTypes.FALSE});
        PUNCTUATION = TokenSet.create(new IElementType[]{GnoTypes.COMMA, GnoTypes.AT, GnoTypes.PERCENT, GnoTypes.HASH});
        OPERATORS = TokenSet.create(new IElementType[]{GnoTypes.ASSIGN, GnoTypes.BIT_OR, GnoTypes.VAR_ASSIGN});
        STRINGS = TokenSet.create(new IElementType[]{GnoTypes.STRING, GnoTypes.RAW_STRING, GnoTypes.CHAR});
        LITERALS = TokenSet.create(new IElementType[]{GnoTypes.STRING, GnoTypes.RAW_STRING, GnoTypes.CHAR, GnoTypes.NUMBER});
        IDENTIFIERS = TokenSet.create(new IElementType[]{GnoTypes.IDENTIFIER, GnoTypes.VARIABLE});
    }
}
