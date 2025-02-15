package com.github.intellij.gno.psi;

import com.github.intellij.gno.language.GnoLanguage;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

public class GnoCompositeElementType extends IElementType {
  public GnoCompositeElementType(@NotNull String debug) {
    super(debug, GnoLanguage.INSTANCE);
  }
}
