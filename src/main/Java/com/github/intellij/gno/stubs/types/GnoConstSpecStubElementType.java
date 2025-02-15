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

import com.github.intellij.gno.psi.GnoConstSpec;
import com.github.intellij.gno.psi.GnoFunctionOrMethodDeclaration;
import com.github.intellij.gno.psi.impl.GnoConstSpecImpl;
import com.github.intellij.gno.stubs.GnoConstSpecStub;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.ArrayFactory;
import com.intellij.util.io.StringRef;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class GnoConstSpecStubElementType extends GnoStubElementType<GnoConstSpecStub, GnoConstSpec> {
  public static final GnoConstSpec[] EMPTY_ARRAY = new GnoConstSpec[0];

  public static final ArrayFactory<GnoConstSpec> ARRAY_FACTORY = count -> count == 0 ? EMPTY_ARRAY : new GnoConstSpec[count];
  
  public GnoConstSpecStubElementType(@NotNull String name) {
    super(name);
  }

  @NotNull
  @Override
  public GnoConstSpec createPsi(@NotNull GnoConstSpecStub stub) {
    return new GnoConstSpecImpl(stub, this);
  }

  @NotNull
  @Override
  public GnoConstSpecStub createStub(@NotNull GnoConstSpec psi, StubElement parentStub) {
    String join = StringUtil.join(psi.getExpressionList(), PsiElement::getText, ";");
    return new GnoConstSpecStub(parentStub, this, StringRef.fromString(join));
  }

  @Override
  public void serialize(@NotNull GnoConstSpecStub stub, @NotNull StubOutputStream dataStream) throws IOException {
    dataStream.writeName(stub.getExpressionsText());
  }

  @Override
  public boolean shouldCreateStub(ASTNode node) {
    return super.shouldCreateStub(node) && PsiTreeUtil.getParentOfType(node.getPsi(), GnoFunctionOrMethodDeclaration.class) == null;
  }

  @NotNull
  @Override
  public GnoConstSpecStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
    return new GnoConstSpecStub(parentStub, this, dataStream.readName());
  }
}
