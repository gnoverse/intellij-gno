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
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;

public class GnoStatementImpl extends GnoCompositeElementImpl implements GnoStatement {

  public GnoStatementImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull GnoVisitor visitor) {
    visitor.visitStatement(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof GnoVisitor) accept((GnoVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public GnoBlock getBlock() {
    return GnoPsiTreeUtil.getChildOfType(this, GnoBlock.class);
  }

  @Override
  @Nullable
  public GnoConstDeclaration getConstDeclaration() {
    return GnoPsiTreeUtil.getChildOfType(this, GnoConstDeclaration.class);
  }

  @Override
  @Nullable
  public GnoTypeDeclaration getTypeDeclaration() {
    return GnoPsiTreeUtil.getChildOfType(this, GnoTypeDeclaration.class);
  }

  @Override
  @Nullable
  public GnoVarDeclaration getVarDeclaration() {
    return GnoPsiTreeUtil.getChildOfType(this, GnoVarDeclaration.class);
  }

  public boolean processDeclarations(PsiScopeProcessor processor, ResolveState state, PsiElement lastParent, PsiElement place) {
    return GnoPsiImplUtil.processDeclarations(this, processor, state, lastParent, place);
  }

}
