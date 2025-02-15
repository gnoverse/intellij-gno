package com.github.intellij.gno.appengine;

import com.github.intellij.gno.util.GnoUtil;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Computable;
import com.intellij.openapi.util.SimpleModificationTracker;
import com.intellij.openapi.util.UserDataHolder;
import com.intellij.openapi.vfs.*;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.util.CachedValueProvider;
import com.intellij.psi.util.CachedValuesManager;
import com.intellij.util.ObjectUtils;
import com.intellij.util.PathUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class YamlFilesModificationTracker extends SimpleModificationTracker {
    public YamlFilesModificationTracker(@NotNull Project project) {
        VirtualFileManager.getInstance().addVirtualFileListener(new VirtualFileAdapter() {
            @Override
            public void fileCreated(@NotNull VirtualFileEvent event) {
                handleEvent(event);
            }

            @Override
            public void fileDeleted(@NotNull VirtualFileEvent event) {
                handleEvent(event);
            }

            @Override
            public void fileMoved(@NotNull VirtualFileMoveEvent event) {
                handleEvent(event);
            }

            @Override
            public void fileCopied(@NotNull VirtualFileCopyEvent event) {
                handleEvent(event);
            }

            private void handleEvent(@NotNull VirtualFileEvent event) {
                if ("yaml".equals(PathUtil.getFileExtension(event.getFileName()))) {
                    incModificationCount();
                }
            }
        }, project);
    }

    public static YamlFilesModificationTracker getInstance(@NotNull Project project) {
        return ServiceManager.getService(project, YamlFilesModificationTracker.class);
    }

    @NotNull
    public static Collection<VirtualFile> getYamlFiles(@NotNull Project project, @Nullable Module module) {
        UserDataHolder dataHolder = ObjectUtils.notNull(module, project);
        return CachedValuesManager.getManager(project).getCachedValue(dataHolder, () -> {
            Collection<VirtualFile> yamlFiles = ApplicationManager.getApplication().runReadAction(new Computable<Collection<VirtualFile>>() {
                @Override
                public Collection<VirtualFile> compute() {
                    return FilenameIndex.getAllFilesByExt(project, "yaml", GnoUtil.moduleScopeWithoutLibraries(project, module));
                }
            });
            return CachedValueProvider.Result.create(yamlFiles, getInstance(project));
        });
    }
}
