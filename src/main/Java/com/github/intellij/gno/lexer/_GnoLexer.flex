package com.github.intellij.gno.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static com.github.intellij.gno.psi.GnoTypes.*;

%%

%public
%class GnoLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

EOL=\R
WHITE_SPACE=\s+


%%
<YYINITIAL> {
  {WHITE_SPACE}                  { return WHITE_SPACE; }

  "{"                            { return LBRACE; }
  "}"                            { return RBRACE; }
  "["                            { return LBRACK; }
  "]"                            { return RBRACK; }
  "("                            { return LPAREN; }
  ")"                            { return RPAREN; }
  ","                            { return COMMA; }
  "="                            { return ASSIGN; }
  "+"                            { return PLUS; }
  "-"                            { return MINUS; }
  "*"                            { return MUL; }
  "/"                            { return DIV; }
  "[a-zA-Z_][a-zA-Z0-9_]*"       { return IDENTIFIER; }
  "[0-9]+"                       { return INT; }
  "\".*?\""                      { return STRING; }
  "import"                       { return IMPORT; }
  "const"                        { return CONST; }
  "type"                         { return TYPE; }
  "func"                         { return FUNC; }
  "var"                          { return VAR; }
  "switch"                       { return SWITCH; }
  "case"                         { return CASE; }
  "select"                       { return SELECT; }
  "for"                          { return FOR; }
  "if"                           { return IF; }
  "else"                         { return ELSE; }
  "return"                       { return RETURN; }
  "map"                          { return MAP; }
  "interface"                    { return INTERFACE; }
  "struct"                       { return STRUCT; }
  "StringLiteral"                { return STRINGLITERAL; }
  "VarDeclaration"               { return VARDECLARATION; }
  "Signature"                    { return SIGNATURE; }
  "COLON"                        { return COLON; }


}

[^] { return BAD_CHARACTER; }
