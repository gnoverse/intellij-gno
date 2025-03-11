// This is a generated file. Not intended for manual editing.
package com.github.intellij.gno.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.github.intellij.gno.psi.impl.*;

public interface GnoTypes {

  IElementType ASSIGNMENT = new GnoElementType("ASSIGNMENT");
  IElementType DECLARATION = new GnoElementType("DECLARATION");
  IElementType EXPRESSION = new GnoElementType("EXPRESSION");
  IElementType FUNCTION_CALL = new GnoElementType("FUNCTION_CALL");
  IElementType IMPORT_DECL = new GnoElementType("IMPORT_DECL");
  IElementType IMPORT_SPEC = new GnoElementType("IMPORT_SPEC");
  IElementType PACKAGE_DECL = new GnoElementType("PACKAGE_DECL");
  IElementType STATEMENT = new GnoElementType("STATEMENT");
  IElementType STRING_LITERAL = new GnoElementType("STRING_LITERAL");

  IElementType ANY_CHAR = new GnoTokenType("ANY_CHAR");
  IElementType COMMENT = new GnoTokenType("COMMENT");
  IElementType DOT = new GnoTokenType(".");
  IElementType EOF = new GnoTokenType("EOF");
  IElementType EOL = new GnoTokenType("EOL");
  IElementType IDENTIFIER = new GnoTokenType("IDENTIFIER");
  IElementType WHITE_SPACE = new GnoTokenType("WHITE_SPACE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ASSIGNMENT) {
        return new GnoAssignmentImpl(node);
      }
      else if (type == DECLARATION) {
        return new GnoDeclarationImpl(node);
      }
      else if (type == EXPRESSION) {
        return new GnoExpressionImpl(node);
      }
      else if (type == FUNCTION_CALL) {
        return new GnoFunctionCallImpl(node);
      }
      else if (type == IMPORT_DECL) {
        return new GnoImportDeclImpl(node);
      }
      else if (type == IMPORT_SPEC) {
        return new GnoImportSpecImpl(node);
      }
      else if (type == PACKAGE_DECL) {
        return new GnoPackageDeclImpl(node);
      }
      else if (type == STATEMENT) {
        return new GnoStatementImpl(node);
      }
      else if (type == STRING_LITERAL) {
        return new GnoStringLiteralImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
