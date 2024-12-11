package com.github.intellij.gno.project;

import com.github.intellij.gno.GoConstants;
import com.github.intellij.gno.GoLibrariesState;
import com.intellij.openapi.components.*;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

@State(
  name = GoConstants.GO_LIBRARIES_SERVICE_NAME,
  storages = {
          @Storage("projectFile"),
          @Storage(value = "directoryBasedConfiguration/" + GoConstants.GO_LIBRARIES_CONFIG_FILE)
  }
)
public class GoProjectLibrariesService extends GoLibrariesService<GoLibrariesState> {
  public static GoProjectLibrariesService getInstance(@NotNull Project project) {
    return ServiceManager.getService(project, GoProjectLibrariesService.class);
  }
}
