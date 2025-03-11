package com.github.intellij.gno.runConfiguration;

import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.openapi.options.SettingsEditor;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class GnoTestSettingsEditor extends SettingsEditor<GnoTestRunConfiguration> {

    private final JPanel panel;
    private final TextFieldWithBrowseButton pathField;

    public GnoTestSettingsEditor() {
        pathField = new TextFieldWithBrowseButton();
        pathField.addBrowseFolderListener("Select Gno Test File/Folder", null, null,
                FileChooserDescriptorFactory.createSingleFileOrFolderDescriptor());

        panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Test Path:"), BorderLayout.WEST);
        panel.add(pathField, BorderLayout.CENTER);
    }

    @Override
    protected void resetEditorFrom(@NotNull GnoTestRunConfiguration configuration) {
        pathField.setText(configuration.getTestPath());
    }

    @Override
    protected void applyEditorTo(@NotNull GnoTestRunConfiguration configuration) {
        configuration.setTestPath(pathField.getText());
    }

    @NotNull
    @Override
    protected JComponent createEditor() {
        return panel;
    }
}
