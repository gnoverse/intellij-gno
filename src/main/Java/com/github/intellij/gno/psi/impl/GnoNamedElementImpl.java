package com.github.intellij.gno.psi.impl;

import com.github.intellij.gno.language.GnoIcons;
import com.github.intellij.gno.project.GnoVendoringUtil;
import com.github.intellij.gno.psi.*;
import com.github.intellij.gno.sdk.GnoPackageUtil;
import com.github.intellij.gno.stubs.GnoNamedStub;
import com.github.intellij.gno.util.GnoUtil;
import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.util.Iconable;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.LocalSearchScope;
import com.intellij.psi.search.SearchScope;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.util.CachedValueProvider;
import com.intellij.psi.util.CachedValuesManager;
import com.intellij.psi.util.PsiModificationTracker;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.ui.IconManager;
import com.intellij.ui.RowIcon;
import com.intellij.usageView.UsageViewUtil;
import com.intellij.util.IncorrectOperationException;
import com.intellij.util.ObjectUtils;
import com.intellij.util.PlatformIcons;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public abstract class GnoNamedElementImpl<T extends GnoNamedStub<?>> extends GnoStubbedElementImpl<T> implements GnoCompositeElement, GnoNamedElement {

  public GnoNamedElementImpl(@NotNull T stub, @NotNull IStubElementType nodeType) {
    super(stub, nodeType);
  }

  public GnoNamedElementImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public boolean isPublic() {
    if (GnoPsiImplUtil.builtin(this)) return true;
    T stub = getStub();
    return stub != null ? stub.isPublic() : StringUtil.isCapitalized(getName());
  }

  @Nullable
  @Override
  public PsiElement getNameIdentifier() {
    return getIdentifier();
  }

  @Nullable
  @Override
  public String getName() {
    T stub = getStub();
    if (stub != null) {
      return stub.getName();
    }
    PsiElement identifier = getIdentifier();
    return identifier != null ? identifier.getText() : null;
  }
  
  @Nullable
  @Override
  public String getQualifiedName() {
    String name = getName();
    if (name == null) return null;
    String packageName = getContainingFile().getPackageName();
    return GnoPsiImplUtil.getFqn(packageName, name);
  }

  @Override
  public int getTextOffset() {
    PsiElement identifier = getIdentifier();
    return identifier != null ? identifier.getTextOffset() : super.getTextOffset();
  }

  @NotNull
  @Override
  public PsiElement setName(@NonNls @NotNull String newName) throws IncorrectOperationException {
    PsiElement identifier = getIdentifier();
    if (identifier != null) {
      identifier.replace(GnoElementFactory.createIdentifierFromText(getProject(), newName));
    }
    return this;
  }

  @Nullable
  @Override
  public GnoType getGnoType(@Nullable ResolveState context) {
    if (context != null) return getGnoTypeInner(context);
    return CachedValuesManager.getCachedValue(this,
                                              () -> CachedValueProvider.Result
                                                .create(getGnoTypeInner(GnoPsiImplUtil.createContextOnElement(this)),
                                                        PsiModificationTracker.MODIFICATION_COUNT));
  }

  @Nullable
  protected GnoType getGnoTypeInner(@Nullable ResolveState context) {
    return findSiblingType();
  }

  @Nullable
  @Override
  public GnoType findSiblingType() {
    T stub = getStub();
    if (stub != null) {
      return GnoPsiTreeUtil.getStubChildOfType(getParentByStub(), GnoType.class);
    }
    return PsiTreeUtil.getNextSiblingOfType(this, GnoType.class);
  }

  @Override
  public boolean processDeclarations(@NotNull PsiScopeProcessor processor,
                                     @NotNull ResolveState state,
                                     PsiElement lastParent,
                                     @NotNull PsiElement place) {
    return GnoCompositeElementImpl.processDeclarationsDefault(this, processor, state, lastParent, place);
  }

  @Override
  public ItemPresentation getPresentation() {
    String text = UsageViewUtil.createNodeText(this);
    if (text != null) {
      boolean vendoringEnabled = GnoVendoringUtil.isVendoringEnabled(ModuleUtilCore.findModuleForPsiElement(getContainingFile()));
      return new ItemPresentation() {
        @Nullable
        @Override
        public String getPresentableText() {
          return getName();
        }

        @Nullable
        @Override
        public String getLocationString() {
          GnoFile file = getContainingFile();
          String fileName = file.getName();
          String importPath = ObjectUtils.chooseNotNull(file.getImportPath(vendoringEnabled), file.getPackageName());
          return "in " + (importPath != null ? importPath  + "/" + fileName : fileName);
        }

        @Nullable
        @Override
        public Icon getIcon(boolean b) {
          return GnoNamedElementImpl.this.getIcon(Iconable.ICON_FLAG_VISIBILITY);
        }
      };
    }
    return super.getPresentation();
  }

  @Nullable
  @Override
  public Icon getIcon(int flags) {
    Icon icon = getIcon();
    if (icon != null) {
      if ((flags & Iconable.ICON_FLAG_VISIBILITY) != 0) {
        Icon visibilityIcon = isPublic() ? PlatformIcons.PUBLIC_ICON
                : PlatformIcons.PRIVATE_ICON;

        // On crée un RowIcon à 2 couches
        RowIcon rowIcon = (RowIcon) IconManager.getInstance().createRowIcon(2);
        rowIcon.setIcon(icon, 0);
        rowIcon.setIcon(visibilityIcon, 1);
        return rowIcon;
      }
      return icon;
    }
    return super.getIcon(flags);
  }

  private @Nullable Icon getIcon() {
    Icon icon = null;
    if (this instanceof GnoMethodDeclaration) icon = GnoIcons.METHOD;
    else if (this instanceof GnoFunctionDeclaration) icon = GnoIcons.FUNCTION;
    else if (this instanceof GnoTypeSpec) icon = GnoIcons.TYPE;
    else if (this instanceof GnoVarDefinition) icon = GnoIcons.VARIABLE;
    else if (this instanceof GnoConstDefinition) icon = GnoIcons.CONSTANT;
    else if (this instanceof GnoFieldDefinition) icon = GnoIcons.FIELD;
    else if (this instanceof GnoMethodSpec) icon = GnoIcons.METHOD;
    else if (this instanceof GnoAnonymousFieldDefinition) icon = GnoIcons.FIELD;
    else if (this instanceof GnoParamDefinition) icon = GnoIcons.PARAMETER;
    else if (this instanceof GnoLabelDefinition) icon = GnoIcons.LABEL;
    return icon;
  }

  @NotNull
  @Override
  public GlobalSearchScope getResolveScope() {
    return isPublic() ? GnoUtil.goPathResolveScope(this) : GnoPackageUtil.packageScope(getContainingFile());
  }

  @NotNull
  @Override
  public SearchScope getUseScope() {
    if (this instanceof GnoVarDefinition || this instanceof GnoConstDefinition || this instanceof GnoLabelDefinition) {
      GnoBlock block = PsiTreeUtil.getParentOfType(this, GnoBlock.class);
      if (block != null) return new LocalSearchScope(block);
    }
    if (!isPublic()) {
      return GnoPackageUtil.packageScope(getContainingFile());
    }
    GnoSpecType parentType = PsiTreeUtil.getStubOrPsiParentOfType(this, GnoSpecType.class);
    if (parentType != null) {
      GnoTypeSpec typeSpec = GnoPsiImplUtil.getTypeSpecSafe(parentType);
      if (typeSpec != null && !StringUtil.isCapitalized(typeSpec.getName())) {
        return GnoPackageUtil.packageScope(getContainingFile());
      }
    }
    return GnoUtil.goPathUseScope(this, !(this instanceof GnoMethodDeclaration));
  }

  @Override
  public boolean isBlank() {
    return StringUtil.equals(getName(), "_");
  }

  @Override
  public boolean shouldGnoDeeper() {
    return true;
  }
}
