package com.github.intellij.gno.language.stubs;

import com.github.intellij.gno.language.psi.GnoType;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubElement;
import com.intellij.util.io.StringRef;

public class GnoTypeStub extends StubWithText<GnoType> {
    public GnoTypeStub(StubElement parent, IStubElementType elementType, StringRef ref) {
        super(parent, elementType, ref);
    }

    public GnoTypeStub(StubElement parent, IStubElementType elementType, String text) {
        this(parent, elementType, StringRef.fromString(text));
    }
}