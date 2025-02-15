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

import com.github.intellij.gno.psi.GnoFieldDefinition;
import com.github.intellij.gno.psi.impl.GnoFieldDefinitionImpl;
import com.github.intellij.gno.stubs.GnoFieldDefinitionStub;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class GnoFieldDefinitionStubElementType extends GnoNamedStubElementType<GnoFieldDefinitionStub, GnoFieldDefinition> {
  public GnoFieldDefinitionStubElementType(@NotNull String name) {
    super(name);
  }

  @NotNull
  @Override
  public GnoFieldDefinition createPsi(@NotNull GnoFieldDefinitionStub stub) {
    return new GnoFieldDefinitionImpl(stub, this);
  }

  @NotNull
  @Override
  public GnoFieldDefinitionStub createStub(@NotNull GnoFieldDefinition psi, StubElement parentStub) {
    return new GnoFieldDefinitionStub(parentStub, this, psi.getName(), psi.isPublic());
  }

  @Override
  public void serialize(@NotNull GnoFieldDefinitionStub stub, @NotNull StubOutputStream dataStream) throws IOException {
    dataStream.writeName(stub.getName());
    dataStream.writeBoolean(stub.isPublic());
  }

  @NotNull
  @Override
  public GnoFieldDefinitionStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
    return new GnoFieldDefinitionStub(parentStub, this, dataStream.readName(), dataStream.readBoolean());
  }
}
