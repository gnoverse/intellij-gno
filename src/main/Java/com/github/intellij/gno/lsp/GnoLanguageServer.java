package com.github.intellij.gno.lsp;

import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.openapi.diagnostic.Logger;
import com.redhat.devtools.lsp4ij.server.OSProcessStreamConnectionProvider;
import java.io.IOException;
import java.nio.file.*;

public class GnoLanguageServer extends OSProcessStreamConnectionProvider {

    private static final Logger LOG = Logger.getInstance(GnoLanguageServer.class);
    private static final String GNOPLS_BINARY = "gnopls";
    private static final String GO_BIN_DIR = System.getProperty("user.home") + "/go/bin";
    private static final String GNOPLS_PATH = GO_BIN_DIR + "/gnopls";

    public GnoLanguageServer() {
        String gnoplsPath = findOrInstallGnopls();
        LOG.info("Using gnopls at: " + gnoplsPath);

        GeneralCommandLine commandLine = new GeneralCommandLine(gnoplsPath);
        commandLine.setWorkDirectory(System.getProperty("user.dir"));
        super.setCommandLine(commandLine);
    }

    private String findOrInstallGnopls() {
        Path gnoplsPath = Paths.get(GNOPLS_PATH);

        if (Files.exists(gnoplsPath) && Files.isExecutable(gnoplsPath)) {
            return gnoplsPath.toString();
        }

        return installGnopls();
    }


    private String installGnopls() {
        String goPath = findGoBinary();
        if (goPath == null) {
            LOG.error("Go binary not found. Cannot install gnopls.");
            return GNOPLS_BINARY;
        }

        LOG.info("Installing gnopls using: " + goPath + " install github.com/gnoverse/gnopls@latest");

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(goPath, "install", "github.com/gnoverse/gnopls@latest");
            processBuilder.environment().put("GOBIN", GO_BIN_DIR);
            processBuilder.environment().put("PATH", System.getenv("PATH") + ":" + GO_BIN_DIR);
            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();
            int exitCode = process.waitFor();

            if (exitCode == 0 && Files.exists(Paths.get(GNOPLS_PATH))) {
                LOG.info("gnopls successfully installed in: " + GNOPLS_PATH);
                return GNOPLS_PATH;
            } else {
                LOG.error("Failed to install gnopls. Exit code: " + exitCode);
                return GNOPLS_BINARY; // Fallback
            }
        } catch (IOException | InterruptedException e) {
            LOG.error("Error during gnopls installation", e);
            Thread.currentThread().interrupt();
            return GNOPLS_BINARY;
        }
    }

    private String findGoBinary() {
        String[] possiblePaths = {"/usr/local/go/bin/go", "/usr/bin/go", "/usr/local/bin/go", "/opt/homebrew/bin/go"};

        try {
            Process process = new ProcessBuilder("which", "go").start();
            String output = new String(process.getInputStream().readAllBytes()).trim();
            if (!output.isEmpty()) {
                return output;
            }
        } catch (IOException e) {
            LOG.warn("Error checking 'which go'", e);
        }

        for (String path : possiblePaths) {
            Path pathObj = Paths.get(path);

            if (Files.exists(pathObj) && Files.isExecutable(pathObj)) {
                return path;
            }
        }


        return null; // Go non trouvé
    }
}
