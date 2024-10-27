package com.github.intellij.gno.language.stubs;

import com.github.intellij.gno.language.psi.GnoFile;
import com.github.intellij.gno.language.GnoFileElementType;
import com.github.intellij.gno.language.psi.GnoPackageClause;
import com.github.intellij.gno.language.stubs.types.GnoPackageClauseStubElementType;
import com.intellij.psi.stubs.PsiFileStubImpl;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.tree.IStubFileElementType;
import com.intellij.util.io.StringRef;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GnoFileStub extends PsiFileStubImpl<GnoFile> {
    private final StringRef myBuildFlags;

    public GnoFileStub(@NotNull GnoFile file) {
        this(file, StringRef.fromNullableString(file.getBuildFlags()));
    }

    public GnoFileStub(@Nullable GnoFile file, StringRef buildFlags) {
        super(file);
        myBuildFlags = buildFlags;
    }

    @NotNull
    @Override
    public IStubFileElementType getType() {
        return GnoFileElementType.INSTANCE;
    }

    @Nullable
    public String getBuildFlags() {
        return myBuildFlags.getString();
    }

    @Nullable
    public StubElement<GnoPackageClause> getPackageClauseStub() {
        return findChildStubByType(GnoPackageClauseStubElementType.INSTANCE);
    }

    @Nullable
    public String getPackageName() {
        StubElement<GnoPackageClause> stub = getPackageClauseStub();
        return stub instanceof GnoPackageClauseStub ? ((GnoPackageClauseStub)stub).getName() : null;
    }
}