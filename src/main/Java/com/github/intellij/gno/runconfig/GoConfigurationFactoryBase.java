package com.github.intellij.gno.runconfig;
import com.intellij.execution.BeforeRunTask;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.openapi.util.Key;

public abstract class GoConfigurationFactoryBase extends ConfigurationFactory {
  protected GoConfigurationFactoryBase(ConfigurationType type) {
    super(type);
  }

  @Override
  public void configureBeforeRunTaskDefaults(Key<? extends BeforeRunTask> providerID, BeforeRunTask task) {
    super.configureBeforeRunTaskDefaults(providerID, task);
  }
}
