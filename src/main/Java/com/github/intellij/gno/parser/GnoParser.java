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
  // PackageDecl? Token* EOF
  static boolean File(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "File")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = File_0(b, l + 1);
    r = r && File_1(b, l + 1);
    r = r && consumeToken(b, EOF);
    exit_section_(b, m, null, r);
    return r;
  }

  // PackageDecl?
  private static boolean File_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "File_0")) return false;
    PackageDecl(b, l + 1);
    return true;
  }

  // Token*
  private static boolean File_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "File_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Token(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "File_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // "package" WHITE_SPACE? IDENTIFIER
  public static boolean PackageDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PackageDecl")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PACKAGE_DECL, "<package decl>");
    r = consumeToken(b, "package");
    r = r && PackageDecl_1(b, l + 1);
    r = r && consumeToken(b, IDENTIFIER);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // WHITE_SPACE?
  private static boolean PackageDecl_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PackageDecl_1")) return false;
    consumeToken(b, WHITE_SPACE);
    return true;
  }

  /* ********************************************************** */
  // IDENTIFIER
  //   | WHITE_SPACE
  //   | EOL
  //   | COMMENT
  //   | DOT
  //   | ANY_CHAR
  public static boolean Token(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Token")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TOKEN, "<token>");
    r = consumeToken(b, IDENTIFIER);
    if (!r) r = consumeToken(b, WHITE_SPACE);
    if (!r) r = consumeToken(b, EOL);
    if (!r) r = consumeToken(b, COMMENT);
    if (!r) r = consumeToken(b, DOT);
    if (!r) r = consumeToken(b, ANY_CHAR);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

}
