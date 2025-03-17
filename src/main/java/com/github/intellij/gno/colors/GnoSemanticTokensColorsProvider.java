package com.github.intellij.gno.colors;

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiFile;
import com.redhat.devtools.lsp4ij.features.semanticTokens.DefaultSemanticTokensColorsProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GnoSemanticTokensColorsProvider extends DefaultSemanticTokensColorsProvider {

    private static final Map<String, TextAttributesKey> TOKEN_COLORS = new HashMap<>();

    static {
        TOKEN_COLORS.put("variable", TextAttributesKey.createTextAttributesKey("GNO_VARIABLE", DefaultLanguageHighlighterColors.LOCAL_VARIABLE));
        TOKEN_COLORS.put("global_variable", TextAttributesKey.createTextAttributesKey("GNO_GLOBAL_VARIABLE", DefaultLanguageHighlighterColors.GLOBAL_VARIABLE));
        TOKEN_COLORS.put("function", TextAttributesKey.createTextAttributesKey("GNO_FUNCTION", DefaultLanguageHighlighterColors.FUNCTION_DECLARATION));
        TOKEN_COLORS.put("function_call", TextAttributesKey.createTextAttributesKey("GNO_FUNCTION_CALL", DefaultLanguageHighlighterColors.FUNCTION_CALL));
        TOKEN_COLORS.put("type", TextAttributesKey.createTextAttributesKey("GNO_TYPE", DefaultLanguageHighlighterColors.CLASS_NAME));
        TOKEN_COLORS.put("interface", TextAttributesKey.createTextAttributesKey("GNO_INTERFACE", DefaultLanguageHighlighterColors.INTERFACE_NAME));
        TOKEN_COLORS.put("constant", TextAttributesKey.createTextAttributesKey("GNO_CONSTANT", DefaultLanguageHighlighterColors.CONSTANT));
        TOKEN_COLORS.put("keyword", TextAttributesKey.createTextAttributesKey("GNO_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD));
        TOKEN_COLORS.put("string", TextAttributesKey.createTextAttributesKey("GNO_STRING", DefaultLanguageHighlighterColors.STRING));
        TOKEN_COLORS.put("number", TextAttributesKey.createTextAttributesKey("GNO_NUMBER", DefaultLanguageHighlighterColors.NUMBER));
        TOKEN_COLORS.put("comment", TextAttributesKey.createTextAttributesKey("GNO_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT));
        TOKEN_COLORS.put("block_comment", TextAttributesKey.createTextAttributesKey("GNO_BLOCK_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT));
        TOKEN_COLORS.put("label", TextAttributesKey.createTextAttributesKey("GNO_LABEL", DefaultLanguageHighlighterColors.LABEL));
        TOKEN_COLORS.put("operator", TextAttributesKey.createTextAttributesKey("GNO_OPERATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN));
        TOKEN_COLORS.put("braces", TextAttributesKey.createTextAttributesKey("GNO_BRACES", DefaultLanguageHighlighterColors.BRACES));
        TOKEN_COLORS.put("parentheses", TextAttributesKey.createTextAttributesKey("GNO_PARENTHESES", DefaultLanguageHighlighterColors.PARENTHESES));
        TOKEN_COLORS.put("dot", TextAttributesKey.createTextAttributesKey("GNO_DOT", DefaultLanguageHighlighterColors.DOT));
        TOKEN_COLORS.put("semicolon", TextAttributesKey.createTextAttributesKey("GNO_SEMICOLON", DefaultLanguageHighlighterColors.SEMICOLON));
        TOKEN_COLORS.put("parameter", TextAttributesKey.createTextAttributesKey("GNO_PARAMETER", DefaultLanguageHighlighterColors.PARAMETER));
        TOKEN_COLORS.put("reassigned_variable", TextAttributesKey.createTextAttributesKey("GNO_REASSIGNED_VARIABLE", DefaultLanguageHighlighterColors.REASSIGNED_LOCAL_VARIABLE));
        TOKEN_COLORS.put("default", TextAttributesKey.createTextAttributesKey("GNO_DEFAULT", DefaultLanguageHighlighterColors.IDENTIFIER));
    }

    @Nullable
    @Override
    public TextAttributesKey getTextAttributesKey(@NotNull String tokenType, @NotNull List<String> tokenModifiers, @NotNull PsiFile file) {
        return TOKEN_COLORS.getOrDefault(tokenType, DefaultLanguageHighlighterColors.IDENTIFIER);
    }
}
