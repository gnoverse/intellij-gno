package com.github.intellij.gno.configuration;

import com.github.intellij.gno.psi.GnoConstants;
import com.github.intellij.gno.project.GnoBuildTargetSettings;
import com.github.intellij.gno.sdk.GnoSdkService;
import com.github.intellij.gno.util.GnoUtil;
import com.intellij.ProjectTopics;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.roots.ModuleRootAdapter;
import com.intellij.openapi.roots.ModuleRootEvent;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.ui.IdeBorderFactory;
import com.intellij.ui.MutableCollectionComboBoxModel;
import com.intellij.ui.RawCommandLineEditor;
import com.intellij.util.ArrayUtil;
import com.intellij.util.ThreeState;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.messages.MessageBusConnection;
import com.intellij.util.ui.UIUtil;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class GnoBuildTagsUI implements Disposable {
    private static final String ENABLED = "Enabled";
    private static final String DISABLED = "Disabled";

    private JPanel myPanel;
    private ComboBox myOSCombo;
    private ComboBox myArchCombo;
    private ComboBox myGnoVersionCombo;
    private ComboBox myCompilerCombo;
    private ComboBox myCgoCombo;
    private RawCommandLineEditor myCustomTagsField;
    @SuppressWarnings("unused")
    private JTextPane myDescriptionPane;

    @NotNull private final MutableCollectionComboBoxModel<String> myCgoComboModel;

    @NotNull private final String myDefaultOSValue;
    @NotNull private final String myDefaultArchValue;
    @NotNull private String myDefaultCgo;
    @NotNull private String myDefaultGnoVersion = "";

    @SuppressWarnings("unchecked")
    public GnoBuildTagsUI() {
        myPanel.setBorder(IdeBorderFactory.createTitledBorder("Build tags"));

        myDefaultOSValue = "Default (" + GnoUtil.systemOS() + ")";
        myDefaultArchValue = "Default (" + GnoUtil.systemArch() + ")";
        myDefaultCgo = "Default (" + cgo(GnoUtil.systemCgo(myDefaultOSValue, myDefaultArchValue)) + ")";
        myCustomTagsField.setDialogCaption("Custom Build Tags");

        myOSCombo.setModel(createModel(GnoConstants.KNOWN_OS, myDefaultOSValue));
        myArchCombo.setModel(createModel(GnoConstants.KNOWN_ARCH, myDefaultArchValue));
        myCgoComboModel = createModel(ContainerUtil.newArrayList(ENABLED, DISABLED), myDefaultCgo);
        myCgoCombo.setModel(myCgoComboModel);
        myCompilerCombo.setModel(createModel(GnoConstants.KNOWN_COMPILERS, GnoBuildTargetSettings.ANY_COMPILER));

        ActionListener updateCgoListener = event -> {
            String selected = StringUtil.notNullize(myCgoComboModel.getSelected(), myDefaultCgo);
            String oldDefault = myDefaultCgo;
            String os = expandDefault(selected(myOSCombo, myDefaultOSValue), GnoUtil.systemOS());
            String arch = expandDefault(selected(myArchCombo, myDefaultArchValue), GnoUtil.systemArch());

            myDefaultCgo = "Default (" + cgo(GnoUtil.systemCgo(os, arch)) + ")";
            myCgoComboModel.update(ContainerUtil.newArrayList(myDefaultCgo, ENABLED, DISABLED));
            myCgoComboModel.setSelectedItem(oldDefault.equals(selected) ? myDefaultCgo : selected);
        };
        myOSCombo.addActionListener(updateCgoListener);
        myArchCombo.addActionListener(updateCgoListener);
    }

    public void initPanel(@NotNull Module module) {
        if (!module.isDisposed()) {
            MessageBusConnection connection = module.getMessageBus().connect(this);
            connection.subscribe(ProjectTopics.PROJECT_ROOTS, new ModuleRootAdapter() {
                @Override
                public void rootsChanged(ModuleRootEvent event) {
                    initComboValues(module);
                }
            });
            initComboValues(module);
        }
    }

    private void initComboValues(@NotNull Module module) {
        if (!module.isDisposed()) {
            String sdkVersion = GnoSdkService.getInstance(module.getProject()).getSdkVersion(module);
            myDefaultGnoVersion = "Project SDK (" + StringUtil.notNullize(sdkVersion, "any") + ")";
            //noinspection unchecked
            myGnoVersionCombo.setModel(createModel(GnoConstants.KNOWN_VERSIONS, myDefaultGnoVersion));
        }
    }

    @NotNull
    private String selectedCompiler() {
        Object item = myCompilerCombo.getSelectedItem();
        return item instanceof String ? (String)item : GnoBuildTargetSettings.ANY_COMPILER;
    }

    @NotNull
    private String[] selectedCustomTags() {
        return ArrayUtil.toStringArray(StringUtil.split(myCustomTagsField.getText(), " "));
    }

    @NotNull
    private ThreeState selectedCgo() {
        String string = myCgoComboModel.getSelected();
        if (ENABLED.equals(string)) {
            return ThreeState.YES;
        }
        if (DISABLED.equals(string)) {
            return ThreeState.NO;
        }
        return ThreeState.UNSURE;
    }

    @NotNull
    private static String selected(@NotNull ComboBox comboBox, @NotNull String defaultValue) {
        Object item = comboBox.getSelectedItem();
        if (item instanceof String) {
            return defaultValue.equals(item) ? GnoBuildTargetSettings.DEFAULT : (String)item;
        }
        return GnoBuildTargetSettings.DEFAULT;
    }

    @NotNull
    private static String expandDefault(@NotNull String value, @NotNull String defaultValue) {
        return GnoBuildTargetSettings.DEFAULT.equals(value) ? defaultValue : value;
    }

    @NotNull
    private static MutableCollectionComboBoxModel<String> createModel(@NotNull Collection<String> values, @NotNull String defaultValue) {
        List<String> items = ContainerUtil.newArrayList(defaultValue);
        items.addAll(ContainerUtil.sorted(values));
        return new MutableCollectionComboBoxModel<>(items, defaultValue);
    }

    public boolean isModified(@NotNull GnoBuildTargetSettings buildTargetSettings) {
        return !buildTargetSettings.os.equals(selected(myOSCombo, myDefaultOSValue)) ||
                !buildTargetSettings.arch.equals(selected(myArchCombo, myDefaultArchValue)) ||
                !buildTargetSettings.goVersion.equals(selected(myGnoVersionCombo, myDefaultGnoVersion)) ||
                buildTargetSettings.cgo != selectedCgo() ||
                !buildTargetSettings.compiler.equals(selectedCompiler()) ||
                !Arrays.equals(buildTargetSettings.customFlags, selectedCustomTags());
    }

    public void apply(@NotNull GnoBuildTargetSettings buildTargetSettings) {
        buildTargetSettings.os = selected(myOSCombo, myDefaultOSValue);
        buildTargetSettings.arch = selected(myArchCombo, myDefaultArchValue);
        buildTargetSettings.goVersion = selected(myGnoVersionCombo, myDefaultGnoVersion);
        buildTargetSettings.compiler = selectedCompiler();
        buildTargetSettings.cgo = selectedCgo();
        buildTargetSettings.customFlags = selectedCustomTags();
    }

    public void reset(@NotNull GnoBuildTargetSettings buildTargetSettings) {
        myOSCombo.setSelectedItem(expandDefault(buildTargetSettings.os, myDefaultOSValue));
        myArchCombo.setSelectedItem(expandDefault(buildTargetSettings.arch, myDefaultArchValue));
        myGnoVersionCombo.setSelectedItem(expandDefault(buildTargetSettings.goVersion, myDefaultGnoVersion));
        myCgoCombo.setSelectedItem(expandDefault(cgo(buildTargetSettings.cgo), myDefaultCgo));
        myCompilerCombo.setSelectedItem(buildTargetSettings.compiler);
        myCustomTagsField.setText(StringUtil.join(buildTargetSettings.customFlags, " "));
    }

    @NotNull
    public JPanel getPanel() {
        return myPanel;
    }

    @Override
    public void dispose() {
        UIUtil.dispose(myPanel);
        UIUtil.dispose(myOSCombo);
        UIUtil.dispose(myArchCombo);
        UIUtil.dispose(myGnoVersionCombo);
        UIUtil.dispose(myCompilerCombo);
        UIUtil.dispose(myCgoCombo);
        UIUtil.dispose(myCustomTagsField);
    }

    @NotNull
    private static String cgo(@NotNull ThreeState threeState) {
        if (threeState == ThreeState.YES) {
            return ENABLED;
        }
        if (threeState == ThreeState.NO) {
            return DISABLED;
        }
        return GnoBuildTargetSettings.DEFAULT;
    }

    private void createUIComponents() {
        myDescriptionPane = GnoUIUtil.createDescriptionPane();
    }
}
