// This is a generated file. Not intended for manual editing.
package com.github.intellij.gno.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.github.intellij.gno.psi.impl.*;

public interface GnoTypes {

  IElementType ARGUMENTS = new GnoElementType("ARGUMENTS");
  IElementType ARRAY_TYPE = new GnoElementType("ARRAY_TYPE");
  IElementType BLOCK = new GnoElementType("BLOCK");
  IElementType CALL_EXPRESSION = new GnoElementType("CALL_EXPRESSION");
  IElementType CASE_CLAUSE = new GnoElementType("CASE_CLAUSE");
  IElementType CASE_CLAUSES = new GnoElementType("CASE_CLAUSES");
  IElementType CONST_DECLARATION = new GnoElementType("CONST_DECLARATION");
  IElementType CONTROL_STATEMENT = new GnoElementType("CONTROL_STATEMENT");
  IElementType EXPRESSION = new GnoElementType("EXPRESSION");
  IElementType EXPRESSION_STATEMENT = new GnoElementType("EXPRESSION_STATEMENT");
  IElementType FIELDS = new GnoElementType("FIELDS");
  IElementType FIELD_DECLARATION = new GnoElementType("FIELD_DECLARATION");
  IElementType FOR_STATEMENT = new GnoElementType("FOR_STATEMENT");
  IElementType FUNCTION_DECLARATION = new GnoElementType("FUNCTION_DECLARATION");
  IElementType FUNCTION_TYPE = new GnoElementType("FUNCTION_TYPE");
  IElementType IF_STATEMENT = new GnoElementType("IF_STATEMENT");
  IElementType IMPORT_DECLARATION = new GnoElementType("IMPORT_DECLARATION");
  IElementType IMPORT_LIST = new GnoElementType("IMPORT_LIST");
  IElementType LITERAL = new GnoElementType("LITERAL");
  IElementType MAP_TYPE = new GnoElementType("MAP_TYPE");
  IElementType PACKAGE_CLAUSE = new GnoElementType("PACKAGE_CLAUSE");
  IElementType PARAMETERS = new GnoElementType("PARAMETERS");
  IElementType PARAMETER_DECLARATION = new GnoElementType("PARAMETER_DECLARATION");
  IElementType POINTER_TYPE = new GnoElementType("POINTER_TYPE");
  IElementType RESULT = new GnoElementType("RESULT");
  IElementType RETURN_STATEMENT = new GnoElementType("RETURN_STATEMENT");
  IElementType SIGNATURE = new GnoElementType("SIGNATURE");
  IElementType STATEMENT = new GnoElementType("STATEMENT");
  IElementType STRUCT_TYPE = new GnoElementType("STRUCT_TYPE");
  IElementType SWITCH_STATEMENT = new GnoElementType("SWITCH_STATEMENT");
  IElementType TOP_LEVEL_DECLARATION = new GnoElementType("TOP_LEVEL_DECLARATION");
  IElementType TYPE_BODY = new GnoElementType("TYPE_BODY");
  IElementType TYPE_DECLARATION = new GnoElementType("TYPE_DECLARATION");
  IElementType TYPE_NAME = new GnoElementType("TYPE_NAME");
  IElementType VAR_DECLARATION = new GnoElementType("VAR_DECLARATION");

