package com.github.intellij.gno.language.stubs;

import com.github.intellij.gno.language.psi.GnoVarSpec;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubBase;
import com.intellij.psi.stubs.StubElement;

public class GnoVarSpecStub extends StubBase<GnoVarSpec> {
    public GnoVarSpecStub(StubElement parent, IStubElementType elementType) {
        super(parent, elementType);
    }
}