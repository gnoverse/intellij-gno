package com.github.intellij.gno.stubs;

import com.github.intellij.gno.psi.GnoFieldDefinition;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubElement;
import com.intellij.util.io.StringRef;

public class GnoFieldDefinitionStub extends GnoNamedStub<GnoFieldDefinition> {
  public GnoFieldDefinitionStub(StubElement parent, IStubElementType elementType, StringRef name, boolean isPublic) {
    super(parent, elementType, name, isPublic);
  }

  public GnoFieldDefinitionStub(StubElement parent, IStubElementType elementType, String name, boolean isPublic) {
    super(parent, elementType, name, isPublic);
  }
}
