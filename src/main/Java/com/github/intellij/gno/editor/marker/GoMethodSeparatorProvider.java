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

package com.github.intellij.gno.editor.marker;

import com.github.intellij.gno.psi.GoFile;
import com.github.intellij.gno.psi.GoTopLevelDeclaration;
import com.intellij.codeInsight.daemon.DaemonCodeAnalyzerSettings;
import com.intellij.codeInsight.daemon.LineMarkerInfo;
import com.intellij.codeInsight.daemon.LineMarkerProvider;
import com.intellij.codeInsight.daemon.impl.LineMarkersPass;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiWhiteSpace;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GoMethodSeparatorProvider implements LineMarkerProvider {
  private final DaemonCodeAnalyzerSettings myDaemonSettings;
  private final EditorColorsManager myColorsManager;

  public GoMethodSeparatorProvider() {
    myDaemonSettings = ApplicationManager.getApplication().getService(DaemonCodeAnalyzerSettings.class);
    myColorsManager = EditorColorsManager.getInstance();
  }

  @Nullable
  @Override
  public LineMarkerInfo getLineMarkerInfo(@NotNull PsiElement o) {
    if (myDaemonSettings.SHOW_METHOD_SEPARATORS && o instanceof GoTopLevelDeclaration && o.getParent() instanceof GoFile) {
      return LineMarkersPass.createMethodSeparatorLineMarker(findAnchorElement((GoTopLevelDeclaration)o), myColorsManager);
    }
    return null;
  }

  @NotNull
  private static PsiElement findAnchorElement(@NotNull GoTopLevelDeclaration o) {
    PsiElement result = o;
    PsiElement p = o;
    while ((p = p.getPrevSibling()) != null) {
      if (p instanceof PsiComment) {
        result = p;
      }
      else if (p instanceof PsiWhiteSpace) {
        if (p.getText().contains("\n\n")) return result;
      }
      else {
        break;
      }
    }
    return result;
  }
}