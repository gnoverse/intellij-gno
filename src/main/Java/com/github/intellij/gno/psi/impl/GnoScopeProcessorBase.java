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

import com.github.intellij.gno.psi.GnoFunctionOrMethodDeclaration;
import com.github.intellij.gno.psi.GnoNamedElement;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveState;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.containers.OrderedSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class GnoScopeProcessorBase extends GnoScopeProcessor {
  @NotNull private final OrderedSet<GnoNamedElement> myResult = new OrderedSet<>();
  @NotNull protected final PsiElement myOrigin;
  @NotNull private final PsiElement myRequestedNameElement;
  protected final boolean myIsCompletion;

  public GnoScopeProcessorBase(@NotNull PsiElement origin) {
    this(origin, origin, false);
  }

  public GnoScopeProcessorBase(@NotNull PsiElement requestedNameElement, @NotNull PsiElement origin, boolean completion) {
    myRequestedNameElement = requestedNameElement;
    myOrigin = origin;
    myIsCompletion = completion;
  }

  @Override
  public boolean execute(@NotNull PsiElement e, @NotNull ResolveState state) {
    if (e instanceof GnoFunctionOrMethodDeclaration) return false;
    if (!(e instanceof GnoNamedElement)) return true;
    String name = ((GnoNamedElement)e).getName();
    if (StringUtil.isEmpty(name) || !myIsCompletion && !myRequestedNameElement.textMatches(name)) return true;
    if (crossOff(e)) return true;
    if (e.equals(myOrigin)) return true;
    return add((GnoNamedElement)e) || myIsCompletion;
  }

  protected boolean add(@NotNull GnoNamedElement psiElement) {
    return !myResult.add(psiElement);
  }

  @Nullable
  public GnoNamedElement getResult() {
    return ContainerUtil.getFirstItem(myResult);
  }

  @NotNull
  public List<GnoNamedElement> getVariants() {
    return myResult;
  }

  protected abstract boolean crossOff(@NotNull PsiElement e);
}
