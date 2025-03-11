// This is a generated file. Not intended for manual editing.
// I've added error ignoring to have only identifiers for hypertext, as the syntax is handled by gnopls
package com.github.intellij.gno.parser;

import com.github.intellij.gno.psi.GnoTypes;
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
  // IDENTIFIER "=" Expression
  public static boolean Assignment(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Assignment")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    r = r && consumeToken(b, "=");
    r = r && Expression(b, l + 1);
    exit_section_(b, m, ASSIGNMENT, r);
    return r;
  }

  /* ********************************************************** */
  // "var" IDENTIFIER ("=" Expression)?
  public static boolean Declaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Declaration")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DECLARATION, "<declaration>");
    r = consumeToken(b, "var");
    r = r && consumeToken(b, IDENTIFIER);
    r = r && Declaration_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ("=" Expression)?
  private static boolean Declaration_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Declaration_2")) return false;
    Declaration_2_0(b, l + 1);
    return true;
  }

  // "=" Expression
  private static boolean Declaration_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Declaration_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, "=");
    r = r && Expression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER | FunctionCall
  public static boolean Expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    if (!r) r = FunctionCall(b, l + 1);
    exit_section_(b, m, EXPRESSION, r);
    return r;
  }

  /* ********************************************************** */
  // (COMMENT | EOL)* PackageDecl ImportDecl* Statement* EOF
  static boolean File(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "File")) return false;
    boolean r;
    Marker m = enter_section_(b);

    r = File_0(b, l + 1);
    r = r && PackageDecl(b, l + 1);
    r = r && File_2(b, l + 1);
    r = r && File_3(b, l + 1);

    // Removes strict EOF requirement
    if (nextTokenIs(b, GnoTypes.EOF)) {
      consumeToken(b, GnoTypes.EOF);
    } else {
      // Ignore all remaining tokens to avoid blocking
      while (!b.eof()) {
        b.advanceLexer();
      }
    }

    exit_section_(b, m, null, r);
    return r;
  }


  // (COMMENT | EOL)*
  private static boolean File_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "File_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!File_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "File_0", c)) break;
    }
    return true;
  }

  // COMMENT | EOL
  private static boolean File_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "File_0_0")) return false;
    boolean r;
    r = consumeToken(b, COMMENT);
    if (!r) r = consumeToken(b, EOL);
    return r;
  }

  // ImportDecl*
  private static boolean File_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "File_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ImportDecl(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "File_2", c)) break;
    }
    return true;
  }

  // Statement*
  private static boolean File_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "File_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Statement(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "File_3", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // IDENTIFIER "(" (Expression ("," Expression)*)? ")"
  public static boolean FunctionCall(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionCall")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    r = r && consumeToken(b, "(");
    r = r && FunctionCall_2(b, l + 1);
    r = r && consumeToken(b, ")");
    exit_section_(b, m, FUNCTION_CALL, r);
    return r;
  }

  // (Expression ("," Expression)*)?
  private static boolean FunctionCall_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionCall_2")) return false;
    FunctionCall_2_0(b, l + 1);
    return true;
  }

  // Expression ("," Expression)*
  private static boolean FunctionCall_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionCall_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Expression(b, l + 1);
    r = r && FunctionCall_2_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ("," Expression)*
  private static boolean FunctionCall_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionCall_2_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!FunctionCall_2_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "FunctionCall_2_0_1", c)) break;
    }
    return true;
  }

  // "," Expression
  private static boolean FunctionCall_2_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionCall_2_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ",");
    r = r && Expression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "import" (StringLiteral | "(" ImportSpec* ")") EOL+
  public static boolean ImportDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportDecl")) return false;
    if (!nextTokenIs(b, "import")) return false;

    boolean r;
    Marker m = enter_section_(b);

    r = consumeToken(b, "import");

    if (nextTokenIs(b, GnoTypes.STRING_LITERAL)) {
      r = r && consumeToken(b, GnoTypes.STRING_LITERAL);
    } else if (consumeToken(b, "(")) {
      while (!b.eof() && !nextTokenIs(b, ")")) {
        if (nextTokenIs(b, GnoTypes.STRING_LITERAL)) {
          consumeToken(b, GnoTypes.STRING_LITERAL);
        } else {
          // Ignores errors by advancing the lexer
          b.advanceLexer();
        }
      }
      r = r && consumeToken(b, ")");
    }

    // Remove all `EOL` content
    while (nextTokenIs(b, GnoTypes.EOL)) {
      consumeToken(b, GnoTypes.EOL);
    }

    exit_section_(b, m, IMPORT_DECL, r);
    return r;
  }


  // StringLiteral | "(" ImportSpec* ")"
  private static boolean ImportDecl_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportDecl_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = StringLiteral(b, l + 1);
    if (!r) r = ImportDecl_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // "(" ImportSpec* ")"
  private static boolean ImportDecl_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportDecl_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, "(");
    r = r && ImportDecl_1_1_1(b, l + 1);
    r = r && consumeToken(b, ")");
    exit_section_(b, m, null, r);
    return r;
  }

  // ImportSpec*
  private static boolean ImportDecl_1_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportDecl_1_1_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ImportSpec(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ImportDecl_1_1_1", c)) break;
    }
    return true;
  }

  // EOL+
  private static boolean ImportDecl_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportDecl_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EOL);
    while (r) {
      int c = current_position_(b);
      if (!consumeToken(b, EOL)) break;
      if (!empty_element_parsed_guard_(b, "ImportDecl_2", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // StringLiteral
  public static boolean ImportSpec(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportSpec")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, IMPORT_SPEC, "<import spec>");
    r = StringLiteral(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "package" WHITE_SPACE IDENTIFIER EOL+
  public static boolean PackageDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PackageDecl")) return false;
    if (!nextTokenIs(b, "package")) return false;
    boolean r;
    Marker m = enter_section_(b);

    r = consumeToken(b, "package");

    // Ignore all errors after `package`
    while (!b.eof() && !nextTokenIs(b, GnoTypes.IDENTIFIER)) {
      b.advanceLexer();
    }

    // If no IDENTIFIER found, a dummy token is generated
    if (!consumeToken(b, GnoTypes.IDENTIFIER)) {
      consumeToken(b, "GeneratedIdentifier");
    }

    // Completely deletes `EOL` and `EOF`
    while (nextTokenIs(b, GnoTypes.EOL) || nextTokenIs(b, GnoTypes.EOF)) {
      consumeToken(b, b.getTokenType());
    }

    exit_section_(b, m, PACKAGE_DECL, r);
    return r;
  }




  // EOL+
  private static boolean PackageDecl_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PackageDecl_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EOL);
    while (r) {
      int c = current_position_(b);
      if (!consumeToken(b, EOL)) break;
      if (!empty_element_parsed_guard_(b, "PackageDecl_3", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // Expression | Declaration | Assignment
  public static boolean Statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Statement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, STATEMENT, "<statement>");
    r = Expression(b, l + 1);
    if (!r) r = Declaration(b, l + 1);
    if (!r) r = Assignment(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // '"'STRINGCONTENT'"'
  public static boolean StringLiteral(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StringLiteral")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, STRING_LITERAL, "<string literal>");
    r = consumeToken(b, "\"");
    r = r && consumeToken(b, STRINGCONTENT);
    r = r && consumeToken(b, "\"");
    exit_section_(b, l, m, r, false, null);
    return r;
  }

}
