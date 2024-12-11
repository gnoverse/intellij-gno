package com.github.intellij.gno.project;

import com.github.intellij.gno.GoConstants;
import com.github.intellij.gno.GoLibrariesState;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StoragePathMacros;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleServiceManager;
import org.jetbrains.annotations.NotNull;

@State(
  name = GoConstants.GO_LIBRARIES_SERVICE_NAME,
  storages = @Storage(value = StoragePathMacros.MODULE_FILE)
)
public class GoModuleLibrariesService extends GoLibrariesService<GoLibrariesState> {
  public static GoModuleLibrariesService getInstance(@NotNull Module module) {
    return ModuleServiceManager.getService(module, GoModuleLibrariesService.class);
  }
}
