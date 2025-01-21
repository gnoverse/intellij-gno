// This is a generated file. Not intended for manual editing.
package com.github.intellij.gno.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.github.intellij.gno.psi.impl.*;

public interface GnoTypes {

  IElementType ADD_EXPR = new GnoElementType("ADD_EXPR");
  IElementType ADD_OP = new GnoElementType("ADD_OP");
  IElementType AND_EXPR = new GnoElementType("AND_EXPR");
  IElementType ARGUMENTS = new GnoElementType("ARGUMENTS");
  IElementType ARRAY_TYPE = new GnoElementType("ARRAY_TYPE");
  IElementType ASSIGN_OP = new GnoElementType("ASSIGN_OP");
  IElementType ASSIGN_STATEMENT = new GnoElementType("ASSIGN_STATEMENT");
  IElementType BLOCK = new GnoElementType("BLOCK");
  IElementType CALL_SUFFIX = new GnoElementType("CALL_SUFFIX");
  IElementType CASE_CLAUSE = new GnoElementType("CASE_CLAUSE");
  IElementType CASE_CLAUSES = new GnoElementType("CASE_CLAUSES");
  IElementType COMPARE_EXPR = new GnoElementType("COMPARE_EXPR");
  IElementType COMPARE_OP = new GnoElementType("COMPARE_OP");
  IElementType COMPOSITE_LITERAL = new GnoElementType("COMPOSITE_LITERAL");
  IElementType CONST_DECLARATION = new GnoElementType("CONST_DECLARATION");
  IElementType CONST_SPEC = new GnoElementType("CONST_SPEC");
  IElementType CONST_SPEC_LIST = new GnoElementType("CONST_SPEC_LIST");
  IElementType EXPRESSION = new GnoElementType("EXPRESSION");
  IElementType EXPRESSION_LIST = new GnoElementType("EXPRESSION_LIST");
  IElementType EXPRESSION_STATEMENT = new GnoElementType("EXPRESSION_STATEMENT");
  IElementType FIELDS = new GnoElementType("FIELDS");
  IElementType FIELD_DECLARATION = new GnoElementType("FIELD_DECLARATION");
  IElementType FIELD_LITERAL = new GnoElementType("FIELD_LITERAL");
  IElementType FIELD_LITERAL_LIST = new GnoElementType("FIELD_LITERAL_LIST");
  IElementType FIELD_TYPE = new GnoElementType("FIELD_TYPE");
  IElementType FOR_STATEMENT = new GnoElementType("FOR_STATEMENT");
  IElementType FUNCTION_DECLARATION = new GnoElementType("FUNCTION_DECLARATION");
  IElementType FUNCTION_TYPE = new GnoElementType("FUNCTION_TYPE");
  IElementType GROUPED_CONST_DECLARATION = new GnoElementType("GROUPED_CONST_DECLARATION");
  IElementType GROUPED_TYPE_DECLARATION = new GnoElementType("GROUPED_TYPE_DECLARATION");
  IElementType GROUPED_VAR_DECLARATION = new GnoElementType("GROUPED_VAR_DECLARATION");
  IElementType IDENTIFIER_LIST = new GnoElementType("IDENTIFIER_LIST");
  IElementType IF_STATEMENT = new GnoElementType("IF_STATEMENT");
  IElementType IMPORT_DECLARATION = new GnoElementType("IMPORT_DECLARATION");
  IElementType IMPORT_LIST = new GnoElementType("IMPORT_LIST");
  IElementType IMPORT_SPEC = new GnoElementType("IMPORT_SPEC");
  IElementType IMPORT_SPEC_LIST = new GnoElementType("IMPORT_SPEC_LIST");
  IElementType INC_DEC_STATEMENT = new GnoElementType("INC_DEC_STATEMENT");
  IElementType LITERAL = new GnoElementType("LITERAL");
  IElementType MAP_TYPE = new GnoElementType("MAP_TYPE");
  IElementType MUL_EXPR = new GnoElementType("MUL_EXPR");
  IElementType MUL_OP = new GnoElementType("MUL_OP");
  IElementType OR_EXPR = new GnoElementType("OR_EXPR");
  IElementType PACKAGE_CLAUSE = new GnoElementType("PACKAGE_CLAUSE");
  IElementType PARAMETERS = new GnoElementType("PARAMETERS");
  IElementType PARAMETER_DECLARATION = new GnoElementType("PARAMETER_DECLARATION");
  IElementType POINTER_TYPE = new GnoElementType("POINTER_TYPE");
  IElementType PRIMARY_EXPR = new GnoElementType("PRIMARY_EXPR");
  IElementType PROPERTY_DECLARATION = new GnoElementType("PROPERTY_DECLARATION");
  IElementType QUALIFIED_IDENTIFIER = new GnoElementType("QUALIFIED_IDENTIFIER");
  IElementType RECEIVER = new GnoElementType("RECEIVER");
  IElementType RECEIVER_PARAMETER = new GnoElementType("RECEIVER_PARAMETER");
  IElementType RESULT = new GnoElementType("RESULT");
  IElementType RETURN_STATEMENT = new GnoElementType("RETURN_STATEMENT");
  IElementType SELECTOR_SUFFIX = new GnoElementType("SELECTOR_SUFFIX");
  IElementType SHIFT_EXPR = new GnoElementType("SHIFT_EXPR");
  IElementType SHIFT_OP = new GnoElementType("SHIFT_OP");
  IElementType SHORT_VAR_DECLARATION = new GnoElementType("SHORT_VAR_DECLARATION");
  IElementType SIGNATURE = new GnoElementType("SIGNATURE");
  IElementType SIMPLE_STATEMENT = new GnoElementType("SIMPLE_STATEMENT");
  IElementType STATEMENT = new GnoElementType("STATEMENT");
  IElementType STRUCT_TYPE = new GnoElementType("STRUCT_TYPE");
  IElementType SWITCH_STATEMENT = new GnoElementType("SWITCH_STATEMENT");
  IElementType TOP_LEVEL_DECLARATION = new GnoElementType("TOP_LEVEL_DECLARATION");
  IElementType TYPE_ANNOTATION = new GnoElementType("TYPE_ANNOTATION");
  IElementType TYPE_ASSERTION_SUFFIX = new GnoElementType("TYPE_ASSERTION_SUFFIX");
  IElementType TYPE_BODY = new GnoElementType("TYPE_BODY");
  IElementType TYPE_CONVERSION = new GnoElementType("TYPE_CONVERSION");
  IElementType TYPE_DECLARATION = new GnoElementType("TYPE_DECLARATION");
  IElementType TYPE_NAME = new GnoElementType("TYPE_NAME");
  IElementType TYPE_SPEC = new GnoElementType("TYPE_SPEC");
  IElementType TYPE_SPEC_LIST = new GnoElementType("TYPE_SPEC_LIST");
  IElementType UNARY_EXPR = new GnoElementType("UNARY_EXPR");
  IElementType UNARY_OP = new GnoElementType("UNARY_OP");
  IElementType VAR_DECLARATION = new GnoElementType("VAR_DECLARATION");
  IElementType VAR_DEFINITION_LIST = new GnoElementType("VAR_DEFINITION_LIST");
  IElementType VAR_SPEC = new GnoElementType("VAR_SPEC");
  IElementType VAR_SPEC_LIST = new GnoElementType("VAR_SPEC_LIST");

