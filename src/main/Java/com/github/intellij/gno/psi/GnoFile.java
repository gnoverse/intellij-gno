package com.github.intellij.gno.psi;

import com.github.intellij.gno.GnoTypes;
import com.github.intellij.gno.language.GnoFileType;
import com.github.intellij.gno.language.GnoLanguage;
import com.github.intellij.gno.psi.impl.GnoPsiImplUtil;
import com.github.intellij.gno.runconfig.testing.GnoTestFinder;
import com.github.intellij.gno.sdk.GnoPackageUtil;
import com.github.intellij.gno.sdk.GnoSdkUtil;
import com.github.intellij.gno.stubs.GnoConstSpecStub;
import com.github.intellij.gno.stubs.GnoFileStub;
import com.github.intellij.gno.stubs.GnoVarSpecStub;
import com.github.intellij.gno.stubs.types.*;
import com.github.intellij.gno.util.GnoUtil;
import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.*;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.SearchScope;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.CachedValueProvider;
import com.intellij.psi.util.CachedValuesManager;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.ArrayFactory;
import com.intellij.util.ArrayUtil;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.containers.MultiMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class GnoFile extends PsiFileBase {

  public GnoFile(@NotNull FileViewProvider viewProvider) {
    super(viewProvider, GnoLanguage.INSTANCE);
  }

  @Nullable
  public String getImportPath(boolean withVendoring) {
    return GnoSdkUtil.getImportPath(getParent(), withVendoring);
  }

  @NotNull
  @Override
  public GlobalSearchScope getResolveScope() {
    return GnoUtil.goPathResolveScope(this);
  }

  @NotNull
  @Override
  public SearchScope getUseScope() {
    return GnoUtil.goPathUseScope(this, true);
  }

  @Nullable
  public GnoPackageClause getPackage() {
    return CachedValuesManager.getCachedValue(this, () -> {
      GnoFileStub stub = getStub();
      if (stub != null) {
        StubElement<GnoPackageClause> packageClauseStub = stub.getPackageClauseStub();
        return CachedValueProvider.Result.create(packageClauseStub != null ? packageClauseStub.getPsi() : null, this);
      }
      return CachedValueProvider.Result.create(findChildByClass(GnoPackageClause.class), this);
    });
  }

  @Nullable
  public GnoImportList getImportList() {
    return findChildByClass(GnoImportList.class);
  }

  @Nullable
  public String getBuildFlags() {
    GnoFileStub stub = getStub();
    if (stub != null) {
      return stub.getBuildFlags();
    }

    // https://code.google.com/p/go/source/browse/src/pkg/go/build/build.go?r=2449e85a115014c3d9251f86d499e5808141e6bc#790
    Collection<String> buildFlags = ContainerUtil.newArrayList();
    int buildFlagLength = GnoConstants.BUILD_FLAG.length();
    for (PsiComment comment : getCommentsToConsider(this)) {
      String commentText = StringUtil.trimStart(comment.getText(), "//").trim();
      if (commentText.startsWith(GnoConstants.BUILD_FLAG) && commentText.length() > buildFlagLength
          && StringUtil.isWhiteSpace(commentText.charAt(buildFlagLength))) {
        ContainerUtil.addIfNotNull(buildFlags, StringUtil.nullize(commentText.substring(buildFlagLength).trim(), true));
      }
    }
    return !buildFlags.isEmpty() ? StringUtil.join(buildFlags, "|") : null;
  }


  @NotNull
  public List<GnoFunctionDeclaration> getFunctions() {
    return CachedValuesManager.getCachedValue(this, () -> {
      GnoFileStub stub = getStub();
      List<GnoFunctionDeclaration> functions = stub != null 
                                              ? getChildrenByType(stub, GnoTypes.FUNCTION_DECLARATION, GnoFunctionDeclarationStubElementType.ARRAY_FACTORY)
                                              : GnoPsiImplUtil.goTraverser().children(this).filter(GnoFunctionDeclaration.class).toList();
      return CachedValueProvider.Result.create(functions, this);
    });
  }

  @NotNull
  public List<GnoMethodDeclaration> getMethods() {
    return CachedValuesManager.getCachedValue(this, () -> {
      StubElement<GnoFile> stub = getStub();
      List<GnoMethodDeclaration> calc = stub != null
                                       ? getChildrenByType(stub, GnoTypes.METHOD_DECLARATION, GnoMethodDeclarationStubElementType.ARRAY_FACTORY)
                                       : GnoPsiImplUtil.goTraverser().children(this).filter(GnoMethodDeclaration.class).toList();
      return CachedValueProvider.Result.create(calc, this);
    });
  }

  @NotNull
  public List<GnoTypeSpec> getTypes() {
    return CachedValuesManager.getCachedValue(this, () -> {
      StubElement<GnoFile> stub = getStub();
      List<GnoTypeSpec> types = stub != null ? getChildrenByType(stub, GnoTypes.TYPE_SPEC, GnoTypeSpecStubElementType.ARRAY_FACTORY) 
                                            : calcTypes();
      return CachedValueProvider.Result.create(types, this);
    });
  }

  @NotNull
  public List<GnoImportSpec> getImports() {
    return CachedValuesManager.getCachedValue(this, () -> {
      StubElement<GnoFile> stub = getStub();
      List<GnoImportSpec> imports = stub != null ? getChildrenByType(stub, GnoTypes.IMPORT_SPEC, GnoImportSpecStubElementType.ARRAY_FACTORY) 
                                                : calcImports();
      return CachedValueProvider.Result.create(imports, this);
    });
  }

  public GnoImportSpec addImport(String path, String alias) {
    GnoImportList importList = getImportList();
    if (importList != null) {
      return importList.addImport(path, alias);
    }
    return null;
  }

  /**
   * @return map like { import path -> import spec } for file
   */
  @NotNull
  public Map<String, GnoImportSpec> getImportedPackagesMap() {
    return CachedValuesManager.getCachedValue(this, () -> {
      Map<String, GnoImportSpec> map = ContainerUtil.newHashMap();
      for (GnoImportSpec spec : getImports()) {
        if (!spec.isForSideEffects()) {
          String importPath = spec.getPath();
          if (StringUtil.isNotEmpty(importPath)) {
            map.put(importPath, spec);
          }
        }
      }
      return CachedValueProvider.Result.create(map, this);
    });
  }

  /**
   * @return map like { local package name, maybe alias -> import spec } for file
   */
  @NotNull
  public MultiMap<String, GnoImportSpec> getImportMap() {
    return CachedValuesManager.getCachedValue(this, () -> {
      MultiMap<String, GnoImportSpec> map = MultiMap.createLinked();
      List<Object> dependencies = ContainerUtil.newArrayList(this);
      Module module = ModuleUtilCore.findModuleForPsiElement(this);
      for (GnoImportSpec spec : getImports()) {
        String alias = spec.getAlias();
        if (alias != null) {
          map.putValue(alias, spec);
          continue;
        }
        if (spec.isDot()) {
          map.putValue(".", spec);
          continue;
        }
        GnoImportString string = spec.getImportString();
        PsiDirectory dir = string.resolve();
        // todo[zolotov]: implement package modification tracker
        ContainerUtil.addIfNotNull(dependencies, dir);
        Collection<String> packagesInDirectory = GnoPackageUtil.getAllPackagesInDirectory(dir, module, true);
        if (!packagesInDirectory.isEmpty()) {
          for (String packageNames : packagesInDirectory) {
            if (!StringUtil.isEmpty(packageNames)) {
              map.putValue(packageNames, spec);
            }
          }
        }
        else {
          String key = spec.getLocalPackageName();
          if (!StringUtil.isEmpty(key)) {
            map.putValue(key, spec);
          }
        }
      }
      return CachedValueProvider.Result.create(map, ArrayUtil.toObjectArray(dependencies));
    });
  }

  @NotNull
  public List<GnoVarDefinition> getVars() {
    return CachedValuesManager.getCachedValue(this, () -> {
      List<GnoVarDefinition> result;
      StubElement<GnoFile> stub = getStub();
      if (stub != null) {
        result = ContainerUtil.newArrayList();
        List<GnoVarSpec> varSpecs = getChildrenByType(stub, GnoTypes.VAR_SPEC, GnoVarSpecStubElementType.ARRAY_FACTORY);
        for (GnoVarSpec spec : varSpecs) {
          GnoVarSpecStub specStub = spec.getStub();
          if (specStub == null) continue;
          result.addAll(getChildrenByType(specStub, GnoTypes.VAR_DEFINITION, GnoVarDefinitionStubElementType.ARRAY_FACTORY));
        }
      }
      else {
        result = calcVars();
      }
      return CachedValueProvider.Result.create(result, this);
    });
  }

  @NotNull
  public List<GnoConstDefinition> getConstants() {
    return CachedValuesManager.getCachedValue(this, () -> {
      StubElement<GnoFile> stub = getStub();
      List<GnoConstDefinition> result;
      if (stub != null) {
        result = ContainerUtil.newArrayList();
        List<GnoConstSpec> constSpecs = getChildrenByType(stub, GnoTypes.CONST_SPEC, GnoConstSpecStubElementType.ARRAY_FACTORY);
        for (GnoConstSpec spec : constSpecs) {
          GnoConstSpecStub specStub = spec.getStub();
          if (specStub == null) continue;
          result.addAll(getChildrenByType(specStub, GnoTypes.CONST_DEFINITION, GnoConstDefinitionStubElementType.ARRAY_FACTORY));
        }
      }
      else {
        result = calcConsts();
      }
      return CachedValueProvider.Result.create(result, this);
    });
  }

  @NotNull
  private List<GnoTypeSpec> calcTypes() {
    return GnoPsiImplUtil.goTraverser().children(this).filter(GnoTypeDeclaration.class).flatten(GnoTypeDeclaration::getTypeSpecList).toList();
  }

  @NotNull
  private List<GnoImportSpec> calcImports() {
    GnoImportList list = getImportList();
    if (list == null) return ContainerUtil.emptyList();
    List<GnoImportSpec> result = ContainerUtil.newArrayList();
    for (GnoImportDeclaration declaration : list.getImportDeclarationList()) {
      result.addAll(declaration.getImportSpecList());
    }
    return result;
  }

  @NotNull
  private List<GnoVarDefinition> calcVars() {
    return GnoPsiImplUtil.goTraverser().children(this).filter(GnoVarDeclaration.class)
      .flatten(GnoVarDeclaration::getVarSpecList)
      .flatten(GnoVarSpec::getVarDefinitionList).toList();
  }

  @NotNull
  private List<GnoConstDefinition> calcConsts() {
    return GnoPsiImplUtil.goTraverser().children(this).filter(GnoConstDeclaration.class)
      .flatten(GnoConstDeclaration::getConstSpecList)
      .flatten(GnoConstSpec::getConstDefinitionList).toList();
  }

  @NotNull
  @Override
  public FileType getFileType() {
    return GnoFileType.INSTANCE;
  }

  public boolean hasMainFunction() { // todo create a map for faster search
    List<GnoFunctionDeclaration> functions = getFunctions();
    for (GnoFunctionDeclaration function : functions) {
      if (GnoConstants.MAIN.equals(function.getName())) {
        return true;
      }
    }
    return false;
  }

  @Nullable
  public String getPackageName() {
    return CachedValuesManager.getCachedValue(this, () -> {
      GnoFileStub stub = getStub();
      if (stub != null) {
        return CachedValueProvider.Result.create(stub.getPackageName(), this);
      }
      GnoPackageClause packageClause = getPackage();
      return CachedValueProvider.Result.create(packageClause != null ? packageClause.getName() : null, this);
    });
  }

  public String getCanonicalPackageName() {
    String packageName = getPackageName();
    if (StringUtil.isNotEmpty(packageName) && GnoTestFinder.isTestFile(this)) {
      return StringUtil.trimEnd(packageName, GnoConstants.TEST_SUFFIX);
    }
    return packageName;
  }

  @Nullable
  @Override
  public GnoFileStub getStub() {
    //noinspection unchecked
    return (GnoFileStub)super.getStub();
  }

  public boolean hasCPathImport() {
    return getImportedPackagesMap().containsKey(GnoConstants.C_PATH);
  }

  public void deleteImport(@NotNull GnoImportSpec importSpec) {
    GnoImportDeclaration importDeclaration = PsiTreeUtil.getParentOfType(importSpec, GnoImportDeclaration.class);
    assert importDeclaration != null;
    PsiElement elementToDelete = importDeclaration.getImportSpecList().size() == 1 ? importDeclaration : importSpec;
    elementToDelete.delete();
  }

  @NotNull
  private static <E extends PsiElement> List<E> getChildrenByType(@NotNull StubElement<? extends PsiElement> stub,
                                                                  IElementType elementType,
                                                                  ArrayFactory<E> f) {
    return Arrays.asList(stub.getChildrenByType(elementType, f));
  }

  @NotNull
  private static Collection<PsiComment> getCommentsToConsider(@NotNull GnoFile file) {
    Collection<PsiComment> commentsToConsider = ContainerUtil.newArrayList();
    PsiElement child = file.getFirstChild();
    int lastEmptyLineOffset = 0;
    while (child != null) {
      if (child instanceof PsiComment) {
        commentsToConsider.add((PsiComment)child);
      }
      else if (child instanceof PsiWhiteSpace) {
        if (StringUtil.countChars(child.getText(), '\n') > 1) {
          lastEmptyLineOffset = child.getTextRange().getStartOffset();
        }
      }
      else {
        break;
      }
      child = child.getNextSibling();
    }
    int finalLastEmptyLineOffset = lastEmptyLineOffset;
    return ContainerUtil.filter(commentsToConsider, comment -> comment.getTextRange().getStartOffset() < finalLastEmptyLineOffset);
  }
}