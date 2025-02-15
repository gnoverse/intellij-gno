/*
 * Copyright 2013-2016 Sergey Ignatov, Alexander Zolotov, Florin Patan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.intellij.gno.psi.impl;

import com.github.intellij.gno.psi.GnoConstants;
import com.github.intellij.gno.GnoTypes;
import com.github.intellij.gno.psi.*;
import com.github.intellij.gno.sdk.GnoSdkUtil;
import com.github.intellij.gno.util.GnoUtil;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.psi.formatter.FormatterUtil;
import com.intellij.psi.impl.source.resolve.ResolveCache;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.ArrayUtil;
import com.intellij.util.IncorrectOperationException;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.containers.OrderedSet;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class GnoTypeReference extends GnoReferenceBase<GnoTypeReferenceExpression> {
  private final boolean myInsideInterfaceType;

  public GnoTypeReference(@NotNull GnoTypeReferenceExpression o) {
    super(o, TextRange.from(o.getIdentifier().getStartOffsetInParent(), o.getIdentifier().getTextLength()));
    myInsideInterfaceType = myElement.getParent() instanceof GnoMethodSpec;
  }

  private static final ResolveCache.PolyVariantResolver<PsiPolyVariantReferenceBase> MY_RESOLVER =
    (psiPolyVariantReferenceBase, incompleteCode) -> ((GnoTypeReference)psiPolyVariantReferenceBase).resolveInner();

  @NotNull
  private ResolveResult[] resolveInner() {
    Collection<ResolveResult> result = new OrderedSet<>();
    processResolveVariants(createResolveProcessor(result, myElement));
    return result.toArray(new ResolveResult[result.size()]);
  }

  @Override
  public boolean isReferenceTo(PsiElement element) {
    return GnoUtil.couldBeReferenceTo(element, myElement) && super.isReferenceTo(element);
  }

  @NotNull
  private PsiElement getIdentifier() {
    return myElement.getIdentifier();
  }

  @Override
  @NotNull
  public ResolveResult[] multiResolve(boolean incompleteCode) {
    return myElement.isValid()
           ? ResolveCache.getInstance(myElement.getProject()).resolveWithCaching(this, MY_RESOLVER, false, false)
           : ResolveResult.EMPTY_ARRAY;
  }

  @NotNull
  @Override
  public Object[] getVariants() {
    return ArrayUtil.EMPTY_OBJECT_ARRAY;
  }

  public boolean processResolveVariants(@NotNull GnoScopeProcessor processor) {
    PsiFile file = myElement.getContainingFile();
    if (!(file instanceof GnoFile)) return false;
    ResolveState state = ResolveState.initial();
    GnoTypeReferenceExpression qualifier = myElement.getQualifier();
    if (qualifier != null) {
      return processQualifierExpression((GnoFile)file, qualifier, processor, state);
    }
    return processUnqualifiedResolve((GnoFile)file, processor, state, true);
  }

  private boolean processQualifierExpression(@NotNull GnoFile file,
                                             @NotNull GnoTypeReferenceExpression qualifier,
                                             @NotNull GnoScopeProcessor processor,
                                             @NotNull ResolveState state) {
    PsiElement target = qualifier.resolve();
    if (target == null || target == qualifier) return false;
    if (target instanceof GnoImportSpec) {
      if (((GnoImportSpec)target).isCImport()) return processor.execute(myElement, state);
      target = ((GnoImportSpec)target).getImportString().resolve();
    }
    if (target instanceof PsiDirectory) {
      processDirectory((PsiDirectory)target, file, null, processor, state, false);
    }
    return false;
  }

  private boolean processUnqualifiedResolve(@NotNull GnoFile file,
                                            @NotNull GnoScopeProcessor processor,
                                            @NotNull ResolveState state,
                                            boolean localResolve) {
    GnoScopeProcessorBase delegate = createDelegate(processor);
    ResolveUtil.treeWalkUp(myElement, delegate);
    Collection<? extends GnoNamedElement> result = delegate.getVariants();
    if (!processNamedElements(processor, state, result, localResolve)) return false;
    if (!processFileEntities(file, processor, state, localResolve)) return false;
    PsiDirectory dir = file.getOriginalFile().getParent();
    if (!processDirectory(dir, file, file.getPackageName(), processor, state, true)) return false;
    if (PsiTreeUtil.getParentOfType(getElement(), GnoReceiver.class) != null) return true;
    if (!processImports(file, processor, state, myElement)) return false;
    if (!processBuiltin(processor, state, myElement)) return false;
    if (getIdentifier().textMatches(GnoConstants.NIL) && PsiTreeUtil.getParentOfType(myElement, GnoTypeCaseClause.class) != null) {
      GnoType type = PsiTreeUtil.getParentOfType(myElement, GnoType.class);
      if (FormatterUtil.getPrevious(type != null ? type.getNode() : null, GnoTypes.CASE) == null) return true;
      GnoFile builtinFile = GnoSdkUtil.findBuiltinFile(myElement);
      if (builtinFile == null) return false;
      GnoVarDefinition nil = ContainerUtil.find(builtinFile.getVars(), v -> GnoConstants.NIL.equals(v.getName()));
      if (nil != null && !processor.execute(nil, state)) return false;
    }
    return true;
  }

  public final static Set<String> DOC_ONLY_TYPES = ContainerUtil.set("Type", "Type1", "IntegerType", "FloatType", "ComplexType");
  private static final Condition<GnoTypeSpec> BUILTIN_TYPE = spec -> {
    String name = spec.getName();
    return name != null && !DOC_ONLY_TYPES.contains(name);
  };

  @NotNull
  private GnoTypeProcessor createDelegate(@NotNull GnoScopeProcessor processor) {
    return new GnoTypeProcessor(myElement, processor.isCompletion());
  }

  @Override
  protected boolean processFileEntities(@NotNull GnoFile file,
                                        @NotNull GnoScopeProcessor processor,
                                        @NotNull ResolveState state,
                                        boolean localProcessing) {
    List<GnoTypeSpec> types = GnoPsiImplUtil.isBuiltinFile(file) ? ContainerUtil.filter(file.getTypes(), BUILTIN_TYPE) : file.getTypes();
    return processNamedElements(processor, state, types, localProcessing);
  }

  private boolean processNamedElements(@NotNull PsiScopeProcessor processor,
                                       @NotNull ResolveState state,
                                       @NotNull Collection<? extends GnoNamedElement> elements, boolean localResolve) {
    for (GnoNamedElement definition : elements) {
      if (definition instanceof GnoTypeSpec && !allowed((GnoTypeSpec)definition)) continue;
      if ((definition.isPublic() || localResolve) && !processor.execute(definition, state)) return false;
    }
    return true;
  }

  public boolean allowed(@NotNull GnoTypeSpec definition) {
    return !myInsideInterfaceType || definition.getSpecType().getType() instanceof GnoInterfaceType;
  }

  @Override
  public PsiElement handleElementRename(String newElementName) throws IncorrectOperationException {
    getIdentifier().replace(GnoElementFactory.createIdentifierFromText(myElement.getProject(), newElementName));
    return myElement;
  }
}
