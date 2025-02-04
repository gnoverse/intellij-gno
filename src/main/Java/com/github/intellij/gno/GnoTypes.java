package com.github.intellij.gno;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.github.intellij.gno.psi.GnoCompositeElementType;
import com.github.intellij.gno.stubs.GnoElementTypeFactory;
import com.github.intellij.gno.psi.GnoTokenType;
import com.github.intellij.gno.psi.impl.*;

public interface GnoTypes {

  IElementType ADD_EXPR = new GnoCompositeElementType("ADD_EXPR");
  IElementType AND_EXPR = new GnoCompositeElementType("AND_EXPR");
  IElementType ANONYMOUS_FIELD_DEFINITION = GnoElementTypeFactory.stubFactory("ANONYMOUS_FIELD_DEFINITION");
  IElementType ARGUMENT_LIST = new GnoCompositeElementType("ARGUMENT_LIST");
  IElementType ARRAY_OR_SLICE_TYPE = GnoElementTypeFactory.stubFactory("ARRAY_OR_SLICE_TYPE");
  IElementType ASSIGNMENT_STATEMENT = new GnoCompositeElementType("ASSIGNMENT_STATEMENT");
  IElementType ASSIGN_OP = new GnoCompositeElementType("ASSIGN_OP");
  IElementType BLOCK = new GnoCompositeElementType("BLOCK");
  IElementType BREAK_STATEMENT = new GnoCompositeElementType("BREAK_STATEMENT");
  IElementType BUILTIN_ARGUMENT_LIST = new GnoCompositeElementType("BUILTIN_ARGUMENT_LIST");
  IElementType BUILTIN_CALL_EXPR = new GnoCompositeElementType("BUILTIN_CALL_EXPR");
  IElementType CALL_EXPR = new GnoCompositeElementType("CALL_EXPR");
  IElementType CHANNEL_TYPE = GnoElementTypeFactory.stubFactory("CHANNEL_TYPE");
  IElementType COMM_CASE = new GnoCompositeElementType("COMM_CASE");
  IElementType COMM_CLAUSE = new GnoCompositeElementType("COMM_CLAUSE");
  IElementType COMPOSITE_LIT = new GnoCompositeElementType("COMPOSITE_LIT");
  IElementType CONDITIONAL_EXPR = new GnoCompositeElementType("CONDITIONAL_EXPR");
  IElementType CONST_DECLARATION = new GnoCompositeElementType("CONST_DECLARATION");
  IElementType CONST_DEFINITION = GnoElementTypeFactory.stubFactory("CONST_DEFINITION");
  IElementType CONST_SPEC = GnoElementTypeFactory.stubFactory("CONST_SPEC");
  IElementType CONTINUE_STATEMENT = new GnoCompositeElementType("CONTINUE_STATEMENT");
  IElementType CONVERSION_EXPR = new GnoCompositeElementType("CONVERSION_EXPR");
  IElementType DEFER_STATEMENT = new GnoCompositeElementType("DEFER_STATEMENT");
  IElementType ELEMENT = new GnoCompositeElementType("ELEMENT");
  IElementType ELSE_STATEMENT = new GnoCompositeElementType("ELSE_STATEMENT");
  IElementType EXPRESSION = new GnoCompositeElementType("EXPRESSION");
  IElementType EXPR_CASE_CLAUSE = new GnoCompositeElementType("EXPR_CASE_CLAUSE");
  IElementType EXPR_SWITCH_STATEMENT = new GnoCompositeElementType("EXPR_SWITCH_STATEMENT");
  IElementType FALLTHROUGH_STATEMENT = new GnoCompositeElementType("FALLTHROUGH_STATEMENT");
  IElementType FIELD_DECLARATION = new GnoCompositeElementType("FIELD_DECLARATION");
  IElementType FIELD_DEFINITION = GnoElementTypeFactory.stubFactory("FIELD_DEFINITION");
  IElementType FIELD_NAME = new GnoCompositeElementType("FIELD_NAME");
  IElementType FOR_CLAUSE = new GnoCompositeElementType("FOR_CLAUSE");
  IElementType FOR_STATEMENT = new GnoCompositeElementType("FOR_STATEMENT");
  IElementType FUNCTION_DECLARATION = GnoElementTypeFactory.stubFactory("FUNCTION_DECLARATION");
  IElementType FUNCTION_LIT = new GnoCompositeElementType("FUNCTION_LIT");
  IElementType FUNCTION_TYPE = GnoElementTypeFactory.stubFactory("FUNCTION_TYPE");
  IElementType GNOTO_STATEMENT = new GnoCompositeElementType("GNOTO_STATEMENT");
  IElementType GNO_STATEMENT = new GnoCompositeElementType("GNO_STATEMENT");
  IElementType TYPE_REFERENCE = new GnoTokenType("TYPE_REFERENCE");
  IElementType IF_STATEMENT = new GnoCompositeElementType("IF_STATEMENT");
  IElementType IMPORT_DECLARATION = new GnoCompositeElementType("IMPORT_DECLARATION");
  IElementType IMPORT_LIST = new GnoCompositeElementType("IMPORT_LIST");
  IElementType IMPORT_SPEC = GnoElementTypeFactory.stubFactory("IMPORT_SPEC");
  IElementType IMPORT_STRING = new GnoCompositeElementType("IMPORT_STRING");
  IElementType INC_DEC_STATEMENT = new GnoCompositeElementType("INC_DEC_STATEMENT");
  IElementType INDEX_OR_SLICE_EXPR = new GnoCompositeElementType("INDEX_OR_SLICE_EXPR");
  IElementType INTERFACE_TYPE = GnoElementTypeFactory.stubFactory("INTERFACE_TYPE");
  IElementType KEY = new GnoCompositeElementType("KEY");
  IElementType LABELED_STATEMENT = new GnoCompositeElementType("LABELED_STATEMENT");
  IElementType LABEL_DEFINITION = GnoElementTypeFactory.stubFactory("LABEL_DEFINITION");
  IElementType LABEL_REF = new GnoCompositeElementType("LABEL_REF");
  IElementType LEFT_HAND_EXPR_LIST = new GnoCompositeElementType("LEFT_HAND_EXPR_LIST");
  IElementType LITERAL = new GnoCompositeElementType("LITERAL");
  IElementType LITERAL_TYPE_EXPR = new GnoCompositeElementType("LITERAL_TYPE_EXPR");
  IElementType LITERAL_VALUE = new GnoCompositeElementType("LITERAL_VALUE");
  IElementType MAP_TYPE = GnoElementTypeFactory.stubFactory("MAP_TYPE");
  IElementType METHOD_DECLARATION = GnoElementTypeFactory.stubFactory("METHOD_DECLARATION");
  IElementType METHOD_SPEC = GnoElementTypeFactory.stubFactory("METHOD_SPEC");
  IElementType MUL_EXPR = new GnoCompositeElementType("MUL_EXPR");
  IElementType OR_EXPR = new GnoCompositeElementType("OR_EXPR");
  IElementType PACKAGE_CLAUSE = GnoElementTypeFactory.stubFactory("PACKAGE_CLAUSE");
  IElementType PARAMETERS = GnoElementTypeFactory.stubFactory("PARAMETERS");
  IElementType PARAMETER_DECLARATION = GnoElementTypeFactory.stubFactory("PARAMETER_DECLARATION");
  IElementType PARAM_DEFINITION = GnoElementTypeFactory.stubFactory("PARAM_DEFINITION");
  IElementType PARENTHESES_EXPR = new GnoCompositeElementType("PARENTHESES_EXPR");
  IElementType PAR_TYPE = GnoElementTypeFactory.stubFactory("PAR_TYPE");
  IElementType POINTER_TYPE = GnoElementTypeFactory.stubFactory("POINTER_TYPE");
  IElementType RANGE_CLAUSE = GnoElementTypeFactory.stubFactory("RANGE_CLAUSE");
  IElementType RECEIVER = GnoElementTypeFactory.stubFactory("RECEIVER");
  IElementType RECV_STATEMENT = GnoElementTypeFactory.stubFactory("RECV_STATEMENT");
  IElementType REFERENCE_EXPRESSION = new GnoCompositeElementType("REFERENCE_EXPRESSION");
  IElementType RESULT = GnoElementTypeFactory.stubFactory("RESULT");
  IElementType RETURN_STATEMENT = new GnoCompositeElementType("RETURN_STATEMENT");
  IElementType SELECTOR_EXPR = new GnoCompositeElementType("SELECTOR_EXPR");
  IElementType SELECT_STATEMENT = new GnoCompositeElementType("SELECT_STATEMENT");
  IElementType SEND_STATEMENT = new GnoCompositeElementType("SEND_STATEMENT");
  IElementType SHORT_VAR_DECLARATION = GnoElementTypeFactory.stubFactory("SHORT_VAR_DECLARATION");
  IElementType SIGNATURE = GnoElementTypeFactory.stubFactory("SIGNATURE");
  IElementType SIMPLE_STATEMENT = new GnoCompositeElementType("SIMPLE_STATEMENT");
  IElementType SPEC_TYPE = GnoElementTypeFactory.stubFactory("SPEC_TYPE");
  IElementType STATEMENT = new GnoCompositeElementType("STATEMENT");
  IElementType STRING_LITERAL = new GnoCompositeElementType("STRING_LITERAL");
  IElementType STRUCT_TYPE = GnoElementTypeFactory.stubFactory("STRUCT_TYPE");
  IElementType SWITCH_START = new GnoCompositeElementType("SWITCH_START");
  IElementType SWITCH_STATEMENT = new GnoCompositeElementType("SWITCH_STATEMENT");
  IElementType TAG = new GnoCompositeElementType("TAG");
  IElementType TYPE = GnoElementTypeFactory.stubFactory("TYPE");
  IElementType TYPE_ASSERTION_EXPR = new GnoCompositeElementType("TYPE_ASSERTION_EXPR");
  IElementType TYPE_CASE_CLAUSE = new GnoCompositeElementType("TYPE_CASE_CLAUSE");
  IElementType TYPE_DECLARATION = new GnoCompositeElementType("TYPE_DECLARATION");
  IElementType TYPE_GUARD = new GnoCompositeElementType("TYPE_GUARD");
  IElementType TYPE_LIST = GnoElementTypeFactory.stubFactory("TYPE_LIST");
  IElementType TYPE_REFERENCE_EXPRESSION = new GnoCompositeElementType("TYPE_REFERENCE_EXPRESSION");
  IElementType TYPE_SPEC = GnoElementTypeFactory.stubFactory("TYPE_SPEC");
  IElementType TYPE_SWITCH_GUARD = new GnoCompositeElementType("TYPE_SWITCH_GUARD");
  IElementType TYPE_SWITCH_STATEMENT = new GnoCompositeElementType("TYPE_SWITCH_STATEMENT");
  IElementType UNARY_EXPR = new GnoCompositeElementType("UNARY_EXPR");
  IElementType VALUE = new GnoCompositeElementType("VALUE");
  IElementType VAR_DECLARATION = new GnoCompositeElementType("VAR_DECLARATION");
  IElementType VAR_DEFINITION = GnoElementTypeFactory.stubFactory("VAR_DEFINITION");
  IElementType VAR_SPEC = GnoElementTypeFactory.stubFactory("VAR_SPEC");

