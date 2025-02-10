package com.github.intellij.gno.psi;

import com.intellij.openapi.util.Ref;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementResolveResult;
import com.intellij.psi.PsiPolyVariantReferenceBase;
import com.intellij.psi.ResolveResult;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class GnoVariableReference extends PsiPolyVariantReferenceBase<GnoVariableExpr> {
    public GnoVariableReference(@NotNull GnoVariableExpr psiElement) {
        if (psiElement == null) {
            $$$reportNull$$$0(0);
        }

        super(psiElement, TextRange.create(0, psiElement.getTextLength()));
    }

    public ResolveResult @NotNull [] multiResolve(boolean incompleteCode) {
        String name = ((GnoVariableExpr)this.myElement).getVariable().getText();
        Ref<PsiElement> result = Ref.create();
        PsiScopeProcessor processor = (element, state) -> {
            if (PsiTreeUtil.isAncestor(element, this.myElement, false)) {
                return true;
            } else {
                if (element instanceof GnoVarDeclarationStatement) {
                    GnoVarDeclaration declaration = ((GnoVarDeclarationStatement)element).getVarDeclaration();
                    GnoVarDefinition definition = declaration.getVarDefinition();
                    if (name.equals(definition.getText())) {
                        result.set(definition);
                        return false;
                    }
                }

                if (element instanceof GnoVarDeclaration) {
                    GnoVarDefinition definition = ((GnoVarDeclaration)element).getVarDefinition();
                    if (name.equals(definition.getVariable().getText())) {
                        result.set(definition);
                        return false;
                    }
                }

                if (element instanceof GnoRangeVarDeclaration) {
                    for(GnoVarDefinition definition : ((GnoRangeVarDeclaration)element).getVarDefinitionList()) {
                        if (name.equals(definition.getVariable().getText())) {
                            result.set(definition);
                            return false;
                        }
                    }
                }

                return true;
            }
        };
        PsiTreeUtil.treeWalkUp(processor, this.myElement, ((GnoVariableExpr)this.myElement).getContainingFile(), ResolveState.initial());
        PsiElement resolve = (PsiElement)result.get();
        ResolveResult[] var10000 = resolve != null ? new ResolveResult[]{new PsiElementResolveResult(resolve)} : ResolveResult.EMPTY_ARRAY;
        if (var10000 == null) {
            $$$reportNull$$$0(1);
        }

        return var10000;
    }

    public Object @NotNull [] getVariants() {
        List<PsiElement> result = new ArrayList();
        PsiScopeProcessor processor = (element, state) -> {
            if (PsiTreeUtil.isAncestor(element, this.myElement, false)) {
                return true;
            } else {
                if (element instanceof GnoVarDeclarationStatement) {
                    GnoVarDeclaration declaration = ((GnoVarDeclarationStatement)element).getVarDeclaration();
                    result.add(declaration.getVarDefinition());
                }

                if (element instanceof GnoVarDeclaration) {
                    result.add(((GnoVarDeclaration)element).getVarDefinition());
                }

                if (element instanceof GnoRangeVarDeclaration) {
                    result.addAll(((GnoRangeVarDeclaration)element).getVarDefinitionList());
                }

                return true;
            }
        };
        PsiTreeUtil.treeWalkUp(processor, this.myElement, ((GnoVariableExpr)this.myElement).getContainingFile(), ResolveState.initial());
        Object[] var10000 = result.toArray(PsiElement.EMPTY_ARRAY);
        if (var10000 == null) {
            $$$reportNull$$$0(2);
        }

        return var10000;
    }

    public PsiElement handleElementRename(@NotNull String newElementName) throws IncorrectOperationException {
        if (newElementName == null) {
            $$$reportNull$$$0(3);
        }

        PsiElement variable = ((GnoVariableExpr)this.myElement).getVariable();
        variable.replace(GnoElementFactory.createVar(((GnoVariableExpr)this.myElement).getProject(), newElementName));
        return this.myElement;
    }
}
