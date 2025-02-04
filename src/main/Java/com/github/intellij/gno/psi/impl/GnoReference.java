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
import com.github.intellij.gno.psi.*;
import com.github.intellij.gno.util.GnoUtil;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.util.*;
import com.intellij.psi.*;
import com.intellij.psi.impl.source.resolve.ResolveCache;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.ArrayUtil;
import com.intellij.util.IncorrectOperationException;
import com.intellij.util.ObjectUtils;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.containers.OrderedSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;

import static com.github.intellij.gno.psi.impl.GnoPsiImplUtil.*;

public class GnoReference extends GnoReferenceBase<GnoReferenceExpressionBase> {
  private static final Key<Object> POINTER = Key.create("POINTER");
  private static final Key<Object> DONT_PROCESS_METHODS = Key.create("DONT_PROCESS_METHODS");

  private static final ResolveCache.PolyVariantResolver<GnoReference> MY_RESOLVER =
    (r, incompleteCode) -> r.resolveInner();

  public GnoReference(@NotNull GnoReferenceExpressionBase o) {
    super(o, TextRange.from(o.getIdentifier().getStartOffsetInParent(), o.getIdentifier().getTextLength()));
  }

  @Nullable
  static PsiFile getContextFile(@NotNull ResolveState state) {
    PsiElement element = getContextElement(state);
    return element != null ? element.getContainingFile() : null;
  }

  @NotNull
  private ResolveResult[] resolveInner() {
    if (!myElement.isValid()) return ResolveResult.EMPTY_ARRAY;
    Collection<ResolveResult> result = new OrderedSet<>();
    processResolveVariants(createResolveProcessor(result, myElement));
    return result.toArray(new ResolveResult[result.size()]);
  }
  
  @Override
  public boolean isReferenceTo(@NotNull PsiElement element) {
    return GnoUtil.couldBeReferenceTo(element, myElement) && super.isReferenceTo(element);
  }

  @NotNull
  private PsiElement getIdentifier() {
    return myElement.getIdentifier();
  }

  @Override
  @NotNull
  public ResolveResult[] multiResolve(boolean incompleteCode) {
    if (!myElement.isValid()) return ResolveResult.EMPTY_ARRAY;
    return ResolveCache.getInstance(myElement.getProject()).resolveWithCaching(this, MY_RESOLVER, false, false);
  }

  @NotNull
  @Override
  public Object[] getVariants() {
    return ArrayUtil.EMPTY_OBJECT_ARRAY;
  }

  public boolean processResolveVariants(@NotNull GnoScopeProcessor processor) {
    PsiFile file = myElement.getContainingFile();
    if (!(file instanceof GnoFile)) return false;
    ResolveState state = createContextOnElement(myElement);
    GnoReferenceExpressionBase qualifier = myElement.getQualifier();
    return qualifier != null
           ? processQualifierExpression((GnoFile)file, qualifier, processor, state)
           : processUnqualifiedResolve((GnoFile)file, processor, state);
  }

  private boolean processQualifierExpression(@NotNull GnoFile file,
                                             @NotNull GnoReferenceExpressionBase qualifier,
                                             @NotNull GnoScopeProcessor processor,
                                             @NotNull ResolveState state) {
    PsiReference reference = qualifier.getReference();
    PsiElement target = reference != null ? reference.resolve() : null;
    if (target == null) return false;
    if (target == qualifier) return processor.execute(myElement, state);
    if (target instanceof GnoImportSpec) {
      if (((GnoImportSpec)target).isCImport()) return processor.execute(myElement, state);
      target = ((GnoImportSpec)target).getImportString().resolve();
    }
    if (target instanceof PsiDirectory && !processDirectory((PsiDirectory)target, file, null, processor, state, false)) return false;
    if (target instanceof GnoTypeOwner) {
      GnoType type = typeOrParameterType((GnoTypeOwner)target, createContextOnElement(myElement));
      if (type instanceof GnoCType) return processor.execute(myElement, state);
      if (type != null) {
        if (!processGnoType(type, processor, state)) return false;
        GnoTypeReferenceExpression ref = getTypeRefExpression(type);
        if (ref != null && ref.resolve() == ref) return processor.execute(myElement, state); // a bit hacky resolve for: var a C.foo; a.b
      }
    }
    return true;
  }

  private boolean processGnoType(@NotNull GnoType type, @NotNull GnoScopeProcessor processor, @NotNull ResolveState state) {
    Boolean result = RecursionManager.doPreventingRecursion(type, true, () -> {
      if (type instanceof GnoParType) return processGnoType(((GnoParType)type).getActualType(), processor, state);
      if (!processExistingType(type, processor, state)) return false;
      if (type instanceof GnoPointerType) {
        if (!processPointer((GnoPointerType)type, processor, state.put(POINTER, true))) return false;
        GnoType pointer = ((GnoPointerType)type).getType();
        if (pointer instanceof GnoPointerType) {
          return processPointer((GnoPointerType)pointer, processor, state.put(POINTER, true));
        }
      }
      return processTypeRef(type, processor, state);
    });
    return Boolean.TRUE.equals(result);
  }

