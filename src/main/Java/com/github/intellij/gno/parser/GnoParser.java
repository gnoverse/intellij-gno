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
  // MulExpr ( AddOp MulExpr )*
  public static boolean AddExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AddExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ADD_EXPR, "<add expr>");
    r = MulExpr(b, l + 1);
    r = r && AddExpr_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ( AddOp MulExpr )*
  private static boolean AddExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AddExpr_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!AddExpr_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "AddExpr_1", c)) break;
    }
    return true;
  }

  // AddOp MulExpr
  private static boolean AddExpr_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AddExpr_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = AddOp(b, l + 1);
    r = r && MulExpr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // PLUS | MINUS | BIT_OR | BIT_XOR
  public static boolean AddOp(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AddOp")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ADD_OP, "<add op>");
    r = consumeToken(b, PLUS);
    if (!r) r = consumeToken(b, MINUS);
    if (!r) r = consumeToken(b, BIT_OR);
    if (!r) r = consumeToken(b, BIT_XOR);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // CompareExpr ( COND_AND CompareExpr )*
  public static boolean AndExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AndExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, AND_EXPR, "<and expr>");
    r = CompareExpr(b, l + 1);
    r = r && AndExpr_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ( COND_AND CompareExpr )*
  private static boolean AndExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AndExpr_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!AndExpr_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "AndExpr_1", c)) break;
    }
    return true;
  }

  // COND_AND CompareExpr
  private static boolean AndExpr_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AndExpr_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COND_AND);
    r = r && CompareExpr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (EOL | Expression (COMMA Expression)*)? COMMA?
  public static boolean Arguments(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Arguments")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ARGUMENTS, "<arguments>");
    r = Arguments_0(b, l + 1);
    r = r && Arguments_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (EOL | Expression (COMMA Expression)*)?
  private static boolean Arguments_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Arguments_0")) return false;
    Arguments_0_0(b, l + 1);
    return true;
  }

  // EOL | Expression (COMMA Expression)*
  private static boolean Arguments_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Arguments_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EOL);
    if (!r) r = Arguments_0_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Expression (COMMA Expression)*
  private static boolean Arguments_0_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Arguments_0_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Expression(b, l + 1);
    r = r && Arguments_0_0_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA Expression)*
  private static boolean Arguments_0_0_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Arguments_0_0_1_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Arguments_0_0_1_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Arguments_0_0_1_1", c)) break;
    }
    return true;
  }

  // COMMA Expression
  private static boolean Arguments_0_0_1_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Arguments_0_0_1_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && Expression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // COMMA?
  private static boolean Arguments_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Arguments_1")) return false;
    consumeToken(b, COMMA);
    return true;
  }

  /* ********************************************************** */
  // LBRACK INT? RBRACK TypeBody
  public static boolean ArrayType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArrayType")) return false;
    if (!nextTokenIs(b, LBRACK)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACK);
    r = r && ArrayType_1(b, l + 1);
    r = r && consumeToken(b, RBRACK);
    r = r && TypeBody(b, l + 1);
    exit_section_(b, m, ARRAY_TYPE, r);
    return r;
  }

  // INT?
  private static boolean ArrayType_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArrayType_1")) return false;
    consumeToken(b, INT);
    return true;
  }

  /* ********************************************************** */
  // ASSIGN        // =
  //            | PLUS_ASSIGN   // +=
  //            | MINUS_ASSIGN  // -=
  //            | MUL_ASSIGN    // *=
  //            | QUOTIENT_ASSIGN // /=
  //            | REMAINDER_ASSIGN // %=
  //            | BIT_AND_ASSIGN   // &=
  //            | BIT_OR_ASSIGN    // |=
  //            | BIT_XOR_ASSIGN   // ^=
  //            | SHIFT_LEFT_ASSIGN // <<=
  //            | SHIFT_RIGHT_ASSIGN // >>=
  //            | BIT_CLEAR_ASSIGN
  public static boolean AssignOp(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AssignOp")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ASSIGN_OP, "<assign op>");
    r = consumeToken(b, ASSIGN);
    if (!r) r = consumeToken(b, PLUS_ASSIGN);
    if (!r) r = consumeToken(b, MINUS_ASSIGN);
    if (!r) r = consumeToken(b, MUL_ASSIGN);
    if (!r) r = consumeToken(b, QUOTIENT_ASSIGN);
    if (!r) r = consumeToken(b, REMAINDER_ASSIGN);
    if (!r) r = consumeToken(b, BIT_AND_ASSIGN);
    if (!r) r = consumeToken(b, BIT_OR_ASSIGN);
    if (!r) r = consumeToken(b, BIT_XOR_ASSIGN);
    if (!r) r = consumeToken(b, SHIFT_LEFT_ASSIGN);
    if (!r) r = consumeToken(b, SHIFT_RIGHT_ASSIGN);
    if (!r) r = consumeToken(b, BIT_CLEAR_ASSIGN);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // Expression AssignOp Expression
  public static boolean AssignStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AssignStatement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ASSIGN_STATEMENT, "<assign statement>");
    r = Expression(b, l + 1);
    r = r && AssignOp(b, l + 1);
    r = r && Expression(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // LBRACE (EOL | Statement)* RBRACE
  public static boolean Block(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Block")) return false;
    if (!nextTokenIs(b, LBRACE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, BLOCK, null);
    r = consumeToken(b, LBRACE);
    p = r; // pin = 1
    r = r && report_error_(b, Block_1(b, l + 1));
    r = p && consumeToken(b, RBRACE) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (EOL | Statement)*
  private static boolean Block_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Block_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Block_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Block_1", c)) break;
    }
    return true;
  }

  // EOL | Statement
  private static boolean Block_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Block_1_0")) return false;
    boolean r;
    r = consumeToken(b, EOL);
    if (!r) r = Statement(b, l + 1);
    return r;
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
  // ShiftExpr ( CompareOp ShiftExpr )*
  public static boolean CompareExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CompareExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, COMPARE_EXPR, "<compare expr>");
    r = ShiftExpr(b, l + 1);
    r = r && CompareExpr_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ( CompareOp ShiftExpr )*
  private static boolean CompareExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CompareExpr_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!CompareExpr_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "CompareExpr_1", c)) break;
    }
    return true;
  }

  // CompareOp ShiftExpr
  private static boolean CompareExpr_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CompareExpr_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = CompareOp(b, l + 1);
    r = r && ShiftExpr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // EQ | NOT_EQ | LESS | LESS_OR_EQUAL | GREATER | GREATER_OR_EQUAL
  public static boolean CompareOp(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CompareOp")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, COMPARE_OP, "<compare op>");
    r = consumeToken(b, EQ);
    if (!r) r = consumeToken(b, NOT_EQ);
    if (!r) r = consumeToken(b, LESS);
    if (!r) r = consumeToken(b, LESS_OR_EQUAL);
    if (!r) r = consumeToken(b, GREATER);
    if (!r) r = consumeToken(b, GREATER_OR_EQUAL);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // TypeName LBRACE (EOL | FieldLiteralList)? RBRACE
  public static boolean CompositeLiteral(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CompositeLiteral")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, COMPOSITE_LITERAL, null);
    r = TypeName(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, consumeToken(b, LBRACE));
    r = p && report_error_(b, CompositeLiteral_2(b, l + 1)) && r;
    r = p && consumeToken(b, RBRACE) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (EOL | FieldLiteralList)?
  private static boolean CompositeLiteral_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CompositeLiteral_2")) return false;
    CompositeLiteral_2_0(b, l + 1);
    return true;
  }

  // EOL | FieldLiteralList
  private static boolean CompositeLiteral_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CompositeLiteral_2_0")) return false;
    boolean r;
    r = consumeToken(b, EOL);
    if (!r) r = FieldLiteralList(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // CONST (ConstSpec | GroupedConstDeclaration)
  public static boolean ConstDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ConstDeclaration")) return false;
    if (!nextTokenIs(b, CONST)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, CONST);
    r = r && ConstDeclaration_1(b, l + 1);
    exit_section_(b, m, CONST_DECLARATION, r);
    return r;
  }

  // ConstSpec | GroupedConstDeclaration
  private static boolean ConstDeclaration_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ConstDeclaration_1")) return false;
    boolean r;
    r = ConstSpec(b, l + 1);
    if (!r) r = GroupedConstDeclaration(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER TypeAnnotation? ASSIGN Expression
  public static boolean ConstSpec(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ConstSpec")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    r = r && ConstSpec_1(b, l + 1);
    r = r && consumeToken(b, ASSIGN);
    r = r && Expression(b, l + 1);
    exit_section_(b, m, CONST_SPEC, r);
    return r;
  }

  // TypeAnnotation?
  private static boolean ConstSpec_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ConstSpec_1")) return false;
    TypeAnnotation(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // (EOL | COMMENT)* ConstSpec (EOL | COMMENT)* (ConstSpec (EOL | COMMENT)*)*
  public static boolean ConstSpecList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ConstSpecList")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CONST_SPEC_LIST, "<const spec list>");
    r = ConstSpecList_0(b, l + 1);
    r = r && ConstSpec(b, l + 1);
    r = r && ConstSpecList_2(b, l + 1);
    r = r && ConstSpecList_3(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (EOL | COMMENT)*
  private static boolean ConstSpecList_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ConstSpecList_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ConstSpecList_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ConstSpecList_0", c)) break;
    }
    return true;
  }

  // EOL | COMMENT
  private static boolean ConstSpecList_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ConstSpecList_0_0")) return false;
    boolean r;
    r = consumeToken(b, EOL);
    if (!r) r = consumeToken(b, COMMENT);
    return r;
  }

  // (EOL | COMMENT)*
  private static boolean ConstSpecList_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ConstSpecList_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ConstSpecList_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ConstSpecList_2", c)) break;
    }
    return true;
  }

  // EOL | COMMENT
  private static boolean ConstSpecList_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ConstSpecList_2_0")) return false;
    boolean r;
    r = consumeToken(b, EOL);
    if (!r) r = consumeToken(b, COMMENT);
    return r;
  }

  // (ConstSpec (EOL | COMMENT)*)*
  private static boolean ConstSpecList_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ConstSpecList_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ConstSpecList_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ConstSpecList_3", c)) break;
    }
    return true;
  }

  // ConstSpec (EOL | COMMENT)*
  private static boolean ConstSpecList_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ConstSpecList_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ConstSpec(b, l + 1);
    r = r && ConstSpecList_3_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (EOL | COMMENT)*
  private static boolean ConstSpecList_3_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ConstSpecList_3_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ConstSpecList_3_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ConstSpecList_3_0_1", c)) break;
    }
    return true;
  }

  // EOL | COMMENT
  private static boolean ConstSpecList_3_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ConstSpecList_3_0_1_0")) return false;
    boolean r;
    r = consumeToken(b, EOL);
    if (!r) r = consumeToken(b, COMMENT);
    return r;
  }

  /* ********************************************************** */
  // OrExpr
  public static boolean Expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, EXPRESSION, "<expression>");
    r = OrExpr(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // Expression ( COMMA Expression )*
  public static boolean ExpressionList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExpressionList")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, EXPRESSION_LIST, "<expression list>");
    r = Expression(b, l + 1);
    r = r && ExpressionList_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ( COMMA Expression )*
  private static boolean ExpressionList_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExpressionList_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ExpressionList_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ExpressionList_1", c)) break;
    }
    return true;
  }

  // COMMA Expression
  private static boolean ExpressionList_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExpressionList_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && Expression(b, l + 1);
    exit_section_(b, m, null, r);
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
  // IdentifierList FieldType
  public static boolean FieldDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FieldDeclaration")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = IdentifierList(b, l + 1);
    r = r && FieldType(b, l + 1);
    exit_section_(b, m, FIELD_DECLARATION, r);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER (COLON Expression)?
  public static boolean FieldLiteral(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FieldLiteral")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    r = r && FieldLiteral_1(b, l + 1);
    exit_section_(b, m, FIELD_LITERAL, r);
    return r;
  }

  // (COLON Expression)?
  private static boolean FieldLiteral_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FieldLiteral_1")) return false;
    FieldLiteral_1_0(b, l + 1);
    return true;
  }

  // COLON Expression
  private static boolean FieldLiteral_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FieldLiteral_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COLON);
    r = r && Expression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // FieldLiteral ( COMMA FieldLiteral )* (EOL | COMMA)?
  public static boolean FieldLiteralList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FieldLiteralList")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = FieldLiteral(b, l + 1);
    r = r && FieldLiteralList_1(b, l + 1);
    r = r && FieldLiteralList_2(b, l + 1);
    exit_section_(b, m, FIELD_LITERAL_LIST, r);
    return r;
  }

  // ( COMMA FieldLiteral )*
  private static boolean FieldLiteralList_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FieldLiteralList_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!FieldLiteralList_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "FieldLiteralList_1", c)) break;
    }
    return true;
  }

  // COMMA FieldLiteral
  private static boolean FieldLiteralList_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FieldLiteralList_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && FieldLiteral(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (EOL | COMMA)?
  private static boolean FieldLiteralList_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FieldLiteralList_2")) return false;
    FieldLiteralList_2_0(b, l + 1);
    return true;
  }

  // EOL | COMMA
  private static boolean FieldLiteralList_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FieldLiteralList_2_0")) return false;
    boolean r;
    r = consumeToken(b, EOL);
    if (!r) r = consumeToken(b, COMMA);
    return r;
  }

  /* ********************************************************** */
  // TypeBody
  public static boolean FieldType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FieldType")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FIELD_TYPE, "<field type>");
    r = TypeBody(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ( EOL )*
  //     FieldDeclaration
  //     ( (EOL | SEMICOLON)+ FieldDeclaration )*
  //     ( EOL | SEMICOLON )*
  public static boolean Fields(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Fields")) return false;
    if (!nextTokenIs(b, "<fields>", EOL, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FIELDS, "<fields>");
    r = Fields_0(b, l + 1);
    r = r && FieldDeclaration(b, l + 1);
    r = r && Fields_2(b, l + 1);
    r = r && Fields_3(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ( EOL )*
  private static boolean Fields_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Fields_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, EOL)) break;
      if (!empty_element_parsed_guard_(b, "Fields_0", c)) break;
    }
    return true;
  }

  // ( (EOL | SEMICOLON)+ FieldDeclaration )*
  private static boolean Fields_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Fields_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Fields_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Fields_2", c)) break;
    }
    return true;
  }

  // (EOL | SEMICOLON)+ FieldDeclaration
  private static boolean Fields_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Fields_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Fields_2_0_0(b, l + 1);
    r = r && FieldDeclaration(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (EOL | SEMICOLON)+
  private static boolean Fields_2_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Fields_2_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Fields_2_0_0_0(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!Fields_2_0_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Fields_2_0_0", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // EOL | SEMICOLON
  private static boolean Fields_2_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Fields_2_0_0_0")) return false;
    boolean r;
    r = consumeToken(b, EOL);
    if (!r) r = consumeToken(b, SEMICOLON);
    return r;
  }

  // ( EOL | SEMICOLON )*
  private static boolean Fields_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Fields_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Fields_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Fields_3", c)) break;
    }
    return true;
  }

  // EOL | SEMICOLON
  private static boolean Fields_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Fields_3_0")) return false;
    boolean r;
    r = consumeToken(b, EOL);
    if (!r) r = consumeToken(b, SEMICOLON);
    return r;
  }

  /* ********************************************************** */
  // (EOL | COMMENT)* PackageClause (EOL | COMMENT)* ImportList? (EOL | COMMENT)* TopLevelDeclaration* (EOL | COMMENT)*
  static boolean File(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "File")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = File_0(b, l + 1);
    r = r && PackageClause(b, l + 1);
    r = r && File_2(b, l + 1);
    r = r && File_3(b, l + 1);
    r = r && File_4(b, l + 1);
    r = r && File_5(b, l + 1);
    r = r && File_6(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (EOL | COMMENT)*
  private static boolean File_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "File_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!File_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "File_0", c)) break;
    }
    return true;
  }

  // EOL | COMMENT
  private static boolean File_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "File_0_0")) return false;
    boolean r;
    r = consumeToken(b, EOL);
    if (!r) r = consumeToken(b, COMMENT);
    return r;
  }

  // (EOL | COMMENT)*
  private static boolean File_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "File_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!File_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "File_2", c)) break;
    }
    return true;
  }

  // EOL | COMMENT
  private static boolean File_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "File_2_0")) return false;
    boolean r;
    r = consumeToken(b, EOL);
    if (!r) r = consumeToken(b, COMMENT);
    return r;
  }

  // ImportList?
  private static boolean File_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "File_3")) return false;
    ImportList(b, l + 1);
    return true;
  }

  // (EOL | COMMENT)*
  private static boolean File_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "File_4")) return false;
    while (true) {
      int c = current_position_(b);
      if (!File_4_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "File_4", c)) break;
    }
    return true;
  }

  // EOL | COMMENT
  private static boolean File_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "File_4_0")) return false;
    boolean r;
    r = consumeToken(b, EOL);
    if (!r) r = consumeToken(b, COMMENT);
    return r;
  }

  // TopLevelDeclaration*
  private static boolean File_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "File_5")) return false;
    while (true) {
      int c = current_position_(b);
      if (!TopLevelDeclaration(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "File_5", c)) break;
    }
    return true;
  }

  // (EOL | COMMENT)*
  private static boolean File_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "File_6")) return false;
    while (true) {
      int c = current_position_(b);
      if (!File_6_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "File_6", c)) break;
    }
    return true;
  }

  // EOL | COMMENT
  private static boolean File_6_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "File_6_0")) return false;
    boolean r;
    r = consumeToken(b, EOL);
    if (!r) r = consumeToken(b, COMMENT);
    return r;
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
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, FUNC);
    r = r && Signature(b, l + 1);
    exit_section_(b, m, FUNCTION_TYPE, r);
    return r;
  }

  /* ********************************************************** */
  // LPAREN EOL ConstSpecList RPAREN
  public static boolean GroupedConstDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "GroupedConstDeclaration")) return false;
    if (!nextTokenIs(b, LPAREN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, LPAREN, EOL);
    r = r && ConstSpecList(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, GROUPED_CONST_DECLARATION, r);
    return r;
  }

  /* ********************************************************** */
  // LPAREN EOL TypeSpecList RPAREN
  public static boolean GroupedTypeDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "GroupedTypeDeclaration")) return false;
    if (!nextTokenIs(b, LPAREN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, LPAREN, EOL);
    r = r && TypeSpecList(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, GROUPED_TYPE_DECLARATION, r);
    return r;
  }

  /* ********************************************************** */
  // LPAREN EOL VarSpecList RPAREN
  public static boolean GroupedVarDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "GroupedVarDeclaration")) return false;
    if (!nextTokenIs(b, LPAREN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, LPAREN, EOL);
    r = r && VarSpecList(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, GROUPED_VAR_DECLARATION, r);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER ( COMMA IDENTIFIER )*
  public static boolean IdentifierList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IdentifierList")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    r = r && IdentifierList_1(b, l + 1);
    exit_section_(b, m, IDENTIFIER_LIST, r);
    return r;
  }

  // ( COMMA IDENTIFIER )*
  private static boolean IdentifierList_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IdentifierList_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!IdentifierList_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "IdentifierList_1", c)) break;
    }
    return true;
  }

  // COMMA IDENTIFIER
  private static boolean IdentifierList_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IdentifierList_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, COMMA, IDENTIFIER);
    exit_section_(b, m, null, r);
    return r;
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
  // IMPORT ( ImportSpec | LPAREN ImportSpecList? RPAREN ) (EOL | COMMENT)*
  public static boolean ImportDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportDeclaration")) return false;
    if (!nextTokenIs(b, IMPORT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IMPORT);
    r = r && ImportDeclaration_1(b, l + 1);
    r = r && ImportDeclaration_2(b, l + 1);
    exit_section_(b, m, IMPORT_DECLARATION, r);
    return r;
  }

  // ImportSpec | LPAREN ImportSpecList? RPAREN
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

  // (EOL | COMMENT)*
  private static boolean ImportDeclaration_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportDeclaration_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ImportDeclaration_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ImportDeclaration_2", c)) break;
    }
    return true;
  }

  // EOL | COMMENT
  private static boolean ImportDeclaration_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportDeclaration_2_0")) return false;
    boolean r;
    r = consumeToken(b, EOL);
    if (!r) r = consumeToken(b, COMMENT);
    return r;
  }

  /* ********************************************************** */
  // (EOL | COMMENT)* ImportDeclaration*
  public static boolean ImportList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportList")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, IMPORT_LIST, "<import list>");
    r = ImportList_0(b, l + 1);
    r = r && ImportList_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (EOL | COMMENT)*
  private static boolean ImportList_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportList_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ImportList_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ImportList_0", c)) break;
    }
    return true;
  }

  // EOL | COMMENT
  private static boolean ImportList_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportList_0_0")) return false;
    boolean r;
    r = consumeToken(b, EOL);
    if (!r) r = consumeToken(b, COMMENT);
    return r;
  }

  // ImportDeclaration*
  private static boolean ImportList_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportList_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ImportDeclaration(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ImportList_1", c)) break;
    }
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
  // ( EOL )* ImportSpec
  //     ( ( EOL )+ ImportSpec )*
  //     ( EOL )*
  public static boolean ImportSpecList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportSpecList")) return false;
    if (!nextTokenIs(b, "<import spec list>", EOL, STRING)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, IMPORT_SPEC_LIST, "<import spec list>");
    r = ImportSpecList_0(b, l + 1);
    r = r && ImportSpec(b, l + 1);
    r = r && ImportSpecList_2(b, l + 1);
    r = r && ImportSpecList_3(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ( EOL )*
  private static boolean ImportSpecList_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportSpecList_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, EOL)) break;
      if (!empty_element_parsed_guard_(b, "ImportSpecList_0", c)) break;
    }
    return true;
  }

  // ( ( EOL )+ ImportSpec )*
  private static boolean ImportSpecList_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportSpecList_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ImportSpecList_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ImportSpecList_2", c)) break;
    }
    return true;
  }

  // ( EOL )+ ImportSpec
  private static boolean ImportSpecList_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportSpecList_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ImportSpecList_2_0_0(b, l + 1);
    r = r && ImportSpec(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ( EOL )+
  private static boolean ImportSpecList_2_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportSpecList_2_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EOL);
    while (r) {
      int c = current_position_(b);
      if (!consumeToken(b, EOL)) break;
      if (!empty_element_parsed_guard_(b, "ImportSpecList_2_0_0", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // ( EOL )*
  private static boolean ImportSpecList_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportSpecList_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, EOL)) break;
      if (!empty_element_parsed_guard_(b, "ImportSpecList_3", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // Expression (PLUS_PLUS | MINUS_MINUS)
  public static boolean IncDecStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IncDecStatement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, INC_DEC_STATEMENT, "<inc dec statement>");
    r = Expression(b, l + 1);
    r = r && IncDecStatement_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // PLUS_PLUS | MINUS_MINUS
  private static boolean IncDecStatement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IncDecStatement_1")) return false;
    boolean r;
    r = consumeToken(b, PLUS_PLUS);
    if (!r) r = consumeToken(b, MINUS_MINUS);
    return r;
  }

  /* ********************************************************** */
  // INT | STRING | NIL
  public static boolean Literal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Literal")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LITERAL, "<literal>");
    r = consumeToken(b, INT);
    if (!r) r = consumeToken(b, STRING);
    if (!r) r = consumeToken(b, NIL);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // MAP LBRACK TypeBody RBRACK TypeBody
  public static boolean MapType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapType")) return false;
    if (!nextTokenIs(b, MAP)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, MAP, LBRACK);
    r = r && TypeBody(b, l + 1);
    r = r && consumeToken(b, RBRACK);
    r = r && TypeBody(b, l + 1);
    exit_section_(b, m, MAP_TYPE, r);
    return r;
  }

  /* ********************************************************** */
  // UnaryExpr ( MulOp UnaryExpr )*
  public static boolean MulExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MulExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, MUL_EXPR, "<mul expr>");
    r = UnaryExpr(b, l + 1);
    r = r && MulExpr_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ( MulOp UnaryExpr )*
  private static boolean MulExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MulExpr_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!MulExpr_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "MulExpr_1", c)) break;
    }
    return true;
  }

  // MulOp UnaryExpr
  private static boolean MulExpr_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MulExpr_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = MulOp(b, l + 1);
    r = r && UnaryExpr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // MUL | QUOTIENT | REMAINDER | BIT_AND
  public static boolean MulOp(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MulOp")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, MUL_OP, "<mul op>");
    r = consumeToken(b, MUL);
    if (!r) r = consumeToken(b, QUOTIENT);
    if (!r) r = consumeToken(b, REMAINDER);
    if (!r) r = consumeToken(b, BIT_AND);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // AndExpr ( COND_OR AndExpr )*
  public static boolean OrExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "OrExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, OR_EXPR, "<or expr>");
    r = AndExpr(b, l + 1);
    r = r && OrExpr_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ( COND_OR AndExpr )*
  private static boolean OrExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "OrExpr_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!OrExpr_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "OrExpr_1", c)) break;
    }
    return true;
  }

  // COND_OR AndExpr
  private static boolean OrExpr_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "OrExpr_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COND_OR);
    r = r && AndExpr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // PACKAGE IDENTIFIER (EOL | COMMENT)*
  public static boolean PackageClause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PackageClause")) return false;
    if (!nextTokenIs(b, PACKAGE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, PACKAGE, IDENTIFIER);
    r = r && PackageClause_2(b, l + 1);
    exit_section_(b, m, PACKAGE_CLAUSE, r);
    return r;
  }

  // (EOL | COMMENT)*
  private static boolean PackageClause_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PackageClause_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!PackageClause_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "PackageClause_2", c)) break;
    }
    return true;
  }

  // EOL | COMMENT
  private static boolean PackageClause_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PackageClause_2_0")) return false;
    boolean r;
    r = consumeToken(b, EOL);
    if (!r) r = consumeToken(b, COMMENT);
    return r;
  }

  /* ********************************************************** */
  // (IDENTIFIER TypeBody) | TypeBody
  public static boolean ParameterDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ParameterDeclaration")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PARAMETER_DECLARATION, "<parameter declaration>");
    r = ParameterDeclaration_0(b, l + 1);
    if (!r) r = TypeBody(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // IDENTIFIER TypeBody
  private static boolean ParameterDeclaration_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ParameterDeclaration_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    r = r && TypeBody(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ParameterDeclaration (COMMA ParameterDeclaration)* COMMA?
  public static boolean Parameters(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Parameters")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PARAMETERS, "<parameters>");
    r = ParameterDeclaration(b, l + 1);
    r = r && Parameters_1(b, l + 1);
    r = r && Parameters_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
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
  // MUL TypeName
  public static boolean PointerType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PointerType")) return false;
    if (!nextTokenIs(b, MUL)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, MUL);
    r = r && TypeName(b, l + 1);
    exit_section_(b, m, POINTER_TYPE, r);
    return r;
  }

  /* ********************************************************** */
  // CompositeLiteral
  //   | TypeConversion
  //   | (IDENTIFIER | Literal)
  //     (SelectorSuffix | CallSuffix | TypeAssertionSuffix)*
  //   | LPAREN Expression RPAREN
  public static boolean PrimaryExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrimaryExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PRIMARY_EXPR, "<primary expr>");
    r = CompositeLiteral(b, l + 1);
    if (!r) r = TypeConversion(b, l + 1);
    if (!r) r = PrimaryExpr_2(b, l + 1);
    if (!r) r = PrimaryExpr_3(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (IDENTIFIER | Literal)
  //     (SelectorSuffix | CallSuffix | TypeAssertionSuffix)*
  private static boolean PrimaryExpr_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrimaryExpr_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = PrimaryExpr_2_0(b, l + 1);
    r = r && PrimaryExpr_2_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // IDENTIFIER | Literal
  private static boolean PrimaryExpr_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrimaryExpr_2_0")) return false;
    boolean r;
    r = consumeToken(b, IDENTIFIER);
    if (!r) r = Literal(b, l + 1);
    return r;
  }

  // (SelectorSuffix | CallSuffix | TypeAssertionSuffix)*
  private static boolean PrimaryExpr_2_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrimaryExpr_2_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!PrimaryExpr_2_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "PrimaryExpr_2_1", c)) break;
    }
    return true;
  }

  // SelectorSuffix | CallSuffix | TypeAssertionSuffix
  private static boolean PrimaryExpr_2_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrimaryExpr_2_1_0")) return false;
    boolean r;
    r = SelectorSuffix(b, l + 1);
    if (!r) r = CallSuffix(b, l + 1);
    if (!r) r = TypeAssertionSuffix(b, l + 1);
    return r;
  }

  // LPAREN Expression RPAREN
  private static boolean PrimaryExpr_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrimaryExpr_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPAREN);
    r = r && Expression(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER VAR_ASSIGN Expression
  public static boolean PropertyDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PropertyDeclaration")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IDENTIFIER, VAR_ASSIGN);
    r = r && Expression(b, l + 1);
    exit_section_(b, m, PROPERTY_DECLARATION, r);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER '.' IDENTIFIER
  public static boolean QualifiedIdentifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "QualifiedIdentifier")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IDENTIFIER, DOT, IDENTIFIER);
    exit_section_(b, m, QUALIFIED_IDENTIFIER, r);
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
  // RETURN ExpressionList?
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

  // ExpressionList?
  private static boolean ReturnStatement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ReturnStatement_1")) return false;
    ExpressionList(b, l + 1);
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
  // AddExpr ( ShiftOp AddExpr )*
  public static boolean ShiftExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ShiftExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SHIFT_EXPR, "<shift expr>");
    r = AddExpr(b, l + 1);
    r = r && ShiftExpr_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ( ShiftOp AddExpr )*
  private static boolean ShiftExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ShiftExpr_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ShiftExpr_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ShiftExpr_1", c)) break;
    }
    return true;
  }

  // ShiftOp AddExpr
  private static boolean ShiftExpr_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ShiftExpr_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ShiftOp(b, l + 1);
    r = r && AddExpr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // SHIFT_LEFT | SHIFT_RIGHT | BIT_CLEAR
  public static boolean ShiftOp(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ShiftOp")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SHIFT_OP, "<shift op>");
    r = consumeToken(b, SHIFT_LEFT);
    if (!r) r = consumeToken(b, SHIFT_RIGHT);
    if (!r) r = consumeToken(b, BIT_CLEAR);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // VarDefinitionList VAR_ASSIGN ExpressionList
  public static boolean ShortVarDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ShortVarDeclaration")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, SHORT_VAR_DECLARATION, null);
    r = VarDefinitionList(b, l + 1);
    r = r && consumeToken(b, VAR_ASSIGN);
    p = r; // pin = 2
    r = r && ExpressionList(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
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
  // ShortVarDeclaration
  //   | IncDecStatement
  //   | AssignStatement
  //   | ExpressionStatement
  public static boolean SimpleStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SimpleStatement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SIMPLE_STATEMENT, "<simple statement>");
    r = ShortVarDeclaration(b, l + 1);
    if (!r) r = IncDecStatement(b, l + 1);
    if (!r) r = AssignStatement(b, l + 1);
    if (!r) r = ExpressionStatement(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // Block
  //   | EOL
  //   | IfStatement
  //   | ForStatement
  //   | SwitchStatement
  //   | ReturnStatement
  //   | SimpleStatement
  public static boolean Statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Statement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, STATEMENT, "<statement>");
    r = Block(b, l + 1);
    if (!r) r = consumeToken(b, EOL);
    if (!r) r = IfStatement(b, l + 1);
    if (!r) r = ForStatement(b, l + 1);
    if (!r) r = SwitchStatement(b, l + 1);
    if (!r) r = ReturnStatement(b, l + 1);
    if (!r) r = SimpleStatement(b, l + 1);
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
  //   | PropertyDeclaration
  public static boolean TopLevelDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TopLevelDeclaration")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TOP_LEVEL_DECLARATION, "<top level declaration>");
    r = FunctionDeclaration(b, l + 1);
    if (!r) r = TypeDeclaration(b, l + 1);
    if (!r) r = VarDeclaration(b, l + 1);
    if (!r) r = ConstDeclaration(b, l + 1);
    if (!r) r = PropertyDeclaration(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // TypeBody
  public static boolean TypeAnnotation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypeAnnotation")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TYPE_ANNOTATION, "<type annotation>");
    r = TypeBody(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // '.' LPAREN TypeBody RPAREN
  public static boolean TypeAssertionSuffix(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypeAssertionSuffix")) return false;
    if (!nextTokenIs(b, DOT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DOT, LPAREN);
    r = r && TypeBody(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, TYPE_ASSERTION_SUFFIX, r);
    return r;
  }

  /* ********************************************************** */
  // '(' TypeBody ')'
  //   | PointerType
  //   | StructType
  //   | MapType
  //   | ArrayType
  //   | FunctionType
  //   | QualifiedIdentifier
  //   | TypeName
  public static boolean TypeBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypeBody")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TYPE_BODY, "<type body>");
    r = TypeBody_0(b, l + 1);
    if (!r) r = PointerType(b, l + 1);
    if (!r) r = StructType(b, l + 1);
    if (!r) r = MapType(b, l + 1);
    if (!r) r = ArrayType(b, l + 1);
    if (!r) r = FunctionType(b, l + 1);
    if (!r) r = QualifiedIdentifier(b, l + 1);
    if (!r) r = TypeName(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // '(' TypeBody ')'
  private static boolean TypeBody_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypeBody_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPAREN);
    r = r && TypeBody(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // LPAREN MUL? TypeName RPAREN LPAREN Expression RPAREN
  public static boolean TypeConversion(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypeConversion")) return false;
    if (!nextTokenIs(b, LPAREN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPAREN);
    r = r && TypeConversion_1(b, l + 1);
    r = r && TypeName(b, l + 1);
    r = r && consumeTokens(b, 0, RPAREN, LPAREN);
    r = r && Expression(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, TYPE_CONVERSION, r);
    return r;
  }

  // MUL?
  private static boolean TypeConversion_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypeConversion_1")) return false;
    consumeToken(b, MUL);
    return true;
  }

  /* ********************************************************** */
  // TYPE (TypeSpec | GroupedTypeDeclaration)
  public static boolean TypeDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypeDeclaration")) return false;
    if (!nextTokenIs(b, TYPE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, TYPE);
    r = r && TypeDeclaration_1(b, l + 1);
    exit_section_(b, m, TYPE_DECLARATION, r);
    return r;
  }

  // TypeSpec | GroupedTypeDeclaration
  private static boolean TypeDeclaration_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypeDeclaration_1")) return false;
    boolean r;
    r = TypeSpec(b, l + 1);
    if (!r) r = GroupedTypeDeclaration(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER ('.' IDENTIFIER )*
  public static boolean TypeName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypeName")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    r = r && TypeName_1(b, l + 1);
    exit_section_(b, m, TYPE_NAME, r);
    return r;
  }

  // ('.' IDENTIFIER )*
  private static boolean TypeName_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypeName_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!TypeName_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "TypeName_1", c)) break;
    }
    return true;
  }

  // '.' IDENTIFIER
  private static boolean TypeName_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypeName_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DOT, IDENTIFIER);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER TypeBody
  public static boolean TypeSpec(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypeSpec")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    r = r && TypeBody(b, l + 1);
    exit_section_(b, m, TYPE_SPEC, r);
    return r;
  }

  /* ********************************************************** */
  // (EOL | COMMENT)* TypeSpec (EOL | COMMENT)* (TypeSpec (EOL | COMMENT)*)*
  public static boolean TypeSpecList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypeSpecList")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TYPE_SPEC_LIST, "<type spec list>");
    r = TypeSpecList_0(b, l + 1);
    r = r && TypeSpec(b, l + 1);
    r = r && TypeSpecList_2(b, l + 1);
    r = r && TypeSpecList_3(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (EOL | COMMENT)*
  private static boolean TypeSpecList_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypeSpecList_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!TypeSpecList_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "TypeSpecList_0", c)) break;
    }
    return true;
  }

  // EOL | COMMENT
  private static boolean TypeSpecList_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypeSpecList_0_0")) return false;
    boolean r;
    r = consumeToken(b, EOL);
    if (!r) r = consumeToken(b, COMMENT);
    return r;
  }

  // (EOL | COMMENT)*
  private static boolean TypeSpecList_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypeSpecList_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!TypeSpecList_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "TypeSpecList_2", c)) break;
    }
    return true;
  }

  // EOL | COMMENT
  private static boolean TypeSpecList_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypeSpecList_2_0")) return false;
    boolean r;
    r = consumeToken(b, EOL);
    if (!r) r = consumeToken(b, COMMENT);
    return r;
  }

  // (TypeSpec (EOL | COMMENT)*)*
  private static boolean TypeSpecList_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypeSpecList_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!TypeSpecList_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "TypeSpecList_3", c)) break;
    }
    return true;
  }

  // TypeSpec (EOL | COMMENT)*
  private static boolean TypeSpecList_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypeSpecList_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = TypeSpec(b, l + 1);
    r = r && TypeSpecList_3_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (EOL | COMMENT)*
  private static boolean TypeSpecList_3_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypeSpecList_3_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!TypeSpecList_3_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "TypeSpecList_3_0_1", c)) break;
    }
    return true;
  }

  // EOL | COMMENT
  private static boolean TypeSpecList_3_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypeSpecList_3_0_1_0")) return false;
    boolean r;
    r = consumeToken(b, EOL);
    if (!r) r = consumeToken(b, COMMENT);
    return r;
  }

  /* ********************************************************** */
  // ( UnaryOp )* PrimaryExpr
  public static boolean UnaryExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UnaryExpr")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, UNARY_EXPR, "<unary expr>");
    r = UnaryExpr_0(b, l + 1);
    p = r; // pin = 1
    r = r && PrimaryExpr(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // ( UnaryOp )*
  private static boolean UnaryExpr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UnaryExpr_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!UnaryExpr_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "UnaryExpr_0", c)) break;
    }
    return true;
  }

  // ( UnaryOp )
  private static boolean UnaryExpr_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UnaryExpr_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = UnaryOp(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // PLUS    // +
  //           | MINUS   // -
  //           | NOT     // !
  //           | BIT_AND // &
  //           | BIT_XOR
  public static boolean UnaryOp(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UnaryOp")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, UNARY_OP, "<unary op>");
    r = consumeToken(b, PLUS);
    if (!r) r = consumeToken(b, MINUS);
    if (!r) r = consumeToken(b, NOT);
    if (!r) r = consumeToken(b, BIT_AND);
    if (!r) r = consumeToken(b, BIT_XOR);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // VAR (VarSpec | GroupedVarDeclaration)
  public static boolean VarDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarDeclaration")) return false;
    if (!nextTokenIs(b, VAR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, VAR);
    r = r && VarDeclaration_1(b, l + 1);
    exit_section_(b, m, VAR_DECLARATION, r);
    return r;
  }

  // VarSpec | GroupedVarDeclaration
  private static boolean VarDeclaration_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarDeclaration_1")) return false;
    boolean r;
    r = VarSpec(b, l + 1);
    if (!r) r = GroupedVarDeclaration(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER ( COMMA IDENTIFIER )*
  public static boolean VarDefinitionList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarDefinitionList")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    r = r && VarDefinitionList_1(b, l + 1);
    exit_section_(b, m, VAR_DEFINITION_LIST, r);
    return r;
  }

  // ( COMMA IDENTIFIER )*
  private static boolean VarDefinitionList_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarDefinitionList_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!VarDefinitionList_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "VarDefinitionList_1", c)) break;
    }
    return true;
  }

  // COMMA IDENTIFIER
  private static boolean VarDefinitionList_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarDefinitionList_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, COMMA, IDENTIFIER);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER TypeBody (ASSIGN Expression)?
  public static boolean VarSpec(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarSpec")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    r = r && TypeBody(b, l + 1);
    r = r && VarSpec_2(b, l + 1);
    exit_section_(b, m, VAR_SPEC, r);
    return r;
  }

  // (ASSIGN Expression)?
  private static boolean VarSpec_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarSpec_2")) return false;
    VarSpec_2_0(b, l + 1);
    return true;
  }

  // ASSIGN Expression
  private static boolean VarSpec_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarSpec_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ASSIGN);
    r = r && Expression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (EOL | COMMENT)* VarSpec (EOL | COMMENT)* (VarSpec (EOL | COMMENT)*)*
  public static boolean VarSpecList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarSpecList")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, VAR_SPEC_LIST, "<var spec list>");
    r = VarSpecList_0(b, l + 1);
    r = r && VarSpec(b, l + 1);
    r = r && VarSpecList_2(b, l + 1);
    r = r && VarSpecList_3(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (EOL | COMMENT)*
  private static boolean VarSpecList_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarSpecList_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!VarSpecList_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "VarSpecList_0", c)) break;
    }
    return true;
  }

  // EOL | COMMENT
  private static boolean VarSpecList_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarSpecList_0_0")) return false;
    boolean r;
    r = consumeToken(b, EOL);
    if (!r) r = consumeToken(b, COMMENT);
    return r;
  }

  // (EOL | COMMENT)*
  private static boolean VarSpecList_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarSpecList_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!VarSpecList_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "VarSpecList_2", c)) break;
    }
    return true;
  }

  // EOL | COMMENT
  private static boolean VarSpecList_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarSpecList_2_0")) return false;
    boolean r;
    r = consumeToken(b, EOL);
    if (!r) r = consumeToken(b, COMMENT);
    return r;
  }

  // (VarSpec (EOL | COMMENT)*)*
  private static boolean VarSpecList_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarSpecList_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!VarSpecList_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "VarSpecList_3", c)) break;
    }
    return true;
  }

  // VarSpec (EOL | COMMENT)*
  private static boolean VarSpecList_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarSpecList_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = VarSpec(b, l + 1);
    r = r && VarSpecList_3_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (EOL | COMMENT)*
  private static boolean VarSpecList_3_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarSpecList_3_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!VarSpecList_3_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "VarSpecList_3_0_1", c)) break;
    }
    return true;
  }

  // EOL | COMMENT
  private static boolean VarSpecList_3_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarSpecList_3_0_1_0")) return false;
    boolean r;
    r = consumeToken(b, EOL);
    if (!r) r = consumeToken(b, COMMENT);
    return r;
  }

}
