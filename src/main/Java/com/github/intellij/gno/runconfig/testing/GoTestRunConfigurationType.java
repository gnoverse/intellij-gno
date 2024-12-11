package com.github.intellij.gno.runconfig.testing;

import com.github.intellij.gno.GoIcons;
import com.intellij.execution.configurations.ConfigurationTypeBase;
import org.jetbrains.annotations.Nullable;

public class GoTestRunConfigurationType extends ConfigurationTypeBase {

  public GoTestRunConfigurationType() {
    super("GoTestRunConfiguration", "Go Test", "Go test run configuration", GoIcons.TEST_RUN);
  }

  @Nullable
  public static GoTestRunConfigurationType getInstance() {
    return CONFIGURATION_TYPE_EP.findExtension(GoTestRunConfigurationType.class);
  }
}
