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

package com.github.intellij.gno.psi.impl;

import com.github.intellij.gno.psi.*;
import com.github.intellij.gno.stubs.GnoTypeStub;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.light.LightElement;
import com.intellij.psi.stubs.IStubElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class GnoLightType<E extends GnoCompositeElement> extends LightElement implements GnoType {
  @NotNull protected final E myElement;

  protected GnoLightType(@NotNull E e) {
    super(e.getManager(), e.getLanguage());
    myElement = e;
    setNavigationElement(e);
  }

  @Nullable
  @Override
  public GnoTypeReferenceExpression getTypeReferenceExpression() {
    return null;
  }

  @Override
  public boolean shouldGnoDeeper() {
    return false;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "{" + myElement + "}";
  }

  @Override
  public IStubElementType getElementType() {
    return null;
  }

  @Override
  public GnoTypeStub getStub() {
    return null;
  }

  @NotNull
  @Override
  public GnoType getUnderlyingType() {
    return GnoPsiImplUtil.getUnderlyingType(this);
  }

  static class LightPointerType extends GnoLightType<GnoType> implements GnoPointerType {
    protected LightPointerType(@NotNull GnoType o) {
      super(o);
    }

    @Override
    public String getText() {
      return "*" + myElement.getText();
    }

    @Nullable
    @Override
    public GnoType getType() {
      return myElement;
    }

    @NotNull
    @Override
    public PsiElement getMul() {
      return myElement; // todo: mock it?
    }
  }

  static class LightTypeList extends GnoLightType<GnoCompositeElement> implements GnoTypeList {
    @NotNull private final List<GnoType> myTypes;

    public LightTypeList(@NotNull GnoCompositeElement o, @NotNull List<GnoType> types) {
      super(o);
      myTypes = types;
    }

    @NotNull
    @Override
    public List<GnoType> getTypeList() {
      return myTypes;
    }

    @Override
    public String toString() {
      return "MyGnoTypeList{myTypes=" + myTypes + '}';
    }

    @Override
    public String getText() {
      return StringUtil.join(getTypeList(), PsiElement::getText, ", ");
    }
  }

  static class LightFunctionType extends GnoLightType<GnoSignatureOwner> implements GnoFunctionType {
    public LightFunctionType(@NotNull GnoSignatureOwner o) {
      super(o);
    }

    @Nullable
    @Override
    public GnoSignature getSignature() {
      return myElement.getSignature();
    }

    @NotNull
    @Override
    public PsiElement getFunc() {
      return myElement instanceof GnoFunctionOrMethodDeclaration ? ((GnoFunctionOrMethodDeclaration)myElement).getFunc() : myElement;
    }

    @Override
    public String getText() {
      GnoSignature signature = myElement.getSignature();
      return "func " + (signature != null ? signature.getText() : "<null>");
    }
  }

  static class LightArrayType extends GnoLightType<GnoType> implements GnoArrayOrSliceType {
    protected LightArrayType(GnoType type) {
      super(type);
    }

    @Override
    public String getText() {
      return "[]" + myElement.getText();
    }

    @Nullable
    @Override
    public GnoExpression getExpression() {
      return null;
    }

    @Nullable
    @Override
    public GnoType getType() {
      return myElement;
    }

    @NotNull
    @Override
    public PsiElement getLbrack() {
      //noinspection ConstantConditions
      return null; // todo: mock?
    }

    @Nullable
    @Override
    public PsiElement getRbrack() {
      return null;
    }

    @Nullable
    @Override
    public PsiElement getTripleDot() {
      return null;
    }
  }
}
