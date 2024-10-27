package com.github.intellij.gno.language.stubs;

import com.github.intellij.gno.language.psi.GnoFunctionOrMethodDeclaration;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubElement;
import com.intellij.util.io.StringRef;

abstract public class GnoFunctionOrMethodDeclarationStub<T extends GnoFunctionOrMethodDeclaration> extends GnoNamedStub<T> {
    protected GnoFunctionOrMethodDeclarationStub(StubElement parent, IStubElementType elementType, StringRef name, boolean isPublic) {
        super(parent, elementType, name, isPublic);
    }

    protected GnoFunctionOrMethodDeclarationStub(StubElement parent, IStubElementType elementType, String name, boolean isPublic) {
        super(parent, elementType, name, isPublic);
    }
}