  IElementType ASSIGN = new GnoTokenType("=");
  IElementType BIT_AND = new GnoTokenType("&");
  IElementType BIT_AND_ASSIGN = new GnoTokenType("&=");
  IElementType BIT_CLEAR = new GnoTokenType("&^");
  IElementType BIT_CLEAR_ASSIGN = new GnoTokenType("&^=");
  IElementType BIT_OR = new GnoTokenType("|");
  IElementType BIT_OR_ASSIGN = new GnoTokenType("|=");
  IElementType BIT_XOR = new GnoTokenType("^");
  IElementType BIT_XOR_ASSIGN = new GnoTokenType("^=");
  IElementType BREAK = new GnoTokenType("break");
  IElementType CASE = new GnoTokenType("case");
  IElementType CHAN = new GnoTokenType("chan");
  IElementType CHAR = new GnoTokenType("char");
  IElementType COLON = new GnoTokenType(":");
  IElementType COMMA = new GnoTokenType(",");
  IElementType COND_AND = new GnoTokenType("&&");
  IElementType COND_OR = new GnoTokenType("||");
  IElementType CONST = new GnoTokenType("const");
  IElementType CONTINUE = new GnoTokenType("continue");
  IElementType DECIMALI = new GnoTokenType("decimali");
  IElementType DEFAULT = new GnoTokenType("default");
  IElementType DEFER = new GnoTokenType("defer");
  IElementType DOT = new GnoTokenType(".");
  IElementType ELSE = new GnoTokenType("else");
  IElementType EQ = new GnoTokenType("==");
  IElementType FALLTHROUGH = new GnoTokenType("fallthrough");
  IElementType FLOAT = new GnoTokenType("float");
  IElementType FLOATI = new GnoTokenType("floati");
  IElementType FOR = new GnoTokenType("for");
  IElementType FUNC = new GnoTokenType("func");
  IElementType GNO = new GnoTokenType("go");
  IElementType GNOTO = new GnoTokenType("goto");
  IElementType GREATER = new GnoTokenType(">");
  IElementType GREATER_OR_EQUAL = new GnoTokenType(">=");
  IElementType HEX = new GnoTokenType("hex");
  IElementType IDENTIFIER = new GnoTokenType("identifier");
  IElementType IF = new GnoTokenType("if");
  IElementType IMPORT = new GnoTokenType("import");
  IElementType INT = new GnoTokenType("int");
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
  IElementType NOT = new GnoTokenType("!");
  IElementType NOT_EQ = new GnoTokenType("!=");
  IElementType OCT = new GnoTokenType("oct");
  IElementType PACKAGE = new GnoTokenType("package");
  IElementType PLUS = new GnoTokenType("+");
  IElementType PLUS_ASSIGN = new GnoTokenType("+=");
  IElementType PLUS_PLUS = new GnoTokenType("++");
  IElementType QUOTIENT = new GnoTokenType("/");
  IElementType QUOTIENT_ASSIGN = new GnoTokenType("/=");
  IElementType RANGE = new GnoTokenType("range");
  IElementType RAW_STRING = new GnoTokenType("raw_string");
  IElementType RBRACE = new GnoTokenType("}");
  IElementType RBRACK = new GnoTokenType("]");
  IElementType REMAINDER = new GnoTokenType("%");
  IElementType REMAINDER_ASSIGN = new GnoTokenType("%=");
  IElementType RETURN = new GnoTokenType("return");
  IElementType RPAREN = new GnoTokenType(")");
  IElementType SELECT = new GnoTokenType("select");
  IElementType SEMICOLON = new GnoTokenType(";");
  IElementType SEMICOLON_SYNTHETIC = new GnoTokenType("<NL>");
  IElementType SEND_CHANNEL = new GnoTokenType("<-");
  IElementType SHIFT_LEFT = new GnoTokenType("<<");
  IElementType SHIFT_LEFT_ASSIGN = new GnoTokenType("<<=");
  IElementType SHIFT_RIGHT = new GnoTokenType(">>");
  IElementType SHIFT_RIGHT_ASSIGN = new GnoTokenType(">>=");
  IElementType STRING = new GnoTokenType("string");
  IElementType STRUCT = new GnoTokenType("struct");
  IElementType SWITCH = new GnoTokenType("switch");
  IElementType TRIPLE_DOT = new GnoTokenType("...");
  IElementType TYPE_ = new GnoTokenType("type");
  IElementType VAR = new GnoTokenType("var");
  IElementType VAR_ASSIGN = new GnoTokenType(":=");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
       if (type == ADD_EXPR) {
        return new GnoAddExprImpl(node);
      }
      else if (type == AND_EXPR) {
        return new GnoAndExprImpl(node);
      }
      else if (type == ANONYMOUS_FIELD_DEFINITION) {
        return new GnoAnonymousFieldDefinitionImpl(node);
      }
      else if (type == ARGUMENT_LIST) {
        return new GnoArgumentListImpl(node);
      }
      else if (type == ARRAY_OR_SLICE_TYPE) {
        return new GnoArrayOrSliceTypeImpl(node);
      }
      else if (type == ASSIGNMENT_STATEMENT) {
        return new GnoAssignmentStatementImpl(node);
      }
      else if (type == ASSIGN_OP) {
        return new GnoAssignOpImpl(node);
      }
      else if (type == BLOCK) {
        return new GnoBlockImpl(node);
      }
      else if (type == BREAK_STATEMENT) {
        return new GnoBreakStatementImpl(node);
      }
      else if (type == BUILTIN_ARGUMENT_LIST) {
        return new GnoBuiltinArgumentListImpl(node);
      }
      else if (type == BUILTIN_CALL_EXPR) {
        return new GnoBuiltinCallExprImpl(node);
      }
      else if (type == CALL_EXPR) {
        return new GnoCallExprImpl(node);
      }
      else if (type == CHANNEL_TYPE) {
        return new GnoChannelTypeImpl(node);
      }
      else if (type == COMM_CASE) {
        return new GnoCommCaseImpl(node);
      }
      else if (type == COMM_CLAUSE) {
        return new GnoCommClauseImpl(node);
      }
      else if (type == COMPOSITE_LIT) {
        return new GnoCompositeLitImpl(node);
      }
      else if (type == CONDITIONAL_EXPR) {
        return new GnoConditionalExprImpl(node);
      }
      else if (type == CONST_DECLARATION) {
        return new GnoConstDeclarationImpl(node);
      }
      else if (type == CONST_DEFINITION) {
        return new GnoConstDefinitionImpl(node);
      }
      else if (type == CONST_SPEC) {
        return new GnoConstSpecImpl(node);
      }
      else if (type == CONTINUE_STATEMENT) {
        return new GnoContinueStatementImpl(node);
      }
      else if (type == CONVERSION_EXPR) {
        return new GnoConversionExprImpl(node);
      }
      else if (type == DEFER_STATEMENT) {
        return new GnoDeferStatementImpl(node);
      }
      else if (type == ELEMENT) {
        return new GnoElementImpl(node);
      }
      else if (type == ELSE_STATEMENT) {
        return new GnoElseStatementImpl(node);
      }
      else if (type == EXPRESSION) {
        return new GnoExpressionImpl(node);
      }
      else if (type == EXPR_CASE_CLAUSE) {
        return new GnoExprCaseClauseImpl(node);
      }
      else if (type == EXPR_SWITCH_STATEMENT) {
        return new GnoExprSwitchStatementImpl(node);
      }
      else if (type == FALLTHROUGH_STATEMENT) {
        return new GnoFallthroughStatementImpl(node);
      }
      else if (type == FIELD_DECLARATION) {
        return new GnoFieldDeclarationImpl(node);
      }
      else if (type == FIELD_DEFINITION) {
        return new GnoFieldDefinitionImpl(node);
      }
      else if (type == FIELD_NAME) {
        return new GnoFieldNameImpl(node);
      }
      else if (type == FOR_CLAUSE) {
        return new GnoForClauseImpl(node);
      }
      else if (type == FOR_STATEMENT) {
        return new GnoForStatementImpl(node);
      }
      else if (type == FUNCTION_DECLARATION) {
        return new GnoFunctionDeclarationImpl(node);
      }
      else if (type == FUNCTION_LIT) {
        return new GnoFunctionLitImpl(node);
      }
      else if (type == FUNCTION_TYPE) {
        return new GnoFunctionTypeImpl(node);
      }
      else if (type == GNOTO_STATEMENT) {
        return new GnoGnotoStatementImpl(node);
      }
      else if (type == GNO_STATEMENT) {
        return new GnoGoStatementImpl(node);
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
      else if (type == IMPORT_STRING) {
        return new GnoImportStringImpl(node);
      }
      else if (type == INC_DEC_STATEMENT) {
        return new GnoIncDecStatementImpl(node);
      }
      else if (type == INDEX_OR_SLICE_EXPR) {
        return new GnoIndexOrSliceExprImpl(node);
      }
      else if (type == INTERFACE_TYPE) {
        return new GnoInterfaceTypeImpl(node);
      }
      else if (type == KEY) {
        return new GnoKeyImpl(node);
      }
      else if (type == LABELED_STATEMENT) {
        return new GnoLabeledStatementImpl(node);
      }
      else if (type == LABEL_DEFINITION) {
        return new GnoLabelDefinitionImpl(node);
      }
      else if (type == LABEL_REF) {
        return new GnoLabelRefImpl(node);
      }
      else if (type == LEFT_HAND_EXPR_LIST) {
        return new GnoLeftHandExprListImpl(node);
      }
      else if (type == LITERAL) {
        return new GnoLiteralImpl(node);
      }
      else if (type == LITERAL_TYPE_EXPR) {
        return new GnoLiteralTypeExprImpl(node);
      }
      else if (type == LITERAL_VALUE) {
        return new GnoLiteralValueImpl(node);
      }
      else if (type == MAP_TYPE) {
        return new GnoMapTypeImpl(node);
      }
      else if (type == METHOD_DECLARATION) {
        return new GnoMethodDeclarationImpl(node);
      }
      else if (type == METHOD_SPEC) {
        return new GnoMethodSpecImpl(node);
      }
      else if (type == MUL_EXPR) {
        return new GnoMulExprImpl(node);
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
      else if (type == PARAM_DEFINITION) {
        return new GnoParamDefinitionImpl(node);
      }
      else if (type == PARENTHESES_EXPR) {
        return new GnoParenthesesExprImpl(node);
      }
      else if (type == PAR_TYPE) {
        return new GnoParTypeImpl(node);
      }
      else if (type == POINTER_TYPE) {
        return new GnoPointerTypeImpl(node);
      }
      else if (type == RANGE_CLAUSE) {
        return new GnoRangeClauseImpl(node);
      }
      else if (type == RECEIVER) {
        return new GnoReceiverImpl(node);
      }
      else if (type == RECV_STATEMENT) {
        return new GnoRecvStatementImpl(node);
      }
      else if (type == REFERENCE_EXPRESSION) {
        return new GnoReferenceExpressionImpl(node);
      }
      else if (type == RESULT) {
        return new GnoResultImpl(node);
      }
      else if (type == RETURN_STATEMENT) {
        return new GnoReturnStatementImpl(node);
      }
      else if (type == SELECTOR_EXPR) {
        return new GnoSelectorExprImpl(node);
      }
      else if (type == SELECT_STATEMENT) {
        return new GnoSelectStatementImpl(node);
      }
      else if (type == SEND_STATEMENT) {
        return new GnoSendStatementImpl(node);
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
      else if (type == SPEC_TYPE) {
        return new GnoSpecTypeImpl(node);
      }
      else if (type == STATEMENT) {
        return new GnoStatementImpl(node);
      }
      else if (type == STRING_LITERAL) {
        return new GnoStringLiteralImpl(node);
      }
      else if (type == STRUCT_TYPE) {
        return new GnoStructTypeImpl(node);
      }
      else if (type == SWITCH_START) {
        return new GnoSwitchStartImpl(node);
      }
      else if (type == SWITCH_STATEMENT) {
        return new GnoSwitchStatementImpl(node);
      }
      else if (type == TAG) {
        return new GnoTagImpl(node);
      }
      else if (type == TYPE) {
        return new GnoTypeImpl(node);
      }
      else if (type == TYPE_ASSERTION_EXPR) {
        return new GnoTypeAssertionExprImpl(node);
      }
      else if (type == TYPE_CASE_CLAUSE) {
        return new GnoTypeCaseClauseImpl(node);
      }
      else if (type == TYPE_DECLARATION) {
        return new GnoTypeDeclarationImpl(node);
      }
      else if (type == TYPE_GUARD) {
        return new GnoTypeGuardImpl(node);
      }
      else if (type == TYPE_LIST) {
        return new GnoTypeListImpl(node);
      }
      else if (type == TYPE_REFERENCE_EXPRESSION) {
        return new GnoTypeReferenceExpressionImpl(node);
      }
      else if (type == TYPE_SPEC) {
        return new GnoTypeSpecImpl(node);
      }
      else if (type == TYPE_SWITCH_GUARD) {
        return new GnoTypeSwitchGuardImpl(node);
      }
      else if (type == TYPE_SWITCH_STATEMENT) {
        return new GnoTypeSwitchStatementImpl(node);
      }
      else if (type == UNARY_EXPR) {
        return new GnoUnaryExprImpl(node);
      }
      else if (type == VALUE) {
        return new GnoValueImpl(node);
      }
      else if (type == VAR_DECLARATION) {
        return new GnoVarDeclarationImpl(node);
      }
      else if (type == VAR_DEFINITION) {
        return new GnoVarDefinitionImpl(node);
      }
      else if (type == VAR_SPEC) {
        return new GnoVarSpecImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
