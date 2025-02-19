package com.github.intellij.gno.language;

import com.github.intellij.gno.psi.GnoPropertyDeclaration;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GnoUtil {

    public static List<GnoPropertyDeclaration> findProperties(Project project) {
        List<GnoPropertyDeclaration> result = new ArrayList<>();
        Collection<VirtualFile> files = FileTypeIndex.getFiles(GnoFileType.INSTANCE, GlobalSearchScope.allScope(project));

        System.out.println("🔍 Nombre de fichiers .gno trouvés : " + files.size());

        for (VirtualFile file : files) {
            PsiFile psiFile = PsiManager.getInstance(project).findFile(file);
            if (psiFile != null) {
                Collection<GnoPropertyDeclaration> properties =
                        PsiTreeUtil.collectElementsOfType(psiFile, GnoPropertyDeclaration.class);

                System.out.println("📌 Fichier : " + file.getName() + " - Propriétés trouvées : " + properties.size());

                result.addAll(properties);
            }
        }
        return result;
    }


    public static List<GnoPropertyDeclaration> findProperties(Project project, String key) {
        List<GnoPropertyDeclaration> result = new ArrayList<>();
        for (GnoPropertyDeclaration property : findProperties(project)) {
            if (key.equals(property.getKey())) {
                result.add(property);
            }
        }
        return result;
    }
}
