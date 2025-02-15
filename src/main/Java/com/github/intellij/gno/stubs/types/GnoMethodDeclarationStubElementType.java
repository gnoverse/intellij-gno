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

import com.github.intellij.gno.psi.GnoMethodDeclaration;
import com.github.intellij.gno.psi.GnoTypeReferenceExpression;
import com.github.intellij.gno.psi.impl.GnoMethodDeclarationImpl;
import com.github.intellij.gno.psi.impl.GnoPsiImplUtil;
import com.github.intellij.gno.stubs.GnoFileStub;
import com.github.intellij.gno.stubs.GnoMethodDeclarationStub;
import com.github.intellij.gno.stubs.index.GnoMethodIndex;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.stubs.IndexSink;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import com.intellij.util.ArrayFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

public class GnoMethodDeclarationStubElementType extends GnoNamedStubElementType<GnoMethodDeclarationStub, GnoMethodDeclaration> {
  public static final GnoMethodDeclaration[] EMPTY_ARRAY = new GnoMethodDeclaration[0];

  public static final ArrayFactory<GnoMethodDeclaration> ARRAY_FACTORY = count -> count == 0 ? EMPTY_ARRAY : new GnoMethodDeclaration[count];
  
  public GnoMethodDeclarationStubElementType(@NotNull String name) {
    super(name);
  }

  @NotNull
  @Override
  public GnoMethodDeclaration createPsi(@NotNull GnoMethodDeclarationStub stub) {
    return new GnoMethodDeclarationImpl(stub, this);
  }

  @Nullable
  @Override
  public GnoMethodDeclarationStub createStub(@NotNull GnoMethodDeclaration psi, StubElement parentStub) {
    return new GnoMethodDeclarationStub(parentStub, this, psi.getName(), psi.isPublic(), calcTypeText(psi));
  }

  @Override
  public void serialize(@NotNull GnoMethodDeclarationStub stub, @NotNull StubOutputStream dataStream) throws IOException {
    dataStream.writeName(stub.getName());
    dataStream.writeBoolean(stub.isPublic());
    dataStream.writeName(stub.getTypeName());
  }

  @NotNull
  @Override
  public GnoMethodDeclarationStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
    return new GnoMethodDeclarationStub(parentStub, this, dataStream.readName(), dataStream.readBoolean(), dataStream.readName());
  }

  @Override
  public void indexStub(@NotNull GnoMethodDeclarationStub stub, @NotNull IndexSink sink) {
    super.indexStub(stub, sink);
    String typeName = stub.getTypeName();
    if (!StringUtil.isEmpty(typeName)) {
      StubElement parent = stub.getParentStub();
      if (parent instanceof GnoFileStub) {
        String packageName = ((GnoFileStub)parent).getPackageName();
        if (!StringUtil.isEmpty(typeName)) {
          sink.occurrence(GnoMethodIndex.KEY, packageName + "." + typeName);
        }
      }
    }
  }

  @Nullable
  public static String calcTypeText(@NotNull GnoMethodDeclaration psi) {
    GnoTypeReferenceExpression reference = GnoPsiImplUtil.getTypeReference(psi.getReceiverType());
    return reference != null ? reference.getIdentifier().getText() : null;
  }
}
