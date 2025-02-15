package com.github.intellij.gno.codeInsight.imports;

import com.github.intellij.gno.psi.GnoConstants;
import com.intellij.openapi.components.*;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.Nullable;

@State(
        name = GnoConstants.GNO
)
public class GnoCodeInsightSettings implements PersistentStateComponent<GnoCodeInsightSettings> {
    private boolean myShowImportPopup = true;
    private boolean myAddUnambiguousImportsOnTheFly = true;

    public static GnoCodeInsightSettings getInstance() {
        return ServiceManager.getService(GnoCodeInsightSettings.class);
    }

    @Nullable
    @Override
    public GnoCodeInsightSettings getState() {
        return this;
    }

    @Override
    public void loadState(GnoCodeInsightSettings state) {
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
