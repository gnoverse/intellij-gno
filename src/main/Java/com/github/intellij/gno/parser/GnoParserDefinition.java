package com.github.intellij.gno.parser;

import com.github.intellij.gno.language.GnoFileElementType;
import com.github.intellij.gno.psi.GnoTypes;
import com.github.intellij.gno.lexer.GnoLexer;
import com.github.intellij.gno.psi.GnoFile;
import com.github.intellij.gno.psi.GnoTokenSets;
import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

public final class GnoParserDefinition implements ParserDefinition {
    public GnoParserDefinition() {
    }

    public @NotNull Lexer createLexer(Project project) {
        return new GnoLexer();
    }

    public @NotNull PsiParser createParser(Project project) {
        return new GnoParser();
    }

    public @NotNull IFileElementType getFileNodeType() {
        return GnoFileElementType.INSTANCE;
    }

    public @NotNull TokenSet getWhitespaceTokens() {
        TokenSet var10000 = GnoTokenSets.WHITESPACES;
        if (var10000 == null) {
            $$$reportNull$$$0(1);
        }
        assert var10000 != null;
        return var10000;
    }

    public @NotNull TokenSet getCommentTokens() {
        return GnoTokenSets.COMMENTS;
    }

    public @NotNull TokenSet getStringLiteralElements() {
        return GnoTokenSets.STRINGS;
    }

    public @NotNull PsiElement createElement(ASTNode node) {
        return GnoTypes.Factory.createElement(node);
    }

    public @NotNull PsiFile createFile(@NotNull FileViewProvider provider) {
        return new GnoFile(provider);
    }
}