  private boolean processPointer(@NotNull GnoPointerType type, @NotNull GnoScopeProcessor processor, @NotNull ResolveState state) {
    GnoType pointer = type.getType();
    return pointer == null || processExistingType(pointer, processor, state) && processTypeRef(pointer, processor, state);
  }

  private boolean processTypeRef(@Nullable GnoType type, @NotNull GnoScopeProcessor processor, @NotNull ResolveState state) {
    if (type == null) {
      return true;
    }
    if (builtin(type)) {
      // do not process builtin types like 'int int' or 'string string'
      return true;
    }
    return processInTypeRef(type.getTypeReferenceExpression(), processor, state);
  }

  private boolean processExistingType(@NotNull GnoType type, @NotNull GnoScopeProcessor processor, @NotNull ResolveState state) {
    PsiFile file = type.getContainingFile();
    if (!(file instanceof GnoFile)) return true;
    PsiFile myFile = ObjectUtils.notNull(getContextFile(state), myElement.getContainingFile());
    if (!(myFile instanceof GnoFile) || !allowed(file, myFile, ModuleUtilCore.findModuleForPsiElement(myFile))) return true;

    boolean localResolve = isLocalResolve(myFile, file);

    GnoTypeSpec parent = getTypeSpecSafe(type);
    boolean canProcessMethods = state.get(DONT_PROCESS_METHODS) == null;
    if (canProcessMethods && parent != null && !processNamedElements(processor, state, parent.getMethods(), localResolve, true)) return false;

    if (type instanceof GnoSpecType) {
      type = type.getUnderlyingType();
    }
    if (type instanceof GnoStructType) {
      GnoScopeProcessorBase delegate = createDelegate(processor);
      type.processDeclarations(delegate, ResolveState.initial(), null, myElement);
      List<GnoTypeReferenceExpression> interfaceRefs = ContainerUtil.newArrayList();
      List<GnoTypeReferenceExpression> structRefs = ContainerUtil.newArrayList();
      for (GnoFieldDeclaration d : ((GnoStructType)type).getFieldDeclarationList()) {
        if (!processNamedElements(processor, state, d.getFieldDefinitionList(), localResolve)) return false;
        GnoAnonymousFieldDefinition anon = d.getAnonymousFieldDefinition();
        GnoTypeReferenceExpression ref = anon != null ? anon.getTypeReferenceExpression() : null;
        if (ref != null) {
          (anon.getType() instanceof GnoPointerType ? structRefs : interfaceRefs).add(ref);
          if (!processNamedElements(processor, state, ContainerUtil.createMaybeSingletonList(anon), localResolve)) return false;
        }
      }
      if (!processCollectedRefs(interfaceRefs, processor, state.put(POINTER, null))) return false;
      if (!processCollectedRefs(structRefs, processor, state)) return false;
    }
    else if (state.get(POINTER) == null && type instanceof GnoInterfaceType) {
      if (!processNamedElements(processor, state, ((GnoInterfaceType)type).getMethods(), localResolve, true)) return false;
      if (!processCollectedRefs(((GnoInterfaceType)type).getBaseTypesReferences(), processor, state)) return false;
    }
    else if (type instanceof GnoFunctionType) {
      GnoSignature signature = ((GnoFunctionType)type).getSignature();
      GnoResult result = signature != null ? signature.getResult() : null;
      GnoType resultType = result != null ? result.getType() : null;
      if (resultType != null && !processGnoType(resultType, processor, state)) return false;
    }
    return true;
  }

  public static boolean isLocalResolve(@NotNull PsiFile originFile, @NotNull PsiFile externalFile) {
    if (!(originFile instanceof GnoFile) || !(externalFile instanceof GnoFile)) return false;
    GnoFile o1 = (GnoFile)originFile.getOriginalFile();
    GnoFile o2 = (GnoFile)externalFile.getOriginalFile();
    return Comparing.equal(o1.getImportPath(false), o2.getImportPath(false)) 
           && Comparing.equal(o1.getPackageName(), o2.getPackageName());
  }

  private boolean processCollectedRefs(@NotNull List<GnoTypeReferenceExpression> refs,
                                       @NotNull GnoScopeProcessor processor,
                                       @NotNull ResolveState state) {
    for (GnoTypeReferenceExpression ref : refs) {
      if (!processInTypeRef(ref, processor, state)) return false;
    }
    return true;
  }

  private boolean processInTypeRef(@Nullable GnoTypeReferenceExpression e, @NotNull GnoScopeProcessor processor, @NotNull ResolveState state) {
    PsiElement resolve = e != null ? e.resolve() : null;
    if (resolve instanceof GnoTypeOwner) {
      GnoType type = ((GnoTypeOwner)resolve).getGnoType(state);
      if (type == null) return true;
      if (!processGnoType(type, processor, state)) return false;
      if (type instanceof GnoSpecType) {
        GnoType inner = ((GnoSpecType)type).getType();
        if (inner instanceof GnoPointerType && state.get(POINTER) != null) return true;
        if (inner != null && !processGnoType(inner, processor, state.put(DONT_PROCESS_METHODS, true))) return false;
      }
      return true;
    }
    return true;
  }

