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

package com.github.intellij.gno.psi.impl.manipulator;

import com.github.intellij.gno.psi.GnoImportString;
import com.github.intellij.gno.psi.impl.GnoElementFactory;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.AbstractElementManipulator;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;

public class GnoImportStringManipulator extends AbstractElementManipulator<GnoImportString> {
  @NotNull
  @Override
  public GnoImportString handleContentChange(@NotNull GnoImportString string, @NotNull TextRange range, String s) throws IncorrectOperationException {
    String newPackage = range.replace(string.getText(), s);
    return (GnoImportString)string.replace(GnoElementFactory.createImportString(string.getProject(), newPackage));
  }

  @NotNull
  @Override
  public TextRange getRangeInElement(@NotNull GnoImportString element) {
    return element.getPathTextRange();
  }
}
