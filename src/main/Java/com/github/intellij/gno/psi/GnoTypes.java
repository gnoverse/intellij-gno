// This is a generated file. Not intended for manual editing.
package com.github.intellij.gno.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.github.intellij.gno.psi.impl.*;

public interface GnoTypes {

  IElementType IDENTIFIER = new GnoElementType("IDENTIFIER");
  IElementType TOKEN = new GnoElementType("TOKEN");

  IElementType ANY_CHAR = new GnoTokenType("ANY_CHAR");
  IElementType COMMENT = new GnoTokenType("COMMENT");
  IElementType DOT = new GnoTokenType(".");
  IElementType EOL = new GnoTokenType("EOL");
  IElementType WHITE_SPACE = new GnoTokenType("WHITE_SPACE");
  IElementType _EOF_ = new GnoTokenType("<EOF>");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == IDENTIFIER) {
        return new GnoIdentifierImpl(node);
      }
      else if (type == TOKEN) {
        return new GnoTokenImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
