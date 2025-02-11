package com.github.intellij.gno.highlighting;

import com.github.intellij.gno.lexer.GnoLexer;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

public final class GnoSyntaxHighlighter extends SyntaxHighlighterBase {
    public static final TextAttributesKey COMMENT;
    public static final TextAttributesKey PUNCTUATION;
    public static final TextAttributesKey COMMA;
    public static final TextAttributesKey DOT;
    public static final TextAttributesKey KEYWORD;
    public static final TextAttributesKey VARIABLE;
    public static final TextAttributesKey STRING;
    public static final TextAttributesKey OPERATOR;
    public static final TextAttributesKey IDENTIFIER;
    public static final TextAttributesKey BRACES;
    public static final TextAttributesKey PARENTHESES;
    public static final TextAttributesKey NUMBER;
    public static final TextAttributesKey DELIMITER;
    public static final TextAttributesKey BAD_CHARACTER;
    public static final TextAttributesKey BACKGROUND;

    GnoSyntaxHighlighter() {
    }

    static {
        COMMENT = TextAttributesKey.createTextAttributesKey("GO_TEMPLATE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
        PUNCTUATION = TextAttributesKey.createTextAttributesKey("GO_TEMPLATE_PUNCTUATION", DefaultLanguageHighlighterColors.COMMA);
        COMMA = TextAttributesKey.createTextAttributesKey("GO_TEMPLATE_COMMA", DefaultLanguageHighlighterColors.COMMA);
        DOT = TextAttributesKey.createTextAttributesKey("GO_TEMPLATE_DOT", DefaultLanguageHighlighterColors.DOT);
        KEYWORD = TextAttributesKey.createTextAttributesKey("GO_TEMPLATE_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
        VARIABLE = TextAttributesKey.createTextAttributesKey("GO_TEMPLATE_VARIABLE", DefaultLanguageHighlighterColors.LOCAL_VARIABLE);
        STRING = TextAttributesKey.createTextAttributesKey("GO_TEMPLATE_STRING", DefaultLanguageHighlighterColors.STRING);
        OPERATOR = TextAttributesKey.createTextAttributesKey("GO_TEMPLATE_OPERATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN);
        IDENTIFIER = TextAttributesKey.createTextAttributesKey("GO_TEMPLATE_IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER);
        BRACES = TextAttributesKey.createTextAttributesKey("GO_TEMPLATE_BRACES", DefaultLanguageHighlighterColors.BRACES);
        PARENTHESES = TextAttributesKey.createTextAttributesKey("GO_TEMPLATE_PARENTHESES", DefaultLanguageHighlighterColors.PARENTHESES);
        NUMBER = TextAttributesKey.createTextAttributesKey("GO_TEMPLATE_NUMBER", DefaultLanguageHighlighterColors.NUMBER);
        DELIMITER = TextAttributesKey.createTextAttributesKey("GO_TEMPLATE_DELIMITER", DefaultLanguageHighlighterColors.BRACES);
        BAD_CHARACTER = TextAttributesKey.createTextAttributesKey("GO_TEMPLATE_BAD_TOKEN", HighlighterColors.BAD_CHARACTER);
        BACKGROUND = TextAttributesKey.createTextAttributesKey("GO_TEMPLATE_BACKGROUND", DefaultLanguageHighlighterColors.TEMPLATE_LANGUAGE_COLOR);
    }

    @Override
    public @NotNull Lexer getHighlightingLexer() {
        return new GnoLexer();
    }

    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {
        return new TextAttributesKey[0];
    }
}
