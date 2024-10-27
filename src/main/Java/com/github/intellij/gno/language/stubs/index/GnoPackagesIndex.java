package com.github.intellij.gno.language.stubs.index;

import com.github.intellij.gno.language.GnoFileElementType;
import com.github.intellij.gno.language.psi.GnoFile;
import com.intellij.psi.stubs.StringStubIndexExtension;
import com.intellij.psi.stubs.StubIndexKey;
import org.jetbrains.annotations.NotNull;

public class GnoPackagesIndex extends StringStubIndexExtension<GnoFile> {
    public static final StubIndexKey<String, GnoFile> KEY = StubIndexKey.createIndexKey("gno.packages");

    @Override
    public int getVersion() {
        return GnoFileElementType.VERSION + 2;
    }

    @NotNull
    @Override
    public StubIndexKey<String, GnoFile> getKey() {
        return KEY;
    }
}