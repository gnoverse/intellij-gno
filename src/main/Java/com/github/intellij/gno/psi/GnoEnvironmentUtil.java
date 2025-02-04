package com.github.intellij.gno.psi;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.PathMacros;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.util.EnvironmentUtil;
import com.intellij.util.PathUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GnoEnvironmentUtil {
    private GnoEnvironmentUtil() {}

    @NotNull
    public static String getBinaryFileNameForPath(@NotNull String path) {
        String resultBinaryName = FileUtil.getNameWithoutExtension(PathUtil.getFileName(path));
        return SystemInfo.isWindows ? resultBinaryName + ".exe" : resultBinaryName;
    }

    @NotNull
    public static String getGaeExecutableFileName(boolean gcloudInstallation) {
        if (SystemInfo.isWindows) {
            return gcloudInstallation ? GnoConstants.GAE_CMD_EXECUTABLE_NAME : GnoConstants.GAE_BAT_EXECUTABLE_NAME;
        }
        return GnoConstants.GAE_EXECUTABLE_NAME;
    }

    @Nullable
    public static String retrieveGnoPathFromEnvironment() {
        if (ApplicationManager.getApplication().isUnitTestMode()) return null;

        String path = EnvironmentUtil.getValue(GnoConstants.GNO_PATH);
        return path != null ? path : PathMacros.getInstance().getValue(GnoConstants.GNO_PATH);
    }
}
