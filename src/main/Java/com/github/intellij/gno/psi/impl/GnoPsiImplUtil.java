package com.github.intellij.gno.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.github.intellij.gno.psi.GnoElementFactory;
import com.github.intellij.gno.psi.GnoPropertyDeclaration;
import com.github.intellij.gno.psi.GnoTypes;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class GnoPsiImplUtil {

    public static String getKey(GnoPropertyDeclaration element) {
        ASTNode keyNode = element.getNode().findChildByType(GnoTypes.IDENTIFIER);
        if (keyNode != null) {
            return keyNode.getText();
        }
        return null;
    }


    public static String getValue(GnoPropertyDeclaration element) {
        ASTNode valueNode = element.getNode().findChildByType(GnoTypes.STRING);
        if (valueNode != null) {
            return valueNode.getText();
        } else {
            return null;
        }
    }

    public static String getName(GnoPropertyDeclaration element) {
        return getKey(element);
    }

    public static PsiElement setName(GnoPropertyDeclaration element, String newName) {
        ASTNode keyNode = element.getNode().findChildByType(GnoTypes.IDENTIFIER);
        if (keyNode != null) {
            GnoPropertyDeclaration property = GnoElementFactory.createProperty(element.getProject(), newName);
            ASTNode newKeyNode = property.getFirstChild().getNode();
            element.getNode().replaceChild(keyNode, newKeyNode);
        }
        return element;
    }

    public static PsiElement getNameIdentifier(GnoPropertyDeclaration element) {
        ASTNode keyNode = element.getNode().findChildByType(GnoTypes.IDENTIFIER);
        if (keyNode != null) {
            return keyNode.getPsi();
        } else {
            return null;
        }
    }

    public static ItemPresentation getPresentation(final GnoPropertyDeclaration element) {
        return new ItemPresentation() {
            @Nullable
            @Override
            public String getPresentableText() {
                return element.getKey();
            }

            @Nullable
            @Override
            public String getLocationString() {
                PsiFile containingFile = element.getContainingFile();
                return containingFile == null ? null : containingFile.getName();
            }

            @Override
            public Icon getIcon(boolean unused) {
                return element.getIcon(0);
            }
        };
    }
}
