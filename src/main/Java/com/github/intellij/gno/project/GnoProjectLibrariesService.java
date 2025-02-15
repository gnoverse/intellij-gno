package com.github.intellij.gno.project;

import com.github.intellij.gno.psi.GnoConstants;
import com.github.intellij.gno.psi.GnoLibrariesState;
import com.intellij.openapi.components.*;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

@State(
        name = GnoConstants.GNO_LIBRARIES_SERVICE_NAME
)
public class GnoProjectLibrariesService extends GnoLibrariesService<GnoLibrariesState> {
    public static GnoProjectLibrariesService getInstance(@NotNull Project project) {
        return ServiceManager.getService(project, GnoProjectLibrariesService.class);
    }
}
