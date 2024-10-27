package com.github.intellij.gno.language.stubs;

import com.github.intellij.gno.language.psi.GnoFunctionDeclaration;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubElement;
import com.intellij.util.io.StringRef;

public class GnoFunctionDeclarationStub extends GnoFunctionOrMethodDeclarationStub<GnoFunctionDeclaration> {
    public GnoFunctionDeclarationStub(StubElement parent, IStubElementType elementType, StringRef name, boolean isPublic) {
        super(parent, elementType, name, isPublic);
    }

    public GnoFunctionDeclarationStub(StubElement parent, IStubElementType elementType, String name, boolean isPublic) {
        super(parent, elementType, name, isPublic);
    }
}