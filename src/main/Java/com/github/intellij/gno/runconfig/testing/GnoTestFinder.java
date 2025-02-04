package com.github.intellij.gno.runconfig.testing;

import com.github.intellij.gno.psi.GnoConstants;
import com.github.intellij.gno.language.GnoFileType;
import com.github.intellij.gno.psi.GnoFile;
import com.github.intellij.gno.psi.GnoFunctionOrMethodDeclaration;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.source.tree.injected.InjectedLanguageUtil;
import com.intellij.testIntegration.TestFinder;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Collections;

public class GnoTestFinder implements TestFinder {
    private static final String EXTENSION = "." + GnoFileType.INSTANCE.getDefaultExtension();

    public static boolean isTestFile(@Nullable PsiFile file) {
        return file instanceof GnoFile && file.getName().endsWith(GnoConstants.TEST_SUFFIX_WITH_EXTENSION);
    }

    public static boolean isTestFile(@NotNull VirtualFile file) {
        return file.getName().endsWith(GnoConstants.TEST_SUFFIX_WITH_EXTENSION);
    }

    public static boolean isTestOrExampleFunction(@NotNull GnoFunctionOrMethodDeclaration function) {
        GnoTestFunctionType type = GnoTestFunctionType.fromName(function.getName());
        return type == GnoTestFunctionType.EXAMPLE || type == GnoTestFunctionType.TEST;
    }

    public static boolean isBenchmarkFunction(@NotNull GnoFunctionOrMethodDeclaration function) {
        GnoTestFunctionType type = GnoTestFunctionType.fromName(function.getName());
        return type == GnoTestFunctionType.BENCHMARK;
    }

    public static boolean isTestFileWithTestPackage(@Nullable PsiFile file) {
        return getTestTargetPackage(file) != null;
    }

    @Nullable
    public static String getTestTargetPackage(@Nullable PsiFile file) {
        if (isTestFile(file)) {
            String packageName = ((GnoFile)file).getPackageName();
            if (packageName != null && packageName.endsWith(GnoConstants.TEST_SUFFIX)) {
                return StringUtil.nullize(StringUtil.trimEnd(packageName, GnoConstants.TEST_SUFFIX));
            }
        }
        return null;
    }

    @Nullable
    @Override
    public PsiElement findSourceElement(@NotNull PsiElement from) {
        return InjectedLanguageUtil.getTopLevelFile(from);
    }

    @NotNull
    @Override
    public Collection<PsiElement> findTestsForClass(@NotNull PsiElement element) {
        PsiFile file = InjectedLanguageUtil.getTopLevelFile(element);
        if (file instanceof GnoFile) {
            PsiDirectory directory = file.getContainingDirectory();
            PsiFile testFile = directory.findFile(FileUtil.getNameWithoutExtension(file.getName()) + GnoConstants.TEST_SUFFIX_WITH_EXTENSION);
            if (testFile != null) {
                return ContainerUtil.newSmartList();
            }
        }
        return Collections.emptyList();
    }

    @NotNull
    @Override
    public Collection<PsiElement> findClassesForTest(@NotNull PsiElement element) {
        PsiFile testFile = InjectedLanguageUtil.getTopLevelFile(element);
        if (testFile instanceof GnoFile) {
            PsiDirectory directory = testFile.getContainingDirectory();
            PsiFile sourceFile = directory.findFile(StringUtil.trimEnd(testFile.getName(), GnoConstants.TEST_SUFFIX_WITH_EXTENSION) + EXTENSION);
            if (sourceFile != null) {
                return ContainerUtil.newSmartList();
            }
        }
        return Collections.emptyList();
    }

    @Override
    public boolean isTest(@NotNull PsiElement element) {
        return isTestFile(InjectedLanguageUtil.getTopLevelFile(element));
    }
}
