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

IDENTIFIER=[a-zA-Z_][a-zA-Z0-9_]*
INT=[0-9]+
STRING=\"([^\"\\\n\r]|\\.)*\"
COMMENT="//"[^\n]*|"/"\\*.*?\\*"/"

%%
<YYINITIAL> {
  {WHITE_SPACE}       { return WHITE_SPACE; }

  "{"                 { return LBRACE; }
  "}"                 { return RBRACE; }
  "["                 { return LBRACK; }
  "]"                 { return RBRACK; }
  "("                 { return LPAREN; }
  ")"                 { return RPAREN; }
  ","                 { return COMMA; }
  "="                 { return ASSIGN; }
  ":"                 { return COLON; }
  ";"                 { return SEMICOLON; }
  "."                 { return DOT; }
  "+"                 { return PLUS; }
  "-"                 { return MINUS; }
  "*"                 { return MUL; }
  "/"                 { return DIV; }
  "%"                 { return MODULO; }
  "&"                 { return BIT_AND; }
  "|"                 { return BIT_OR; }
  "^"                 { return BIT_XOR; }
  "<<"                { return SHIFT_LEFT; }
  ">>"                { return SHIFT_RIGHT; }
  "=="                { return EQUALS; }
  "!="                { return NOT_EQUALS; }
  "<"                 { return LESS; }
  ">"                 { return GREATER; }
  "<="                { return LESS_EQUAL; }
  ">="                { return GREATER_EQUAL; }
  "&&"                { return AND; }
  "||"                { return OR; }
  "!"                 { return NOT; }
  "package"           { return PACKAGE; }
  "import"            { return IMPORT; }
  "const"             { return CONST; }
  "var"               { return VAR; }
  "func"              { return FUNC; }
  "type"              { return TYPE; }
  "struct"            { return STRUCT; }
  "interface"         { return INTERFACE; }
  "map"               { return MAP; }
  "select"            { return SELECT; }
  "switch"            { return SWITCH; }
  "case"              { return CASE; }
  "default"           { return DEFAULT; }
  "for"               { return FOR; }
  "if"                { return IF; }
  "else"              { return ELSE; }
  "return"            { return RETURN; }
  "go"                { return GO; }
  "defer"             { return DEFER; }
  "chan"              { return CHAN; }

  {IDENTIFIER}        { return IDENTIFIER; }
  {INT}               { return INT; }
  {STRING}            { return STRING; }
  {COMMENT}           { return COMMENT; }

}

[^] { return BAD_CHARACTER; }
