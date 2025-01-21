package com.github.intellij.gno.language;

import com.google.common.collect.Lists;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.github.intellij.gno.psi.GnoFile;
import com.github.intellij.gno.psi.GnoPropertyDeclaration;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class GnoUtil {

    /**
     * Searches the entire project for Gno language files with instances of the Gno property with the given key.
     *
     * @param project current project
     * @param key     to check
     * @return matching properties
     */
    public static List<GnoPropertyDeclaration> findProperties(Project project, String key) {
        System.out.println("🔍 Recherche des propriétés pour : " + key);

        List<GnoPropertyDeclaration> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(GnoFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            GnoFile gnoFile = (GnoFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (gnoFile != null) {
                GnoPropertyDeclaration[] properties = PsiTreeUtil.getChildrenOfType(gnoFile, GnoPropertyDeclaration.class);
                if (properties != null) {
                    for (GnoPropertyDeclaration property : properties) {
                        if (key.equals(property.getKey())) {
                            result.add(property);
                        }
                    }
                }
            }
        }
        return result;
    }

    public static List<GnoPropertyDeclaration> findProperties(Project project) {
        List<GnoPropertyDeclaration> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(GnoFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            GnoFile gnoFile = (GnoFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (gnoFile != null) {
                GnoPropertyDeclaration[] properties = PsiTreeUtil.getChildrenOfType(gnoFile, GnoPropertyDeclaration.class);
                if (properties != null) {
                    Collections.addAll(result, properties);
                }
            }
        }
        return result;
    }

    /**
     * Attempts to collect any comment elements above the Gno key/value pair.
     */
    public static @NotNull String findDocumentationComment(GnoPropertyDeclaration property) {
        List<String> result = new LinkedList<>();
        PsiElement element = property.getPrevSibling();
        while (element instanceof PsiComment || element instanceof PsiWhiteSpace) {
            if (element instanceof PsiComment) {
                String commentText = element.getText().replaceFirst("[!# ]+", "");
                result.add(commentText);
            }
            element = element.getPrevSibling();
        }
        return StringUtil.join(Lists.reverse(result), "\n ");
    }

}
