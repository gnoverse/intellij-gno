package com.github.intellij.gno.lexer;

import com.intellij.lexer.FlexAdapter;
import com.github.intellij.gno.psi.GnoTokenSets;
import com.intellij.lexer.MergingLexerAdapter;
import com.intellij.psi.tree.TokenSet;

public class GnoLexer extends MergingLexerAdapter {
    public GnoLexer() {
        super(new FlexAdapter(new _GnoLexer()), TokenSet.orSet(new TokenSet[]{GnoTokenSets.COMMENTS, GnoTokenSets.WHITESPACES}));
    }
}
