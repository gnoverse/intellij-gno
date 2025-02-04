package com.github.intellij.gno.sdk;

import com.github.intellij.gno.psi.GnoConstants;
import com.github.intellij.gno.psi.GnoEnvironmentUtil;
import com.intellij.execution.configurations.PathEnvironmentVariableUtil;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.util.SimpleModificationTracker;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.PathUtil;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.TestOnly;

import java.io.File;
import java.util.Set;

public abstract class GnoSdkService extends SimpleModificationTracker {
    public static final Logger LOG = Logger.getInstance(GnoSdkService.class);
    private static final Set<String> FEDORA_SUBDIRECTORIES = ContainerUtil.newHashSet("linux_amd64", "linux_386", "linux_arm");
    private static String ourTestSdkVersion;

    @NotNull
    protected final Project myProject;

    protected GnoSdkService(@NotNull Project project) {
        myProject = project;
    }

    public static GnoSdkService getInstance(@NotNull Project project) {
        return ServiceManager.getService(project, GnoSdkService.class);
    }

    @Nullable
    public abstract String getSdkHomePath(@Nullable Module module);

    @NotNull
    public static String libraryRootToSdkPath(@NotNull VirtualFile root) {
        return VfsUtilCore.urlToPath(StringUtil.trimEnd(StringUtil.trimEnd(StringUtil.trimEnd(root.getUrl(), "src/pkg"), "src"), "/"));
    }

    @Nullable
    public String getSdkVersion(@Nullable Module module) {
        return ourTestSdkVersion;
    }

    public boolean isAppEngineSdk(@Nullable Module module) {
        return isAppEngineSdkPath(getSdkHomePath(module));
    }

    public static boolean isAppEngineSdkPath(@Nullable String path) {
        return isLooksLikeAppEngineSdkPath(path) && getGaeExecutablePath(path) != null;
    }

    private static boolean isLooksLikeAppEngineSdkPath(@Nullable String path) {
        return path != null && path.endsWith(GnoConstants.APP_ENGINE_GNO_ROOT_DIRECTORY_PATH);
    }

    public abstract void chooseAndSetSdk(@Nullable Module module);

    /**
     * Use this method in order to check whether the method is appropriate for providing Gno-specific code insight
     */
    @Contract("null -> false")
    public boolean isGnoModule(@Nullable Module module) {
        return module != null && !module.isDisposed();
    }

    @Nullable
    public Configurable createSdkConfigurable() {
        return null;
    }

    @Nullable
    public String getGnoExecutablePath(@Nullable Module module) {
        return getGnoExecutablePath(getSdkHomePath(module));
    }

    public static String getGnoExecutablePath(@Nullable String sdkHomePath) {
        if (sdkHomePath != null) {
            if (isLooksLikeAppEngineSdkPath(sdkHomePath)) {
                LOG.debug("Looks like GAE sdk at " + sdkHomePath);
                String executablePath = getGaeExecutablePath(sdkHomePath);
                if (executablePath != null) return executablePath;
            }

            File binDirectory = new File(sdkHomePath, "bin");
            if (!binDirectory.exists() && SystemInfo.isLinux) {
                LOG.debug(sdkHomePath + "/bin doesn't exist, checking linux-specific paths");
                // failed to define executable path in old linux and old go
                File goFromPath = PathEnvironmentVariableUtil.findInPath(GnoConstants.GNO_EXECUTABLE_NAME);
                if (goFromPath != null && goFromPath.exists()) {
                    LOG.debug("Gno executable found at " + goFromPath.getAbsolutePath());
                    return goFromPath.getAbsolutePath();
                }
            }

            String executableName = GnoEnvironmentUtil.getBinaryFileNameForPath(GnoConstants.GNO_EXECUTABLE_NAME);
            String executable = FileUtil.join(sdkHomePath, "bin", executableName);

            if (!new File(executable).exists() && SystemInfo.isLinux) {
                LOG.debug(executable + " doesn't exists. Looking for binaries in fedora-specific directories");
                // fedora
                for (String directory : FEDORA_SUBDIRECTORIES) {
                    File file = new File(binDirectory, directory);
                    if (file.exists() && file.isDirectory()) {
                        return FileUtil.join(file.getAbsolutePath(), executableName);
                    }
                }
            }
            LOG.debug("Gno executable found at " + executable);
            return executable;
        }
        return null;
    }

    @Nullable
    private static String getGaeExecutablePath(@NotNull String sdkHomePath) {
        String goExecutablePath = PathUtil.toSystemIndependentName(sdkHomePath);
        goExecutablePath = StringUtil.trimEnd(goExecutablePath, GnoConstants.APP_ENGINE_GNO_ROOT_DIRECTORY_PATH);

        boolean gcloudInstallation = goExecutablePath.endsWith(GnoConstants.GCLOUD_APP_ENGINE_DIRECTORY_PATH);
        if (gcloudInstallation) {
            LOG.debug("Detected gcloud GAE installation at " + goExecutablePath);
            goExecutablePath = FileUtil.join(StringUtil.trimEnd(goExecutablePath, GnoConstants.GCLOUD_APP_ENGINE_DIRECTORY_PATH), "bin");
        }
        String executablePath = FileUtil.join(goExecutablePath, GnoEnvironmentUtil.getGaeExecutableFileName(gcloudInstallation));
        return new File(executablePath).exists() ? executablePath : null;
    }

    @TestOnly
    public static void setTestingSdkVersion(@Nullable String version, @NotNull Disposable disposable) {
        ourTestSdkVersion = version;
        Disposer.register(disposable, () -> {
            //noinspection AssignmentToStaticFieldFromInstanceMethod
            ourTestSdkVersion = null;
        });
    }
}
