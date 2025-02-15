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

import com.github.intellij.gno.psi.GnoParameterDeclaration;
import com.github.intellij.gno.psi.impl.GnoParameterDeclarationImpl;
import com.github.intellij.gno.stubs.GnoParameterDeclarationStub;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class GnoParameterDeclarationStubElementType extends GnoStubElementType<GnoParameterDeclarationStub, GnoParameterDeclaration> {
  public GnoParameterDeclarationStubElementType(@NotNull String name) {
    super(name);
  }

  @NotNull
  @Override
  public GnoParameterDeclaration createPsi(@NotNull GnoParameterDeclarationStub stub) {
    return new GnoParameterDeclarationImpl(stub, this);
  }

  @NotNull
  @Override
  public GnoParameterDeclarationStub createStub(@NotNull GnoParameterDeclaration psi, StubElement parentStub) {
    return new GnoParameterDeclarationStub(parentStub, this, psi.getText(), psi.isVariadic());
  }

  @Override
  public void serialize(@NotNull GnoParameterDeclarationStub stub, @NotNull StubOutputStream dataStream) throws IOException {
    dataStream.writeName(stub.getText());
    dataStream.writeBoolean(stub.isVariadic());
  }

  @NotNull
  @Override
  public GnoParameterDeclarationStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
    return new GnoParameterDeclarationStub(parentStub, this, dataStream.readName(), dataStream.readBoolean());
  }
}
