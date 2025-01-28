package com.github.intellij.gno.lsp;

import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.openapi.diagnostic.Logger;
import com.redhat.devtools.lsp4ij.server.OSProcessStreamConnectionProvider;

public class GnoLanguageServer extends OSProcessStreamConnectionProvider {

    private static final Logger LOG = Logger.getInstance(GnoLanguageServer.class);
    private static final String GNOPLS_BINARY = "gnopls";

    public GnoLanguageServer() {
        String gnoplsPath = findGnopls();
        LOG.info("Using gnopls to : " + gnoplsPath);

        GeneralCommandLine commandLine = new GeneralCommandLine(gnoplsPath);
        commandLine.setWorkDirectory(System.getProperty("user.dir"));
        super.setCommandLine(commandLine);
    }

    private String findGnopls() {
        return GNOPLS_BINARY;
    }
}
