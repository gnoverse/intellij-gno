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

package com.github.intellij.gno.stubs.types;

import com.github.intellij.gno.psi.GnoFunctionOrMethodDeclaration;
import com.github.intellij.gno.psi.GnoVarDefinition;
import com.github.intellij.gno.psi.impl.GnoVarDefinitionImpl;
import com.github.intellij.gno.stubs.GnoVarDefinitionStub;
import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.ArrayFactory;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class GnoVarDefinitionStubElementType extends GnoNamedStubElementType<GnoVarDefinitionStub, GnoVarDefinition> {
  public static final GnoVarDefinition[] EMPTY_ARRAY = new GnoVarDefinition[0];

  public static final ArrayFactory<GnoVarDefinition> ARRAY_FACTORY = count -> count == 0 ? EMPTY_ARRAY : new GnoVarDefinition[count];
  
  public GnoVarDefinitionStubElementType(@NotNull String name) {
    super(name);
  }

  @NotNull
  @Override
  public GnoVarDefinition createPsi(@NotNull GnoVarDefinitionStub stub) {
    return new GnoVarDefinitionImpl(stub, this);
  }

  @NotNull
  @Override
  public GnoVarDefinitionStub createStub(@NotNull GnoVarDefinition psi, StubElement parentStub) {
    return new GnoVarDefinitionStub(parentStub, this, psi.getName(), psi.isPublic());
  }

  @Override
  public void serialize(@NotNull GnoVarDefinitionStub stub, @NotNull StubOutputStream dataStream) throws IOException {
    dataStream.writeName(stub.getName());
    dataStream.writeBoolean(stub.isPublic());
  }

  @NotNull
  @Override
  public GnoVarDefinitionStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
    return new GnoVarDefinitionStub(parentStub, this, dataStream.readName(), dataStream.readBoolean());
  }

  @Override
  public boolean shouldCreateStub(@NotNull ASTNode node) {
    return super.shouldCreateStub(node) && PsiTreeUtil.getParentOfType(node.getPsi(), GnoFunctionOrMethodDeclaration.class) == null;
  }
}
