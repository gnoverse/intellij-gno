package com.github.intellij.gno.configuration;

import com.github.intellij.gno.project.GnoModuleSettings;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.options.ConfigurableBase;
import org.jetbrains.annotations.NotNull;

public class GnoModuleSettingsConfigurable extends ConfigurableBase<GnoModuleSettingsUI, GnoModuleSettings> {
    @NotNull private final Module myModule;
    private final boolean myDialogMode;

    public GnoModuleSettingsConfigurable(@NotNull Module module, boolean dialogMode) {
        super("go.project.settings", "Gno Project Settings", null);
        myModule = module;
        myDialogMode = dialogMode;
    }

    @NotNull
    @Override
    protected GnoModuleSettings getSettings() {
        return GnoModuleSettings.getInstance(myModule);
    }

    @Override
    protected GnoModuleSettingsUI createUi() {
        return new GnoModuleSettingsUI(myModule, myDialogMode);
    }
}
