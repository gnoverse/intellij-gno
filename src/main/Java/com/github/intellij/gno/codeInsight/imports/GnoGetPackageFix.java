package com.github.intellij.gno.codeInsight.imports;

import com.github.intellij.gno.sdk.GnoSdkService;
import com.github.intellij.gno.util.GnoExecutor;
import com.intellij.codeInsight.intention.HighPriorityAction;
import com.intellij.codeInspection.LocalQuickFixBase;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.psi.PsiElement;
import com.intellij.util.Consumer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GnoGetPackageFix extends LocalQuickFixBase implements HighPriorityAction {
    @NotNull private final String myPackage;

    public GnoGetPackageFix(@NotNull String packageName) {
        super("gno get -t " + packageName + "/...", "gno get");
        myPackage = packageName;
    }

    public static void applyFix(@NotNull Project project,
                                @Nullable Module module,
                                @NotNull String packageName,
                                boolean startInBackground) {
        String sdkPath = GnoSdkService.getInstance(project).getSdkHomePath(module);
        if (StringUtil.isEmpty(sdkPath)) return;
        CommandProcessor.getInstance().runUndoTransparentAction(() -> {
            Consumer<Boolean> consumer = aBoolean -> VirtualFileManager.getInstance().asyncRefresh(null);
            GnoExecutor.in(project, module).withPresentableName("gno get -t " + packageName + "/...")
                    .withParameters("get", "-t", packageName+"/...").showNotifications(false, true).showOutputOnError()
                    .executeWithProgress(!startInBackground, consumer);
        });
    }

    @Override
    public void applyFix(@NotNull Project project, @NotNull ProblemDescriptor descriptor) {
        PsiElement element = descriptor.getPsiElement();
        if (element != null) {
            applyFix(project, ModuleUtilCore.findModuleForPsiElement(element.getContainingFile()), myPackage, true);
        }
    }
}
