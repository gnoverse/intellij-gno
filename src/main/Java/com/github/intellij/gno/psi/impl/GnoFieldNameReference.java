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
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.ResolveState;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GnoFieldNameReference extends GnoCachedReference<GnoReferenceExpressionBase> {
  public GnoFieldNameReference(@NotNull GnoReferenceExpressionBase element) {
    super(element);
  }

  @Override
  public boolean processResolveVariants(@NotNull GnoScopeProcessor processor) {
    GnoScopeProcessor fieldProcessor = processor instanceof GnoFieldProcessor ? processor : new GnoFieldProcessor(myElement) {
      @Override
      public boolean execute(@NotNull PsiElement e, @NotNull ResolveState state) {
        return super.execute(e, state) && processor.execute(e, state);
      }
    };
    GnoKey key = PsiTreeUtil.getParentOfType(myElement, GnoKey.class);
    GnoValue value = PsiTreeUtil.getParentOfType(myElement, GnoValue.class);
    if (key == null && (value == null || PsiTreeUtil.getPrevSiblingOfType(value, GnoKey.class) != null)) return true;

    GnoType type = GnoPsiImplUtil.getLiteralType(myElement, true);
    if (!processStructType(fieldProcessor, type)) return false;
    return !(type instanceof GnoPointerType && !processStructType(fieldProcessor, ((GnoPointerType)type).getType()));
  }

  private boolean processStructType(@NotNull GnoScopeProcessor fieldProcessor, @Nullable GnoType type) {
    return !(type instanceof GnoStructType && !type.processDeclarations(fieldProcessor, ResolveState.initial(), null, myElement));
  }

  public boolean inStructTypeKey() {
    return GnoPsiImplUtil.getParentGnoValue(myElement) == null && GnoPsiImplUtil.getLiteralType(myElement, false) instanceof GnoStructType;
  }

  @Nullable
  @Override
  public PsiElement resolveInner() {
    GnoScopeProcessorBase p = new GnoFieldProcessor(myElement);
    processResolveVariants(p);
    return p.getResult();
  }

  private static class GnoFieldProcessor extends GnoScopeProcessorBase {
    private final Module myModule;

    public GnoFieldProcessor(@NotNull PsiElement element) {
      super(element);
      PsiFile containingFile = myOrigin.getContainingFile();
      myModule = containingFile != null ? ModuleUtilCore.findModuleForPsiElement(containingFile.getOriginalFile()) : null;
    }

    @Override
    protected boolean crossOff(@NotNull PsiElement e) {
      if (!(e instanceof GnoFieldDefinition) && !(e instanceof GnoAnonymousFieldDefinition)) return true;
      GnoNamedElement named = (GnoNamedElement)e;
      PsiFile myFile = myOrigin.getContainingFile();
      PsiFile file = e.getContainingFile();
      if (!(myFile instanceof GnoFile) || !GnoPsiImplUtil.allowed(file, myFile, myModule)) return true;
      boolean localResolve = GnoReference.isLocalResolve(myFile, file);
      return !e.isValid() || !(named.isPublic() || localResolve);
    }
  }
}
