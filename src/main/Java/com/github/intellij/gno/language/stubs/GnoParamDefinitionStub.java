package com.github.intellij.gno.language.stubs;

import com.github.intellij.gno.language.psi.GnoParamDefinition;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubElement;
import com.intellij.util.io.StringRef;

public class GnoParamDefinitionStub extends GnoNamedStub<GnoParamDefinition> {
    public GnoParamDefinitionStub(StubElement parent, IStubElementType elementType, StringRef name, boolean isPublic) {
        super(parent, elementType, name, isPublic);
    }

    public GnoParamDefinitionStub(StubElement parent, IStubElementType elementType, String name, boolean isPublic) {
        super(parent, elementType, name, isPublic);
    }
}