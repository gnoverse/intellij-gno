// This is a generated file. Not intended for manual editing.
package com.github.intellij.gno.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.github.intellij.gno.psi.impl.*;

public interface GnoTypes {

  IElementType PACKAGE_DECL = new GnoElementType("PACKAGE_DECL");
  IElementType TOKEN = new GnoElementType("TOKEN");

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
      if (type == PACKAGE_DECL) {
        return new GnoPackageDeclImpl(node);
      }
      else if (type == TOKEN) {
        return new GnoTokenImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
