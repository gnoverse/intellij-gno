package com.github.intellij.gno.lexer;

import com.intellij.lexer.FlexAdapter;

public class GnoLexerAdapter extends FlexAdapter {
    public GnoLexerAdapter() {
        super(new _GnoLexer(null));
    }
}