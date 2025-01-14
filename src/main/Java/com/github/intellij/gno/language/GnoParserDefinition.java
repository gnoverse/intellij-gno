package com.github.intellij.gno.language;

import com.github.intellij.gno.lexer.GnoLexerAdapter;
import com.github.intellij.gno.parser.GnoParser;
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

final class GnoParserDefinition implements ParserDefinition {

    public static final IFileElementType FILE = new IFileElementType(GnoLanguage.INSTANCE);

    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new GnoLexerAdapter();
    }

    @NotNull
    @Override
    public TokenSet getCommentTokens() {
        return GnoTokenSets.COMMENTS;
    }

    @NotNull
    @Override
    public TokenSet getStringLiteralElements() {
        return TokenSet.EMPTY;
    }

    @NotNull
    @Override
    public PsiParser createParser(final Project project) {
        return new GnoParser();
    }

    @NotNull
    @Override
    public IFileElementType getFileNodeType() {
        return FILE;
    }

    @NotNull
    @Override
    public PsiFile createFile(@NotNull FileViewProvider viewProvider) {
        return new GnoFile(viewProvider);
    }

    @NotNull
    @Override
    public PsiElement createElement(ASTNode node) {
        return GnoTypes.Factory.createElement(node);
    }

}