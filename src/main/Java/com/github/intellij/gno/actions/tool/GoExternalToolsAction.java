package com.github.intellij.gno.actions.tool;

import com.github.intellij.gno.GoConstants;
import com.github.intellij.gno.GoFileType;
import com.github.intellij.gno.sdk.GoSdkService;
import com.github.intellij.gno.util.GoExecutor;
import com.intellij.execution.ExecutionException;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.Consumer;
import com.intellij.util.ExceptionUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class GoExternalToolsAction extends DumbAwareAction {
  private static final Logger LOG = Logger.getInstance(GoExternalToolsAction.class);

  @Override
  public void update(@NotNull AnActionEvent e) {
    super.update(e);
    Project project = e.getProject();
    VirtualFile file = e.getData(CommonDataKeys.VIRTUAL_FILE);
    if (project == null || file == null || !file.isInLocalFileSystem() || !isAvailableOnFile(file)) {
      e.getPresentation().setEnabled(false);
      return;
    }
    Module module = ModuleUtilCore.findModuleForFile(file, project);
    e.getPresentation().setEnabled(GoSdkService.getInstance(project).isGoModule(module));
  }

  protected boolean isAvailableOnFile(VirtualFile file) {
    return file.getFileType() == GoFileType.INSTANCE;
  }

  @Override
  public void actionPerformed(@NotNull AnActionEvent e) {
    Project project = e.getProject();
    VirtualFile file = e.getRequiredData(CommonDataKeys.VIRTUAL_FILE);
    assert project != null;
    String title = StringUtil.notNullize(e.getPresentation().getText());

    Module module = ModuleUtilCore.findModuleForFile(file, project);
    try {
      doSomething(file, module, project, title);
    }
    catch (ExecutionException ex) {
      LOG.error(ex);
    }
  }

  protected boolean doSomething(@NotNull VirtualFile virtualFile,
                                @Nullable Module module,
                                @NotNull Project project,
                                @NotNull String title) throws ExecutionException {
    return doSomething(virtualFile, module, project, title, false);
  }

  private boolean doSomething(@NotNull VirtualFile virtualFile,
                              @Nullable Module module,
                              @NotNull Project project,
                              @NotNull String title,
                              boolean withProgress) {
    java.util.function.Consumer<Boolean> emptyConsumer = b -> {};
    return doSomething(virtualFile, module, project, title, withProgress, emptyConsumer);
  }

  protected boolean doSomething(@NotNull VirtualFile virtualFile,
                                @Nullable Module module,
                                @NotNull Project project,
                                @NotNull String title,
                                boolean withProgress,
                                @NotNull java.util.function.Consumer<Boolean> consumer) {
    Document document = FileDocumentManager.getInstance().getDocument(virtualFile);
    if (document != null) {
      FileDocumentManager.getInstance().saveDocument(document);
    } else {
      FileDocumentManager.getInstance().saveAllDocuments();
    }

    createExecutor(project, module, title, virtualFile).executeWithProgress(withProgress, result -> {
      consumer.accept(result);
      VfsUtil.markDirtyAndRefresh(true, true, true, virtualFile);
    });
    return true;
  }

  protected GoExecutor createExecutor(@NotNull Project project,
                                      @Nullable Module module,
                                      @NotNull String title,
                                      @NotNull VirtualFile virtualFile) {
    String filePath = virtualFile.getCanonicalPath();
    assert filePath != null;
    return createExecutor(project, module, title, filePath);
  }

  @NotNull
  protected abstract GoExecutor createExecutor(@NotNull Project project,
                                               @Nullable Module module,
                                               @NotNull String title,
                                               @NotNull String filePath);
}
