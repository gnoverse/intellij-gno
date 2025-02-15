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
import com.github.intellij.gno.stubs.GnoVarSpecStub;
import com.github.intellij.gno.psi.*;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.psi.stubs.IStubElementType;

public class GnoVarSpecImpl extends GnoStubbedElementImpl<GnoVarSpecStub> implements GnoVarSpec {

  public GnoVarSpecImpl(GnoVarSpecStub stub, IStubElementType nodeType) {
    super(stub, nodeType);
  }

  public GnoVarSpecImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull GnoVisitor visitor) {
    visitor.visitVarSpec(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof GnoVisitor) accept((GnoVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<GnoExpression> getExpressionList() {
    return GnoPsiTreeUtil.getChildrenOfTypeAsList(this, GnoExpression.class);
  }

  @Override
  @Nullable
  public GnoType getType() {
    return GnoPsiTreeUtil.getStubChildOfType(this, GnoType.class);
  }

  @Override
  @NotNull
  public List<GnoVarDefinition> getVarDefinitionList() {
    return GnoPsiTreeUtil.getStubChildrenOfTypeAsList(this, GnoVarDefinition.class);
  }

  @Override
  @Nullable
  public PsiElement getAssign() {
    return findChildByType(ASSIGN);
  }

  public boolean processDeclarations(PsiScopeProcessor processor, ResolveState state, PsiElement lastParent, PsiElement place) {
    return GnoPsiImplUtil.processDeclarations(this, processor, state, lastParent, place);
  }

  public void deleteDefinition(GnoVarDefinition definitionToDelete) {
    GnoPsiImplUtil.deleteDefinition(this, definitionToDelete);
  }

  @NotNull
  public List<GnoExpression> getRightExpressionsList() {
    return GnoPsiImplUtil.getRightExpressionsList(this);
  }

}
