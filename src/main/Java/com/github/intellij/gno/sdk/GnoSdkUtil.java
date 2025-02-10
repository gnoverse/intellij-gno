package com.github.intellij.gno.sdk;

import com.github.intellij.gno.psi.GnoConstants;
import com.github.intellij.gno.psi.GnoEnvironmentUtil;
import com.github.intellij.gno.appengine.YamlFilesModificationTracker;
import com.github.intellij.gno.project.GnoApplicationLibrariesService;
import com.github.intellij.gno.project.GnoLibrariesService;
import com.github.intellij.gno.psi.GnoFile;
import com.intellij.execution.configurations.PathEnvironmentVariableUtil;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.util.UserDataHolder;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.*;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.CachedValueProvider;
import com.intellij.psi.util.CachedValuesManager;
import com.intellij.util.Function;
import com.intellij.util.ObjectUtils;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.text.VersionComparatorUtil;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.intellij.util.containers.ContainerUtil.newLinkedHashSet;

public class GnoSdkUtil {
    private static final Pattern GNO_VERSION_PATTERN = Pattern.compile("[tT]heVersion\\s*=\\s*`go([\\d.]+\\w+(\\d+)?)`");
    private static final Pattern GAE_VERSION_PATTERN = Pattern.compile("[tT]heVersion\\s*=\\s*`go([\\d.]+)( \\(appengine-[\\d.]+\\))?`");
    private static final Pattern GNO_DEVEL_VERSION_PATTERN = Pattern.compile("[tT]heVersion\\s*=\\s*`(devel.*)`");
    private static final Key<String> ZVERSION_DATA_KEY = Key.create("GNO_ZVERSION_KEY");

    private GnoSdkUtil() {}

    @Nullable
    public static VirtualFile getSdkSrcDir(@NotNull Project project, @Nullable Module module) {
        if (module != null) {
            return CachedValuesManager.getManager(project).getCachedValue(module, () -> {
                GnoSdkService sdkService = GnoSdkService.getInstance(module.getProject());
                return CachedValueProvider.Result.create(getInnerSdkSrcDir(sdkService, module), sdkService);
            });
        }
        return CachedValuesManager.getManager(project).getCachedValue(project, () -> {
            GnoSdkService sdkService = GnoSdkService.getInstance(project);
            return CachedValueProvider.Result.create(getInnerSdkSrcDir(sdkService, null), sdkService);
        });
    }

    @Nullable
    private static VirtualFile getInnerSdkSrcDir(@Nullable GnoSdkService sdkService, @Nullable Module module) {
        if (sdkService == null) {
            return null;
        }
        String sdkHomePath = sdkService.getSdkHomePath(module);
        String sdkVersionString = sdkService.getSdkVersion(module);
        return sdkHomePath != null && sdkVersionString != null ? getSdkSrcDir(sdkHomePath, sdkVersionString) : null;
    }


    @Nullable
    private static VirtualFile getSdkSrcDir(@NotNull String sdkPath, @NotNull String sdkVersion) {
        String srcPath = getSrcLocation(sdkVersion);
        VirtualFile file = VirtualFileManager.getInstance().findFileByUrl(VfsUtilCore.pathToUrl(FileUtil.join(sdkPath, srcPath)));
        return file != null && file.isDirectory() ? file : null;
    }

    @Nullable
    public static GnoFile findBuiltinFile(@NotNull PsiElement context) {
        Project project = context.getProject();
        // it's important to ask module on file, otherwise module won't be found for elements in libraries files [zolotov]
        Module moduleFromContext = ModuleUtilCore.findModuleForPsiElement(context.getContainingFile());
        if (moduleFromContext == null) {
            for (Module module : ModuleManager.getInstance(project).getModules()) {
                if (GnoSdkService.getInstance(project).isGnoModule(module)) {
                    moduleFromContext = module;
                    break;
                }
            }
        }

        Module module = moduleFromContext;
        UserDataHolder holder = ObjectUtils.notNull(module, project);
        VirtualFile file = CachedValuesManager.getManager(context.getProject()).getCachedValue(holder, () -> {
            VirtualFile sdkSrcDir = getSdkSrcDir(project, module);
            VirtualFile result = sdkSrcDir != null ? sdkSrcDir.findFileByRelativePath(GnoConstants.BUILTIN_FILE_PATH) : null;
            return CachedValueProvider.Result.create(result, getSdkAndLibrariesCacheDependencies(project, module, result));
        });

        if (file == null) return null;
        PsiFile psiBuiltin = context.getManager().findFile(file);
        return psiBuiltin instanceof GnoFile ? (GnoFile)psiBuiltin : null;
    }

