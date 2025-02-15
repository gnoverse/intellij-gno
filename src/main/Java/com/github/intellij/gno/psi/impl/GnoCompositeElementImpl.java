package com.github.intellij.gno.psi.impl;

import com.github.intellij.gno.psi.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GnoCompositeElementImpl extends ASTWrapperPsiElement implements GnoCompositeElement {
  public GnoCompositeElementImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public String toString() {
    return getNode().getElementType().toString();
  }

  @Override
  public boolean processDeclarations(@NotNull PsiScopeProcessor processor,
                                     @NotNull ResolveState state,
                                     @Nullable PsiElement lastParent,
                                     @NotNull PsiElement place) {
    return processDeclarationsDefault(this, processor, state, lastParent, place);
  }

  public static boolean processDeclarationsDefault(@NotNull GnoCompositeElement o,
                                                   @NotNull PsiScopeProcessor processor,
                                                   @NotNull ResolveState state,
                                                   @Nullable PsiElement lastParent,
                                                   @NotNull PsiElement place) {
    if (o instanceof GnoLeftHandExprList || o instanceof GnoExpression) return true;

    if (!o.shouldGnoDeeper()) return processor.execute(o, state);
    if (!processor.execute(o, state)) return false;
    if ((
          o instanceof GnoSwitchStatement ||
          o instanceof GnoIfStatement ||
          o instanceof GnoForStatement ||
          o instanceof GnoCommClause ||
          o instanceof GnoBlock ||
          o instanceof GnoCaseClause
        ) 
        && processor instanceof GnoScopeProcessorBase) {
      if (!PsiTreeUtil.isAncestor(o, ((GnoScopeProcessorBase)processor).myOrigin, false)) return true;
    }

    return o instanceof GnoBlock
           ? processBlock((GnoBlock)o, processor, state, lastParent, place)
           : ResolveUtil.processChildren(o, processor, state, lastParent, place);
  }

  private static boolean processBlock(@NotNull GnoBlock o,
                                      @NotNull PsiScopeProcessor processor,
                                      @NotNull ResolveState state,
                                      @Nullable PsiElement lastParent, @NotNull PsiElement place) {
    return ResolveUtil.processChildrenFromTop(o, processor, state, lastParent, place) && processParameters(o, processor);
  }

  private static boolean processParameters(@NotNull GnoBlock b, @NotNull PsiScopeProcessor processor) {
    if (processor instanceof GnoScopeProcessorBase && b.getParent() instanceof GnoSignatureOwner) {
      return GnoPsiImplUtil.processSignatureOwner((GnoSignatureOwner)b.getParent(), (GnoScopeProcessorBase)processor);
    }
    return true;
  }

  @Override
  public boolean shouldGnoDeeper() {
    return true;
  }
}
