package com.github.intellij.gno.language.psi.impl;

import com.github.intellij.gno.language.psi.GnoCompositeElement;
import com.github.intellij.gno.language.psi.GnoFile;
import com.github.intellij.gno.language.stubs.TextHolder;
import com.intellij.extapi.psi.StubBasedPsiElementBase;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public abstract class GnoStubbedElementImpl<T extends StubBase<?>> extends StubBasedPsiElementBase<T> implements GnoCompositeElement {
    public GnoStubbedElementImpl(@NotNull T stub, @NotNull IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public GnoStubbedElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public String toString() {
        return getElementType().toString();
    }

    @Nullable
    @Override
    public String getText() {
        T stub = getStub();
        if (stub instanceof TextHolder) {
            String text = ((TextHolder)stub).getText();
            if (text != null) return text;
        }
        return super.getText();
    }

    @Override
    public PsiElement getParent() {
        return getParentByStub();
    }

    @Override
    public boolean processDeclarations(@NotNull PsiScopeProcessor processor,
                                       @NotNull ResolveState state,
                                       PsiElement lastParent,
                                       @NotNull PsiElement place) {
        return GnoCompositeElementImpl.processDeclarationsDefault(this, processor, state, lastParent, place);
    }

    @NotNull
    @Override
    public GnoFile getContainingFile() {
        return (GnoFile)super.getContainingFile();
    }

    @Override
    public boolean shouldGnoDeeper() {
        return true;
    }
}