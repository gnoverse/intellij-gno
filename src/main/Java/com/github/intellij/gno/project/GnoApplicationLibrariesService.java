package com.github.intellij.gno.project;

import com.github.intellij.gno.psi.GnoConstants;
import com.github.intellij.gno.psi.GnoLibrariesState;
import com.github.intellij.gno.sdk.GnoSdkUtil;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import org.jetbrains.annotations.NotNull;

@State(
        name = GnoConstants.GNO_LIBRARIES_SERVICE_NAME
)
public class GnoApplicationLibrariesService extends GnoLibrariesService<GnoApplicationLibrariesService.GnoApplicationLibrariesState> {
    @NotNull
    @Override
    protected GnoApplicationLibrariesState createState() {
        return new GnoApplicationLibrariesState();
    }

    public static GnoApplicationLibrariesService getInstance() {
        return ServiceManager.getService(GnoApplicationLibrariesService.class);
    }

    public boolean isUseGnoPathFromSystemEnvironment() {
        return myState.isUseGnoPathFromSystemEnvironment();
    }

    public void setUseGnoPathFromSystemEnvironment(boolean useGnoPathFromSystemEnvironment) {
        if (myState.isUseGnoPathFromSystemEnvironment() != useGnoPathFromSystemEnvironment) {
            myState.setUseGnoPathFromSystemEnvironment(useGnoPathFromSystemEnvironment);
            if (!GnoSdkUtil.getGnoPathsRootsFromEnvironment().isEmpty()) {
                incModificationCount();
                ApplicationManager.getApplication().getMessageBus().syncPublisher(LIBRARIES_TOPIC).librariesChanged(getLibraryRootUrls());
            }
        }
    }

    public static class GnoApplicationLibrariesState extends GnoLibrariesState {
        private boolean myUseGnoPathFromSystemEnvironment = true;

        public boolean isUseGnoPathFromSystemEnvironment() {
            return myUseGnoPathFromSystemEnvironment;
        }

        public void setUseGnoPathFromSystemEnvironment(boolean useGnoPathFromSystemEnvironment) {
            myUseGnoPathFromSystemEnvironment = useGnoPathFromSystemEnvironment;
        }
    }
}
