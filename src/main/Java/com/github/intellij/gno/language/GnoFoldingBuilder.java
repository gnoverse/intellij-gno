package com.github.intellij.gno.language;

import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilderEx;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.FoldingGroup;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.containers.ContainerUtil;
import com.github.intellij.gno.psi.GnoPropertyDeclaration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class GnoFoldingBuilder extends FoldingBuilderEx implements DumbAware {

    @Override
    public FoldingDescriptor @NotNull [] buildFoldRegions(@NotNull PsiElement root,
                                                          @NotNull Document document,
                                                          boolean quick) {
        System.out.println("🚀 GnoFoldingBuilder call !");
        FoldingGroup group = FoldingGroup.newGroup("GnoFolding");
        List<FoldingDescriptor> descriptors = new ArrayList<>();


        for (PsiElement element : PsiTreeUtil.findChildrenOfType(root, PsiElement.class)) {
            String text = element.getText();
            if (text.startsWith("gno:")) {
                Project project = element.getProject();
                String key = text.substring(4);

                System.out.println("✅ Clé trouvée : " + key);

                GnoPropertyDeclaration property = ContainerUtil.getOnlyItem(GnoUtil.findProperties(project, key));
                if (property != null) {
                    System.out.println("✅ Pliage trouvé pour : " + key);
                    descriptors.add(new FoldingDescriptor(element.getNode(),
                            new TextRange(element.getTextRange().getStartOffset(),
                                    element.getTextRange().getEndOffset()),
                            group, Collections.singleton(property)));
                }
            }
        }
        return descriptors.toArray(FoldingDescriptor.EMPTY_ARRAY);
    }


    @Nullable
    @Override
    public String getPlaceholderText(@NotNull ASTNode node) {
        PsiElement element = node.getPsi();
        String text = element.getText();
        if (!text.startsWith("gno:")) {
            return null;
        }

        String key = text.substring(4);
        GnoPropertyDeclaration property = ContainerUtil.getOnlyItem(
                GnoUtil.findProperties(element.getProject(), key)
        );

        if (property == null) {
            return StringUtil.THREE_DOTS;
        }

        String propertyValue = property.getValue();
        return propertyValue != null ?
                propertyValue.replaceAll("\n", "\\n").replaceAll("\"", "\\\\\"") :
                StringUtil.THREE_DOTS;
    }

    @Override
    public boolean isCollapsedByDefault(@NotNull ASTNode node) {
        return true;
    }
}
