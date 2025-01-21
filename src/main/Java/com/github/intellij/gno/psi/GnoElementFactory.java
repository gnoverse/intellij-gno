package com.github.intellij.gno.psi;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFileFactory;
import com.github.intellij.gno.language.GnoFileType;

public class GnoElementFactory {

    public static GnoPropertyDeclaration createProperty(Project project, String name) {
        final GnoFile file = createFile(project, name);
        return (GnoPropertyDeclaration) file.getFirstChild();
    }

    public static GnoFile createFile(Project project, String text) {
        String name = "dummy.gno";
        return (GnoFile) PsiFileFactory.getInstance(project).createFileFromText(name, GnoFileType.INSTANCE, text);
    }

    public static GnoPropertyDeclaration createProperty(Project project, String name, String value) {
        final GnoFile file = createFile(project, name + " := " + value);
        return (GnoPropertyDeclaration) file.getFirstChild();
    }

    public static PsiElement createCRLF(Project project) {
        final GnoFile file = createFile(project, "\n");
        return file.getFirstChild();
    }

}
