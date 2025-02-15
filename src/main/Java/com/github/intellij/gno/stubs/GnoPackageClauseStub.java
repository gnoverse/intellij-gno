package com.github.intellij.gno.stubs;

import com.github.intellij.gno.psi.GnoPackageClause;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubBase;
import com.intellij.psi.stubs.StubElement;
import com.intellij.util.io.StringRef;

public class GnoPackageClauseStub extends StubBase<GnoPackageClause> {
  private final String myName;
  public GnoPackageClauseStub(StubElement parent, IStubElementType elementType, String name) {
    super(parent, elementType);
    myName = name;
  }

  public GnoPackageClauseStub(StubElement stub, IStubElementType elementType, StringRef ref) {
    super(stub, elementType);
    myName = ref != null ? ref.getString() : null;
  }

  public String getName() {
    return myName;
  }
}
