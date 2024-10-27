package com.github.intellij.gno.language.stubs;

import com.github.intellij.gno.language.psi.GnoMethodDeclaration;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubElement;
import com.intellij.util.io.StringRef;
import org.jetbrains.annotations.Nullable;

public class GnoMethodDeclarationStub extends GnoFunctionOrMethodDeclarationStub<GnoMethodDeclaration> {
    private final StringRef myTypeName;

    public GnoMethodDeclarationStub(StubElement parent, IStubElementType elementType, StringRef name, boolean isPublic, StringRef typeName) {
        super(parent, elementType, name, isPublic);
        myTypeName = typeName;
    }

    public GnoMethodDeclarationStub(StubElement parent, IStubElementType elementType, String name, boolean isPublic, String typeName) {
        super(parent, elementType, name, isPublic);
        myTypeName = StringRef.fromString(typeName);
    }

    @Nullable
    public String getTypeName() {
        return myTypeName == null ? null : myTypeName.getString();
    }
}