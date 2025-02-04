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

package com.github.intellij.gno.psi.impl.imports;

import com.github.intellij.gno.language.GnoLanguage;
import com.github.intellij.gno.codeInsight.imports.GnoImportPackageQuickFix;
import com.github.intellij.gno.psi.GnoCompositeElement;
import com.intellij.codeInsight.daemon.ReferenceImporter;
import com.intellij.codeInsight.daemon.impl.CollectHighlightsUtil;
import com.intellij.codeInsight.daemon.impl.DaemonListeners;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiReference;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GnoReferenceImporter implements ReferenceImporter {
  @Override
  public boolean autoImportReferenceAtCursor(@NotNull Editor editor, @NotNull PsiFile file) {
    if (!file.getViewProvider().getLanguages().contains(GnoLanguage.INSTANCE) ||
        !DaemonListeners.canChangeFileSilently(file)) {
      return false;
    }

    int caretOffset = editor.getCaretModel().getOffset();
    Document document = editor.getDocument();
    int lineNumber = document.getLineNumber(caretOffset);
    int startOffset = document.getLineStartOffset(lineNumber);
    int endOffset = document.getLineEndOffset(lineNumber);

    List<PsiElement> elements = CollectHighlightsUtil.getElementsInRange(file, startOffset, endOffset);
    for (PsiElement element : elements) {
      if (element instanceof GnoCompositeElement) {
        for (PsiReference reference : element.getReferences()) {
          GnoImportPackageQuickFix fix = new GnoImportPackageQuickFix(reference);
          if (fix.doAutoImportOrShowHint(editor, false)) {
            return true;
          }
        }
      }
    }
    return false;
  }


  public boolean autoImportReferenceAt(@NotNull Editor editor, @NotNull PsiFile file, int offset) {
    if (!file.getViewProvider().getLanguages().contains(GnoLanguage.INSTANCE)) {
      return false;
    }
    PsiReference reference = file.findReferenceAt(offset);
    if (reference != null) {
      return new GnoImportPackageQuickFix(reference).doAutoImportOrShowHint(editor, false);
    }
    return false;
  }
}
