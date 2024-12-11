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

package com.github.intellij.gno.inspections.unresolved;

import com.github.intellij.gno.GoConstants;
import com.github.intellij.gno.inspections.GoInspectionBase;
import com.github.intellij.gno.psi.GoFile;
import com.github.intellij.gno.psi.GoFunctionDeclaration;
import com.github.intellij.gno.psi.GoVisitor;
import com.github.intellij.gno.quickfix.GoDeleteQuickFix;
import com.github.intellij.gno.quickfix.GoRenameToBlankQuickFix;
import com.github.intellij.gno.runconfig.testing.GoTestFinder;
import com.github.intellij.gno.runconfig.testing.GoTestFunctionType;
import com.intellij.codeInspection.LocalInspectionToolSession;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.search.searches.ReferencesSearch;
import org.jetbrains.annotations.NotNull;

public class GoUnusedFunctionInspection extends GoInspectionBase {
  @NotNull
  @Override
  protected GoVisitor buildGoVisitor(@NotNull ProblemsHolder holder, @NotNull LocalInspectionToolSession session) {
    return new GoVisitor() {
      @Override
      public void visitFunctionDeclaration(@NotNull GoFunctionDeclaration o) {
        if (o.isBlank()) return;
        GoFile file = o.getContainingFile();
        String name = o.getName();
        if (!canRun(name)) return;
        if (GoConstants.MAIN.equals(file.getPackageName()) && GoConstants.MAIN.equals(name)) return;
        if (GoConstants.INIT.equals(name)) return;
        if (GoTestFinder.isTestFile(file) && GoTestFunctionType.fromName(name) != null) return;
        if (ReferencesSearch.search(o, o.getUseScope()).findFirst() == null) {
          PsiElement id = o.getIdentifier();
          TextRange range = TextRange.from(id.getStartOffsetInParent(), id.getTextLength());
          holder.registerProblem(o, "Unused function <code>#ref</code> #loc", ProblemHighlightType.LIKE_UNUSED_SYMBOL, range,
                                 new GoDeleteQuickFix("Delete function", GoFunctionDeclaration.class), new GoRenameToBlankQuickFix(o));
        }
      }
    };
  }

  protected boolean canRun(String name) {
    return !StringUtil.isCapitalized(name);
  }
}
