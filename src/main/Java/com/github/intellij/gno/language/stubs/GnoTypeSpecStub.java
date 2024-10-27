package com.github.intellij.gno.language.stubs;

import com.github.intellij.gno.language.psi.GnoTypeSpec;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubElement;
import com.intellij.util.io.StringRef;

public class GnoTypeSpecStub extends GnoNamedStub<GnoTypeSpec> {
    public GnoTypeSpecStub(StubElement parent, IStubElementType elementType, StringRef name, boolean isPublic) {
        super(parent, elementType, name, isPublic);
    }

    public GnoTypeSpecStub(StubElement parent, IStubElementType elementType, String name, boolean isPublic) {
        super(parent, elementType, name, isPublic);
    }
}