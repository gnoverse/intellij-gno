package com.github.intellij.gno.psi;

import com.github.intellij.gno.language.GnoLanguage;
import com.github.intellij.gno.language.GnoFileType;
import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;

public class GnoFile extends PsiFileBase {
    public GnoFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, GnoLanguage.INSTANCE);
    }

    public @NotNull FileType getFileType() {
        return GnoFileType.INSTANCE;
    }
}
