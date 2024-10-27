package com.github.intellij.gno.language.stubs;

import com.github.intellij.gno.language.psi.GnoNamedElement;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.NamedStubBase;
import com.intellij.psi.stubs.StubElement;
import com.intellij.util.io.StringRef;

abstract public class GnoNamedStub<T extends GnoNamedElement> extends NamedStubBase<T> {
    private final boolean myIsPublic;

    public GnoNamedStub(StubElement parent, IStubElementType elementType, StringRef name, boolean isPublic) {
        super(parent, elementType, name);
        myIsPublic = isPublic;
    }

    public GnoNamedStub(StubElement parent, IStubElementType elementType, String name, boolean isPublic) {
        super(parent, elementType, name);
        myIsPublic = isPublic;
    }

    public boolean isPublic() {
        return myIsPublic;
    }

    @Override
    public String toString() {
        String name = getName();
        String str = super.toString();
        return StringUtil.isEmpty(name) ? str : str + ": " + name;
    }
}
