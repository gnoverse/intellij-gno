package com.github.intellij.gno.runConfiguration;

import com.intellij.execution.configurations.RunConfigurationOptions;
import com.intellij.openapi.components.StoredProperty;

public class GnoTestRunConfigurationOptions extends RunConfigurationOptions {

    private final StoredProperty<String> testPath =
            string(".").provideDelegate(this, "testPath");

    public String getTestPath() {
        return testPath.getValue(this);
    }

    public void setTestPath(String path) {
        testPath.setValue(this, path);
    }
}
