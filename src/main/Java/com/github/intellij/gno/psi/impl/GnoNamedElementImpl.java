package com.github.intellij.gno.psi.impl;

import com.github.intellij.gno.psi.GnoElementFactory;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;
import com.github.intellij.gno.psi.GnoNamedElement;
import com.github.intellij.gno.psi.GnoTypes;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public abstract class GnoNamedElementImpl extends ASTWrapperPsiElement implements GnoNamedElement {

    public GnoNamedElementImpl(ASTNode node) {
        super(node);
    }

    @Override
    public String getKey() {
        ASTNode keyNode = getNode().findChildByType(GnoTypes.IDENTIFIER);
        return keyNode != null ? keyNode.getText() : null;
    }

    @Override
    public String getValue() {
        ASTNode valueNode = getNode().findChildByType(GnoTypes.EXPRESSION);
        return valueNode != null ? valueNode.getText() : null;
    }

    @Override
    public String getName() {
        String key = getKey();
        System.out.println("🟢 getName() appelé pour : " + key);
        return key;
    }

    @Override
    public PsiElement setName(String newName) {
        ASTNode keyNode = getNode().findChildByType(GnoTypes.IDENTIFIER);
        if (keyNode != null) {
            GnoNamedElement property = GnoElementFactory.createProperty(getProject(), newName);
            ASTNode newKeyNode = property.getFirstChild().getNode();
            getNode().replaceChild(keyNode, newKeyNode);
        }
        return this;
    }

    @Override
    public PsiElement getNameIdentifier() {
        ASTNode keyNode = getNode().findChildByType(GnoTypes.IDENTIFIER);
        return keyNode != null ? keyNode.getPsi() : null;
    }

    @Override
    public ItemPresentation getPresentation() {
        return new ItemPresentation() {
            @Nullable
            @Override
            public String getPresentableText() {
                return getKey();
            }

            @Nullable
            @Override
            public String getLocationString() {
                return getContainingFile() != null ? getContainingFile().getName() : null;
            }

            @Override
            public Icon getIcon(boolean unused) {
                return null;
            }
        };
    }
}
