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

import com.github.intellij.gno.psi.GnoFunctionOrMethodDeclaration;
import com.github.intellij.gno.psi.GnoType;
import com.github.intellij.gno.stubs.GnoFunctionOrMethodDeclarationStub;
import com.intellij.lang.ASTNode;
import com.intellij.psi.ResolveState;
import com.intellij.psi.stubs.IStubElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

abstract public class GnoFunctionOrMethodDeclarationImpl<T extends GnoFunctionOrMethodDeclarationStub<?>> extends GnoNamedElementImpl<T>
  implements GnoFunctionOrMethodDeclaration {
  public GnoFunctionOrMethodDeclarationImpl(@NotNull T stub, @NotNull IStubElementType nodeType) {
    super(stub, nodeType);
  }

  public GnoFunctionOrMethodDeclarationImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  @Nullable
  public GnoType getGnoTypeInner(@Nullable ResolveState context) {
    return GnoPsiImplUtil.getGnoTypeInner(this, context);
  }
}