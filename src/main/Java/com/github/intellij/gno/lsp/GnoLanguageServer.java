package com.github.intellij.gno.lsp;

import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.redhat.devtools.lsp4ij.server.OSProcessStreamConnectionProvider;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class GnoLanguageServer extends OSProcessStreamConnectionProvider {

    private static final Logger LOG = Logger.getInstance(GnoLanguageServer.class);
    private static final String GNOPLS_BINARY = "gnopls";
    private static final String GO_BIN_DIR = System.getProperty("user.home") + "/go/bin";
    private static final String GNOPLS_PATH = GO_BIN_DIR + "/gnopls";

    public GnoLanguageServer() {
        String gnoplsPath = findOrInstallGnopls();

        if (gnoplsPath == null || gnoplsPath.equals(GNOPLS_BINARY)) {
            Messages.showErrorDialog("Failed to install `gnopls`. Please install it manually using:\n" +
                    "`go install github.com/gnoverse/gnopls@latest`", "Installation Error");
            LOG.error("gnopls installation failed. Exiting GnoLanguageServer.");
            return;
        }

        LOG.info("Using gnopls at: " + gnoplsPath);

        GeneralCommandLine commandLine = new GeneralCommandLine(gnoplsPath);
        commandLine.setWorkDirectory(System.getProperty("user.dir"));
        super.setCommandLine(commandLine);
    }

    private String findOrInstallGnopls() {
        String gnoplsPath;
        if (isWindows() && usingWSL()) {
            // Use the WSL converted path for gnopls
            gnoplsPath = getWslGnoplsPath();
        } else {
            // Default path for non-WSL environments
            gnoplsPath = GNOPLS_PATH;
        }

        Path gnoplsFile = Paths.get(gnoplsPath);
        if (Files.exists(gnoplsFile) && Files.isExecutable(gnoplsFile)) {
            return gnoplsPath;
        }

        return installGnopls();
    }

    /**
     * Installs gnopls by running "go install github.com/gnoverse/gnopls@latest".
     * Returns the path to the installed binary.
     */
    private String installGnopls() {
        return ProgressManager.getInstance().runProcessWithProgressSynchronously(() -> {
            LOG.info("Downloading and installing gnopls...");

            String goPath = findGoBinary();
            if (goPath == null) {
                LOG.error("Go binary not found. Cannot install gnopls.");
                return null;
            }

            LOG.info("Installing gnopls using: " + goPath + " install github.com/gnoverse/gnopls@latest");

            try {
                ProcessBuilder processBuilder = new ProcessBuilder(goPath, "install", "github.com/gnoverse/gnopls@latest");
                processBuilder.environment().put("PATH", System.getenv("PATH") + ":" + GO_BIN_DIR);
                processBuilder.redirectErrorStream(true);

                Process process = processBuilder.start();
                int exitCode = process.waitFor();

                String installedPath;
                if (isWindows() && usingWSL()) {
                    installedPath = getWslGnoplsPath();
                } else {
                    installedPath = GNOPLS_PATH;
                }

                if (exitCode == 0 && Files.exists(Paths.get(installedPath))) {
                    LOG.info("gnopls successfully installed at: " + installedPath);
                    return installedPath;
                } else {
                    LOG.error("Failed to install gnopls. Exit code: " + exitCode);
                    return null;
                }
            } catch (IOException | InterruptedException e) {
                LOG.error("Error during gnopls installation", e);
                Thread.currentThread().interrupt();
                return null;
            }
        }, "Installing Gno Language Server", true, null);
    }

    /**
     * Finds the Go binary. If on Windows, first tries to get the path via WSL.
     */
    private String findGoBinary() {
        if (isWindows()) {
            String wslGoPath = findGoPathWithWSL();
            if (wslGoPath != null) {
                return wslGoPath;
            }
        }
        try {
            Process process = new ProcessBuilder("which", "go").start();
            String output = new String(process.getInputStream().readAllBytes()).trim();
            if (!output.isEmpty()) {
                return output;
            }
        } catch (IOException e) {
            LOG.warn("Error checking 'which go'", e);
        }

        String[] possiblePaths = {
                "/usr/local/go/bin/go",
                "/usr/bin/go",
                "/usr/local/bin/go",
                "/opt/homebrew/bin/go"
        };

        for (String path : possiblePaths) {
            Path pathObj = Paths.get(path);
            if (Files.exists(pathObj) && Files.isExecutable(pathObj)) {
                return path;
            }
        }

        return null;
    }


    private String findGoPathWithWSL() {
        try {
            Process whichProcess = new ProcessBuilder("wsl.exe", "which", "go").start();
            String wslPath = new String(whichProcess.getInputStream().readAllBytes()).trim();
            if (wslPath.isEmpty()) {
                return null;
            }

            Process wslPathProcess = new ProcessBuilder("wsl.exe", "wslpath", "-m", wslPath).start();
            String windowsPath = new String(wslPathProcess.getInputStream().readAllBytes()).trim();
            if (!windowsPath.isEmpty()) {
                Path p = Paths.get(windowsPath);
                if (Files.exists(p) && Files.isExecutable(p)) {
                    return windowsPath;
                }
                return windowsPath;
            }
        } catch (IOException e) {
            LOG.warn("Error checking WSL 'which go' or 'wslpath'", e);
        }
        return null;
    }

    /**
     * Retrieves the WSL home directory by executing "wsl.exe printenv HOME"
     * and builds the Linux path for gnopls as "$HOME/go/bin/gnopls".
     */
    private String getWslGnoplsLinuxPath() {
        try {
            Process process = new ProcessBuilder("wsl.exe", "printenv", "HOME").start();
            String homeDir = new String(process.getInputStream().readAllBytes()).trim();
            if (homeDir.isEmpty()) {
                LOG.warn("WSL HOME environment variable is empty.");
                return null;
            }
            return homeDir + "/go/bin/gnopls";
        } catch (IOException e) {
            LOG.warn("Error retrieving WSL home directory", e);
            return null;
        }
    }


    private String getWslGnoplsPath() {
        String wslLinuxGnoplsPath = getWslGnoplsLinuxPath();
        if (wslLinuxGnoplsPath == null) {
            return null;
        }
        try {
            Process process = new ProcessBuilder("wsl.exe", "wslpath", "-m", wslLinuxGnoplsPath).start();
            String windowsPath = new String(process.getInputStream().readAllBytes()).trim();
            if (!windowsPath.isEmpty()) {
                return windowsPath;
            }
        } catch (IOException e) {
            LOG.warn("Error converting WSL path for gnopls", e);
        }
        return null;
    }

    private boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("win");
    }

    private boolean usingWSL() {
        return findGoPathWithWSL() != null;
    }

    @Override
    public Object getInitializationOptions(VirtualFile rootUri) {
        Map<String, Object> options = new HashMap<>();
        options.put("ui.semanticTokens", true);
        return options;
    }
}
