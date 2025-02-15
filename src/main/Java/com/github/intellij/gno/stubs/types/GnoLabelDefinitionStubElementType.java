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

package com.github.intellij.gno.stubs.types;

import com.github.intellij.gno.psi.GnoLabelDefinition;
import com.github.intellij.gno.psi.impl.GnoLabelDefinitionImpl;
import com.github.intellij.gno.stubs.GnoLabelDefinitionStub;
import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class GnoLabelDefinitionStubElementType extends GnoNamedStubElementType<GnoLabelDefinitionStub, GnoLabelDefinition> {
  public GnoLabelDefinitionStubElementType(@NotNull String name) {
    super(name);
  }

  @NotNull
  @Override
  public GnoLabelDefinition createPsi(@NotNull GnoLabelDefinitionStub stub) {
    return new GnoLabelDefinitionImpl(stub, this);
  }

  @NotNull
  @Override
  public GnoLabelDefinitionStub createStub(@NotNull GnoLabelDefinition psi, StubElement parentStub) {
    return new GnoLabelDefinitionStub(parentStub, this, psi.getName(), psi.isPublic());
  }

  @Override
  public void serialize(@NotNull GnoLabelDefinitionStub stub, @NotNull StubOutputStream dataStream) throws IOException {
    dataStream.writeName(stub.getName());
    dataStream.writeBoolean(stub.isPublic());
  }

  @Override
  public boolean shouldCreateStub(@NotNull ASTNode node) {
    return false; // todo
  }

  @NotNull
  @Override
  public GnoLabelDefinitionStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
    return new GnoLabelDefinitionStub(parentStub, this, dataStream.readName(), dataStream.readBoolean());
  }
}