  private boolean processUnqualifiedResolve(@NotNull GnoFile file,
                                            @NotNull GnoScopeProcessor processor,
                                            @NotNull ResolveState state) {
    if (getIdentifier().textMatches("_")) return processor.execute(myElement, state);

    PsiElement parent = myElement.getParent();

    if (parent instanceof GnoSelectorExpr) {
      boolean result = processSelector((GnoSelectorExpr)parent, processor, state, myElement);
      if (processor.isCompletion()) return result;
      if (!result || prevDot(myElement)) return false;
    }

    PsiElement grandPa = parent.getParent();
    if (grandPa instanceof GnoSelectorExpr && !processSelector((GnoSelectorExpr)grandPa, processor, state, parent)) return false;
    
    if (prevDot(parent)) return false;

    if (!processBlock(processor, state, true)) return false;
    if (!processReceiver(processor, state, true)) return false;
    if (!processImports(file, processor, state, myElement)) return false;
    if (!processFileEntities(file, processor, state, true)) return false;
    if (!processDirectory(file.getOriginalFile().getParent(), file, file.getPackageName(), processor, state, true)) return false;
    return processBuiltin(processor, state, myElement);
  }

  private boolean processReceiver(@NotNull GnoScopeProcessor processor, @NotNull ResolveState state, boolean localResolve) {
    GnoScopeProcessorBase delegate = createDelegate(processor);
    GnoMethodDeclaration method = PsiTreeUtil.getParentOfType(myElement, GnoMethodDeclaration.class);
    GnoReceiver receiver = method != null ? method.getReceiver() : null;
    if (receiver == null) return true;
    receiver.processDeclarations(delegate, ResolveState.initial(), null, myElement);
    return processNamedElements(processor, state, delegate.getVariants(), localResolve);
  }

  private boolean processBlock(@NotNull GnoScopeProcessor processor, @NotNull ResolveState state, boolean localResolve) {
    GnoScopeProcessorBase delegate = createDelegate(processor);
    ResolveUtil.treeWalkUp(myElement, delegate);
    return processNamedElements(processor, state, delegate.getVariants(), localResolve);
  }

  private boolean processSelector(@NotNull GnoSelectorExpr parent,
                                  @NotNull GnoScopeProcessor processor,
                                  @NotNull ResolveState state,
                                  @Nullable PsiElement another) {
    List<GnoExpression> list = parent.getExpressionList();
    if (list.size() > 1 && list.get(1).isEquivalentTo(another)) {
      GnoExpression e = list.get(0);
      List<GnoReferenceExpression> refs = ContainerUtil.newArrayList(PsiTreeUtil.findChildrenOfType(e, GnoReferenceExpression.class));
      GnoExpression o = refs.size() > 1 ? refs.get(refs.size() - 1) : e;
      PsiReference ref = o.getReference();
      PsiElement resolve = ref != null ? ref.resolve() : null;
      if (resolve == o) return processor.execute(myElement, state); // var c = C.call(); c.a.b.d;
      GnoType type = e.getGnoType(createContextOnElement(myElement));
      if (type != null && !processGnoType(type, processor, state)) return false;
    }
    return true;
  }

  @NotNull
  private GnoVarProcessor createDelegate(@NotNull GnoScopeProcessor processor) {
    return new GnoVarProcessor(getIdentifier(), myElement, processor.isCompletion(), true) {
      @Override
      protected boolean crossOff(@NotNull PsiElement e) {
        if (e instanceof GnoFieldDefinition) return true;
        return super.crossOff(e) && !(e instanceof GnoTypeSpec);
      }
    };
  }

  @Override
  protected boolean processFileEntities(@NotNull GnoFile file,
                                        @NotNull GnoScopeProcessor processor,
                                        @NotNull ResolveState state,
                                        boolean localProcessing) {
    if (!processNamedElements(processor, state, file.getConstants(), o -> !Comparing.equal(GnoConstants.IOTA, o.getName()) ||
                                                                    !builtin(o) ||
           PsiTreeUtil.getParentOfType(getContextElement(state), GnoConstSpec.class) != null, localProcessing, false)) return false;
    if (!processNamedElements(processor, state, file.getVars(), localProcessing)) return false;
    Condition<GnoNamedElement> dontProcessInit = o -> o instanceof GnoFunctionDeclaration && !Comparing.equal(o.getName(), GnoConstants.INIT);
    if (!processNamedElements(processor, state, file.getFunctions(), dontProcessInit, localProcessing, false)) return false;
    return processNamedElements(processor, state, file.getTypes(), localProcessing);
  }

  @NotNull
  @Override
  public PsiElement handleElementRename(@NotNull String newElementName) throws IncorrectOperationException {
    getIdentifier().replace(GnoElementFactory.createIdentifierFromText(myElement.getProject(), newElementName));
    return myElement;
  }

  @Override
  public boolean equals(Object o) {
    return this == o || o instanceof GnoReference && getElement() == ((GnoReference)o).getElement();
  }

  @Override
  public int hashCode() {
    return getElement().hashCode();
  }
}