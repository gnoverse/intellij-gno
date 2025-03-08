package com.github.intellij.gno.runConfiguration;

import com.intellij.execution.configurations.ConfigurationTypeBase;
import com.intellij.openapi.util.NotNullLazyValue;
import com.intellij.icons.AllIcons;

public final class GnoTestConfigurationType extends ConfigurationTypeBase {

    public static final String ID = "GnoTestRunConfiguration";

    public GnoTestConfigurationType() {
        super(ID, "Gno Test", "Run configuration for Gno tests",
                NotNullLazyValue.createValue(() -> AllIcons.Nodes.Console));
        addFactory(new GnoTestConfigurationFactory(this));
    }
}
