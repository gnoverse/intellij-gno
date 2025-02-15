package com.github.intellij.gno.highlighting;

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class GnoSyntaxHighlightingColors {
  public static final TextAttributesKey LINE_COMMENT = createTextAttributesKey("GNO_LINE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
  public static final TextAttributesKey BLOCK_COMMENT = createTextAttributesKey("GNO_BLOCK_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT);
  public static final TextAttributesKey KEYWORD = createTextAttributesKey("GNO_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
  public static final TextAttributesKey STRING = createTextAttributesKey("GNO_STRING", DefaultLanguageHighlighterColors.STRING);
  public static final TextAttributesKey NUMBER = createTextAttributesKey("GNO_NUMBER", DefaultLanguageHighlighterColors.NUMBER);
  public static final TextAttributesKey BRACKETS = createTextAttributesKey("GNO_BRACKET", DefaultLanguageHighlighterColors.BRACKETS);
  public static final TextAttributesKey BRACES = createTextAttributesKey("GNO_BRACES", DefaultLanguageHighlighterColors.BRACES);
  public static final TextAttributesKey PARENTHESES = createTextAttributesKey("GNO_PARENTHESES", DefaultLanguageHighlighterColors.PARENTHESES);
  public static final TextAttributesKey OPERATOR = createTextAttributesKey("GNO_OPERATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN);
  public static final TextAttributesKey IDENTIFIER = createTextAttributesKey("GNO_IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER);
  public static final TextAttributesKey DOT = createTextAttributesKey("GNO_DOT", DefaultLanguageHighlighterColors.DOT);
  public static final TextAttributesKey SEMICOLON = createTextAttributesKey("GNO_SEMICOLON", DefaultLanguageHighlighterColors.SEMICOLON);
  public static final TextAttributesKey COLON = createTextAttributesKey("GNO_COLON", HighlighterColors.TEXT);
  public static final TextAttributesKey COMMA = createTextAttributesKey("GNO_COMMA", DefaultLanguageHighlighterColors.COMMA);
  public static final TextAttributesKey BAD_CHARACTER = createTextAttributesKey("GNO_BAD_TOKEN", HighlighterColors.BAD_CHARACTER);
  public static final TextAttributesKey TYPE_SPECIFICATION = createTextAttributesKey("GNO_TYPE_SPECIFICATION", DefaultLanguageHighlighterColors.CLASS_NAME);
  public static final TextAttributesKey TYPE_REFERENCE = createTextAttributesKey("GNO_TYPE_REFERENCE", DefaultLanguageHighlighterColors.CLASS_REFERENCE);
  public static final TextAttributesKey BUILTIN_TYPE_REFERENCE = createTextAttributesKey("GNO_BUILTIN_TYPE_REFERENCE", DefaultLanguageHighlighterColors.CLASS_REFERENCE);
  public static final TextAttributesKey BUILTIN_FUNCTION = createTextAttributesKey("GNO_BUILTIN_FUNCTION", DefaultLanguageHighlighterColors.FUNCTION_DECLARATION);
  public static final TextAttributesKey EXPORTED_FUNCTION = createTextAttributesKey("GNO_EXPORTED_FUNCTION", DefaultLanguageHighlighterColors.FUNCTION_DECLARATION);
  public static final TextAttributesKey LOCAL_FUNCTION = createTextAttributesKey("GNO_LOCAL_FUNCTION", DefaultLanguageHighlighterColors.FUNCTION_DECLARATION);
  public static final TextAttributesKey PACKAGE_EXPORTED_INTERFACE = createTextAttributesKey("GNO_PACKAGE_EXPORTED_INTERFACE", DefaultLanguageHighlighterColors.INTERFACE_NAME);
  public static final TextAttributesKey PACKAGE_EXPORTED_STRUCT = createTextAttributesKey("GNO_PACKAGE_EXPORTED_STRUCT", DefaultLanguageHighlighterColors.CLASS_NAME);
  public static final TextAttributesKey PACKAGE_EXPORTED_CONSTANT = createTextAttributesKey("GNO_PACKAGE_EXPORTED_CONSTANT", DefaultLanguageHighlighterColors.CONSTANT);
  public static final TextAttributesKey PACKAGE_EXPORTED_VARIABLE = createTextAttributesKey("GNO_PACKAGE_EXPORTED_VARIABLE", DefaultLanguageHighlighterColors.GLOBAL_VARIABLE);
  public static final TextAttributesKey PACKAGE_LOCAL_INTERFACE = createTextAttributesKey("GNO_PACKAGE_LOCAL_INTERFACE", DefaultLanguageHighlighterColors.INTERFACE_NAME);
  public static final TextAttributesKey PACKAGE_LOCAL_STRUCT = createTextAttributesKey("GNO_PACKAGE_LOCAL_STRUCT", DefaultLanguageHighlighterColors.CLASS_NAME);
  public static final TextAttributesKey PACKAGE_LOCAL_CONSTANT = createTextAttributesKey("GNO_PACKAGE_LOCAL_CONSTANT", DefaultLanguageHighlighterColors.CONSTANT);
  public static final TextAttributesKey PACKAGE_LOCAL_VARIABLE = createTextAttributesKey("GNO_PACKAGE_LOCAL_VARIABLE", DefaultLanguageHighlighterColors.LOCAL_VARIABLE);
  public static final TextAttributesKey STRUCT_EXPORTED_MEMBER = createTextAttributesKey("GNO_STRUCT_EXPORTED_MEMBER", DefaultLanguageHighlighterColors.GLOBAL_VARIABLE);
  public static final TextAttributesKey STRUCT_LOCAL_MEMBER = createTextAttributesKey("GNO_STRUCT_LOCAL_MEMBER", DefaultLanguageHighlighterColors.LOCAL_VARIABLE);
  public static final TextAttributesKey METHOD_RECEIVER = createTextAttributesKey("GNO_METHOD_RECEIVER", DefaultLanguageHighlighterColors.LOCAL_VARIABLE);
  public static final TextAttributesKey FUNCTION_PARAMETER = createTextAttributesKey("GNO_FUNCTION_PARAMETER", DefaultLanguageHighlighterColors.LOCAL_VARIABLE);
  public static final TextAttributesKey LOCAL_CONSTANT = createTextAttributesKey("GNO_LOCAL_CONSTANT", DefaultLanguageHighlighterColors.CONSTANT);
  public static final TextAttributesKey LOCAL_VARIABLE = createTextAttributesKey("GNO_LOCAL_VARIABLE", DefaultLanguageHighlighterColors.LOCAL_VARIABLE);
  public static final TextAttributesKey SCOPE_VARIABLE = createTextAttributesKey("GNO_SCOPE_VARIABLE", DefaultLanguageHighlighterColors.LOCAL_VARIABLE);
  public static final TextAttributesKey LABEL = createTextAttributesKey("GNO_LABEL", DefaultLanguageHighlighterColors.LABEL);
  public static final TextAttributesKey FUNCTION_NAME = createTextAttributesKey("GNO_FUNCTION_NAME", DefaultLanguageHighlighterColors.FUNCTION_DECLARATION);
  public static final TextAttributesKey KEY_VALUE = createTextAttributesKey("GNO_KEY_VALUE", DefaultLanguageHighlighterColors.INSTANCE_FIELD);
  public static final TextAttributesKey TYPE_NAME = createTextAttributesKey("GNO_TYPE_NAME", DefaultLanguageHighlighterColors.CLASS_NAME);


  private GnoSyntaxHighlightingColors() {
  }
}
