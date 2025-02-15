package com.github.intellij.gno.lexer;

import com.github.intellij.gno.parser.GnoParserDefinition;
import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.MergingLexerAdapter;
import com.intellij.psi.tree.TokenSet;

public class GnoLexer extends MergingLexerAdapter {
  public GnoLexer() {
    super(new FlexAdapter(new _GnoLexer()), TokenSet.orSet(GnoParserDefinition.COMMENTS, GnoParserDefinition.WHITESPACES));
  }
}
