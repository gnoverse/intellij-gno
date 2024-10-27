package com.github.intellij.gno.language.stubs.types;

import com.github.intellij.gno.language.psi.GnoPackageClause;
import com.github.intellij.gno.language.psi.impl.GnoPackageClauseImpl;
import com.github.intellij.gno.language.stubs.GnoPackageClauseStub;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class GnoPackageClauseStubElementType extends GnoStubElementType<GnoPackageClauseStub, GnoPackageClause> {
    public static final GnoPackageClauseStubElementType INSTANCE = new GnoPackageClauseStubElementType();

    private GnoPackageClauseStubElementType() {
        super("PACKAGE_CLAUSE");
    }

    @NotNull
    @Override
    public GnoPackageClause createPsi(@NotNull GnoPackageClauseStub stub) {
        return new GnoPackageClauseImpl(stub, this);
    }

    @NotNull
    @Override
    public GnoPackageClauseStub createStub(@NotNull GnoPackageClause psi, StubElement parentStub) {
        return new GnoPackageClauseStub(parentStub, this, psi.getName());
    }

    @Override
    public void serialize(@NotNull GnoPackageClauseStub stub, @NotNull StubOutputStream dataStream) throws IOException {
        dataStream.writeName(stub.getName());
    }

    @NotNull
    @Override
    public GnoPackageClauseStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
        return new GnoPackageClauseStub(parentStub, this, dataStream.readName());
    }
}