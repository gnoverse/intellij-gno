package com.github.intellij.gno.language.stubs;

import com.github.intellij.gno.language.psi.GnoParameterDeclaration;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubElement;
import com.intellij.util.io.StringRef;

public class GnoParameterDeclarationStub extends StubWithText<GnoParameterDeclaration> {
    private final boolean myVariadic;

    public GnoParameterDeclarationStub(StubElement parent, IStubElementType elementType, StringRef ref, boolean variadic) {
        super(parent, elementType, ref);
        myVariadic = variadic;
    }

    public GnoParameterDeclarationStub(StubElement parent, IStubElementType elementType, String text, boolean variadic) {
        this(parent, elementType, StringRef.fromString(text), variadic);
    }

    public boolean isVariadic() {
        return myVariadic;
    }
}