  IElementType AND = new GnoTokenType("&&");
  IElementType ASSIGN = new GnoTokenType("=");
  IElementType BIT_AND = new GnoTokenType("&");
  IElementType BIT_OR = new GnoTokenType("|");
  IElementType BIT_XOR = new GnoTokenType("^");
  IElementType CASE = new GnoTokenType("case");
  IElementType CHAN = new GnoTokenType("chan");
  IElementType COLON = new GnoTokenType(":");
  IElementType COMMA = new GnoTokenType(",");
  IElementType COMMENT = new GnoTokenType("COMMENT");
  IElementType CONST = new GnoTokenType("const");
  IElementType DEFAULT = new GnoTokenType("default");
  IElementType DEFER = new GnoTokenType("defer");
  IElementType DIV = new GnoTokenType("/");
  IElementType DOT = new GnoTokenType(".");
  IElementType ELSE = new GnoTokenType("else");
  IElementType EQUALS = new GnoTokenType("==");
  IElementType FOR = new GnoTokenType("for");
  IElementType FUNC = new GnoTokenType("func");
  IElementType GO = new GnoTokenType("go");
  IElementType GREATER = new GnoTokenType(">");
  IElementType GREATER_EQUAL = new GnoTokenType(">=");
  IElementType IDENTIFIER = new GnoTokenType("IDENTIFIER");
  IElementType IF = new GnoTokenType("if");
  IElementType IMPORT = new GnoTokenType("import");
  IElementType INT = new GnoTokenType("INT");
  IElementType INTERFACE = new GnoTokenType("interface");
  IElementType LBRACE = new GnoTokenType("{");
  IElementType LBRACK = new GnoTokenType("[");
  IElementType LESS = new GnoTokenType("<");
  IElementType LESS_EQUAL = new GnoTokenType("<=");
  IElementType LPAREN = new GnoTokenType("(");
  IElementType MAP = new GnoTokenType("map");
  IElementType MINUS = new GnoTokenType("-");
  IElementType MODULO = new GnoTokenType("%");
  IElementType MUL = new GnoTokenType("*");
  IElementType NOT = new GnoTokenType("!");
  IElementType NOT_EQUALS = new GnoTokenType("!=");
  IElementType OR = new GnoTokenType("||");
  IElementType PACKAGE = new GnoTokenType("package");
  IElementType PLUS = new GnoTokenType("+");
  IElementType RBRACE = new GnoTokenType("}");
  IElementType RBRACK = new GnoTokenType("]");
  IElementType RETURN = new GnoTokenType("return");
  IElementType RPAREN = new GnoTokenType(")");
  IElementType SELECT = new GnoTokenType("select");
  IElementType SEMICOLON = new GnoTokenType(";");
  IElementType SHIFT_LEFT = new GnoTokenType("<<");
  IElementType SHIFT_RIGHT = new GnoTokenType(">>");
  IElementType STRING = new GnoTokenType("STRING");
  IElementType STRUCT = new GnoTokenType("struct");
  IElementType SWITCH = new GnoTokenType("switch");
  IElementType TYPE = new GnoTokenType("type");
  IElementType VAR = new GnoTokenType("var");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ARGUMENTS) {
        return new GnoArgumentsImpl(node);
      }
      else if (type == ARRAY_TYPE) {
        return new GnoArrayTypeImpl(node);
      }
      else if (type == BLOCK) {
        return new GnoBlockImpl(node);
      }
      else if (type == CALL_EXPRESSION) {
        return new GnoCallExpressionImpl(node);
      }
      else if (type == CASE_CLAUSE) {
        return new GnoCaseClauseImpl(node);
      }
      else if (type == CASE_CLAUSES) {
        return new GnoCaseClausesImpl(node);
      }
      else if (type == CONST_DECLARATION) {
        return new GnoConstDeclarationImpl(node);
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
      else if (type == FUNCTION_TYPE) {
        return new GnoFunctionTypeImpl(node);
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
      else if (type == MAP_TYPE) {
        return new GnoMapTypeImpl(node);
      }
      else if (type == PACKAGE_CLAUSE) {
        return new GnoPackageClauseImpl(node);
      }
      else if (type == PARAMETERS) {
        return new GnoParametersImpl(node);
      }
      else if (type == PARAMETER_DECLARATION) {
        return new GnoParameterDeclarationImpl(node);
      }
      else if (type == POINTER_TYPE) {
        return new GnoPointerTypeImpl(node);
      }
      else if (type == RESULT) {
        return new GnoResultImpl(node);
      }
      else if (type == RETURN_STATEMENT) {
        return new GnoReturnStatementImpl(node);
      }
      else if (type == SIGNATURE) {
        return new GnoSignatureImpl(node);
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
      else if (type == TYPE_BODY) {
        return new GnoTypeBodyImpl(node);
      }
      else if (type == TYPE_DECLARATION) {
        return new GnoTypeDeclarationImpl(node);
      }
      else if (type == TYPE_NAME) {
        return new GnoTypeNameImpl(node);
      }
      else if (type == VAR_DECLARATION) {
        return new GnoVarDeclarationImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
