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
        IFileElementType var10000 = GnoFileElementType.INSTANCE;
        if (var10000 == null) {
            $$$reportNull$$$0(0);
        }

        return var10000;
    }

    public @NotNull TokenSet getWhitespaceTokens() {
        TokenSet var10000 = GnoTokenSets.WHITESPACES;
        if (var10000 == null) {
            $$$reportNull$$$0(1);
        }

        return var10000;
    }

    public @NotNull TokenSet getCommentTokens() {
        TokenSet var10000 = GnoTokenSets.COMMENTS;
        if (var10000 == null) {
            $$$reportNull$$$0(2);
        }

        return var10000;
    }

    public @NotNull TokenSet getStringLiteralElements() {
        TokenSet var10000 = GnoTokenSets.STRINGS;
        if (var10000 == null) {
            $$$reportNull$$$0(3);
        }

        return var10000;
    }

    public @NotNull PsiElement createElement(ASTNode node) {
        PsiElement var10000 = GnoTypes.Factory.createElement(node);
        if (var10000 == null) {
            $$$reportNull$$$0(4);
        }

        return var10000;
    }

    public @NotNull PsiFile createFile(@NotNull FileViewProvider provider) {
        if (provider == null) {
            $$$reportNull$$$0(5);
        }

        return new GnoFile(provider);
    }
}