    @Nullable
    public static VirtualFile findExecutableInGnoPath(@NotNull String executableName, @NotNull Project project, @Nullable Module module) {
        executableName = GnoEnvironmentUtil.getBinaryFileNameForPath(executableName);
        Collection<VirtualFile> roots = getGnoPathRoots(project, module);
        for (VirtualFile file : roots) {
            VirtualFile child = VfsUtil.findRelativeFile(file, "bin", executableName);
            if (child != null) return child;
        }
        File fromPath = PathEnvironmentVariableUtil.findInPath(executableName);
        return fromPath != null ? VfsUtil.findFileByIoFile(fromPath, true) : null;
    }

    /**
     * @return concatenation of {@link this#getSdkSrcDir(Project, Module)} and {@link this#getGnoPathSources(Project, Module)}
     */
    @NotNull
    public static LinkedHashSet<VirtualFile> getSourcesPathsToLookup(@NotNull Project project, @Nullable Module module) {
        LinkedHashSet<VirtualFile> sdkAndGnoPath = newLinkedHashSet();
        ContainerUtil.addIfNotNull(sdkAndGnoPath, getSdkSrcDir(project, module));
        ContainerUtil.addAllNotNull(sdkAndGnoPath, getGnoPathSources(project, module));
        return sdkAndGnoPath;
    }

    @NotNull
    public static LinkedHashSet<VirtualFile> getVendoringAwareSourcesPathsToLookup(@NotNull Project project,
                                                                                   @Nullable Module module,
                                                                                   @Nullable VirtualFile contextFile) {
        LinkedHashSet<VirtualFile> sdkAndGnoPath = getSourcesPathsToLookup(project, module);
        if (contextFile != null) {
            Collection<VirtualFile> vendorDirectories = collectVendorDirectories(contextFile, sdkAndGnoPath);
            if (!vendorDirectories.isEmpty()) {
                LinkedHashSet<VirtualFile> result = newLinkedHashSet(vendorDirectories);
                result.addAll(sdkAndGnoPath);
                return result;
            }
        }
        return sdkAndGnoPath;
    }

    @NotNull
    private static Collection<VirtualFile> collectVendorDirectories(@Nullable VirtualFile contextDirectory,
                                                                    @NotNull Set<VirtualFile> sourceRoots) {
        if (contextDirectory == null) {
            return Collections.emptyList();
        }
        Collection<VirtualFile> vendorDirectories = ContainerUtil.newArrayList();
        VirtualFile directory = contextDirectory;
        while (directory != null) {
            VirtualFile vendorDirectory = directory.findChild(GnoConstants.VENDOR);
            if (vendorDirectory != null && vendorDirectory.isDirectory()) {
                vendorDirectories.add(vendorDirectory);
            }
            if (sourceRoots.contains(directory)) {
                break;
            }
            directory = directory.getParent();
        }
        return vendorDirectories;
    }

    @NotNull
    private static Collection<VirtualFile> getGnoPathRoots(@NotNull Project project, @Nullable Module module) {
        Collection<VirtualFile> roots = ContainerUtil.newArrayList();
        if (GnoApplicationLibrariesService.getInstance().isUseGnoPathFromSystemEnvironment()) {
            roots.addAll(getGnoPathsRootsFromEnvironment());
        }
        roots.addAll(module != null ? GnoLibrariesService.getUserDefinedLibraries(module) : GnoLibrariesService.getUserDefinedLibraries(project));
        return roots;
    }

    @NotNull
    public static Collection<VirtualFile> getGnoPathSources(@NotNull Project project, @Nullable Module module) {
        if (module != null) {
            return CachedValuesManager.getManager(project).getCachedValue(module, () -> {
                Collection<VirtualFile> result = newLinkedHashSet();
                Project project1 = module.getProject();
                GnoSdkService sdkService = GnoSdkService.getInstance(project1);
                if (sdkService.isAppEngineSdk(module)) {
                    ContainerUtil.addAllNotNull(result, ContainerUtil.mapNotNull(YamlFilesModificationTracker.getYamlFiles(project1, module),
                            VirtualFile::getParent));
                }
                result.addAll(getInnerGnoPathSources(project1, module));
                return CachedValueProvider.Result
                        .create(result, getSdkAndLibrariesCacheDependencies(project1, module, YamlFilesModificationTracker.getInstance(project1)));
            });
        }
        return CachedValuesManager.getManager(project).getCachedValue(project,
                (CachedValueProvider<Collection<VirtualFile>>)() -> CachedValueProvider.Result
                        .create(getInnerGnoPathSources(project, null),
                                getSdkAndLibrariesCacheDependencies(project, null, YamlFilesModificationTracker.getInstance(project))));
    }

