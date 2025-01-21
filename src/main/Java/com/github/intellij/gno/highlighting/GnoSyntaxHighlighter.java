package com.github.intellij.gno.highlighting;

import com.github.intellij.gno.lexer.GnoLexerAdapter;
import com.github.intellij.gno.psi.GnoTypes;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class GnoSyntaxHighlighter extends SyntaxHighlighterBase {

    // Highlighting keys
    public static final TextAttributesKey SEPARATOR =
            createTextAttributesKey("GNO_SEPARATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN);
    public static final TextAttributesKey KEY =
            createTextAttributesKey("GNO_KEY", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey VALUE =
            createTextAttributesKey("GNO_VALUE", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey COMMENT =
            createTextAttributesKey("GNO_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey BAD_CHARACTER =
            createTextAttributesKey("GNO_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);

    // Attribute arrays
    private static final TextAttributesKey[] BAD_CHAR_KEYS = new TextAttributesKey[]{BAD_CHARACTER};
    private static final TextAttributesKey[] SEPARATOR_KEYS = new TextAttributesKey[]{SEPARATOR};
    private static final TextAttributesKey[] KEY_KEYS = new TextAttributesKey[]{KEY};
    private static final TextAttributesKey[] VALUE_KEYS = new TextAttributesKey[]{VALUE};
    private static final TextAttributesKey[] COMMENT_KEYS = new TextAttributesKey[]{COMMENT};
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new GnoLexerAdapter();
    }

    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {
        System.out.println("🔍 Token analysé : " + tokenType);
        if (tokenType.equals(GnoTypes.COLON) || tokenType.equals(GnoTypes.ASSIGN)) {
            return SEPARATOR_KEYS;
        }
        if (isKeyword(tokenType)) {
            return KEY_KEYS;
        }
        if (tokenType.equals(GnoTypes.STRING) || tokenType.equals(GnoTypes.INT)) {
            return VALUE_KEYS;
        }
        if (tokenType.equals(GnoTypes.COMMENT)) {
            return COMMENT_KEYS;
        }
        if (tokenType.equals(TokenType.BAD_CHARACTER)) {
            return BAD_CHAR_KEYS;
        }
        return EMPTY_KEYS;
    }

    // Helper method to check if a token is a keyword
    private boolean isKeyword(IElementType tokenType) {
        return tokenType.equals(GnoTypes.IMPORT) ||
                tokenType.equals(GnoTypes.PACKAGE) ||
                tokenType.equals(GnoTypes.CONST) ||
                tokenType.equals(GnoTypes.TYPE) ||
                tokenType.equals(GnoTypes.FUNC) ||
                tokenType.equals(GnoTypes.VAR) ||
                tokenType.equals(GnoTypes.SWITCH) ||
                tokenType.equals(GnoTypes.CASE) ||
                tokenType.equals(GnoTypes.SELECT) ||
                tokenType.equals(GnoTypes.FOR) ||
                tokenType.equals(GnoTypes.IF) ||
                tokenType.equals(GnoTypes.ELSE) ||
                tokenType.equals(GnoTypes.RETURN) ||
                tokenType.equals(GnoTypes.MAP) ||
                tokenType.equals(GnoTypes.INTERFACE) ||
                tokenType.equals(GnoTypes.STRUCT) ||
                tokenType.equals(GnoTypes.STRING);
    }
}
