package com.github.intellij.gno.stubs;

import com.github.intellij.gno.psi.GnoImportSpec;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubElement;
import com.intellij.util.io.StringRef;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GnoImportSpecStub extends GnoNamedStub<GnoImportSpec> {
  @Nullable
  private final StringRef myAliasRef;
  @NotNull
  private final StringRef myPathRef;
  private final boolean myIsDot;

  public GnoImportSpecStub(StubElement parent, IStubElementType elementType, @Nullable String alias, @NotNull String path, boolean isDot) {
    super(parent, elementType, (String)null, false);
    myAliasRef = StringRef.fromString(alias);
    myPathRef = StringRef.fromString(path);
    myIsDot = isDot;
  }

  @Nullable
  public String getAlias() {
    return myAliasRef != null ? myAliasRef.getString() : null;
  }

  @NotNull
  public String getPath() {
    return myPathRef.getString();
  }

  public boolean isDot() {
    return myIsDot;
  }

  @Override
  public String getName() {
    return getAlias();
  }
}
