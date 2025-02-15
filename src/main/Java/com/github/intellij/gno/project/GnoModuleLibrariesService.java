package com.github.intellij.gno.project;

import com.github.intellij.gno.psi.GnoConstants;
import com.github.intellij.gno.psi.GnoLibrariesState;
import com.intellij.openapi.components.State;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleServiceManager;
import org.jetbrains.annotations.NotNull;

@State(
        name = GnoConstants.GNO_LIBRARIES_SERVICE_NAME
)
public class GnoModuleLibrariesService extends GnoLibrariesService<GnoLibrariesState> {
    public static GnoModuleLibrariesService getInstance(@NotNull Module module) {
        return ModuleServiceManager.getService(module, GnoModuleLibrariesService.class);
    }
}
