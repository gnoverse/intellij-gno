package com.github.intellij.gno.codeInsight.imports;

import com.github.intellij.gno.project.GnoExcludedPathsSettings;
import com.intellij.openapi.application.ApplicationBundle;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.ui.*;
import com.intellij.ui.components.JBList;
import com.intellij.util.ui.FormBuilder;
import com.intellij.util.ui.UIUtil;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class GnoAutoImportConfigurable implements SearchableConfigurable {
    private JCheckBox myCbShowImportPopup;
    private JCheckBox myCbAddUnambiguousImports;
    private JBList myExcludePackagesList;
    private DefaultListModel myExcludePackagesModel;

    @NotNull private final GnoCodeInsightSettings myCodeInsightSettings;
    @NotNull private final GnoExcludedPathsSettings myExcludedSettings;
    private final boolean myIsDefaultProject;
    private final boolean myIsDialog;

    public GnoAutoImportConfigurable(@NotNull Project project, boolean dialogMode) {
        myCodeInsightSettings = GnoCodeInsightSettings.getInstance();
        myExcludedSettings = GnoExcludedPathsSettings.getInstance(project);
        myIsDefaultProject = project.isDefault();
        myIsDialog = dialogMode;
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        FormBuilder builder = FormBuilder.createFormBuilder();
        myCbShowImportPopup = new JCheckBox(ApplicationBundle.message("checkbox.show.import.popup"));
        myCbAddUnambiguousImports = new JCheckBox(ApplicationBundle.message("checkbox.add.unambiguous.imports.on.the.fly"));
        builder.addComponent(myCbShowImportPopup);
        builder.addComponent(myCbAddUnambiguousImports);

        myExcludePackagesList = new JBList();
        JComponent excludedPanel = new JPanel(new BorderLayout());
        excludedPanel.add(ToolbarDecorator.createDecorator(myExcludePackagesList)
                .setAddAction(new AddImportExclusionAction()).disableUpDownActions().createPanel(), BorderLayout.CENTER);
        excludedPanel.setBorder(IdeBorderFactory.createTitledBorder(ApplicationBundle.message("exclude.from.completion.group"), true));
        if (!myIsDefaultProject) {
            builder.addComponent(excludedPanel);
        }

        JPanel result = new JPanel(new BorderLayout());
        result.add(builder.getPanel(), BorderLayout.NORTH);
        if (myIsDialog) result.setPreferredSize(new Dimension(300, -1));
        return result;
    }

    public void focusList() {
        myExcludePackagesList.setSelectedIndex(0);
        myExcludePackagesList.requestFocus();
    }

    private String[] getExcludedPackages() {
        String[] excludedPackages = new String[myExcludePackagesModel.size()];
        for (int i = 0; i < myExcludePackagesModel.size(); i++) {
            excludedPackages[i] = (String)myExcludePackagesModel.elementAt(i);
        }
        Arrays.sort(excludedPackages);
        return excludedPackages;
    }

    @Override
    public boolean isModified() {
        return myCodeInsightSettings.isShowImportPopup() != myCbShowImportPopup.isSelected() ||
                myCodeInsightSettings.isAddUnambiguousImportsOnTheFly() != myCbAddUnambiguousImports.isSelected() ||
                !Arrays.equals(getExcludedPackages(), myExcludedSettings.getExcludedPackages());
    }

    @Override
    public void apply() throws ConfigurationException {
        myCodeInsightSettings.setShowImportPopup(myCbShowImportPopup.isSelected());
        myCodeInsightSettings.setAddUnambiguousImportsOnTheFly(myCbAddUnambiguousImports.isSelected());
        myExcludedSettings.setExcludedPackages(getExcludedPackages());
    }

    @Override
    public void reset() {
        myCbShowImportPopup.setSelected(myCodeInsightSettings.isShowImportPopup());
        myCbAddUnambiguousImports.setSelected(myCodeInsightSettings.isAddUnambiguousImportsOnTheFly());

        myExcludePackagesModel = new DefaultListModel();
        for (String name : myExcludedSettings.getExcludedPackages()) {
            myExcludePackagesModel.add(myExcludePackagesModel.size(), name);
        }
        myExcludePackagesList.setModel(myExcludePackagesModel);
    }

    @NotNull
    @Override
    public String getId() {
        return "go.autoimport";
    }

    @Nullable
    @Override
    public Runnable enableSearch(String option) {
        return null;
    }

    @Nls
    @Override
    public String getDisplayName() {
        return "Auto Import";
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return null;
    }

    @Override
    public void disposeUIResources() {
        UIUtil.dispose(myCbShowImportPopup);
        UIUtil.dispose(myCbAddUnambiguousImports);
        UIUtil.dispose(myExcludePackagesList);
        myCbShowImportPopup = null;
        myCbAddUnambiguousImports = null;
        myExcludePackagesList = null;
        myExcludePackagesModel.removeAllElements();
        myExcludePackagesModel = null;
    }

    private class AddImportExclusionAction implements AnActionButtonRunnable {
        @Override
        public void run(AnActionButton button) {
            String packageName =
                    Messages.showInputDialog("Enter the import path to exclude from auto-import and completion:",
                            "Exclude Import Path",
                            Messages.getWarningIcon());
            addExcludedPackage(packageName);
        }

        private void addExcludedPackage(@Nullable String packageName) {
            if (StringUtil.isEmpty(packageName)) return;
            int index = -Arrays.binarySearch(myExcludePackagesModel.toArray(), packageName) - 1;
            if (index >= 0) {
                myExcludePackagesModel.add(index, packageName);
                ScrollingUtil.ensureIndexIsVisible(myExcludePackagesList, index, 0);
            }
            myExcludePackagesList.clearSelection();
            myExcludePackagesList.setSelectedValue(packageName, true);
            myExcludePackagesList.requestFocus();
        }
    }
}
