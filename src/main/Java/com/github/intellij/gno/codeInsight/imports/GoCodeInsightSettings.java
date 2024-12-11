package com.github.intellij.gno.codeInsight.imports;

import com.github.intellij.gno.GoConstants;
import com.intellij.openapi.components.*;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.Nullable;

@State(
  name = GoConstants.GO,
  storages = @Storage(value = StoragePathMacros.MODULE_FILE)
)
public class GoCodeInsightSettings implements PersistentStateComponent<GoCodeInsightSettings> {
  private boolean myShowImportPopup = true;
  private boolean myAddUnambiguousImportsOnTheFly = true;

  public static GoCodeInsightSettings getInstance() {
    return ServiceManager.getService(GoCodeInsightSettings.class);
  }

  @Nullable
  @Override
  public GoCodeInsightSettings getState() {
    return this;
  }

  @Override
  public void loadState(GoCodeInsightSettings state) {
    XmlSerializerUtil.copyBean(state, this);
  }

  public boolean isShowImportPopup() {
    return myShowImportPopup;
  }

  public void setShowImportPopup(boolean showImportPopup) {
    myShowImportPopup = showImportPopup;
  }

  public boolean isAddUnambiguousImportsOnTheFly() {
    return myAddUnambiguousImportsOnTheFly;
  }

  public void setAddUnambiguousImportsOnTheFly(boolean addUnambiguousImportsOnTheFly) {
    myAddUnambiguousImportsOnTheFly = addUnambiguousImportsOnTheFly;
  }
}
