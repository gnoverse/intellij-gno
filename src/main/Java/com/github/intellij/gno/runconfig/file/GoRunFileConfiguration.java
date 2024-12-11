package com.github.intellij.gno.runconfig.file;

import com.github.intellij.gno.runconfig.GoModuleBasedConfiguration;
import com.github.intellij.gno.runconfig.GoRunConfigurationWithMain;
import com.github.intellij.gno.runconfig.GoRunningState;
import com.github.intellij.gno.runconfig.ui.GoRunFileConfigurationEditorForm;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.ModuleBasedConfiguration;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.configurations.RuntimeConfigurationException;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.ide.scratch.ScratchUtil;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.PathUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.UUID;

public class GoRunFileConfiguration extends GoRunConfigurationWithMain<GoRunFileRunningState> {
  public GoRunFileConfiguration(Project project, String name, @NotNull ConfigurationType configurationType) {
    super(name, new GoModuleBasedConfiguration<GoRunningState>(project), configurationType.getConfigurationFactories()[0]);
  }

  @NotNull
  @Override
  protected ModuleBasedConfiguration createInstance() {
    return new GoRunFileConfiguration(getProject(), getName(), GoRunFileConfigurationType.getInstance());
  }

  @NotNull
  @Override
  public SettingsEditor<? extends RunConfiguration> getConfigurationEditor() {
    return new GoRunFileConfigurationEditorForm(getProject());
  }

  @Override
  public void checkConfiguration() throws RuntimeConfigurationException {
    super.checkBaseConfiguration();
    super.checkFileConfiguration();
  }

  @NotNull
  @Override
  protected GoRunFileRunningState newRunningState(@NotNull ExecutionEnvironment env, @NotNull Module module) {
    String path = getFilePath();
    if (!"go".equals(PathUtil.getFileExtension(path))) {
      VirtualFile f = LocalFileSystem.getInstance().refreshAndFindFileByPath(path);
      if (ScratchUtil.isScratch(f)) {
        String suffixWithoutExt = "." + UUID.randomUUID().toString().substring(0, 4);
        String suffix = suffixWithoutExt + ".go";
        String before = f.getName();
        String beforeWithoutExt = FileUtil.getNameWithoutExtension(before);
        ApplicationManager.getApplication().runWriteAction(new Runnable() {
          @Override
          public void run() {
            try {
              f.rename(this, before + suffix);
            }
            catch (IOException ignored) {
            }
          }
        });
        setFilePath(path + suffix);
        setName(getName().replace(beforeWithoutExt, beforeWithoutExt + suffixWithoutExt));
      }
    }
    return new GoRunFileRunningState(env, module, this);
  }
}
