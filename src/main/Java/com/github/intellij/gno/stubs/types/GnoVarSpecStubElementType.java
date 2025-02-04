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

import com.github.intellij.gno.GnoTypes;
import com.github.intellij.gno.psi.GnoFunctionOrMethodDeclaration;
import com.github.intellij.gno.psi.GnoVarSpec;
import com.github.intellij.gno.psi.impl.GnoVarSpecImpl;
import com.github.intellij.gno.stubs.GnoVarSpecStub;
import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.ArrayFactory;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class GnoVarSpecStubElementType extends GnoStubElementType<GnoVarSpecStub, GnoVarSpec> {
  public static final GnoVarSpec[] EMPTY_ARRAY = new GnoVarSpec[0];

  public static final ArrayFactory<GnoVarSpec> ARRAY_FACTORY = count -> count == 0 ? EMPTY_ARRAY : new GnoVarSpec[count];
  
  public GnoVarSpecStubElementType(@NotNull String name) {
    super(name);
  }

  @NotNull
  @Override
  public GnoVarSpec createPsi(@NotNull GnoVarSpecStub stub) {
    return new GnoVarSpecImpl(stub, this);
  }

  @NotNull
  @Override
  public GnoVarSpecStub createStub(@NotNull GnoVarSpec psi, StubElement parentStub) {
    return new GnoVarSpecStub(parentStub, this);
  }

  @Override
  public boolean shouldCreateStub(ASTNode node) {
    return super.shouldCreateStub(node) &&
           node.getElementType() == GnoTypes.VAR_SPEC &&
           PsiTreeUtil.getParentOfType(node.getPsi(), GnoFunctionOrMethodDeclaration.class) == null;
  }

  @Override
  public void serialize(@NotNull GnoVarSpecStub stub, @NotNull StubOutputStream dataStream) throws IOException {
  }

  @NotNull
  @Override
  public GnoVarSpecStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
    return new GnoVarSpecStub(parentStub, this);
  }
}
