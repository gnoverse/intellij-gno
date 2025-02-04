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

package com.github.intellij.gno.stubs;

import com.github.intellij.gno.psi.GnoParameterDeclaration;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubElement;
import com.intellij.util.io.StringRef;

public class GnoParameterDeclarationStub extends StubWithText<GnoParameterDeclaration> {
  private final boolean myVariadic;

  public GnoParameterDeclarationStub(StubElement parent, IStubElementType elementType, StringRef ref, boolean variadic) {
    super(parent, elementType, ref);
    myVariadic = variadic;
  }

  public GnoParameterDeclarationStub(StubElement parent, IStubElementType elementType, String text, boolean variadic) {
    this(parent, elementType, StringRef.fromString(text), variadic);
  }

  public boolean isVariadic() {
    return myVariadic;
  }
}
