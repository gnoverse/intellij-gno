package com.github.intellij.gno.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLanguageInjectionHost;
import com.github.intellij.gno.psi.impl.GnoStringLiteralImpl;
import com.github.intellij.gno.util.GnoStringLiteralEscaper;

public interface GnoStringLiteral extends GnoExpression, PsiLanguageInjectionHost {

  @Nullable
  PsiElement getRawString();

  @Nullable
  PsiElement getString();

  boolean isValidHost();

  @NotNull
  GnoStringLiteralImpl updateText(String text);

  @NotNull
  GnoStringLiteralEscaper createLiteralTextEscaper();

  @NotNull
  String getDecodedText();

}
