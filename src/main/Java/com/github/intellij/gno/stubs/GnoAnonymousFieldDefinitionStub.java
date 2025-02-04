package com.github.intellij.gno.stubs;

import com.github.intellij.gno.psi.GnoAnonymousFieldDefinition;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubElement;
import com.intellij.util.io.StringRef;

public class GnoAnonymousFieldDefinitionStub extends GnoNamedStub<GnoAnonymousFieldDefinition> {
  public GnoAnonymousFieldDefinitionStub(StubElement parent, IStubElementType elementType, StringRef name, boolean isPublic) {
    super(parent, elementType, name, isPublic);
  }

  public GnoAnonymousFieldDefinitionStub(StubElement parent, IStubElementType elementType, String name, boolean isPublic) {
    super(parent, elementType, name, isPublic);
  }
}
