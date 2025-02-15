package com.github.intellij.gno.highlighting;

import com.github.intellij.gno.parser.GnoParserDefinition;
import com.github.intellij.gno.GnoTypes;
import com.github.intellij.gno.lexer.GnoLexer;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import static com.github.intellij.gno.highlighting.GnoSyntaxHighlightingColors.*;

public class GnoSyntaxHighlighter extends SyntaxHighlighterBase {
  private static final Map<IElementType, TextAttributesKey> ATTRIBUTES = new HashMap<IElementType, TextAttributesKey>();

  static {
    fillMap(ATTRIBUTES, LINE_COMMENT, GnoParserDefinition.LINE_COMMENT);
    fillMap(ATTRIBUTES, BLOCK_COMMENT, GnoParserDefinition.MULTILINE_COMMENT);
    fillMap(ATTRIBUTES, PARENTHESES, GnoTypes.LPAREN, GnoTypes.RPAREN);
    fillMap(ATTRIBUTES, BRACES, GnoTypes.LBRACE, GnoTypes.RBRACE);
    fillMap(ATTRIBUTES, BRACKETS, GnoTypes.LBRACK, GnoTypes.RBRACK);
    fillMap(ATTRIBUTES, BAD_CHARACTER, TokenType.BAD_CHARACTER);
    fillMap(ATTRIBUTES, IDENTIFIER, GnoTypes.IDENTIFIER);
    fillMap(ATTRIBUTES, DOT, GnoTypes.DOT, GnoTypes.TRIPLE_DOT);
    fillMap(ATTRIBUTES, COLON, GnoTypes.COLON);
    fillMap(ATTRIBUTES, SEMICOLON, GnoTypes.SEMICOLON);
    fillMap(ATTRIBUTES, COMMA, GnoTypes.COMMA);
    fillMap(ATTRIBUTES, GnoParserDefinition.OPERATORS, OPERATOR);
    fillMap(ATTRIBUTES, GnoParserDefinition.KEYWORDS, KEYWORD);
    fillMap(ATTRIBUTES, GnoParserDefinition.NUMBERS, NUMBER);
    fillMap(ATTRIBUTES, GnoParserDefinition.STRING_LITERALS, STRING);
    fillMap(ATTRIBUTES, FUNCTION_NAME, GnoTypes.FUNCTION_DECLARATION);
    fillMap(ATTRIBUTES, KEY_VALUE, GnoTypes.KEY);
    fillMap(ATTRIBUTES, TYPE_NAME, GnoTypes.TYPE_REFERENCE);
  }

  @NotNull
  public Lexer getHighlightingLexer() {
    return new GnoLexer();
  }

  @NotNull
  public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
    return pack(ATTRIBUTES.get(tokenType));
  }
}