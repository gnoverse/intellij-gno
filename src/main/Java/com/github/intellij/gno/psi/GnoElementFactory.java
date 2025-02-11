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

        GnoFile file = createFileFromText(project, "{{." + text + "}}");
        GnoFieldChainExpr expr = Objects.requireNonNull(PsiTreeUtil.findChildOfType(file, GnoFieldChainExpr.class));

        return expr.getIdentifier();
    }

    public static @NotNull GnoFile createFileFromText(@NotNull Project project, @NotNull String text) {

        GnoFile var10000 = (GnoFile)PsiFileFactory.getInstance(project).createFileFromText("template.html", GnoLanguage.INSTANCE, text);
        if (var10000 == null) {
            throw new NullPointerException("WHITESPACES is null");
        }
        return var10000;
    }

    public static @NotNull PsiElement createVar(@NotNull Project project, @NotNull String name) {

        String varName = name.startsWith("$") ? name : "$" + name;
        GnoFile file = createFileFromText(project, "{{" + varName + " := . }}");
        GnoVarDefinition definition = Objects.requireNonNull(PsiTreeUtil.findChildOfType(file, GnoVarDefinition.class));

        return definition.getVariable();
    }

    public static @NotNull PsiComment createComment(@NotNull Project project, @NotNull String commentText) {

        GnoFile file = createFileFromText(project, "{{/*" + commentText + "*/}}");

        return Objects.requireNonNull(PsiTreeUtil.findChildOfType(file, PsiComment.class));
    }
}