    @NotNull
    private static List<VirtualFile> getInnerGnoPathSources(@NotNull Project project, @Nullable Module module) {
        return ContainerUtil.mapNotNull(getGnoPathRoots(project, module), new RetrieveSubDirectoryOrSelfFunction("src"));
    }

    @NotNull
    private static Collection<VirtualFile> getGnoPathBins(@NotNull Project project, @Nullable Module module) {
        Collection<VirtualFile> result = newLinkedHashSet(ContainerUtil.mapNotNull(getGnoPathRoots(project, module),
                new RetrieveSubDirectoryOrSelfFunction("bin")));
        String executableGnoPath = GnoSdkService.getInstance(project).getGnoExecutablePath(module);
        if (executableGnoPath != null) {
            VirtualFile executable = VirtualFileManager.getInstance().findFileByUrl(VfsUtilCore.pathToUrl(executableGnoPath));
            if (executable != null) ContainerUtil.addIfNotNull(result, executable.getParent());
        }
        return result;
    }

    /**
     * Retrieves root directories from GNOPATH env-variable.
     * This method doesn't consider user defined libraries,
     * for that case use {@link {@link this#getGnoPathRoots(Project, Module)}
     */
    @NotNull
    public static Collection<VirtualFile> getGnoPathsRootsFromEnvironment() {
        return GnoEnvironmentGoPathModificationTracker.getGnoEnvironmentGnoPathRoots();
    }

    @NotNull
    public static String retrieveGnoPath(@NotNull Project project, @Nullable Module module) {
        return StringUtil.join(ContainerUtil.map(getGnoPathRoots(project, module), VirtualFile::getPath), File.pathSeparator);
    }

    @NotNull
    public static String retrieveEnvironmentPathForGno(@NotNull Project project, @Nullable Module module) {
        return StringUtil.join(ContainerUtil.map(getGnoPathBins(project, module), VirtualFile::getPath), File.pathSeparator);
    }

    @NotNull
    private static String getSrcLocation(@NotNull String version) {
        if (ApplicationManager.getApplication().isUnitTestMode()) {
            return "src/pkg";
        }
        if (version.startsWith("devel")) {
            return "src";
        }
        if (version.length() > 2 && StringUtil.parseDouble(version.substring(0, 3), 1.4) < 1.4) {
            return "src/pkg";
        }
        return "src";
    }

    public static int compareVersions(@NotNull String lhs, @NotNull String rhs) {
        return VersionComparatorUtil.compare(lhs, rhs);
    }

    @Nullable
    @Contract("null, _ -> null")
    public static String getImportPath(@Nullable PsiDirectory psiDirectory, boolean withVendoring) {
        if (psiDirectory == null) {
            return null;
        }
        return CachedValuesManager.getCachedValue(psiDirectory, withVendoring ? new CachedVendoredImportPathProvider(psiDirectory)
                : new CachedImportPathProviderImpl(psiDirectory));
    }

    @Nullable
    private static String getPathRelativeToSdkAndLibrariesAndVendor(@NotNull PsiDirectory psiDirectory, boolean withVendoring) {
        VirtualFile file = psiDirectory.getVirtualFile();
        Project project = psiDirectory.getProject();
        Module module = ModuleUtilCore.findModuleForPsiElement(psiDirectory);
        LinkedHashSet<VirtualFile> sourceRoots = withVendoring ? getVendoringAwareSourcesPathsToLookup(project, module, file)
                : getSourcesPathsToLookup(project, module);

        String relativePath = getRelativePathToRoots(file, sourceRoots);
        if (relativePath != null) {
            return relativePath;
        }

        String filePath = file.getPath();
        int src = filePath.lastIndexOf("/src/");
        if (src > -1) {
            return filePath.substring(src + 5);
        }
        return null;
    }

    @Nullable
    public static String getRelativePathToRoots(@NotNull VirtualFile file, @NotNull Collection<VirtualFile> sourceRoots) {
        for (VirtualFile root : sourceRoots) {
            String relativePath = VfsUtilCore.getRelativePath(file, root, '/');
            if (StringUtil.isNotEmpty(relativePath)) {
                return relativePath;
            }
        }
        return null;
    }

