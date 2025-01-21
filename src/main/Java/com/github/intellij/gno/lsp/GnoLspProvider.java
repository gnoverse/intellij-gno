package com.github.intellij.gno.lsp;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.platform.lsp.api.LspServerDescriptor;
import com.intellij.platform.lsp.api.LspServerSupportProvider;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GnoLspProvider implements LspServerSupportProvider {

    @Override
    public void fileOpened(@NotNull Project project, @NotNull VirtualFile file, @NotNull LspServerStarter serverStarter) {
        if ("gno".equalsIgnoreCase(file.getExtension())) {
            serverStarter.ensureServerStarted(new GnoLspServerDescriptor(project));
        }
    }

    private static class GnoLspServerDescriptor extends LspServerDescriptor {
        protected GnoLspServerDescriptor(@NotNull Project project) {
            super(project, "Gno");
        }


        public @NotNull String getExecutable() {
            return "/Users/theodub/go/bin/gnopls"; // Mets ici le bon chemin vers gnopls
        }


        public @NotNull List<String> getAdditionalOptions() {
            return List.of("serve");
        }


        public boolean isSupportedFile(@NotNull VirtualFile file) {
            return "gno".equalsIgnoreCase(file.getExtension());
        }
    }
}
