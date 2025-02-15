package com.github.intellij.gno.runconfig;

import com.github.intellij.gno.psi.GnoConstants;
import com.github.intellij.gno.language.GnoFileType;
import com.github.intellij.gno.psi.GnoFile;
import com.github.intellij.gno.psi.GnoPackageClause;
import com.github.intellij.gno.runconfig.testing.GnoTestFinder;
import com.intellij.execution.actions.ConfigurationContext;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.process.ProcessOutputTypes;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.FileIndexFacade;
import com.intellij.openapi.ui.ComponentWithBrowseButton;
import com.intellij.openapi.ui.TextBrowseFolderListener;
import com.intellij.openapi.ui.TextComponentAccessor;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileSystemItem;
import com.intellij.psi.PsiManager;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class GnoRunUtil {
    private GnoRunUtil() {
    }

    @Contract("null -> false")
    public static boolean isPackageContext(@Nullable PsiElement contextElement) {
        return PsiTreeUtil.getNonStrictParentOfType(contextElement, GnoPackageClause.class) != null;
    }

    @Nullable
    public static PsiFile findMainFileInDirectory(@NotNull VirtualFile packageDirectory, @NotNull Project project) {
        for (VirtualFile file : packageDirectory.getChildren()) {
            if (file == null) {
                continue;
            }
            PsiFile psiFile = PsiManager.getInstance(project).findFile(file);
            if (isMainGnoFile(psiFile)) {
                return psiFile;
            }
        }
        return null;
    }

    @Nullable
    public static PsiElement getContextElement(@Nullable ConfigurationContext context) {
        if (context == null) {
            return null;
        }
        PsiElement psiElement = context.getPsiLocation();
        if (psiElement == null || !psiElement.isValid()) {
            return null;
        }

        FileIndexFacade indexFacade = FileIndexFacade.getInstance(psiElement.getProject());
        PsiFileSystemItem psiFile = psiElement instanceof PsiFileSystemItem ? (PsiFileSystemItem)psiElement : psiElement.getContainingFile();
        VirtualFile file = psiFile != null ? psiFile.getVirtualFile() : null;

        return psiElement;
    }

    public static void installGnoWithMainFileChooser(Project project, @NotNull TextFieldWithBrowseButton fileField) {
        installFileChooser(project, fileField, false, false, file -> {
            if (file.getFileType() != GnoFileType.INSTANCE) {
                return false;
            }
            return isMainGnoFile(PsiManager.getInstance(project).findFile(file));
        });
    }

    @Contract("null -> false")
    public static boolean isMainGnoFile(@Nullable PsiFile psiFile) {
        if (!GnoTestFinder.isTestFile(psiFile) && psiFile instanceof GnoFile) {
            return GnoConstants.MAIN.equals(((GnoFile)psiFile).getPackageName()) && ((GnoFile)psiFile).hasMainFunction();
        }
        return false;
    }

    public static void installFileChooser(@NotNull Project project,
                                          @NotNull ComponentWithBrowseButton field,
                                          boolean directory) {
        installFileChooser(project, field, directory, false);
    }

    public static void installFileChooser(@NotNull Project project, @NotNull ComponentWithBrowseButton field, boolean directory,
                                          boolean showFileSystemRoots) {
        installFileChooser(project, field, directory, showFileSystemRoots, null);
    }

    public static void installFileChooser(@NotNull Project project,
                                          @NotNull ComponentWithBrowseButton field,
                                          boolean directory,
                                          boolean showFileSystemRoots,
                                          @Nullable Condition<VirtualFile> fileFilter) {
        FileChooserDescriptor chooseDirectoryDescriptor = directory
                ? FileChooserDescriptorFactory.createSingleFolderDescriptor()
                : FileChooserDescriptorFactory.createSingleLocalFileDescriptor();
        chooseDirectoryDescriptor.setRoots(project.getBaseDir());
        chooseDirectoryDescriptor.setShowFileSystemRoots(showFileSystemRoots);
        chooseDirectoryDescriptor.withFileFilter(fileFilter);
        if (field instanceof TextFieldWithBrowseButton) {
            ((TextFieldWithBrowseButton)field).addBrowseFolderListener(new TextBrowseFolderListener(chooseDirectoryDescriptor, project));
        }
    }

    public static void printGnoEnvVariables(@NotNull GeneralCommandLine commandLine, @NotNull ProcessHandler handler) {
        Map<String, String> environment = commandLine.getEnvironment();
        handler.notifyTextAvailable("GNOROOT=" + StringUtil.nullize(environment.get(GnoConstants.GNO_ROOT)) + '\n', ProcessOutputTypes.SYSTEM);
        handler.notifyTextAvailable("GNOPATH=" + StringUtil.nullize(environment.get(GnoConstants.GNO_PATH)) + '\n', ProcessOutputTypes.SYSTEM);
    }
}
