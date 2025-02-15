package com.github.intellij.gno.configuration;

import com.github.intellij.gno.sdk.GnoSdkService;
import com.intellij.application.options.ModuleAwareProjectConfigurable;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public abstract class GnoModuleAwareConfigurable extends ModuleAwareProjectConfigurable {
    public GnoModuleAwareConfigurable(@NotNull Project project, String displayName, String helpTopic) {
        super(project, displayName, helpTopic);
    }

    @Override
    protected boolean isSuitableForModule(@NotNull Module module) {
        if (module.isDisposed()) {
            return false;
        }
        Project project = module.getProject();
        return !project.isDisposed() && GnoSdkService.getInstance(project).isGnoModule(module);
    }
}