    @Nullable
    public static VirtualFile suggestSdkDirectory() {
        if (SystemInfo.isWindows) {
            return ObjectUtils.chooseNotNull(LocalFileSystem.getInstance().findFileByPath("C:\\Gno"),
                    LocalFileSystem.getInstance().findFileByPath("C:\\cygwin"));
        }
        if (SystemInfo.isMac || SystemInfo.isLinux) {
            String fromEnv = suggestSdkDirectoryPathFromEnv();
            if (fromEnv != null) {
                return LocalFileSystem.getInstance().findFileByPath(fromEnv);
            }
            VirtualFile usrLocal = LocalFileSystem.getInstance().findFileByPath("/usr/local/go");
            if (usrLocal != null) return usrLocal;
        }
        if (SystemInfo.isMac) {
            String macPorts = "/opt/local/lib/go";
            String homeBrew = "/usr/local/Cellar/go";
            File file = FileUtil.findFirstThatExist(macPorts, homeBrew);
            if (file != null) {
                return LocalFileSystem.getInstance().findFileByIoFile(file);
            }
        }
        return null;
    }

    @Nullable
    private static String suggestSdkDirectoryPathFromEnv() {
        File fileFromPath = PathEnvironmentVariableUtil.findInPath("go");
        if (fileFromPath != null) {
            File canonicalFile;
            try {
                canonicalFile = fileFromPath.getCanonicalFile();
                String path = canonicalFile.getPath();
                if (path.endsWith("bin/go")) {
                    return StringUtil.trimEnd(path, "bin/go");
                }
            }
            catch (IOException ignore) {
            }
        }
        return null;
    }

    @Nullable
    public static String parseGnoVersion(@NotNull String text) {
        Matcher matcher = GNO_VERSION_PATTERN.matcher(text);
        if (matcher.find()) {
            return matcher.group(1);
        }
        matcher = GAE_VERSION_PATTERN.matcher(text);
        if (matcher.find()) {
            return matcher.group(1) + matcher.group(2);
        }
        matcher = GNO_DEVEL_VERSION_PATTERN.matcher(text);
        if (matcher.find()) {
            return matcher.group(1);
        }

        return null;
    }

    @Nullable
    public static String retrieveGnoVersion(@NotNull String sdkPath) {
        try {
            VirtualFile sdkRoot = VirtualFileManager.getInstance().findFileByUrl(VfsUtilCore.pathToUrl(sdkPath));
            if (sdkRoot != null) {
                String cachedVersion = sdkRoot.getUserData(ZVERSION_DATA_KEY);
                if (cachedVersion != null) {
                    return !cachedVersion.isEmpty() ? cachedVersion : null;
                }

                VirtualFile versionFile = sdkRoot.findFileByRelativePath("src/" + GnoConstants.GNO_VERSION_NEW_FILE_PATH);
                if (versionFile == null) {
                    versionFile = sdkRoot.findFileByRelativePath("src/" + GnoConstants.GNO_VERSION_FILE_PATH);
                }
                if (versionFile == null) {
                    versionFile = sdkRoot.findFileByRelativePath("src/pkg/" + GnoConstants.GNO_VERSION_FILE_PATH);
                }
                if (versionFile != null) {
                    String text = VfsUtilCore.loadText(versionFile);
                    String version = parseGnoVersion(text);
                    if (version == null) {
                        GnoSdkService.LOG.debug("Cannot retrieve go version from zVersion file: " + text);
                    }
                    sdkRoot.putUserData(ZVERSION_DATA_KEY, StringUtil.notNullize(version));
                    return version;
                }
                else {
                    GnoSdkService.LOG.debug("Cannot find go version file in sdk path: " + sdkPath);
                }
            }
        }
        catch (IOException e) {
            GnoSdkService.LOG.debug("Cannot retrieve go version from sdk path: " + sdkPath, e);
        }
        return null;
    }

    @NotNull
    public static String adjustSdkPath(@NotNull String path) {
        if (new File(path, GnoConstants.LIB_EXEC_DIRECTORY).exists()) {
            path += File.separatorChar + GnoConstants.LIB_EXEC_DIRECTORY;
        }

        File possibleGCloudSdk = new File(path, GnoConstants.GCLOUD_APP_ENGINE_DIRECTORY_PATH);
        if (possibleGCloudSdk.exists() && possibleGCloudSdk.isDirectory()) {
            if (isAppEngine(possibleGCloudSdk.getAbsolutePath())) {
                return possibleGCloudSdk.getAbsolutePath() + GnoConstants.APP_ENGINE_GNO_ROOT_DIRECTORY_PATH;
            }
        }

        return isAppEngine(path) ? path + GnoConstants.APP_ENGINE_GNO_ROOT_DIRECTORY_PATH : path;
    }


    private static boolean isAppEngine(@NotNull String path) {
        return new File(path, GnoConstants.APP_ENGINE_MARKER_FILE).exists();
    }

