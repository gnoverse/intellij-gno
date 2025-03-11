package com.github.intellij.gno.language;

import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.charset.Charset;

public abstract class GnoLanguageFileType implements FileType {
    protected static final @NotNull Language INSTANCE = new GnoLanguage();
    private final Language myLanguage;
    private final boolean mySecondary;


    protected GnoLanguageFileType(@NotNull Language language) {
        this(language, false);
    }


    protected GnoLanguageFileType(@NotNull Language language, boolean secondary) {
        myLanguage = language;
        mySecondary = secondary;
    }

    /**
     * Returns the language used in the files of the type.
     *
     * @return The language instance.
     */
    @NotNull
    public final Language getLanguage() {
        return myLanguage;
    }

    @Override
    public final boolean isBinary() {
        return false;
    }


    public boolean isSecondary() {
        return mySecondary;
    }

    @Deprecated
    public boolean isJVMDebuggingSupported() {
        return false;
    }


    @SuppressWarnings("DeprecatedIsStillUsed")
    @Deprecated
    public Charset extractCharsetFromFileContent(@Nullable Project project, @Nullable VirtualFile file, @NotNull String content) {
        return null;
    }

    public Charset extractCharsetFromFileContent(@Nullable Project project, @Nullable VirtualFile file, @NotNull CharSequence content) {
        return extractCharsetFromFileContent(project, file, content.toString());
    }

    @Override
    @Nls
    @NotNull
    public String getDisplayName() {
        return myLanguage.getDisplayName();
    }
}
