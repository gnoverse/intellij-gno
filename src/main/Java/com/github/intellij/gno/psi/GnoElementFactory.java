package com.github.intellij.gno.psi;

import com.github.intellij.gno.language.GnoLanguage;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.util.PsiTreeUtil;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public final class GnoElementFactory {
    public GnoElementFactory() {
    }

    public static @NotNull PsiElement createIdentifierFromText(@NotNull Project project, @NotNull String text) {
        if (project == null) {
            $$$reportNull$$$0(0);
        }

        if (text == null) {
            $$$reportNull$$$0(1);
        }

        GnoFile file = createFileFromText(project, "{{." + text + "}}");
        GnoFieldChainExpr expr = (GnoFieldChainExpr)Objects.requireNonNull((GnoFieldChainExpr)PsiTreeUtil.findChildOfType(file, GnoFieldChainExpr.class));
        PsiElement var10000 = expr.getIdentifier();
        if (var10000 == null) {
            $$$reportNull$$$0(2);
        }

        return var10000;
    }

    public static @NotNull GnoFile createFileFromText(@NotNull Project project, @NotNull String text) {
        if (project == null) {
            $$$reportNull$$$0(3);
        }

        if (text == null) {
            $$$reportNull$$$0(4);
        }

        GnoFile var10000 = (GnoFile)PsiFileFactory.getInstance(project).createFileFromText("template.html", GnoLanguage.INSTANCE, text);
        if (var10000 == null) {
            $$$reportNull$$$0(5);
        }

        return var10000;
    }

    public static @NotNull PsiElement createVar(@NotNull Project project, @NotNull String name) {
        if (project == null) {
            $$$reportNull$$$0(6);
        }

        if (name == null) {
            $$$reportNull$$$0(7);
        }

        String varName = name.startsWith("$") ? name : "$" + name;
        GnoFile file = createFileFromText(project, "{{" + varName + " := . }}");
        GnoVarDefinition definition = (GnoVarDefinition)Objects.requireNonNull((GnoVarDefinition)PsiTreeUtil.findChildOfType(file, GnoVarDefinition.class));
        PsiElement var10000 = definition.getVariable();
        if (var10000 == null) {
            $$$reportNull$$$0(8);
        }

        return var10000;
    }

    public static @NotNull PsiComment createComment(@NotNull Project project, @NotNull String commentText) {
        if (project == null) {
            $$$reportNull$$$0(9);
        }

        if (commentText == null) {
            $$$reportNull$$$0(10);
        }

        GnoFile file = createFileFromText(project, "{{/*" + commentText + "*/}}");
        PsiComment var10000 = (PsiComment)Objects.requireNonNull((PsiComment)PsiTreeUtil.findChildOfType(file, PsiComment.class));
        if (var10000 == null) {
            $$$reportNull$$$0(11);
        }

        return var10000;
    }
}
