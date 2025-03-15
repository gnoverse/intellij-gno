package com.github.intellij.gno.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static com.github.intellij.gno.psi.GnoTypes.*;

%%

%{
  public _GnoLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class _GnoLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

EOL=\R
WHITE_SPACE=\s+

COMMENT="//"[^\n]*|"/"\\*.*?\\*"/"
WHITE_SPACE=[ \t]+
EOL=\r?\n+
IDENTIFIER=[a-zA-Z_][a-zA-Z0-9_]*
ANY_CHAR=[\u0000-\uFFFF]
STRINGCONTENT=[a-zA-Z0-9_./]+

%%
<YYINITIAL> {
  {WHITE_SPACE}         { return WHITE_SPACE; }

  "."                   { return DOT; }
  "EOF"                 { return EOF; }

  {COMMENT}             { return COMMENT; }
  {WHITE_SPACE}         { return WHITE_SPACE; }
  {EOL}                 { return EOL; }
  {IDENTIFIER}          { return IDENTIFIER; }
  {ANY_CHAR}            { return ANY_CHAR; }
  {STRINGCONTENT}       { return STRINGCONTENT; }

}

[^] { return BAD_CHARACTER; }
