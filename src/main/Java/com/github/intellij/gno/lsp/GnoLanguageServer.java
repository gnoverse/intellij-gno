package com.github.intellij.gno.lsp;

import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.openapi.diagnostic.Logger;
import com.redhat.devtools.lsp4ij.server.OSProcessStreamConnectionProvider;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GnoLanguageServer extends OSProcessStreamConnectionProvider {

    private static final Logger LOG = Logger.getInstance(GnoLanguageServer.class);
    private static final String GNOPLS_BINARY = "gnopls";

    public GnoLanguageServer() {
        String gnoplsPath = findOrInstallGnopls();
        GeneralCommandLine commandLine = new GeneralCommandLine(gnoplsPath);
        super.setCommandLine(commandLine);
    }

    private String findOrInstallGnopls() {
        if (isCommandAvailable(GNOPLS_BINARY)) {
            LOG.info("gnopls is found in the path");
            return GNOPLS_BINARY;
        }

        String userHome = System.getProperty("user.home");
        String installDir = userHome + "/go/bin";
        String gnoplsPath = installDir + "/gnopls";

        if (new File(gnoplsPath).exists()) {
            LOG.info("gnopls found in ~/go/bin.");
            return gnoplsPath;
        }

        installGnopls(installDir);

        return gnoplsPath;
    }

    private boolean isCommandAvailable(String command) {
        try {
            Process process = new ProcessBuilder(command, "version").start();
            process.waitFor();
            return process.exitValue() == 0;
        } catch (IOException | InterruptedException e) {
            return false;
        }
    }

    private void installGnopls(String installDir) {
        LOG.info("gnopls not found. Installation in progress...");

        try {
            Files.createDirectories(Paths.get(installDir));

            ProcessBuilder processBuilder = new ProcessBuilder("go", "install", "github.com/gnolang/gnopls@latest");
            processBuilder.environment().put("GOBIN", installDir);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            process.waitFor();

            if (new File(installDir + "/gnopls").exists()) {
                LOG.info("gnopls successfully installed in " + installDir);
            } else {
                LOG.error("Failed to install gnopls");
            }
        } catch (IOException | InterruptedException e) {
            LOG.error("Error installing gnopls", e);
        }
    }
}
