package com.github.intellij.gno.stubs;

import com.github.intellij.gno.psi.GnoType;
import com.github.intellij.gno.psi.GnoVarSpec;
import com.github.intellij.gno.psi.impl.*;
import com.github.intellij.gno.stubs.types.*;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.util.ReflectionUtil;
import com.intellij.util.containers.HashMap;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class GnoElementTypeFactory {
  private static final Map<String, Class> TYPES = new HashMap<String, Class>() {
    {
      put("ARRAY_OR_SLICE_TYPE", GnoArrayOrSliceTypeImpl.class);
      put("CHANNEL_TYPE", GnoChannelTypeImpl.class);
      put("FUNCTION_TYPE", GnoFunctionTypeImpl.class);
      put("INTERFACE_TYPE", GnoInterfaceTypeImpl.class);
      put("MAP_TYPE", GnoMapTypeImpl.class);
      put("POINTER_TYPE", GnoPointerTypeImpl.class);
      put("STRUCT_TYPE", GnoStructTypeImpl.class);
      put("TYPE", GnoTypeImpl.class);
      put("PAR_TYPE", GnoParTypeImpl.class);
      put("SPEC_TYPE", GnoSpecTypeImpl.class);
      put("TYPE_LIST", GnoTypeListImpl.class);
    }
  };

  private GnoElementTypeFactory() {}

  public static IStubElementType stubFactory(@NotNull String name) {
    if ("CONST_DEFINITION".equals(name)) return new GnoConstDefinitionStubElementType(name);
    if ("FIELD_DEFINITION".equals(name)) return new GnoFieldDefinitionStubElementType(name);
    if ("ANONYMOUS_FIELD_DEFINITION".equals(name)) return new GnoAnonymousFieldDefinitionStubElementType(name);
    if ("FUNCTION_DECLARATION".equals(name)) return new GnoFunctionDeclarationStubElementType(name);
    if ("METHOD_DECLARATION".equals(name)) return new GnoMethodDeclarationStubElementType(name);
    if ("IMPORT_SPEC".equals(name)) return new GnoImportSpecStubElementType(name);
    if ("PARAM_DEFINITION".equals(name)) return new GnoParamDefinitionStubElementType(name);
    if ("RECEIVER".equals(name)) return new GnoReceiverStubElementType(name);
    if ("TYPE_SPEC".equals(name)) return new GnoTypeSpecStubElementType(name);
    if ("METHOD_SPEC".equals(name)) return new GnoMethodSpecStubElementType(name);
    if ("CONST_SPEC".equals(name)) return new GnoConstSpecStubElementType(name);
    if ("PACKAGE_CLAUSE".equals(name)) return GnoPackageClauseStubElementType.INSTANCE;
    if ("VAR_SPEC".equals(name)) return new GnoVarSpecStubElementType(name);
    if ("SHORT_VAR_DECLARATION".equals(name)) return new GnoVarSpecStubElementType(name) {
      @NotNull
      @Override
      public GnoVarSpec createPsi(@NotNull GnoVarSpecStub stub) {
        return new GnoShortVarDeclarationImpl(stub, this);
      }
    };
    if ("RECV_STATEMENT".equals(name)) return new GnoVarSpecStubElementType(name) {
      @NotNull
      @Override
      public GnoVarSpec createPsi(@NotNull GnoVarSpecStub stub) {
        return new GnoRecvStatementImpl(stub, this);
      }
    };
    if ("RANGE_CLAUSE".equals(name)) return new GnoVarSpecStubElementType(name) {
      @NotNull
      @Override
      public GnoVarSpec createPsi(@NotNull GnoVarSpecStub stub) {
        return new GnoRangeClauseImpl(stub, this);
      }
    };
    if ("VAR_DEFINITION".equals(name)) return new GnoVarDefinitionStubElementType(name);
    if ("LABEL_DEFINITION".equals(name)) return new GnoLabelDefinitionStubElementType(name);
    if ("PARAMETERS".equals(name)) return new GnoParametersStubElementType(name);
    if ("SIGNATURE".equals(name)) return new GnoSignatureStubElementType(name);
    if ("PARAMETER_DECLARATION".equals(name)) return new GnoParameterDeclarationStubElementType(name);
    if ("RESULT".equals(name)) return new GnoResultStubElementType(name);

    Class c = TYPES.get(name);
    if (c != null) {
      return new GnoTypeStubElementType(name) {
        @NotNull
        @Override
        public GnoType createPsi(@NotNull GnoTypeStub stub) {
          try {
            //noinspection unchecked
            return (GnoType)ReflectionUtil.createInstance(c.getConstructor(stub.getClass(), IStubElementType.class), stub, this);
          }
          catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
          }
        }
      };
    }
    throw new RuntimeException("Unknown element type: " + name);
  }
}
