package com.github.intellij.gno.runConfiguration;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.execution.configurations.RunConfigurationOptions;
import com.intellij.openapi.components.BaseState;

public class GnoTestConfigurationFactory extends ConfigurationFactory {

    protected GnoTestConfigurationFactory(ConfigurationType type) {
        super(type);
    }

    @NotNull
    @Override
    public RunConfiguration createTemplateConfiguration(@NotNull Project project) {
        return new GnoTestRunConfiguration(project, this, "Gno Test");
    }

    @NotNull
    @Override
    public String getId() {
        return GnoTestConfigurationType.ID;
    }

    @Nullable
    @Override
    public Class<? extends BaseState> getOptionsClass() {
        return GnoTestRunConfigurationOptions.class;
    }
}
