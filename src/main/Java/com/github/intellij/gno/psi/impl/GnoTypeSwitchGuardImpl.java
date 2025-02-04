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

// This is a generated file. Not intended for manual editing.
package com.github.intellij.gno.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GnoPsiTreeUtil;
import static com.github.intellij.gno.GnoTypes.*;
import com.github.intellij.gno.psi.*;

public class GnoTypeSwitchGuardImpl extends GnoCompositeElementImpl implements GnoTypeSwitchGuard {

  public GnoTypeSwitchGuardImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull GnoVisitor visitor) {
    visitor.visitTypeSwitchGuard(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof GnoVisitor) accept((GnoVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public GnoExpression getExpression() {
    return notNullChild(GnoPsiTreeUtil.getChildOfType(this, GnoExpression.class));
  }

  @Override
  @NotNull
  public GnoTypeGuard getTypeGuard() {
    return notNullChild(GnoPsiTreeUtil.getChildOfType(this, GnoTypeGuard.class));
  }

  @Override
  @Nullable
  public GnoVarDefinition getVarDefinition() {
    return GnoPsiTreeUtil.getChildOfType(this, GnoVarDefinition.class);
  }

  @Override
  @NotNull
  public PsiElement getDot() {
    return notNullChild(findChildByType(DOT));
  }

  @Override
  @Nullable
  public PsiElement getVarAssign() {
    return findChildByType(VAR_ASSIGN);
  }

}