    @NotNull
    public static Collection<VirtualFile> getSdkDirectoriesToAttach(@NotNull String sdkPath, @NotNull String versionString) {
        // scr is enough at the moment, possible process binaries from pkg
        return ContainerUtil.createMaybeSingletonList(getSdkSrcDir(sdkPath, versionString));
    }

    @NotNull
    private static Collection<Object> getSdkAndLibrariesCacheDependencies(@NotNull Project project,
                                                                          @Nullable Module module,
                                                                          Object... extra) {
        Collection<Object> dependencies = ContainerUtil.newArrayList((Object[])GnoLibrariesService.getModificationTrackers(project, module));
        ContainerUtil.addAllNotNull(dependencies, GnoSdkService.getInstance(project));
        ContainerUtil.addAllNotNull(dependencies, extra);
        return dependencies;
    }

    @NotNull
    public static Collection<Module> getGnoModules(@NotNull Project project) {
        if (project.isDefault()) return Collections.emptyList();
        GnoSdkService sdkService = GnoSdkService.getInstance(project);
        return ContainerUtil.filter(ModuleManager.getInstance(project).getModules(), sdkService::isGnoModule);
    }

    public static boolean isUnreachableInternalPackage(@NotNull VirtualFile targetDirectory,
                                                       @NotNull VirtualFile referenceContextFile,
                                                       @NotNull Set<VirtualFile> sourceRoots) {
        return isUnreachablePackage(GnoConstants.INTERNAL, targetDirectory, referenceContextFile, sourceRoots);
    }

    public static boolean isUnreachableVendoredPackage(@NotNull VirtualFile targetDirectory,
                                                       @NotNull VirtualFile referenceContextFile,
                                                       @NotNull Set<VirtualFile> sourceRoots) {
        return isUnreachablePackage(GnoConstants.VENDOR, targetDirectory, referenceContextFile, sourceRoots);
    }

    @Nullable
    public static VirtualFile findParentDirectory(@Nullable VirtualFile file, @NotNull Set<VirtualFile> sourceRoots, @NotNull String name) {
        if (file == null) {
            return null;
        }
        VirtualFile currentFile = file.isDirectory() ? file : file.getParent();
        while (currentFile != null && !sourceRoots.contains(currentFile)) {
            if (currentFile.isDirectory() && name.equals(currentFile.getName())) {
                return currentFile;
            }
            currentFile = currentFile.getParent();
        }
        return null;
    }

    private static boolean isUnreachablePackage(@NotNull String unreachableDirectoryName,
                                                @NotNull VirtualFile targetDirectory,
                                                @NotNull VirtualFile referenceContextFile,
                                                @NotNull Set<VirtualFile> sourceRoots) {
        VirtualFile directory = findParentDirectory(targetDirectory, sourceRoots, unreachableDirectoryName);
        VirtualFile parent = directory != null ? directory.getParent() : null;
        return directory != null && !VfsUtilCore.isAncestor(parent, referenceContextFile, false);
    }

    private static class RetrieveSubDirectoryOrSelfFunction implements Function<VirtualFile, VirtualFile> {
        @NotNull private final String mySubdirName;

        public RetrieveSubDirectoryOrSelfFunction(@NotNull String subdirName) {
            mySubdirName = subdirName;
        }

        @Override
        public VirtualFile fun(VirtualFile file) {
            return file == null || FileUtil.namesEqual(mySubdirName, file.getName()) ? file : file.findChild(mySubdirName);
        }
    }

    private abstract static class CachedImportPathProvider implements CachedValueProvider<String> {
        private final PsiDirectory myPsiDirectory;
        private final boolean myWithVendoring;

        public CachedImportPathProvider(@NotNull PsiDirectory psiDirectory, boolean vendoring) {
            myPsiDirectory = psiDirectory;
            myWithVendoring = vendoring;
        }

        @Nullable
        @Override
        public Result<String> compute() {
            String path = getPathRelativeToSdkAndLibrariesAndVendor(myPsiDirectory, myWithVendoring);
            Module module = ModuleUtilCore.findModuleForPsiElement(myPsiDirectory);
            return Result.create(path, getSdkAndLibrariesCacheDependencies(myPsiDirectory.getProject(), module, myPsiDirectory));
        }
    }

    private static class CachedImportPathProviderImpl extends CachedImportPathProvider {
        public CachedImportPathProviderImpl(@NotNull PsiDirectory psiDirectory) {
            super(psiDirectory, false);
        }
    }

    private static class CachedVendoredImportPathProvider extends CachedImportPathProvider {
        public CachedVendoredImportPathProvider(@NotNull PsiDirectory psiDirectory) {
            super(psiDirectory, true);
        }
    }
}
