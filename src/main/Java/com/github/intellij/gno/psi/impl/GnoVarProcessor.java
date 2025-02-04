/*
 * Copyright 2013-2015 Sergey Ignatov, Alexander Zolotov, Florin Patan
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
import com.intellij.openapi.util.Comparing;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GnoVarProcessor extends GnoScopeProcessorBase {
  private final boolean myImShortVarDeclaration;
  private final PsiElement myParentGuard; 
  @Nullable private final GnoCompositeElement myScope;
  
  public GnoVarProcessor(@NotNull PsiElement origin, boolean completion) {
    this(origin, origin, completion, false);
  }

  public GnoVarProcessor(@NotNull PsiElement requestedName, @NotNull PsiElement origin, boolean completion, boolean delegate) {
    super(requestedName, origin, completion);
    myImShortVarDeclaration = PsiTreeUtil.getParentOfType(origin, GnoShortVarDeclaration.class) != null && !delegate;
    myParentGuard = origin.getParent() instanceof GnoTypeSwitchGuard ? origin.getParent() : null;
    myScope = getScope(origin);
  }

  @Override
  protected boolean add(@NotNull GnoNamedElement o) {
    PsiElement commonParent = PsiTreeUtil.findCommonParent(o, myOrigin);
    if (commonParent instanceof GnoRangeClause || commonParent instanceof GnoTypeSwitchGuard) return true;
    PsiElement p = o.getParent();
    boolean inVarOrRange = PsiTreeUtil.getParentOfType(o, GnoVarDeclaration.class) != null || p instanceof GnoRangeClause;
    boolean differentBlocks = differentBlocks(o);
    boolean inShortVar = PsiTreeUtil.getParentOfType(o, GnoShortVarDeclaration.class, GnoRecvStatement.class) != null;
    if (inShortVar && differentBlocks && myImShortVarDeclaration) return true;
    if (differentBlocks && inShortVar && !inVarOrRange && getResult() != null && !myIsCompletion) return true;
    if (inShortVar && fromNotAncestorBlock(o)) return true;
    if (myParentGuard != null && o instanceof GnoVarDefinition && p.isEquivalentTo(myParentGuard)) return true;
    return super.add(o);
  }

  private boolean fromNotAncestorBlock(@NotNull GnoNamedElement o) {
    return (myScope instanceof GnoExprCaseClause || myScope instanceof GnoCommClause) &&
           !PsiTreeUtil.isAncestor(getScope(o), myOrigin, false);
  }

  private boolean differentBlocks(@Nullable GnoNamedElement o) {
    return !Comparing.equal(myScope, getScope(o));
  }

  @Nullable
  public static GnoCompositeElement getScope(@Nullable PsiElement o) {
    GnoForStatement forStatement = PsiTreeUtil.getParentOfType(o, GnoForStatement.class);
    if (forStatement != null) return forStatement.getBlock();
    GnoIfStatement ifStatement = PsiTreeUtil.getParentOfType(o, GnoIfStatement.class);
    if (ifStatement != null) return ifStatement.getBlock();
    GnoElseStatement elseStatement = PsiTreeUtil.getParentOfType(o, GnoElseStatement.class);
    if (elseStatement != null) return elseStatement.getBlock();
    GnoExprCaseClause exprCaseClause = PsiTreeUtil.getParentOfType(o, GnoExprCaseClause.class);
    if (exprCaseClause != null) return exprCaseClause;
    GnoCommClause commClause = PsiTreeUtil.getParentOfType(o, GnoCommClause.class);
    if (commClause != null) return commClause;
    return PsiTreeUtil.getParentOfType(o, GnoBlock.class);
  }

  @Override
  protected boolean crossOff(@NotNull PsiElement e) {
    return !(e instanceof GnoVarDefinition) &&
           !(e instanceof GnoParamDefinition) &&
           !(e instanceof GnoReceiver) &&
           !(e instanceof GnoFieldDefinition) &&
           !(e instanceof GnoAnonymousFieldDefinition) &&
           !(e instanceof GnoConstDefinition);
  }
}