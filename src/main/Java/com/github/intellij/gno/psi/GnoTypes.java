// This is a generated file. Not intended for manual editing.
package com.github.intellij.gno.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.github.intellij.gno.psi.impl.*;

public interface GnoTypes {

  IElementType BLOCK = new GnoElementType("BLOCK");
  IElementType CASE_CLAUSE = new GnoElementType("CASE_CLAUSE");
  IElementType CASE_CLAUSES = new GnoElementType("CASE_CLAUSES");
  IElementType CONTROL_STATEMENT = new GnoElementType("CONTROL_STATEMENT");
  IElementType EXPRESSION = new GnoElementType("EXPRESSION");
  IElementType EXPRESSION_STATEMENT = new GnoElementType("EXPRESSION_STATEMENT");
  IElementType FIELDS = new GnoElementType("FIELDS");
  IElementType FIELD_DECLARATION = new GnoElementType("FIELD_DECLARATION");
  IElementType FOR_STATEMENT = new GnoElementType("FOR_STATEMENT");
  IElementType FUNCTION_DECLARATION = new GnoElementType("FUNCTION_DECLARATION");
  IElementType IF_STATEMENT = new GnoElementType("IF_STATEMENT");
  IElementType IMPORT_DECLARATION = new GnoElementType("IMPORT_DECLARATION");
  IElementType IMPORT_LIST = new GnoElementType("IMPORT_LIST");
  IElementType LITERAL = new GnoElementType("LITERAL");
  IElementType PACKAGE_CLAUSE = new GnoElementType("PACKAGE_CLAUSE");
  IElementType STATEMENT = new GnoElementType("STATEMENT");
  IElementType STRUCT_TYPE = new GnoElementType("STRUCT_TYPE");
  IElementType SWITCH_STATEMENT = new GnoElementType("SWITCH_STATEMENT");
  IElementType TOP_LEVEL_DECLARATION = new GnoElementType("TOP_LEVEL_DECLARATION");
  IElementType TYPE_DECLARATION = new GnoElementType("TYPE_DECLARATION");

  IElementType ASSIGN = new GnoTokenType("=");
  IElementType CASE = new GnoTokenType("case");
  IElementType COLON = new GnoTokenType(":");
  IElementType COMMA = new GnoTokenType(",");
  IElementType COMMENT = new GnoTokenType("//[^\\n]*|/\\\\*.*?\\\\*/");
  IElementType CONST = new GnoTokenType("const");
  IElementType DIV = new GnoTokenType("/");
  IElementType ELSE = new GnoTokenType("else");
  IElementType FOR = new GnoTokenType("for");
  IElementType FUNC = new GnoTokenType("func");
  IElementType IDENTIFIER = new GnoTokenType("[a-zA-Z_][a-zA-Z0-9_]*");
  IElementType IF = new GnoTokenType("if");
  IElementType IMPORT = new GnoTokenType("import");
  IElementType INT = new GnoTokenType("[0-9]+");
  IElementType INTERFACE = new GnoTokenType("interface");
  IElementType LBRACE = new GnoTokenType("{");
  IElementType LBRACK = new GnoTokenType("[");
  IElementType LPAREN = new GnoTokenType("(");
  IElementType MAP = new GnoTokenType("map");
  IElementType MINUS = new GnoTokenType("-");
  IElementType MUL = new GnoTokenType("*");
  IElementType PACKAGE = new GnoTokenType("package");
  IElementType PLUS = new GnoTokenType("+");
  IElementType RBRACE = new GnoTokenType("}");
  IElementType RBRACK = new GnoTokenType("]");
  IElementType RETURN = new GnoTokenType("return");
  IElementType RPAREN = new GnoTokenType(")");
  IElementType SELECT = new GnoTokenType("select");
  IElementType SEMICOLON = new GnoTokenType(";");
  IElementType SIGNATURE = new GnoTokenType("Signature");
  IElementType STRING = new GnoTokenType("\".*?\"");
  IElementType STRUCT = new GnoTokenType("struct");
  IElementType SWITCH = new GnoTokenType("switch");
  IElementType TYPE = new GnoTokenType("Type");
  IElementType VALUE = new GnoTokenType("VALUE");
  IElementType VAR = new GnoTokenType("var");
  IElementType VARDECLARATION = new GnoTokenType("VarDeclaration");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == BLOCK) {
        return new GnoBlockImpl(node);
      }
      else if (type == CASE_CLAUSE) {
        return new GnoCaseClauseImpl(node);
      }
      else if (type == CASE_CLAUSES) {
        return new GnoCaseClausesImpl(node);
      }
      else if (type == CONTROL_STATEMENT) {
        return new GnoControlStatementImpl(node);
      }
      else if (type == EXPRESSION) {
        return new GnoExpressionImpl(node);
      }
      else if (type == EXPRESSION_STATEMENT) {
        return new GnoExpressionStatementImpl(node);
      }
      else if (type == FIELDS) {
        return new GnoFieldsImpl(node);
      }
      else if (type == FIELD_DECLARATION) {
        return new GnoFieldDeclarationImpl(node);
      }
      else if (type == FOR_STATEMENT) {
        return new GnoForStatementImpl(node);
      }
      else if (type == FUNCTION_DECLARATION) {
        return new GnoFunctionDeclarationImpl(node);
      }
      else if (type == IF_STATEMENT) {
        return new GnoIfStatementImpl(node);
      }
      else if (type == IMPORT_DECLARATION) {
        return new GnoImportDeclarationImpl(node);
      }
      else if (type == IMPORT_LIST) {
        return new GnoImportListImpl(node);
      }
      else if (type == LITERAL) {
        return new GnoLiteralImpl(node);
      }
      else if (type == PACKAGE_CLAUSE) {
        return new GnoPackageClauseImpl(node);
      }
      else if (type == STATEMENT) {
        return new GnoStatementImpl(node);
      }
      else if (type == STRUCT_TYPE) {
        return new GnoStructTypeImpl(node);
      }
      else if (type == SWITCH_STATEMENT) {
        return new GnoSwitchStatementImpl(node);
      }
      else if (type == TOP_LEVEL_DECLARATION) {
        return new GnoTopLevelDeclarationImpl(node);
      }
      else if (type == TYPE_DECLARATION) {
        return new GnoTypeDeclarationImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
