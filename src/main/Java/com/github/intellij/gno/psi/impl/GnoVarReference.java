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

import com.github.intellij.gno.psi.GnoBlock;
import com.github.intellij.gno.psi.GnoFieldDefinition;
import com.github.intellij.gno.psi.GnoStatement;
import com.github.intellij.gno.psi.GnoVarDefinition;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveState;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GnoVarReference extends GnoCachedReference<GnoVarDefinition> {
  private final GnoBlock myPotentialStopBlock;

  public GnoVarReference(@NotNull GnoVarDefinition element) {
    super(element);
    myPotentialStopBlock = PsiTreeUtil.getParentOfType(element, GnoBlock.class);
  }

  @Nullable
  @Override
  public PsiElement resolveInner() {
    GnoVarProcessor p = new GnoVarProcessor(myElement, false) {
      @Override
      protected boolean crossOff(@NotNull PsiElement e) {
        return e instanceof GnoFieldDefinition || super.crossOff(e);
      }
    };
    processResolveVariants(p);
    return p.getResult();
  }

  @Override
  public boolean processResolveVariants(@NotNull GnoScopeProcessor processor) {
    GnoVarProcessor p = processor instanceof GnoVarProcessor
                       ? (GnoVarProcessor)processor
                       : new GnoVarProcessor(myElement, processor.isCompletion()) {
                         @Override
                         public boolean execute(@NotNull PsiElement e, @NotNull ResolveState state) {
                           return super.execute(e, state) && processor.execute(e, state);
                         }
                       };

    if (myPotentialStopBlock != null) {
      myPotentialStopBlock.processDeclarations(p, ResolveState.initial(), PsiTreeUtil.getParentOfType(myElement, GnoStatement.class), myElement);
      return true;
    }
    return false;
  }
}
