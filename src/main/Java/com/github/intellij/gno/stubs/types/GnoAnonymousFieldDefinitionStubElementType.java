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

import com.github.intellij.gno.psi.GnoAnonymousFieldDefinition;
import com.github.intellij.gno.psi.impl.GnoAnonymousFieldDefinitionImpl;
import com.github.intellij.gno.stubs.GnoAnonymousFieldDefinitionStub;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class GnoAnonymousFieldDefinitionStubElementType extends GnoNamedStubElementType<GnoAnonymousFieldDefinitionStub, GnoAnonymousFieldDefinition> {
  public GnoAnonymousFieldDefinitionStubElementType(@NotNull String name) {
    super(name);
  }

  @NotNull
  @Override
  public GnoAnonymousFieldDefinition createPsi(@NotNull GnoAnonymousFieldDefinitionStub stub) {
    return new GnoAnonymousFieldDefinitionImpl(stub, this);
  }

  @NotNull
  @Override
  public GnoAnonymousFieldDefinitionStub createStub(@NotNull GnoAnonymousFieldDefinition psi, StubElement parentStub) {
    return new GnoAnonymousFieldDefinitionStub(parentStub, this, psi.getName(), psi.isPublic());
  }

  @Override
  public void serialize(@NotNull GnoAnonymousFieldDefinitionStub stub, @NotNull StubOutputStream dataStream) throws IOException {
    dataStream.writeName(stub.getName());
    dataStream.writeBoolean(stub.isPublic());
  }

  @NotNull
  @Override
  public GnoAnonymousFieldDefinitionStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
    return new GnoAnonymousFieldDefinitionStub(parentStub, this, dataStream.readName(), dataStream.readBoolean());
  }
}
