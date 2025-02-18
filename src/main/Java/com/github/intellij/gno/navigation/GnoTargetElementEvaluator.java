package com.github.intellij.gno.navigation;

import com.intellij.codeInsight.TargetElementEvaluatorEx2;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.Nullable;

public class GnoTargetElementEvaluator extends TargetElementEvaluatorEx2 {


    public @Nullable PsiElement getElementAt(@Nullable PsiElement element) {
        if (element == null) return null;

        PsiElement referenceElement = PsiTreeUtil.getNonStrictParentOfType(element, PsiElement.class);

        if (referenceElement != null && isValidTarget(referenceElement)) {
            return referenceElement;
        }
        return null;
    }

    private boolean isValidTarget(PsiElement element) {
        if (element.getNode() == null) return false;

        String elementType = element.getNode().getElementType().toString();

        // Exclure les espaces, commentaires, et chaînes de caractères
        if (elementType.contains("COMMENT") || elementType.contains("STRING") || elementType.contains("WHITE_SPACE")) {
            return false;
        }

        // Vérifier si c'est une déclaration ou référence valable
        return elementType.equals("IDENTIFIER") && element.getReference() != null;
    }
}
