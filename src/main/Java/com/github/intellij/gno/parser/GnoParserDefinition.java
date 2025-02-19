package com.github.intellij.gno.parser;

import com.github.intellij.gno.language.GnoLanguage;
import com.github.intellij.gno.lexer.GnoLexerAdapter;
import com.github.intellij.gno.psi.GnoFile;
import com.github.intellij.gno.psi.GnoTokenSets;
import com.github.intellij.gno.psi.GnoTypes;
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

    public static final IFileElementType FILE = new IFileElementType(GnoLanguage.INSTANCE);

    @Override
    public @NotNull Lexer createLexer(Project project) {
        return new GnoLexerAdapter();
    }

    @Override
    public @NotNull PsiParser createParser(@NotNull Project project) {
        return new GnoParser();
    }

    @Override
    public @NotNull IFileElementType getFileNodeType() {
        return FILE;
    }

    @Override
    public @NotNull TokenSet getWhitespaceTokens() {
        return GnoTokenSets.WHITESPACES;
    }

    @Override
    public @NotNull TokenSet getCommentTokens() {
        return GnoTokenSets.COMMENTS;
    }

    @Override
    public @NotNull TokenSet getStringLiteralElements() {
        return TokenSet.EMPTY;
    }

    @Override
    public @NotNull PsiFile createFile(@NotNull FileViewProvider viewProvider) {
        return new GnoFile(viewProvider);
    }

    @Override
    public @NotNull PsiElement createElement(@NotNull ASTNode node) {
        return GnoTypes.Factory.createElement(node);
    }
}
