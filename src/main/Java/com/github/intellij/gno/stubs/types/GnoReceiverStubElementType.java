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

import com.github.intellij.gno.psi.GnoReceiver;
import com.github.intellij.gno.psi.impl.GnoReceiverImpl;
import com.github.intellij.gno.stubs.GnoReceiverStub;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class GnoReceiverStubElementType extends GnoNamedStubElementType<GnoReceiverStub, GnoReceiver> {
  public GnoReceiverStubElementType(@NotNull String name) {
    super(name);
  }

  @Override
  public boolean shouldCreateStub(@NotNull ASTNode node) {
    return true;
  }

  @NotNull
  @Override
  public GnoReceiver createPsi(@NotNull GnoReceiverStub stub) {
    return new GnoReceiverImpl(stub, this);
  }

  @NotNull
  @Override
  public GnoReceiverStub createStub(@NotNull GnoReceiver psi, StubElement parentStub) {
    return new GnoReceiverStub(parentStub, this, StringUtil.notNullize(psi.getName()), psi.isPublic());
  }

  @Override
  public void serialize(@NotNull GnoReceiverStub stub, @NotNull StubOutputStream dataStream) throws IOException {
    dataStream.writeName(stub.getName());
    dataStream.writeBoolean(stub.isPublic());
  }

  @NotNull
  @Override
  public GnoReceiverStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
    return new GnoReceiverStub(parentStub, this, dataStream.readName(), dataStream.readBoolean());
  }

  @Override
  protected boolean shouldIndex() {
    return false;
  }
}
