package com.github.intellij.gno.configuration;

import com.github.intellij.gno.project.GnoApplicationLibrariesService;
import com.github.intellij.gno.project.GnoLibrariesService;
import com.intellij.openapi.fileChooser.FileChooserDialog;
import com.intellij.openapi.fileChooser.FileChooserFactory;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.util.Iconable;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.ui.CollectionListModel;
import com.intellij.ui.ColoredListCellRenderer;
import com.intellij.ui.SimpleTextAttributes;
import com.intellij.ui.ToolbarDecorator;
import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.components.JBList;
import com.intellij.util.IconUtil;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.ui.UIUtil;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.intellij.openapi.fileChooser.FileChooserDescriptorFactory.createMultipleFoldersDescriptor;

public class GnoLibrariesConfigurable implements SearchableConfigurable, Configurable.NoScroll {
    @NotNull private final String myDisplayName;
    private final GnoLibrariesService<?> myLibrariesService;
    private final String[] myReadOnlyPaths;
    private final JBCheckBox myUseEnvGnoPathCheckBox = new JBCheckBox("Use GOPATH that's defined in system environment");
    private final JPanel myPanel = new JPanel(new BorderLayout());
    private final CollectionListModel<ListItem> myListModel = new CollectionListModel<>();

    public GnoLibrariesConfigurable(@NotNull String displayName, @NotNull GnoLibrariesService librariesService, String... urls) {
        myDisplayName = displayName;
        myLibrariesService = librariesService;
        myReadOnlyPaths = urls;

        JBList filesList = new JBList(myListModel);
        filesList.setCellRenderer(new ColoredListCellRenderer() {
            @Override
            protected void customizeCellRenderer(@NotNull JList list, Object value, int index, boolean selected, boolean hasFocus) {
                ListItem item = (ListItem)value;
                String url = item.url;
                if (item.readOnly) {
                    append("[GOPATH] ", SimpleTextAttributes.GRAY_ATTRIBUTES);
                }
                VirtualFile file = VirtualFileManager.getInstance().findFileByUrl(url);
                if (file != null) {
                    append(file.getPresentableUrl(), item.readOnly ? SimpleTextAttributes.GRAY_ATTRIBUTES : SimpleTextAttributes.REGULAR_ATTRIBUTES);
                    setIcon(IconUtil.getIcon(file, Iconable.ICON_FLAG_READ_STATUS, null));
                }
                else {
                    append(VfsUtilCore.urlToPath(url), SimpleTextAttributes.ERROR_ATTRIBUTES);
                }
            }
        });

        ToolbarDecorator decorator = ToolbarDecorator.createDecorator(filesList)
                .setAddAction(button -> {
                    FileChooserDialog fileChooser = FileChooserFactory.getInstance()
                            .createFileChooser(createMultipleFoldersDescriptor(), null, filesList);

                    VirtualFile fileToSelect = null;
                    ListItem lastItem = ContainerUtil.getLastItem(myListModel.getItems());
                    if (lastItem != null) {
                        fileToSelect = VirtualFileManager.getInstance().findFileByUrl(lastItem.url);
                    }

                    VirtualFile[] newDirectories = fileChooser.choose(null, fileToSelect);
                    if (newDirectories.length > 0) {
                        for (VirtualFile newDirectory : newDirectories) {
                            String newDirectoryUrl = newDirectory.getUrl();
                            boolean alreadyAdded = false;
                            for (ListItem item : myListModel.getItems()) {
                                if (newDirectoryUrl.equals(item.url) && !item.readOnly) {
                                    filesList.clearSelection();
                                    filesList.setSelectedValue(item, true);
                                    scrollToSelection(filesList);
                                    alreadyAdded = true;
                                    break;
                                }
                            }
                            if (!alreadyAdded) {
                                myListModel.add(new ListItem(newDirectoryUrl, false));
                            }
                        }
                    }
                })
                .setRemoveActionUpdater(event -> {
                    for (Object selectedValue : filesList.getSelectedValuesList()) {
                        if (((ListItem)selectedValue).readOnly) {
                            return false;
                        }
                    }
                    return true;
                })
                .setRemoveAction(button -> {
                    for (Object selectedValue : filesList.getSelectedValuesList()) {
                        myListModel.remove((ListItem)selectedValue);
                    }
                });
        myPanel.add(decorator.createPanel(), BorderLayout.CENTER);
        if (librariesService instanceof GnoApplicationLibrariesService) {
            myUseEnvGnoPathCheckBox.addActionListener(event -> {
                if (myUseEnvGnoPathCheckBox.isSelected()) {
                    addReadOnlyPaths();
                }
                else {
                    removeReadOnlyPaths();
                }
            });
            myPanel.add(myUseEnvGnoPathCheckBox, BorderLayout.SOUTH);
        }
    }

