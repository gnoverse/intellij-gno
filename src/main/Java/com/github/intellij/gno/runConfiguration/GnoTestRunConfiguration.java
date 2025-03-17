package com.github.intellij.gno.runConfiguration;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.*;
import com.intellij.execution.process.ProcessHandlerFactory;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.process.ProcessTerminatedListener;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GnoTestRunConfiguration extends RunConfigurationBase<GnoTestRunConfigurationOptions> {

    protected GnoTestRunConfiguration(Project project,
                                      ConfigurationFactory factory,
                                      String name) {
        super(project, factory, name);
    }

    @NotNull
    @Override
    protected GnoTestRunConfigurationOptions getOptions() {
        return (GnoTestRunConfigurationOptions) super.getOptions();
    }

    public String getTestPath() {
        return getOptions().getTestPath();
    }

    public void setTestPath(String path) {
        getOptions().setTestPath(path);
    }

    @NotNull
    @Override
    public SettingsEditor<? extends RunConfiguration> getConfigurationEditor() {
        return new GnoTestSettingsEditor();
    }

    @Nullable
    @Override
    public RunProfileState getState(@NotNull Executor executor,
                                    @NotNull ExecutionEnvironment environment) {
        return new CommandLineState(environment) {
            @NotNull
            @Override
            protected ProcessHandler startProcess() throws ExecutionException {
                GeneralCommandLine commandLine = new GeneralCommandLine("gno", "test", "-v", getTestPath());
                commandLine.setWorkDirectory(environment.getProject().getBasePath());
                OSProcessHandler processHandler = ProcessHandlerFactory.getInstance()
                        .createColoredProcessHandler(commandLine);
                ProcessTerminatedListener.attach(processHandler);
                return processHandler;
            }
        };
    }
}
