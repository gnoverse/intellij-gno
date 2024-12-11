package com.github.intellij.gno.codeInsight.unwrap;

import com.github.intellij.gno.psi.GoArgumentList;
import com.github.intellij.gno.psi.GoCallExpr;
import com.github.intellij.gno.psi.GoExpression;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import com.intellij.util.ObjectUtils;
import org.jetbrains.annotations.NotNull;

public class GoFunctionArgumentUnwrapper extends GoUnwrapper {
  public GoFunctionArgumentUnwrapper() {
    super("Unwrap argument");
  }

  @Override
  public boolean isApplicableTo(PsiElement e) {
    return e instanceof GoExpression && e.getParent() instanceof GoArgumentList && e.getParent().getParent() instanceof GoCallExpr;
  }

  @Override
  protected void doUnwrap(PsiElement element, Context context) throws IncorrectOperationException {
    PsiElement parent = element.getParent();
    GoCallExpr call = parent != null ? ObjectUtils.tryCast(parent.getParent(), GoCallExpr.class) : null;
    if (call != null) {
      context.extractElement(element, call);
      context.delete(call);
    }
  }

  @Override
  public @NotNull String getDescription(PsiElement e) {
    String text = e.getText();
    if (text.length() > 20) text = text.substring(0, 17) + "...";
    return "Unwrap " + text;
  }
}
