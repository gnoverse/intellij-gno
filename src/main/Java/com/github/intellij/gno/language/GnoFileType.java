package com.github.intellij.gno.language;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class GnoFileType extends LanguageFileType {
    public static final GnoFileType INSTANCE = new GnoFileType();

    private GnoFileType() {
        super(GnoLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "Gno File";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Gno language file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "gno";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return GnoIcon.FILE;
    }
}
