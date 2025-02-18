package com.github.intellij.gno.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.github.intellij.gno.language.GnoFileType;
import com.github.intellij.gno.language.GnoLanguage;

import org.jetbrains.annotations.NotNull;

public class GnoFile extends PsiFileBase {

    public GnoFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, GnoLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return GnoFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Gno File";
    }

}