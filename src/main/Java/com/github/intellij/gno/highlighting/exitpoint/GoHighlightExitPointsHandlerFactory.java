package com.github.intellij.gno.highlighting.exitpoint;

import com.github.intellij.gno.GoTypes;
import com.github.intellij.gno.psi.GoCallExpr;
import com.github.intellij.gno.psi.GoReferenceExpression;
import com.github.intellij.gno.psi.impl.GoPsiImplUtil;
import com.intellij.codeInsight.highlighting.HighlightUsagesHandlerBase;
import com.intellij.codeInsight.highlighting.HighlightUsagesHandlerFactoryBase;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GoHighlightExitPointsHandlerFactory extends HighlightUsagesHandlerFactoryBase {
  private static final TokenSet BREAK_HIGHLIGHTING_TOKENS = TokenSet.create(GoTypes.BREAK, GoTypes.SWITCH, GoTypes.FOR, GoTypes.SELECT);

  @Nullable
  @Override
  public HighlightUsagesHandlerBase createHighlightUsagesHandler(@NotNull Editor editor,
                                                                 @NotNull PsiFile file,
                                                                 @NotNull PsiElement target) {
    if (target instanceof LeafPsiElement) {
      IElementType elementType = ((LeafPsiElement)target).getElementType();
      if (elementType == GoTypes.RETURN || elementType == GoTypes.FUNC || isPanicCall(target)) {
        return null;
      }
      else if (BREAK_HIGHLIGHTING_TOKENS.contains(elementType)) {
        return GoBreakStatementExitPointHandler.createForElement(editor, file, target);
      }
    }
    return null;
  }

  private static boolean isPanicCall(@NotNull PsiElement e) {
    PsiElement parent = e.getParent();
    if (parent instanceof GoReferenceExpression) {
      PsiElement grandPa = parent.getParent();
      if (grandPa instanceof GoCallExpr) return GoPsiImplUtil.isPanic((GoCallExpr)grandPa);
    }
    return false;
  }
}