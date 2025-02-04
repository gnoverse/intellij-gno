package com.github.intellij.gno.configuration;

import com.github.intellij.gno.project.GnoApplicationLibrariesService;
import com.github.intellij.gno.project.GnoModuleLibrariesService;
import com.github.intellij.gno.project.GnoProjectLibrariesService;
import com.github.intellij.gno.sdk.GnoSdkUtil;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.options.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.HideableDecorator;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class GnoLibrariesConfigurableProvider extends ConfigurableProvider {
    @NotNull private final Project myProject;

    public GnoLibrariesConfigurableProvider(@NotNull Project project) {
        myProject = project;
    }

    @Nullable
    @Override
    public Configurable createConfigurable() {
        return createConfigurable(false);
    }

    @Nullable
    private Configurable createConfigurable(boolean dialogMode) {
        return new CompositeConfigurable<UnnamedConfigurable>() {

            @Nullable
            @Override
            public JComponent createComponent() {
                List<UnnamedConfigurable> configurables = getConfigurables();
                Collection<HideableDecorator> hideableDecorators = ContainerUtil.newHashSet();

                GridLayoutManager layoutManager = new GridLayoutManager(configurables.size() + 1, 1, new Insets(0, 0, 0, 0), -1, -1);
                JPanel rootPanel = new JPanel(layoutManager);
                Spacer spacer = new Spacer();
                rootPanel.add(spacer, new GridConstraints(configurables.size(), 0, 1, 1, GridConstraints.ANCHOR_SOUTH,
                        GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED,
                        GridConstraints.SIZEPOLICY_FIXED, null, null, null));

                for (int i = 0; i < configurables.size(); i++) {
                    UnnamedConfigurable configurable = configurables.get(i);
                    JComponent configurableComponent = configurable.createComponent();
                    assert configurableComponent != null;
                    JPanel hideablePanel = new JPanel(new BorderLayout());

                    rootPanel.add(hideablePanel, configurableConstrains(i));

                    if (configurable instanceof Configurable) {
                        String displayName = ((Configurable)configurable).getDisplayName();
                        ListenableHideableDecorator decorator = new ListenableHideableDecorator(hideablePanel, displayName, configurableComponent);
                        decorator.addListener(new MyHideableDecoratorListener(layoutManager, hideablePanel,
                                spacer, hideableDecorators,
                                configurableExpandedPropertyKey((Configurable)configurable)
                        ));
                        hideableDecorators.add(decorator);
                        decorator.setOn(isConfigurableExpanded(i, (Configurable)configurable));
                    }
                }
                if (dialogMode) {
                    rootPanel.setPreferredSize(new Dimension(400, 600));
                }
                rootPanel.revalidate();
                return rootPanel;
            }

            @NotNull
            @Override
            protected List<UnnamedConfigurable> createConfigurables() {
                List<UnnamedConfigurable> result = ContainerUtil.newArrayList();
                String[] urlsFromEnv = ContainerUtil.map2Array(GnoSdkUtil.getGnoPathsRootsFromEnvironment(), String.class, VirtualFile::getUrl);
                result.add(new GnoLibrariesConfigurable("Global libraries", GnoApplicationLibrariesService.getInstance(), urlsFromEnv));
                if (!myProject.isDefault()) {
                    result.add(new GnoLibrariesConfigurable("Project libraries", GnoProjectLibrariesService.getInstance(myProject)));
                    result.add(new GnoModuleAwareConfigurable(myProject, "Module libraries", null) {
                        @NotNull
                        @Override
                        protected UnnamedConfigurable createModuleConfigurable(@NotNull Module module) {
                            return new GnoLibrariesConfigurable("Module libraries", GnoModuleLibrariesService.getInstance(module));
                        }
                    });
                }
                return result;
            }

            @NotNull
            @Nls
            @Override
            public String getDisplayName() {
                return "Gno Libraries";
            }

            @Nullable
            @Override
            public String getHelpTopic() {
                return null;
            }

            @NotNull
            private GridConstraints configurableConstrains(int i) {
                return new GridConstraints(i, 0, 1, 1, GridConstraints.ANCHOR_NORTHEAST, GridConstraints.FILL_BOTH,
                        GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW |
                                GridConstraints.SIZEPOLICY_CAN_SHRINK,
                        GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_CAN_SHRINK,
                        null, null, null);
            }

            private boolean isConfigurableExpanded(int index, @NotNull Configurable configurable) {
                return PropertiesComponent.getInstance(myProject).getBoolean(configurableExpandedPropertyKey(configurable), index < 2);
            }

            private void storeConfigurableExpandedProperty(@NotNull String storeKey, @NotNull Boolean value) {
                PropertiesComponent.getInstance(myProject).setValue(storeKey, value.toString());
            }

            private String configurableExpandedPropertyKey(@NotNull Configurable configurable) {
                String keyName = "configurable " + configurable.getDisplayName() + " is expanded".toLowerCase(Locale.US);
                return keyName.replace(' ', '.');
            }

            class MyHideableDecoratorListener extends ListenableHideableDecorator.MyListener {
                private final GridLayoutManager myLayoutManager;
                private final JPanel myHideablePanel;
                @NotNull private final String myStoreKey;
                private final Spacer mySpacer;
                private final Collection<HideableDecorator> myHideableDecorators;

                public MyHideableDecoratorListener(@NotNull GridLayoutManager layoutManager,
                                                   @NotNull JPanel hideablePanel,
                                                   @NotNull Spacer spacer,
                                                   @NotNull Collection<HideableDecorator> hideableDecorators,
                                                   @NotNull String storeKey) {
                    myLayoutManager = layoutManager;
                    myHideablePanel = hideablePanel;
                    myStoreKey = storeKey;
                    mySpacer = spacer;
                    myHideableDecorators = hideableDecorators;
                }

                @Override
                public void on() {
                    GridConstraints c = myLayoutManager.getConstraintsForComponent(myHideablePanel);
                    c.setVSizePolicy(c.getVSizePolicy() | GridConstraints.SIZEPOLICY_WANT_GROW);

                    GridConstraints spacerConstraints = myLayoutManager.getConstraintsForComponent(mySpacer);
                    spacerConstraints.setVSizePolicy(spacerConstraints.getVSizePolicy() & ~GridConstraints.SIZEPOLICY_WANT_GROW);

                    storeConfigurableExpandedProperty(myStoreKey, Boolean.TRUE);
                }


                @Override
                public void beforeOff() {
                    GridConstraints c = myLayoutManager.getConstraintsForComponent(myHideablePanel);
                    c.setVSizePolicy(c.getVSizePolicy() & ~GridConstraints.SIZEPOLICY_WANT_GROW);
                }

                @Override
                public void afterOff() {
                    if (isAllDecoratorsCollapsed()) {
                        GridConstraints c = myLayoutManager.getConstraintsForComponent(mySpacer);
                        c.setVSizePolicy(c.getVSizePolicy() | GridConstraints.SIZEPOLICY_WANT_GROW);
                    }

                    storeConfigurableExpandedProperty(myStoreKey, Boolean.FALSE);
                }

                private boolean isAllDecoratorsCollapsed() {
                    for (HideableDecorator hideableDecorator : myHideableDecorators) {
                        if (hideableDecorator.isExpanded()) {
                            return false;
                        }
                    }
                    return true;
                }
            }
        };
    }

    public static void showModulesConfigurable(@NotNull Project project) {
        ApplicationManager.getApplication().assertIsDispatchThread();
        if (!project.isDisposed()) {
            Configurable configurable = new GnoLibrariesConfigurableProvider(project).createConfigurable(true);
            ShowSettingsUtil.getInstance().editConfigurable(project, configurable);
        }
    }
}