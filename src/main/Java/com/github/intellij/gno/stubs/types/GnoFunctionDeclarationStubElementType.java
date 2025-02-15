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

import com.github.intellij.gno.psi.GnoFunctionDeclaration;
import com.github.intellij.gno.psi.GnoNamedElement;
import com.github.intellij.gno.psi.impl.GnoFunctionDeclarationImpl;
import com.github.intellij.gno.stubs.GnoFunctionDeclarationStub;
import com.github.intellij.gno.stubs.index.GnoFunctionIndex;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubIndexKey;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import com.intellij.util.ArrayFactory;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class GnoFunctionDeclarationStubElementType extends GnoNamedStubElementType<GnoFunctionDeclarationStub, GnoFunctionDeclaration> {
  public static final GnoFunctionDeclaration[] EMPTY_ARRAY = new GnoFunctionDeclaration[0];

  public static final ArrayFactory<GnoFunctionDeclaration> ARRAY_FACTORY =
    count -> count == 0 ? EMPTY_ARRAY : new GnoFunctionDeclaration[count];
  
  private static final ArrayList<StubIndexKey<String, ? extends GnoNamedElement>> EXTRA_KEYS =
    ContainerUtil.newArrayList(GnoFunctionIndex.KEY);

  public GnoFunctionDeclarationStubElementType(@NotNull String name) {
    super(name);
  }

  @NotNull
  @Override
  public GnoFunctionDeclaration createPsi(@NotNull GnoFunctionDeclarationStub stub) {
    return new GnoFunctionDeclarationImpl(stub, this);
  }

  @NotNull
  @Override
  public GnoFunctionDeclarationStub createStub(@NotNull GnoFunctionDeclaration psi, StubElement parentStub) {
    return new GnoFunctionDeclarationStub(parentStub, this, psi.getName(), psi.isPublic());
  }

  @Override
  public void serialize(@NotNull GnoFunctionDeclarationStub stub, @NotNull StubOutputStream dataStream) throws IOException {
    dataStream.writeName(stub.getName());
    dataStream.writeBoolean(stub.isPublic());
  }

  @NotNull
  @Override
  public GnoFunctionDeclarationStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
    return new GnoFunctionDeclarationStub(parentStub, this, dataStream.readName(), dataStream.readBoolean());
  }

  @NotNull
  @Override
  protected Collection<StubIndexKey<String, ? extends GnoNamedElement>> getExtraIndexKeys() {
    return EXTRA_KEYS;
  }
}
