package com.github.intellij.gno.highlighting;

import com.github.intellij.gno.lexer.GnoLexer;
import com.github.intellij.gno.lexer.GnoTokenTypes;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.editor.DefaultLanguageHighlighterColors.*;

public class GnoSyntaxHighlighter extends SyntaxHighlighterBase {
    public static final TextAttributesKey KEYWORD =
            TextAttributesKey.createTextAttributesKey("GNO_KEYWORD", KEYWORD);
    public static final TextAttributesKey STRING =
            TextAttributesKey.createTextAttributesKey("GNO_STRING", STRING);
    public static final TextAttributesKey COMMENT =
            TextAttributesKey.createTextAttributesKey("GNO_COMMENT", LINE_COMMENT);

    private static final TextAttributesKey[] EMPTY = new TextAttributesKey[0];

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new GnoLexer();
    }

    @NotNull
    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {
        if (tokenType.equals(GnoTokenTypes.KEYWORD)) {
            return pack(KEYWORD);
        } else if (tokenType.equals(GnoTokenTypes.STRING)) {
            return pack(STRING);
        } else if (tokenType.equals(GnoTokenTypes.COMMENT)) {
            return pack(COMMENT);
        }
        return EMPTY;
    }
}

