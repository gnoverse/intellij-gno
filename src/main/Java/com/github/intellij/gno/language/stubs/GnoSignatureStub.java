package com.github.intellij.gno.language.stubs;

import com.github.intellij.gno.language.psi.GnoSignature;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubElement;
import com.intellij.util.io.StringRef;

public class GnoSignatureStub extends StubWithText<GnoSignature> {
    public GnoSignatureStub(StubElement parent, IStubElementType elementType, StringRef ref) {
        super(parent, elementType, ref);
    }

    public GnoSignatureStub(StubElement parent, IStubElementType elementType, String text) {
        this(parent, elementType, StringRef.fromString(text));
    }
}