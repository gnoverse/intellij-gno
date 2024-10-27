package com.github.intellij.gno.language.stubs;

import com.github.intellij.gno.language.psi.GnoMethodSpec;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubElement;
import com.intellij.util.io.StringRef;

public class GnoMethodSpecStub extends GnoNamedStub<GnoMethodSpec> {
    private final int myArity;

    public GnoMethodSpecStub(StubElement parent, IStubElementType elementType, StringRef name, boolean isPublic, int arity) {
        super(parent, elementType, name, isPublic);
        myArity = arity;
    }

    public GnoMethodSpecStub(StubElement parent, IStubElementType elementType, String name, boolean isPublic, int arity) {
        super(parent, elementType, name, isPublic);
        myArity = arity;
    }

    public int getArity() {
        return myArity;
    }
}