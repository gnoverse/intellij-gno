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

import com.github.intellij.gno.psi.GnoNamedElement;
import com.github.intellij.gno.psi.GnoTypeSpec;
import com.github.intellij.gno.psi.impl.GnoTypeSpecImpl;
import com.github.intellij.gno.stubs.GnoTypeSpecStub;
import com.github.intellij.gno.stubs.index.GnoTypesIndex;
import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubIndexKey;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import com.intellij.util.ArrayFactory;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Collection;

public class GnoTypeSpecStubElementType extends GnoNamedStubElementType<GnoTypeSpecStub, GnoTypeSpec> {
  public static final GnoTypeSpec[] EMPTY_ARRAY = new GnoTypeSpec[0];

  public static final ArrayFactory<GnoTypeSpec> ARRAY_FACTORY = count -> count == 0 ? EMPTY_ARRAY : new GnoTypeSpec[count];
  
  public GnoTypeSpecStubElementType(@NotNull String name) {
    super(name);
  }

  @NotNull
  @Override
  public GnoTypeSpec createPsi(@NotNull GnoTypeSpecStub stub) {
    return new GnoTypeSpecImpl(stub, this);
  }

  @NotNull
  @Override
  public GnoTypeSpecStub createStub(@NotNull GnoTypeSpec psi, StubElement parentStub) {
    return new GnoTypeSpecStub(parentStub, this, psi.getName(), psi.isPublic());
  }

  @Override
  public void serialize(@NotNull GnoTypeSpecStub stub, @NotNull StubOutputStream dataStream) throws IOException {
    dataStream.writeName(stub.getName());
    dataStream.writeBoolean(stub.isPublic());
  }

  @NotNull
  @Override
  public GnoTypeSpecStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
    return new GnoTypeSpecStub(parentStub, this, dataStream.readName(), dataStream.readBoolean());
  }

  @NotNull
  @Override
  protected Collection<StubIndexKey<String, ? extends GnoNamedElement>> getExtraIndexKeys() {
    return ContainerUtil.list(GnoTypesIndex.KEY);
  }

  @Override
  protected boolean shouldCreateStubInBlock(ASTNode node) {
    return true;
  }
}
