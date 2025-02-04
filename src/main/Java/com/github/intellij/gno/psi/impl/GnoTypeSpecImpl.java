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
import com.github.intellij.gno.stubs.GnoTypeSpecStub;
import com.github.intellij.gno.psi.*;
import com.intellij.psi.ResolveState;
import com.intellij.psi.stubs.IStubElementType;

public class GnoTypeSpecImpl extends GnoNamedElementImpl<GnoTypeSpecStub> implements GnoTypeSpec {

  public GnoTypeSpecImpl(GnoTypeSpecStub stub, IStubElementType nodeType) {
    super(stub, nodeType);
  }

  public GnoTypeSpecImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull GnoVisitor visitor) {
    visitor.visitTypeSpec(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof GnoVisitor) accept((GnoVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public GnoSpecType getSpecType() {
    return notNullChild(GnoPsiTreeUtil.getStubChildOfType(this, GnoSpecType.class));
  }

  @Nullable
  public GnoType getGnoTypeInner(ResolveState context) {
    return GnoPsiImplUtil.getGnoTypeInner(this, context);
  }

  @NotNull
  public List<GnoMethodDeclaration> getMethods() {
    return GnoPsiImplUtil.getMethods(this);
  }

  public boolean shouldGnoDeeper() {
    return GnoPsiImplUtil.shouldGnoDeeper(this);
  }

  @Override
  @NotNull
  public PsiElement getIdentifier() {
    GnoSpecType p1 = getSpecType();
    return p1.getIdentifier();
  }

}
