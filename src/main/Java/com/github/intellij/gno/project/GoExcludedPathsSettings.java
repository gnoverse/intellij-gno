package com.github.intellij.gno.project;

import com.intellij.openapi.components.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.SimpleModificationTracker;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.util.ArrayUtil;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GoExcludedPathsSettings extends SimpleModificationTracker implements PersistentStateComponent<GoExcludedPathsSettings> {
  private String[] myExcludedPackages = ArrayUtil.EMPTY_STRING_ARRAY;

  public static GoExcludedPathsSettings getInstance(Project project) {
    return ServiceManager.getService(project, GoExcludedPathsSettings.class);
  }

  @Nullable
  @Override
  public GoExcludedPathsSettings getState() {
    return this;
  }

  @Override
  public void loadState(GoExcludedPathsSettings state) {
    XmlSerializerUtil.copyBean(state, this);
  }

  public String[] getExcludedPackages() {
    return myExcludedPackages;
  }

  public void setExcludedPackages(String... excludedPackages) {
    myExcludedPackages = excludedPackages;
    incModificationCount();
  }

  public boolean isExcluded(@Nullable String importPath) {
    if (importPath == null) {
      return false;
    }
    for (String excludedPath : myExcludedPackages) {
      if (FileUtil.isAncestor(excludedPath, importPath, false)) return true;
    }
    return false;
  }

  public void excludePath(@NotNull String importPath) {
    setExcludedPackages(ArrayUtil.append(myExcludedPackages, importPath));
  }
}
