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
  // IfStatement
  //   | ForStatement
  //   | SwitchStatement
  public static boolean ControlStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ControlStatement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CONTROL_STATEMENT, "<control statement>");
    r = IfStatement(b, l + 1);
    if (!r) r = ForStatement(b, l + 1);
    if (!r) r = SwitchStatement(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // Literal | IDENTIFIER
  public static boolean Expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, EXPRESSION, "<expression>");
    r = Literal(b, l + 1);
    if (!r) r = consumeToken(b, IDENTIFIER);
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
  // IDENTIFIER Type
  public static boolean FieldDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FieldDeclaration")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IDENTIFIER, TYPE);
    exit_section_(b, m, FIELD_DECLARATION, r);
    return r;
  }

  /* ********************************************************** */
  // FieldDeclaration*
  public static boolean Fields(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Fields")) return false;
    Marker m = enter_section_(b, l, _NONE_, FIELDS, "<fields>");
    while (true) {
      int c = current_position_(b);
      if (!FieldDeclaration(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Fields", c)) break;
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // PackageClause ImportList TopLevelDeclaration*
  static boolean File(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "File")) return false;
    if (!nextTokenIs(b, PACKAGE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = PackageClause(b, l + 1);
    r = r && ImportList(b, l + 1);
    r = r && File_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
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
  // FOR Expression Block
  public static boolean ForStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForStatement")) return false;
    if (!nextTokenIs(b, FOR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, FOR);
    r = r && Expression(b, l + 1);
    r = r && Block(b, l + 1);
    exit_section_(b, m, FOR_STATEMENT, r);
    return r;
  }

  /* ********************************************************** */
  // FUNC IDENTIFIER Signature Block
  public static boolean FunctionDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionDeclaration")) return false;
    if (!nextTokenIs(b, FUNC)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, FUNC, IDENTIFIER, SIGNATURE);
    r = r && Block(b, l + 1);
    exit_section_(b, m, FUNCTION_DECLARATION, r);
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
  // IMPORT VALUE
  public static boolean ImportDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportDeclaration")) return false;
    if (!nextTokenIs(b, IMPORT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IMPORT, VALUE);
    exit_section_(b, m, IMPORT_DECLARATION, r);
    return r;
  }

  /* ********************************************************** */
  // LPAREN (ImportDeclaration COMMA?)* RPAREN | ImportDeclaration
  public static boolean ImportList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportList")) return false;
    if (!nextTokenIs(b, "<import list>", IMPORT, LPAREN)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, IMPORT_LIST, "<import list>");
    r = ImportList_0(b, l + 1);
    if (!r) r = ImportDeclaration(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // LPAREN (ImportDeclaration COMMA?)* RPAREN
  private static boolean ImportList_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportList_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPAREN);
    r = r && ImportList_0_1(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  // (ImportDeclaration COMMA?)*
  private static boolean ImportList_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportList_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ImportList_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ImportList_0_1", c)) break;
    }
    return true;
  }

  // ImportDeclaration COMMA?
  private static boolean ImportList_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportList_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ImportDeclaration(b, l + 1);
    r = r && ImportList_0_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // COMMA?
  private static boolean ImportList_0_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportList_0_1_0_1")) return false;
    consumeToken(b, COMMA);
    return true;
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
  // PACKAGE IDENTIFIER
  public static boolean PackageClause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PackageClause")) return false;
    if (!nextTokenIs(b, PACKAGE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, PACKAGE, IDENTIFIER);
    exit_section_(b, m, PACKAGE_CLAUSE, r);
    return r;
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
  // SWITCH Expression LBRACE CaseClauses RBRACE
  public static boolean SwitchStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SwitchStatement")) return false;
    if (!nextTokenIs(b, SWITCH)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SWITCH);
    r = r && Expression(b, l + 1);
    r = r && consumeToken(b, LBRACE);
    r = r && CaseClauses(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, SWITCH_STATEMENT, r);
    return r;
  }

  /* ********************************************************** */
  // FunctionDeclaration
  //   | TypeDeclaration
  //   | VarDeclaration
  public static boolean TopLevelDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TopLevelDeclaration")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TOP_LEVEL_DECLARATION, "<top level declaration>");
    r = FunctionDeclaration(b, l + 1);
    if (!r) r = TypeDeclaration(b, l + 1);
    if (!r) r = consumeToken(b, VARDECLARATION);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // TYPE IDENTIFIER StructType
  public static boolean TypeDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypeDeclaration")) return false;
    if (!nextTokenIs(b, TYPE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, TYPE, IDENTIFIER);
    r = r && StructType(b, l + 1);
    exit_section_(b, m, TYPE_DECLARATION, r);
    return r;
  }

}
