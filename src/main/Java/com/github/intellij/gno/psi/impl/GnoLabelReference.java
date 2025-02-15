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
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveState;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class GnoLabelReference extends GnoCachedReference<GnoLabelRef> {
  private final GnoScopeProcessorBase myProcessor = new GnoScopeProcessorBase(myElement) {
    @Override
    protected boolean crossOff(@NotNull PsiElement e) {
      return !(e instanceof GnoLabelDefinition) || ((GnoLabelDefinition)e).isBlank();
    }
  };

  public GnoLabelReference(@NotNull GnoLabelRef element) {
    super(element);
  }

  @NotNull
  private Collection<GnoLabelDefinition> getLabelDefinitions() {
    GnoFunctionLit functionLit = PsiTreeUtil.getParentOfType(myElement, GnoFunctionLit.class);
    PsiElement blockToSearch = functionLit != null ? functionLit.getBlock() : PsiTreeUtil.getTopmostParentOfType(myElement, GnoBlock.class); 
    return PsiTreeUtil.findChildrenOfType(blockToSearch, GnoLabelDefinition.class);
  }

  @Nullable
  @Override
  protected PsiElement resolveInner() {
    return !processResolveVariants(myProcessor) ? myProcessor.getResult() : null;
  }

  @Override
  public boolean processResolveVariants(@NotNull GnoScopeProcessor processor) {
    GnoBreakStatement breakStatement = PsiTreeUtil.getParentOfType(myElement, GnoBreakStatement.class);
    if (breakStatement != null) {
      return processDefinitionsForBreakReference(breakStatement, processor);
    }
    return processAllDefinitions(processor);
  }

  private boolean processAllDefinitions(@NotNull GnoScopeProcessor processor) {
    Collection<GnoLabelDefinition> defs = getLabelDefinitions();
    for (GnoLabelDefinition def : defs) {
      if (!processor.execute(def, ResolveState.initial())) {
        return false;
      }
    }
    return true;
  }

  private static boolean processDefinitionsForBreakReference(@NotNull GnoBreakStatement breakStatement,
                                                             @NotNull GnoScopeProcessor processor) {
    PsiElement breakStatementOwner = GnoPsiImplUtil.getBreakStatementOwner(breakStatement);
    while (breakStatementOwner != null) {
      PsiElement parent = breakStatementOwner.getParent();
      if (parent instanceof GnoLabeledStatement) {
        if (!processor.execute(((GnoLabeledStatement)parent).getLabelDefinition(), ResolveState.initial())) {
          return false;
        }
      }
      breakStatementOwner = GnoPsiImplUtil.getBreakStatementOwner(breakStatementOwner);
    }
    return true;
  }
}
