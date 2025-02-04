package com.github.intellij.gno.language;

import com.github.intellij.gno.psi.GnoFile;
import com.github.intellij.gno.stubs.GnoFileStub;
import com.github.intellij.gno.stubs.index.GnoPackagesIndex;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiFile;
import com.intellij.psi.StubBuilder;
import com.intellij.psi.stubs.*;
import com.intellij.psi.tree.IStubFileElementType;
import com.intellij.util.io.StringRef;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class GnoFileElementType extends IStubFileElementType<GnoFileStub> {
    public static final IStubFileElementType INSTANCE = new GnoFileElementType();
    public static final int VERSION = 21;

    private GnoFileElementType() {
        super("GNO_FILE", GnoLanguage.INSTANCE);
    }

    @Override
    public int getStubVersion() {
        return VERSION;
    }

    @NotNull
    @Override
    public StubBuilder getBuilder() {
        return new DefaultStubBuilder() {
            @NotNull
            @Override
            protected StubElement createStubForFile(@NotNull PsiFile file) {
                if (file instanceof GnoFile) {
                    return new GnoFileStub((GnoFile)file);
                }
                return super.createStubForFile(file);
            }
        };
    }

    @Override
    public void indexStub(@NotNull GnoFileStub stub, @NotNull IndexSink sink) {
        super.indexStub(stub, sink);
        String packageName = stub.getPackageName();
        if (StringUtil.isNotEmpty(packageName)) {
            sink.occurrence(GnoPackagesIndex.KEY, packageName);
        }
    }

    @Override
    public void serialize(@NotNull GnoFileStub stub, @NotNull StubOutputStream dataStream) throws IOException {
        dataStream.writeUTF(StringUtil.notNullize(stub.getBuildFlags()));
    }

    @NotNull
    @Override
    public GnoFileStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
        return new GnoFileStub(null, StringRef.fromNullableString(StringUtil.nullize(dataStream.readUTF())));
    }

    @NotNull
    @Override
    public String getExternalId() {
        return "gno.FILE";
    }
}
