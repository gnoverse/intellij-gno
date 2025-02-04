package com.github.intellij.gno.project;

import com.intellij.openapi.components.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.SimpleModificationTracker;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.util.ArrayUtil;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(
        name = "GnoExcludedPaths"
)
public class GnoExcludedPathsSettings extends SimpleModificationTracker implements PersistentStateComponent<GnoExcludedPathsSettings> {
    private String[] myExcludedPackages = ArrayUtil.EMPTY_STRING_ARRAY;

    public static GnoExcludedPathsSettings getInstance(Project project) {
        return ServiceManager.getService(project, GnoExcludedPathsSettings.class);
    }

    @Nullable
    @Override
    public GnoExcludedPathsSettings getState() {
        return this;
    }

    @Override
    public void loadState(GnoExcludedPathsSettings state) {
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
