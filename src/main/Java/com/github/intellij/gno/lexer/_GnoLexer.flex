package fleet.com.github.intellij.gno.lexer;

import fleet.com.intellij.lexer.FlexLexer;
import fleet.com.intellij.psi.tree.IElementType;

import static fleet.com.intellij.psi.TokenType.BAD_CHARACTER;
import static fleet.com.intellij.psi.TokenType.WHITE_SPACE;
import static fleet.com.github.intellij.gno.psi.GnoTypes.*;

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

IDENTIFIER=[a-zA-Z_][a-zA-Z0-9_]*
INT=[0-9]+
STRING=\"([^\"\\\n\r]|\\.)*\"
COMMENT="//"[^\n]*|"/"\\*.*?\\*"/"
WHITE_SPACE=[ \t]+
EOL=\r?\n+

%%
<YYINITIAL> {
  {WHITE_SPACE}       { return WHITE_SPACE; }

  "{"                 { return LBRACE; }
  "}"                 { return RBRACE; }
  "["                 { return LBRACK; }
  "]"                 { return RBRACK; }
  "("                 { return LPAREN; }
  ")"                 { return RPAREN; }
  ":"                 { return COLON; }
  ";"                 { return SEMICOLON; }
  ","                 { return COMMA; }
  "=="                { return EQ; }
  "="                 { return ASSIGN; }
  "!="                { return NOT_EQ; }
  "!"                 { return NOT; }
  "++"                { return PLUS_PLUS; }
  "+="                { return PLUS_ASSIGN; }
  "+"                 { return PLUS; }
  "--"                { return MINUS_MINUS; }
  "-="                { return MINUS_ASSIGN; }
  "-"                 { return MINUS; }
  "||"                { return COND_OR; }
  "|="                { return BIT_OR_ASSIGN; }
  "&^="               { return BIT_CLEAR_ASSIGN; }
  "&^"                { return BIT_CLEAR; }
  "&&"                { return COND_AND; }
  "&="                { return BIT_AND_ASSIGN; }
  "&"                 { return BIT_AND; }
  "|"                 { return BIT_OR; }
  "<<="               { return SHIFT_LEFT_ASSIGN; }
  "<<"                { return SHIFT_LEFT; }
  "<-"                { return SEND_CHANNEL; }
  "<="                { return LESS_OR_EQUAL; }
  "<"                 { return LESS; }
  "^="                { return BIT_XOR_ASSIGN; }
  "^"                 { return BIT_XOR; }
  "*="                { return MUL_ASSIGN; }
  "*"                 { return MUL; }
  "/="                { return QUOTIENT_ASSIGN; }
  "/"                 { return QUOTIENT; }
  "%="                { return REMAINDER_ASSIGN; }
  "%"                 { return REMAINDER; }
  ">>="               { return SHIFT_RIGHT_ASSIGN; }
  ">>"                { return SHIFT_RIGHT; }
  ">="                { return GREATER_OR_EQUAL; }
  ">"                 { return GREATER; }
  ":="                { return VAR_ASSIGN; }
  "..."               { return TRIPLE_DOT; }
  "."                 { return DOT; }
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
  "nil"               { return NIL; }

  {IDENTIFIER}        { return IDENTIFIER; }
  {INT}               { return INT; }
  {STRING}            { return STRING; }
  {COMMENT}           { return COMMENT; }
  {WHITE_SPACE}       { return WHITE_SPACE; }
  {EOL}               { return EOL; }

}

[^] { return BAD_CHARACTER; }
