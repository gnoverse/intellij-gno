package com.github.intellij.gno.configuration;

import com.github.intellij.gno.psi.GnoConstants;
import com.github.intellij.gno.codeInsight.imports.GnoAutoImportConfigurable;
import com.github.intellij.gno.sdk.GnoSdkService;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurableProvider;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.options.UnnamedConfigurable;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GnoConfigurableProvider extends ConfigurableProvider {
    @NotNull private final Project myProject;

    public GnoConfigurableProvider(@NotNull Project project) {
        myProject = project;
    }

    @Nullable
    @Override
    public Configurable createConfigurable() {
        Configurable projectSettingsConfigurable = new GnoProjectSettingsConfigurable(myProject);
        Configurable librariesConfigurable = new GnoLibrariesConfigurableProvider(myProject).createConfigurable();
        Configurable sdkConfigurable = GnoSdkService.getInstance(myProject).createSdkConfigurable();
        Configurable autoImportConfigurable = new GnoAutoImportConfigurable(myProject, false);
        return sdkConfigurable != null
                ? new GnoCompositeConfigurable(sdkConfigurable, projectSettingsConfigurable, librariesConfigurable, autoImportConfigurable)
                : new GnoCompositeConfigurable(projectSettingsConfigurable, librariesConfigurable, autoImportConfigurable);
    }

    private static class GnoCompositeConfigurable extends SearchableConfigurable.Parent.Abstract {
        private Configurable[] myConfigurables;

        public GnoCompositeConfigurable(Configurable... configurables) {
            myConfigurables = configurables;
        }

        @Override
        protected Configurable[] buildConfigurables() {
            return myConfigurables;
        }

        @NotNull
        @Override
        public String getId() {
            return "gno";
        }

        @Nls
        @Override
        public String getDisplayName() {
            return GnoConstants.GNO;
        }

        @Nullable
        @Override
        public String getHelpTopic() {
            return null;
        }

        @Override
        public void disposeUIResources() {
            super.disposeUIResources();
            myConfigurables = null;
        }
    }

    public static class GnoProjectSettingsConfigurable extends GnoModuleAwareConfigurable {
        public GnoProjectSettingsConfigurable(@NotNull Project project) {
            super(project, "Project Settings", null);
        }

        @NotNull
        @Override
        protected UnnamedConfigurable createModuleConfigurable(Module module) {
            return new GnoModuleSettingsConfigurable(module, false);
        }
    }
}
