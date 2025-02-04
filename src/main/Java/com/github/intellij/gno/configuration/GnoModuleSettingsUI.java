package com.github.intellij.gno.configuration;

import com.github.intellij.gno.project.GnoBuildTargetSettings;
import com.github.intellij.gno.project.GnoModuleSettings;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.options.ConfigurableUi;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.util.Disposer;
import com.intellij.util.ui.UIUtil;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class GnoModuleSettingsUI implements ConfigurableUi<GnoModuleSettings>, Disposable {
    private JPanel myPanel;
    private JPanel myBuildTagsPanel;
    private JPanel myVendoringPanel;
    private GnoVendoringUI myVendoringUI;
    private GnoBuildTagsUI myBuildTagsUI;

    public GnoModuleSettingsUI(@NotNull Module module, boolean dialogMode) {
        if (dialogMode) {
            myPanel.setPreferredSize(new Dimension(400, -1));
        }

        myVendoringUI.initPanel(module);
        myBuildTagsUI.initPanel(module);
    }

    @Override
    public void reset(@NotNull GnoModuleSettings settings) {
        myBuildTagsUI.reset(settings.getBuildTargetSettings());
        myVendoringUI.reset(settings);
    }

    @Override
    public boolean isModified(@NotNull GnoModuleSettings settings) {
        return myVendoringUI.isModified(settings) || myBuildTagsUI.isModified(settings.getBuildTargetSettings());
    }

    @Override
    public void apply(@NotNull GnoModuleSettings settings) throws ConfigurationException {
        myVendoringUI.apply(settings);

        GnoBuildTargetSettings newBuildTargetSettings = new GnoBuildTargetSettings();
        myBuildTagsUI.apply(newBuildTargetSettings);
        settings.setBuildTargetSettings(newBuildTargetSettings);
    }

    @NotNull
    @Override
    public JComponent getComponent() {
        return myPanel;
    }

    private void createUIComponents() {
        myVendoringUI = new GnoVendoringUI();
        myBuildTagsUI = new GnoBuildTagsUI();

        myVendoringPanel = myVendoringUI.getPanel();
        myBuildTagsPanel = myBuildTagsUI.getPanel();
    }

    @Override
    public void dispose() {
        Disposer.dispose(myVendoringUI);
        Disposer.dispose(myBuildTagsUI);
        UIUtil.dispose(myPanel);
    }
}