    private static void scrollToSelection(JList list) {
        int selectedRow = list.getSelectedIndex();
        if (selectedRow >= 0) {
            list.scrollRectToVisible(list.getCellBounds(selectedRow, 0));
        }
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        return myPanel;
    }

    @Override
    public boolean isModified() {
        return !getUserDefinedUrls().equals(myLibrariesService.getLibraryRootUrls()) ||
                myLibrariesService instanceof GnoApplicationLibrariesService &&
                        ((GnoApplicationLibrariesService)myLibrariesService).isUseGnoPathFromSystemEnvironment() !=
                                myUseEnvGnoPathCheckBox.isSelected();
    }

    @Override
    public void apply() throws ConfigurationException {
        myLibrariesService.setLibraryRootUrls(getUserDefinedUrls());
        if (myLibrariesService instanceof GnoApplicationLibrariesService) {
            ((GnoApplicationLibrariesService)myLibrariesService).setUseGnoPathFromSystemEnvironment(myUseEnvGnoPathCheckBox.isSelected());
        }
    }

    @Override
    public void reset() {
        myListModel.removeAll();
        resetLibrariesFromEnvironment();
        for (String url : myLibrariesService.getLibraryRootUrls()) {
            myListModel.add(new ListItem(url, false));
        }
    }

    private void resetLibrariesFromEnvironment() {
        if (myLibrariesService instanceof GnoApplicationLibrariesService) {
            myUseEnvGnoPathCheckBox.setSelected(((GnoApplicationLibrariesService)myLibrariesService).isUseGnoPathFromSystemEnvironment());
            if (((GnoApplicationLibrariesService)myLibrariesService).isUseGnoPathFromSystemEnvironment()) {
                addReadOnlyPaths();
            }
            else {
                removeReadOnlyPaths();
            }
        }
    }

    private void addReadOnlyPaths() {
        for (String url : myReadOnlyPaths) {
            myListModel.add(new ListItem(url, true));
        }
    }

    private void removeReadOnlyPaths() {
        List<ListItem> toRemove = myListModel.getItems().stream().filter(item -> item.readOnly).collect(Collectors.toList());
        for (ListItem item : toRemove) {
            myListModel.remove(item);
        }
    }

    @Override
    public void disposeUIResources() {
        UIUtil.dispose(myUseEnvGnoPathCheckBox);
        UIUtil.dispose(myPanel);
        myListModel.removeAll();
    }

    @NotNull
    @Nls
    @Override
    public String getDisplayName() {
        return myDisplayName;
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return null;
    }

    @NotNull
    private Collection<String> getUserDefinedUrls() {
        Collection<String> libraryUrls = ContainerUtil.newArrayList();
        for (ListItem item : myListModel.getItems()) {
            if (!item.readOnly) {
                libraryUrls.add(item.url);
            }
        }
        return libraryUrls;
    }

    @NotNull
    @Override
    public String getId() {
        return "go.libraries";
    }

    @Nullable
    @Override
    public Runnable enableSearch(String option) {
        return null;
    }

    private static class ListItem {
        final boolean readOnly;
        final String url;

        public ListItem(String url, boolean readOnly) {
            this.readOnly = readOnly;
            this.url = url;
        }
    }
}