  IElementType ASSIGN = new GnoTokenType("=");
  IElementType BIT_AND = new GnoTokenType("&");
  IElementType BIT_AND_ASSIGN = new GnoTokenType("&=");
  IElementType BIT_CLEAR = new GnoTokenType("&^");
  IElementType BIT_CLEAR_ASSIGN = new GnoTokenType("&^=");
  IElementType BIT_OR = new GnoTokenType("|");
  IElementType BIT_OR_ASSIGN = new GnoTokenType("|=");
  IElementType BIT_XOR = new GnoTokenType("^");
  IElementType BIT_XOR_ASSIGN = new GnoTokenType("^=");
  IElementType CASE = new GnoTokenType("case");
  IElementType CHAN = new GnoTokenType("chan");
  IElementType COLON = new GnoTokenType(":");
  IElementType COMMA = new GnoTokenType(",");
  IElementType COMMENT = new GnoTokenType("COMMENT");
  IElementType COND_AND = new GnoTokenType("&&");
  IElementType COND_OR = new GnoTokenType("||");
  IElementType CONST = new GnoTokenType("const");
  IElementType DEFAULT = new GnoTokenType("default");
  IElementType DEFER = new GnoTokenType("defer");
  IElementType DOT = new GnoTokenType(".");
  IElementType ELSE = new GnoTokenType("else");
  IElementType EOL = new GnoTokenType("EOL");
  IElementType EQ = new GnoTokenType("==");
  IElementType FOR = new GnoTokenType("for");
  IElementType FUNC = new GnoTokenType("func");
  IElementType GO = new GnoTokenType("go");
  IElementType GREATER = new GnoTokenType(">");
  IElementType GREATER_OR_EQUAL = new GnoTokenType(">=");
  IElementType IDENTIFIER = new GnoTokenType("IDENTIFIER");
  IElementType IF = new GnoTokenType("if");
  IElementType IMPORT = new GnoTokenType("import");
  IElementType INT = new GnoTokenType("INT");
  IElementType INTERFACE = new GnoTokenType("interface");
  IElementType LBRACE = new GnoTokenType("{");
  IElementType LBRACK = new GnoTokenType("[");
  IElementType LESS = new GnoTokenType("<");
  IElementType LESS_OR_EQUAL = new GnoTokenType("<=");
  IElementType LPAREN = new GnoTokenType("(");
  IElementType MAP = new GnoTokenType("map");
  IElementType MINUS = new GnoTokenType("-");
  IElementType MINUS_ASSIGN = new GnoTokenType("-=");
  IElementType MINUS_MINUS = new GnoTokenType("--");
  IElementType MUL = new GnoTokenType("*");
  IElementType MUL_ASSIGN = new GnoTokenType("*=");
  IElementType NIL = new GnoTokenType("nil");
  IElementType NOT = new GnoTokenType("!");
  IElementType NOT_EQ = new GnoTokenType("!=");
  IElementType PACKAGE = new GnoTokenType("package");
  IElementType PLUS = new GnoTokenType("+");
  IElementType PLUS_ASSIGN = new GnoTokenType("+=");
  IElementType PLUS_PLUS = new GnoTokenType("++");
  IElementType QUOTIENT = new GnoTokenType("/");
  IElementType QUOTIENT_ASSIGN = new GnoTokenType("/=");
  IElementType RBRACE = new GnoTokenType("}");
  IElementType RBRACK = new GnoTokenType("]");
  IElementType REMAINDER = new GnoTokenType("%");
  IElementType REMAINDER_ASSIGN = new GnoTokenType("%=");
  IElementType RETURN = new GnoTokenType("return");
  IElementType RPAREN = new GnoTokenType(")");
  IElementType SELECT = new GnoTokenType("select");
  IElementType SEMICOLON = new GnoTokenType(";");
  IElementType SEND_CHANNEL = new GnoTokenType("<-");
  IElementType SHIFT_LEFT = new GnoTokenType("<<");
  IElementType SHIFT_LEFT_ASSIGN = new GnoTokenType("<<=");
  IElementType SHIFT_RIGHT = new GnoTokenType(">>");
  IElementType SHIFT_RIGHT_ASSIGN = new GnoTokenType(">>=");
  IElementType STRING = new GnoTokenType("STRING");
  IElementType STRUCT = new GnoTokenType("struct");
  IElementType SWITCH = new GnoTokenType("switch");
  IElementType TRIPLE_DOT = new GnoTokenType("...");
  IElementType TYPE = new GnoTokenType("type");
  IElementType VAR = new GnoTokenType("var");
  IElementType VAR_ASSIGN = new GnoTokenType(":=");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ADD_EXPR) {
        return new GnoAddExprImpl(node);
      }
      else if (type == ADD_OP) {
        return new GnoAddOpImpl(node);
      }
      else if (type == AND_EXPR) {
        return new GnoAndExprImpl(node);
      }
      else if (type == ARGUMENTS) {
        return new GnoArgumentsImpl(node);
      }
      else if (type == ARRAY_TYPE) {
        return new GnoArrayTypeImpl(node);
      }
      else if (type == ASSIGN_OP) {
        return new GnoAssignOpImpl(node);
      }
      else if (type == ASSIGN_STATEMENT) {
        return new GnoAssignStatementImpl(node);
      }
      else if (type == BLOCK) {
        return new GnoBlockImpl(node);
      }
      else if (type == CALL_SUFFIX) {
        return new GnoCallSuffixImpl(node);
      }
      else if (type == CASE_CLAUSE) {
        return new GnoCaseClauseImpl(node);
      }
      else if (type == CASE_CLAUSES) {
        return new GnoCaseClausesImpl(node);
      }
      else if (type == COMPARE_EXPR) {
        return new GnoCompareExprImpl(node);
      }
      else if (type == COMPARE_OP) {
        return new GnoCompareOpImpl(node);
      }
      else if (type == COMPOSITE_LITERAL) {
        return new GnoCompositeLiteralImpl(node);
      }
      else if (type == CONST_DECLARATION) {
        return new GnoConstDeclarationImpl(node);
      }
      else if (type == CONST_SPEC) {
        return new GnoConstSpecImpl(node);
      }
      else if (type == CONST_SPEC_LIST) {
        return new GnoConstSpecListImpl(node);
      }
      else if (type == EXPRESSION) {
        return new GnoExpressionImpl(node);
      }
      else if (type == EXPRESSION_LIST) {
        return new GnoExpressionListImpl(node);
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
      else if (type == FIELD_LITERAL) {
        return new GnoFieldLiteralImpl(node);
      }
      else if (type == FIELD_LITERAL_LIST) {
        return new GnoFieldLiteralListImpl(node);
      }
      else if (type == FIELD_TYPE) {
        return new GnoFieldTypeImpl(node);
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
      else if (type == GROUPED_CONST_DECLARATION) {
        return new GnoGroupedConstDeclarationImpl(node);
      }
      else if (type == GROUPED_TYPE_DECLARATION) {
        return new GnoGroupedTypeDeclarationImpl(node);
      }
      else if (type == GROUPED_VAR_DECLARATION) {
        return new GnoGroupedVarDeclarationImpl(node);
      }
      else if (type == IDENTIFIER_LIST) {
        return new GnoIdentifierListImpl(node);
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
      else if (type == IMPORT_SPEC) {
        return new GnoImportSpecImpl(node);
      }
      else if (type == IMPORT_SPEC_LIST) {
        return new GnoImportSpecListImpl(node);
      }
      else if (type == INC_DEC_STATEMENT) {
        return new GnoIncDecStatementImpl(node);
      }
      else if (type == LITERAL) {
        return new GnoLiteralImpl(node);
      }
      else if (type == MAP_TYPE) {
        return new GnoMapTypeImpl(node);
      }
      else if (type == MUL_EXPR) {
        return new GnoMulExprImpl(node);
      }
      else if (type == MUL_OP) {
        return new GnoMulOpImpl(node);
      }
      else if (type == OR_EXPR) {
        return new GnoOrExprImpl(node);
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
      else if (type == PRIMARY_EXPR) {
        return new GnoPrimaryExprImpl(node);
      }
      else if (type == PROPERTY_DECLARATION) {
        return new GnoPropertyDeclarationImpl(node);
      }
      else if (type == QUALIFIED_IDENTIFIER) {
        return new GnoQualifiedIdentifierImpl(node);
      }
      else if (type == RECEIVER) {
        return new GnoReceiverImpl(node);
      }
      else if (type == RECEIVER_PARAMETER) {
        return new GnoReceiverParameterImpl(node);
      }
      else if (type == RESULT) {
        return new GnoResultImpl(node);
      }
      else if (type == RETURN_STATEMENT) {
        return new GnoReturnStatementImpl(node);
      }
      else if (type == SELECTOR_SUFFIX) {
        return new GnoSelectorSuffixImpl(node);
      }
      else if (type == SHIFT_EXPR) {
        return new GnoShiftExprImpl(node);
      }
      else if (type == SHIFT_OP) {
        return new GnoShiftOpImpl(node);
      }
      else if (type == SHORT_VAR_DECLARATION) {
        return new GnoShortVarDeclarationImpl(node);
      }
      else if (type == SIGNATURE) {
        return new GnoSignatureImpl(node);
      }
      else if (type == SIMPLE_STATEMENT) {
        return new GnoSimpleStatementImpl(node);
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
      else if (type == TYPE_ANNOTATION) {
        return new GnoTypeAnnotationImpl(node);
      }
      else if (type == TYPE_ASSERTION_SUFFIX) {
        return new GnoTypeAssertionSuffixImpl(node);
      }
      else if (type == TYPE_BODY) {
        return new GnoTypeBodyImpl(node);
      }
      else if (type == TYPE_CONVERSION) {
        return new GnoTypeConversionImpl(node);
      }
      else if (type == TYPE_DECLARATION) {
        return new GnoTypeDeclarationImpl(node);
      }
      else if (type == TYPE_NAME) {
        return new GnoTypeNameImpl(node);
      }
      else if (type == TYPE_SPEC) {
        return new GnoTypeSpecImpl(node);
      }
      else if (type == TYPE_SPEC_LIST) {
        return new GnoTypeSpecListImpl(node);
      }
      else if (type == UNARY_EXPR) {
        return new GnoUnaryExprImpl(node);
      }
      else if (type == UNARY_OP) {
        return new GnoUnaryOpImpl(node);
      }
      else if (type == VAR_DECLARATION) {
        return new GnoVarDeclarationImpl(node);
      }
      else if (type == VAR_DEFINITION_LIST) {
        return new GnoVarDefinitionListImpl(node);
      }
      else if (type == VAR_SPEC) {
        return new GnoVarSpecImpl(node);
      }
      else if (type == VAR_SPEC_LIST) {
        return new GnoVarSpecListImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
