/*
 * Copyright 2013-2016 Sergey Ignatov, Alexander Zolotov, Florin Patan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.intellij.gno.psi.impl;

import com.github.intellij.gno.psi.*;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.Comparing;
import com.intellij.openapi.util.Trinity;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.tree.LeafElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GnoExpressionUtil {

  public static boolean identical(@Nullable GnoExpression left, @Nullable GnoExpression right) {
    if (left == right) return true;
    if (left == null || right == null) return false;
    GnoExpression l = unwrap(left);
    GnoExpression r = unwrap(right);
    if (l == null || r == null) return false;

    if (!l.getClass().equals(r.getClass())) return false;
    if (l instanceof GnoBinaryExpr) {
      GnoBinaryExpr lBin = (GnoBinaryExpr)l;
      GnoBinaryExpr rBin = (GnoBinaryExpr)r;
      return isOperatorEquals(lBin.getOperator(), rBin.getOperator()) && isChildrenExprEquals(lBin, rBin);
    }
    if (l instanceof GnoUnaryExpr) {
      GnoUnaryExpr lUnary = (GnoUnaryExpr)l;
      GnoUnaryExpr rUnary = (GnoUnaryExpr)r;
      return isOperatorEquals(lUnary.getOperator(), rUnary.getOperator()) && identical(lUnary.getExpression(), rUnary.getExpression());
    }
    if (l instanceof GnoReferenceExpression) {
      PsiElement resolve = ((GnoReferenceExpression)l).resolve();
      return resolve != null && resolve.isEquivalentTo(((GnoReferenceExpression)r).resolve());
    }
    if (l instanceof GnoIndexOrSliceExpr) {
      GnoIndexOrSliceExpr lSlice = (GnoIndexOrSliceExpr)l;
      GnoIndexOrSliceExpr rSlice = (GnoIndexOrSliceExpr)r;
      return identical(lSlice.getExpression(), rSlice.getExpression()) && isIndicesIdentical(lSlice.getIndices(), rSlice.getIndices());
    }
    if (l instanceof GnoStringLiteral) {
      return Comparing.equal(((GnoStringLiteral)l).getDecodedText(), ((GnoStringLiteral)r).getDecodedText());
    }
    if (l instanceof GnoLiteralTypeExpr) {
      GnoLiteralTypeExpr lLit = (GnoLiteralTypeExpr)l;
      GnoLiteralTypeExpr rLit = (GnoLiteralTypeExpr)r;
      GnoTypeReferenceExpression lExpr = lLit.getTypeReferenceExpression();
      GnoTypeReferenceExpression rExpr = rLit.getTypeReferenceExpression();
      PsiElement lResolve = lExpr != null ? lExpr.resolve() : null;
      return lResolve != null && rExpr != null && lResolve.equals(rExpr.resolve());
      //todo: add || GnoTypeUtil.identical(lLit.getType(), rLit.getType)
    }
    if (l instanceof GnoLiteral) {
      return l.textMatches(r);
    }
    if (l instanceof GnoBuiltinCallExpr || l instanceof GnoCallExpr) {
      return false;
    }
    if (l instanceof GnoCompositeLit) {
      GnoCompositeLit lLit = (GnoCompositeLit)l;
      GnoCompositeLit rLit = (GnoCompositeLit)r;
      return identical(lLit.getLiteralValue(), rLit.getLiteralValue());
      // todo: add && GnoTypeUtil.identical
    }
    if (l instanceof GnoTypeAssertionExpr) {
      GnoTypeAssertionExpr lAssertion = (GnoTypeAssertionExpr)l;
      GnoTypeAssertionExpr rAssertion = (GnoTypeAssertionExpr)r;
      return identical(lAssertion.getExpression(), rAssertion.getExpression());
      //todo: add && GnoTypeUtil.identical(lAssertion.getType(), rAssertion.getType())
    }

    String lText = l.getText();
    return lText != null && lText.equals(r.getText());
  }

  private static boolean isIndicesIdentical(@NotNull Trinity<GnoExpression, GnoExpression, GnoExpression> l,
                                            @NotNull Trinity<GnoExpression, GnoExpression, GnoExpression> r) {
    return identical(l.first, r.first) && identical(l.second, r.second) && identical(l.third, r.third);
  }

  private static boolean identical(@Nullable GnoLiteralValue l, @Nullable GnoLiteralValue r) {
    if (l == null || r == null) return false;
    return l.textMatches(r); //todo: fill
  }

  private static boolean isOperatorEquals(@Nullable PsiElement l, @Nullable PsiElement r) {
    if (l == null || r == null) return false;
    ASTNode lNode = l.getNode();
    ASTNode rNode = r.getNode();
    return lNode instanceof LeafElement && lNode.getElementType().equals(rNode.getElementType());
  }

  private static boolean isOrderImportant(@NotNull GnoBinaryExpr o) {
    if (o instanceof GnoConversionExpr || o instanceof GnoSelectorExpr) return true;
    if (o instanceof GnoMulExpr) {
      GnoMulExpr m = (GnoMulExpr)o;
      return m.getRemainder() != null || m.getQuotient() != null || m.getShiftLeft() != null || m.getShiftRight() != null;
    }
    if (o instanceof GnoConditionalExpr) {
      GnoConditionalExpr c = (GnoConditionalExpr)o;
      return c.getEq() == null && c.getNotEq() == null;
    }
    return false;
  }

  private static boolean isChildrenExprEquals(@NotNull GnoBinaryExpr left, @NotNull GnoBinaryExpr right) {
    GnoExpression l1 = left.getLeft();
    GnoExpression l2 = left.getRight();
    GnoExpression r1 = right.getLeft();
    GnoExpression r2 = right.getRight();

    boolean order = isOrderImportant(left);

    return identical(l1, r1) && identical(l2, r2) || !order && identical(l1, r2) && identical(l2, r1);
  }

  @Nullable
  private static GnoExpression unwrap(@Nullable GnoExpression o) {
    if (o instanceof GnoParenthesesExpr) return unwrap(((GnoParenthesesExpr)o).getExpression());
    if (o instanceof GnoUnaryExpr && ((GnoUnaryExpr)o).getPlus() != null) return unwrap(((GnoUnaryExpr)o).getExpression());
    return o;
  }
}
