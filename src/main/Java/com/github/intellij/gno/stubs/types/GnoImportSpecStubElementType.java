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

import com.github.intellij.gno.psi.GnoImportSpec;
import com.github.intellij.gno.psi.impl.GnoImportSpecImpl;
import com.github.intellij.gno.stubs.GnoImportSpecStub;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import com.intellij.util.ArrayFactory;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class GnoImportSpecStubElementType extends GnoNamedStubElementType<GnoImportSpecStub, GnoImportSpec> {
  public static final GnoImportSpec[] EMPTY_ARRAY = new GnoImportSpec[0];
  public static final ArrayFactory<GnoImportSpec> ARRAY_FACTORY = count -> count == 0 ? EMPTY_ARRAY : new GnoImportSpec[count];

  public GnoImportSpecStubElementType(@NotNull String name) {
    super(name);
  }

  @NotNull
  @Override
  public GnoImportSpec createPsi(@NotNull GnoImportSpecStub stub) {
    return new GnoImportSpecImpl(stub, this);
  }

  @NotNull
  @Override
  public GnoImportSpecStub createStub(@NotNull GnoImportSpec psi, StubElement parentStub) {
    return new GnoImportSpecStub(parentStub, this, psi.getAlias(), psi.getPath(), psi.isDot());
  }

  @Override
  public void serialize(@NotNull GnoImportSpecStub stub, @NotNull StubOutputStream dataStream) throws IOException {
    dataStream.writeUTFFast(StringUtil.notNullize(stub.getAlias()));
    dataStream.writeUTFFast(stub.getPath());
    dataStream.writeBoolean(stub.isDot());
  }

  @NotNull
  @Override
  public GnoImportSpecStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
    return new GnoImportSpecStub(parentStub, this, StringUtil.nullize(dataStream.readUTFFast()), 
                                dataStream.readUTFFast(), dataStream.readBoolean());
  }

  @Override
  public boolean shouldCreateStub(@NotNull ASTNode node) {
    return true;
  }
}
