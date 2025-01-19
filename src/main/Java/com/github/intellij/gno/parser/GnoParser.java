// This is a generated file. Not intended for manual editing.
package com.github.intellij.gno.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static com.github.intellij.gno.psi.GnoTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class GnoParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return File(b, l + 1);
  }

  /* ********************************************************** */
  // Expression (COMMA Expression)* COMMA?
  public static boolean Arguments(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Arguments")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ARGUMENTS, "<arguments>");
    r = Expression(b, l + 1);
    r = r && Arguments_1(b, l + 1);
    r = r && Arguments_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (COMMA Expression)*
  private static boolean Arguments_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Arguments_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Arguments_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Arguments_1", c)) break;
    }
    return true;
  }

  // COMMA Expression
  private static boolean Arguments_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Arguments_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && Expression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // COMMA?
  private static boolean Arguments_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Arguments_2")) return false;
    consumeToken(b, COMMA);
    return true;
  }

  /* ********************************************************** */
  // LBRACK INT? RBRACK TypeBody
  public static boolean ArrayType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArrayType")) return false;
    if (!nextTokenIs(b, LBRACK)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, ARRAY_TYPE, null);
    r = consumeToken(b, LBRACK);
    p = r; // pin = 1
    r = r && report_error_(b, ArrayType_1(b, l + 1));
    r = p && report_error_(b, consumeToken(b, RBRACK)) && r;
    r = p && TypeBody(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // INT?
  private static boolean ArrayType_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArrayType_1")) return false;
    consumeToken(b, INT);
    return true;
  }

  /* ********************************************************** */
  // LBRACE Statement* RBRACE
  public static boolean Block(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Block")) return false;
    if (!nextTokenIs(b, LBRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACE);
    r = r && Block_1(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, BLOCK, r);
    return r;
  }

  // Statement*
  private static boolean Block_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Block_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Statement(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Block_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // LPAREN Arguments? RPAREN
  public static boolean CallSuffix(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CallSuffix")) return false;
    if (!nextTokenIs(b, LPAREN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPAREN);
    r = r && CallSuffix_1(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, CALL_SUFFIX, r);
    return r;
  }

  // Arguments?
  private static boolean CallSuffix_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CallSuffix_1")) return false;
    Arguments(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // CASE Expression COLON Statement*
  public static boolean CaseClause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CaseClause")) return false;
    if (!nextTokenIs(b, CASE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, CASE);
    r = r && Expression(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && CaseClause_3(b, l + 1);
    exit_section_(b, m, CASE_CLAUSE, r);
    return r;
  }

  // Statement*
  private static boolean CaseClause_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CaseClause_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Statement(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "CaseClause_3", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // CaseClause*
  public static boolean CaseClauses(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CaseClauses")) return false;
    Marker m = enter_section_(b, l, _NONE_, CASE_CLAUSES, "<case clauses>");
    while (true) {
      int c = current_position_(b);
      if (!CaseClause(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "CaseClauses", c)) break;
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // CONST IDENTIFIER (ASSIGN Expression)
  public static boolean ConstDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ConstDeclaration")) return false;
    if (!nextTokenIs(b, CONST)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, CONST_DECLARATION, null);
    r = consumeTokens(b, 1, CONST, IDENTIFIER);
    p = r; // pin = 1
    r = r && ConstDeclaration_2(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // ASSIGN Expression
  private static boolean ConstDeclaration_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ConstDeclaration_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ASSIGN);
    r = r && Expression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // IfStatement
  //   | ForStatement
  //   | SwitchStatement
  //   | ReturnStatement
  public static boolean ControlStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ControlStatement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CONTROL_STATEMENT, "<control statement>");
    r = IfStatement(b, l + 1);
    if (!r) r = ForStatement(b, l + 1);
    if (!r) r = SwitchStatement(b, l + 1);
    if (!r) r = ReturnStatement(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // PrimaryExpression
  public static boolean Expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, EXPRESSION, "<expression>");
    r = PrimaryExpression(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // Expression
  public static boolean ExpressionStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExpressionStatement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, EXPRESSION_STATEMENT, "<expression statement>");
    r = Expression(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER TypeBody
  public static boolean FieldDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FieldDeclaration")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    r = r && TypeBody(b, l + 1);
    exit_section_(b, m, FIELD_DECLARATION, r);
    return r;
  }

  /* ********************************************************** */
  // FieldDeclaration (SEMICOLON FieldDeclaration)* SEMICOLON?
  public static boolean Fields(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Fields")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = FieldDeclaration(b, l + 1);
    r = r && Fields_1(b, l + 1);
    r = r && Fields_2(b, l + 1);
    exit_section_(b, m, FIELDS, r);
    return r;
  }

  // (SEMICOLON FieldDeclaration)*
  private static boolean Fields_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Fields_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Fields_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Fields_1", c)) break;
    }
    return true;
  }

  // SEMICOLON FieldDeclaration
  private static boolean Fields_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Fields_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SEMICOLON);
    r = r && FieldDeclaration(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // SEMICOLON?
  private static boolean Fields_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Fields_2")) return false;
    consumeToken(b, SEMICOLON);
    return true;
  }

  /* ********************************************************** */
  // PackageClause ImportDeclaration* TopLevelDeclaration*
  static boolean File(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "File")) return false;
    if (!nextTokenIs(b, PACKAGE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = PackageClause(b, l + 1);
    r = r && File_1(b, l + 1);
    r = r && File_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ImportDeclaration*
  private static boolean File_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "File_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ImportDeclaration(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "File_1", c)) break;
    }
    return true;
  }

  // TopLevelDeclaration*
  private static boolean File_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "File_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!TopLevelDeclaration(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "File_2", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // FOR Expression? Block
  public static boolean ForStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForStatement")) return false;
    if (!nextTokenIs(b, FOR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, FOR);
    r = r && ForStatement_1(b, l + 1);
    r = r && Block(b, l + 1);
    exit_section_(b, m, FOR_STATEMENT, r);
    return r;
  }

  // Expression?
  private static boolean ForStatement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForStatement_1")) return false;
    Expression(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // FUNC Receiver? IDENTIFIER Signature Block
  public static boolean FunctionDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionDeclaration")) return false;
    if (!nextTokenIs(b, FUNC)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, FUNC);
    r = r && FunctionDeclaration_1(b, l + 1);
    r = r && consumeToken(b, IDENTIFIER);
    r = r && Signature(b, l + 1);
    r = r && Block(b, l + 1);
    exit_section_(b, m, FUNCTION_DECLARATION, r);
    return r;
  }

  // Receiver?
  private static boolean FunctionDeclaration_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionDeclaration_1")) return false;
    Receiver(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // FUNC Signature
  public static boolean FunctionType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionType")) return false;
    if (!nextTokenIs(b, FUNC)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, FUNCTION_TYPE, null);
    r = consumeToken(b, FUNC);
    p = r; // pin = 1
    r = r && Signature(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // IF Expression Block ELSE? Block?
  public static boolean IfStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfStatement")) return false;
    if (!nextTokenIs(b, IF)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IF);
    r = r && Expression(b, l + 1);
    r = r && Block(b, l + 1);
    r = r && IfStatement_3(b, l + 1);
    r = r && IfStatement_4(b, l + 1);
    exit_section_(b, m, IF_STATEMENT, r);
    return r;
  }

  // ELSE?
  private static boolean IfStatement_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfStatement_3")) return false;
    consumeToken(b, ELSE);
    return true;
  }

  // Block?
  private static boolean IfStatement_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfStatement_4")) return false;
    Block(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // IMPORT ( ImportSpec
  //                              | LPAREN ImportSpecList? RPAREN )
  public static boolean ImportDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportDeclaration")) return false;
    if (!nextTokenIs(b, IMPORT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IMPORT);
    r = r && ImportDeclaration_1(b, l + 1);
    exit_section_(b, m, IMPORT_DECLARATION, r);
    return r;
  }

  // ImportSpec
  //                              | LPAREN ImportSpecList? RPAREN
  private static boolean ImportDeclaration_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportDeclaration_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ImportSpec(b, l + 1);
    if (!r) r = ImportDeclaration_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // LPAREN ImportSpecList? RPAREN
  private static boolean ImportDeclaration_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportDeclaration_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPAREN);
    r = r && ImportDeclaration_1_1_1(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  // ImportSpecList?
  private static boolean ImportDeclaration_1_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportDeclaration_1_1_1")) return false;
    ImportSpecList(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // STRING
  public static boolean ImportSpec(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportSpec")) return false;
    if (!nextTokenIs(b, STRING)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, STRING);
    exit_section_(b, m, IMPORT_SPEC, r);
    return r;
  }

  /* ********************************************************** */
  // ImportSpec+
  public static boolean ImportSpecList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportSpecList")) return false;
    if (!nextTokenIs(b, STRING)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ImportSpec(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!ImportSpec(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ImportSpecList", c)) break;
    }
    exit_section_(b, m, IMPORT_SPEC_LIST, r);
    return r;
  }

  /* ********************************************************** */
  // INT | STRING
  public static boolean Literal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Literal")) return false;
    if (!nextTokenIs(b, "<literal>", INT, STRING)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LITERAL, "<literal>");
    r = consumeToken(b, INT);
    if (!r) r = consumeToken(b, STRING);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // MAP LBRACK TypeBody RBRACK TypeBody
  public static boolean MapType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapType")) return false;
    if (!nextTokenIs(b, MAP)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, MAP_TYPE, null);
    r = consumeTokens(b, 1, MAP, LBRACK);
    p = r; // pin = 1
    r = r && report_error_(b, TypeBody(b, l + 1));
    r = p && report_error_(b, consumeToken(b, RBRACK)) && r;
    r = p && TypeBody(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // PACKAGE IDENTIFIER
  public static boolean PackageClause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PackageClause")) return false;
    if (!nextTokenIs(b, PACKAGE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, PACKAGE_CLAUSE, null);
    r = consumeTokens(b, 1, PACKAGE, IDENTIFIER);
    p = r; // pin = 1
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // IDENTIFIER TypeBody
  public static boolean ParameterDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ParameterDeclaration")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, PARAMETER_DECLARATION, null);
    r = consumeToken(b, IDENTIFIER);
    p = r; // pin = 1
    r = r && TypeBody(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // ParameterDeclaration (COMMA ParameterDeclaration)* COMMA?
  public static boolean Parameters(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Parameters")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ParameterDeclaration(b, l + 1);
    r = r && Parameters_1(b, l + 1);
    r = r && Parameters_2(b, l + 1);
    exit_section_(b, m, PARAMETERS, r);
    return r;
  }

  // (COMMA ParameterDeclaration)*
  private static boolean Parameters_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Parameters_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Parameters_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Parameters_1", c)) break;
    }
    return true;
  }

  // COMMA ParameterDeclaration
  private static boolean Parameters_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Parameters_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && ParameterDeclaration(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // COMMA?
  private static boolean Parameters_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Parameters_2")) return false;
    consumeToken(b, COMMA);
    return true;
  }

  /* ********************************************************** */
  // MUL TypeBody
  public static boolean PointerType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PointerType")) return false;
    if (!nextTokenIs(b, MUL)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, POINTER_TYPE, null);
    r = consumeToken(b, MUL);
    p = r; // pin = 1
    r = r && TypeBody(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // (IDENTIFIER | Literal)
  //   (SelectorSuffix | CallSuffix)*
  public static boolean PrimaryExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrimaryExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PRIMARY_EXPRESSION, "<primary expression>");
    r = PrimaryExpression_0(b, l + 1);
    r = r && PrimaryExpression_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // IDENTIFIER | Literal
  private static boolean PrimaryExpression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrimaryExpression_0")) return false;
    boolean r;
    r = consumeToken(b, IDENTIFIER);
    if (!r) r = Literal(b, l + 1);
    return r;
  }

  // (SelectorSuffix | CallSuffix)*
  private static boolean PrimaryExpression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrimaryExpression_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!PrimaryExpression_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "PrimaryExpression_1", c)) break;
    }
    return true;
  }

  // SelectorSuffix | CallSuffix
  private static boolean PrimaryExpression_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrimaryExpression_1_0")) return false;
    boolean r;
    r = SelectorSuffix(b, l + 1);
    if (!r) r = CallSuffix(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // LPAREN ReceiverParameter RPAREN
  public static boolean Receiver(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Receiver")) return false;
    if (!nextTokenIs(b, LPAREN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPAREN);
    r = r && ReceiverParameter(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, RECEIVER, r);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER TypeBody
  public static boolean ReceiverParameter(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ReceiverParameter")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    r = r && TypeBody(b, l + 1);
    exit_section_(b, m, RECEIVER_PARAMETER, r);
    return r;
  }

  /* ********************************************************** */
  // LPAREN Parameters RPAREN | TypeBody
  public static boolean Result(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Result")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, RESULT, "<result>");
    r = Result_0(b, l + 1);
    if (!r) r = TypeBody(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // LPAREN Parameters RPAREN
  private static boolean Result_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Result_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPAREN);
    r = r && Parameters(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // RETURN Expression?
  public static boolean ReturnStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ReturnStatement")) return false;
    if (!nextTokenIs(b, RETURN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, RETURN);
    r = r && ReturnStatement_1(b, l + 1);
    exit_section_(b, m, RETURN_STATEMENT, r);
    return r;
  }

  // Expression?
  private static boolean ReturnStatement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ReturnStatement_1")) return false;
    Expression(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // '.' IDENTIFIER
  public static boolean SelectorSuffix(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SelectorSuffix")) return false;
    if (!nextTokenIs(b, DOT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DOT, IDENTIFIER);
    exit_section_(b, m, SELECTOR_SUFFIX, r);
    return r;
  }

  /* ********************************************************** */
  // LPAREN Parameters? RPAREN Result?
  public static boolean Signature(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Signature")) return false;
    if (!nextTokenIs(b, LPAREN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPAREN);
    r = r && Signature_1(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    r = r && Signature_3(b, l + 1);
    exit_section_(b, m, SIGNATURE, r);
    return r;
  }

  // Parameters?
  private static boolean Signature_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Signature_1")) return false;
    Parameters(b, l + 1);
    return true;
  }

  // Result?
  private static boolean Signature_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Signature_3")) return false;
    Result(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // ExpressionStatement | Block | ControlStatement
  public static boolean Statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Statement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, STATEMENT, "<statement>");
    r = ExpressionStatement(b, l + 1);
    if (!r) r = Block(b, l + 1);
    if (!r) r = ControlStatement(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // STRUCT LBRACE Fields? RBRACE
  public static boolean StructType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructType")) return false;
    if (!nextTokenIs(b, STRUCT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, STRUCT, LBRACE);
    r = r && StructType_2(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, STRUCT_TYPE, r);
    return r;
  }

  // Fields?
  private static boolean StructType_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructType_2")) return false;
    Fields(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // SWITCH Expression? LBRACE CaseClauses RBRACE
  public static boolean SwitchStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SwitchStatement")) return false;
    if (!nextTokenIs(b, SWITCH)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SWITCH);
    r = r && SwitchStatement_1(b, l + 1);
    r = r && consumeToken(b, LBRACE);
    r = r && CaseClauses(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, SWITCH_STATEMENT, r);
    return r;
  }

  // Expression?
  private static boolean SwitchStatement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SwitchStatement_1")) return false;
    Expression(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // FunctionDeclaration
  //   | TypeDeclaration
  //   | VarDeclaration
  //   | ConstDeclaration
  public static boolean TopLevelDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TopLevelDeclaration")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TOP_LEVEL_DECLARATION, "<top level declaration>");
    r = FunctionDeclaration(b, l + 1);
    if (!r) r = TypeDeclaration(b, l + 1);
    if (!r) r = VarDeclaration(b, l + 1);
    if (!r) r = ConstDeclaration(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // TypeName | StructType | MapType | ArrayType | PointerType | FunctionType
  public static boolean TypeBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypeBody")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TYPE_BODY, "<type body>");
    r = TypeName(b, l + 1);
    if (!r) r = StructType(b, l + 1);
    if (!r) r = MapType(b, l + 1);
    if (!r) r = ArrayType(b, l + 1);
    if (!r) r = PointerType(b, l + 1);
    if (!r) r = FunctionType(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // TYPE IDENTIFIER TypeBody
  public static boolean TypeDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypeDeclaration")) return false;
    if (!nextTokenIs(b, TYPE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, TYPE, IDENTIFIER);
    r = r && TypeBody(b, l + 1);
    exit_section_(b, m, TYPE_DECLARATION, r);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER
  public static boolean TypeName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypeName")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    exit_section_(b, m, TYPE_NAME, r);
    return r;
  }

  /* ********************************************************** */
  // VAR IDENTIFIER TypeBody (ASSIGN Expression)?
  public static boolean VarDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarDeclaration")) return false;
    if (!nextTokenIs(b, VAR)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, VAR_DECLARATION, null);
    r = consumeTokens(b, 1, VAR, IDENTIFIER);
    p = r; // pin = 1
    r = r && report_error_(b, TypeBody(b, l + 1));
    r = p && VarDeclaration_3(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (ASSIGN Expression)?
  private static boolean VarDeclaration_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarDeclaration_3")) return false;
    VarDeclaration_3_0(b, l + 1);
    return true;
  }

  // ASSIGN Expression
  private static boolean VarDeclaration_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarDeclaration_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ASSIGN);
    r = r && Expression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

}
