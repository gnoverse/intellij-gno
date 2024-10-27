package com.github.intellij.gno.language.sdk;

import com.github.intellij.gno.language.GnoConstants;
import com.goide.configuration.GnoSdkConfigurable;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ComponentManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.OrderRootType;
import com.intellij.openapi.roots.libraries.Library;
import com.intellij.openapi.roots.libraries.LibraryTable;
import com.intellij.openapi.roots.libraries.LibraryTablesRegistrar;
import com.intellij.openapi.util.Computable;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.util.CachedValueProvider;
import com.intellij.psi.util.CachedValuesManager;
import com.intellij.util.ObjectUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GnoSmallIDEsSdkService extends GnoSdkService {
    public static final String LIBRARY_NAME = "Gno SDK";

    public GnoSmallIDEsSdkService(@NotNull Project project) {
        super(project);
    }

    @Nullable
    @Override
    public String getSdkHomePath(@Nullable Module module) {
        ComponentManager holder = ObjectUtils.notNull(module, myProject);
        return CachedValuesManager.getManager(myProject).getCachedValue(holder, () -> CachedValueProvider.Result
                .create(ApplicationManager.getApplication().runReadAction(new Computable<String>() {
                    @Nullable
                    @Override
                    public String compute() {
                        LibraryTable table = LibraryTablesRegistrar.getInstance().getLibraryTable(myProject);
                        for (Library library : table.getLibraries()) {
                            String libraryName = library.getName();
                            if (libraryName != null && libraryName.startsWith(LIBRARY_NAME)) {
                                for (VirtualFile root : library.getFiles(OrderRootType.CLASSES)) {
                                    if (isGnoSdkLibRoot(root)) {
                                        return libraryRootToSdkPath(root);
                                    }
                                }
                            }
                        }
                        return null;
                    }
                }), this));
    }

    @Nullable
    @Override
    public String getSdkVersion(@Nullable Module module) {
        String parentVersion = super.getSdkVersion(module);
        if (parentVersion != null) {
            return parentVersion;
        }

        ComponentManager holder = ObjectUtils.notNull(module, myProject);
        return CachedValuesManager.getManager(myProject).getCachedValue(holder, () -> {
            String result = null;
            String sdkHomePath = getSdkHomePath(module);
            if (sdkHomePath != null) {
                result = GnoSdkUtil.retrieveGnoVersion(sdkHomePath);
            }
            return CachedValueProvider.Result.create(result, this);
        });
    }

    @Override
    public void chooseAndSetSdk(@Nullable Module module) {
        ShowSettingsUtil.getInstance().editConfigurable(myProject, new GnoSdkConfigurable(myProject, true));
    }

    @Nullable
    @Override
    public Configurable createSdkConfigurable() {
        return !myProject.isDefault() ? new GnoSdkConfigurable(myProject, false) : null;
    }

    @Override
    public boolean isGnoModule(@Nullable Module module) {
        return super.isGnoModule(module) && getSdkHomePath(module) != null;
    }

    public static boolean isGnoSdkLibRoot(@NotNull VirtualFile root) {
        return root.isInLocalFileSystem() &&
                root.isDirectory() &&
                (VfsUtilCore.findRelativeFile(GnoConstants.GNO_VERSION_FILE_PATH, root) != null ||
                        VfsUtilCore.findRelativeFile(GnoConstants.GNO_VERSION_NEW_FILE_PATH, root) != null
                );
    }
}