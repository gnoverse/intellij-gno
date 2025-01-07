package com.github.intellij.gno.lexer;

import com.intellij.lexer.FlexAdapter;

public class GnoLexer extends FlexAdapter {
    public GnoLexer() {
        super(new _GnoLexer(null)); // _GnoLexer généré avec JFlex ou autre outil.
    }
}

