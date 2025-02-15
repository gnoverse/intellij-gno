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

import com.github.intellij.gno.psi.GnoPackageClause;
import com.github.intellij.gno.psi.impl.GnoPackageClauseImpl;
import com.github.intellij.gno.stubs.GnoPackageClauseStub;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class GnoPackageClauseStubElementType extends GnoStubElementType<GnoPackageClauseStub, GnoPackageClause> {
  public static final GnoPackageClauseStubElementType INSTANCE = new GnoPackageClauseStubElementType();

  private GnoPackageClauseStubElementType() {
    super("PACKAGE_CLAUSE");
  }

  @NotNull
  @Override
  public GnoPackageClause createPsi(@NotNull GnoPackageClauseStub stub) {
    return new GnoPackageClauseImpl(stub, this);
  }

  @NotNull
  @Override
  public GnoPackageClauseStub createStub(@NotNull GnoPackageClause psi, StubElement parentStub) {
    return new GnoPackageClauseStub(parentStub, this, psi.getName());
  }

  @Override
  public void serialize(@NotNull GnoPackageClauseStub stub, @NotNull StubOutputStream dataStream) throws IOException {
    dataStream.writeName(stub.getName());
  }

  @NotNull
  @Override
  public GnoPackageClauseStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
    return new GnoPackageClauseStub(parentStub, this, dataStream.readName());
  }
}
