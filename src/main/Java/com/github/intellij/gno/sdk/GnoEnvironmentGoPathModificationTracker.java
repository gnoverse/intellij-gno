package com.github.intellij.gno.sdk;

import com.github.intellij.gno.psi.GnoEnvironmentUtil;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.*;
import com.intellij.util.SystemProperties;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Collection;
import java.util.Set;

public class GnoEnvironmentGoPathModificationTracker {
    private final Set<String> pathsToTrack = ContainerUtil.newHashSet();
    private final Collection<VirtualFile> goPathRoots = ContainerUtil.newLinkedHashSet();

    public GnoEnvironmentGoPathModificationTracker() {
        String goPath = GnoEnvironmentUtil.retrieveGnoPathFromEnvironment();
        if (goPath != null) {
            String home = SystemProperties.getUserHome();
            for (String s : StringUtil.split(goPath, File.pathSeparator)) {
                if (s.contains("$HOME")) {
                    if (home == null) {
                        continue;
                    }
                    s = s.replaceAll("\\$HOME", home);
                }
                pathsToTrack.add(s);
            }
        }
        recalculateFiles();

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

            private void handleEvent(VirtualFileEvent event) {
                if (pathsToTrack.contains(event.getFile().getPath())) {
                    recalculateFiles();
                }
            }
        });
    }

    private void recalculateFiles() {
        Collection<VirtualFile> result = ContainerUtil.newLinkedHashSet();
        for (String path : pathsToTrack) {
            ContainerUtil.addIfNotNull(result, LocalFileSystem.getInstance().findFileByPath(path));
        }
        updateGnoPathRoots(result);
    }

    private synchronized void updateGnoPathRoots(Collection<VirtualFile> newRoots) {
        goPathRoots.clear();
        goPathRoots.addAll(newRoots);
    }

    private synchronized Collection<VirtualFile> getGnoPathRoots() {
        return goPathRoots;
    }

    public static Collection<VirtualFile> getGnoEnvironmentGnoPathRoots() {
        return ServiceManager.getService(GnoEnvironmentGoPathModificationTracker.class).getGnoPathRoots();
    }
}
