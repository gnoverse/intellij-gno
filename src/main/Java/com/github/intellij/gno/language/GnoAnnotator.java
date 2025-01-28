package com.github.intellij.gno.language;

import com.github.intellij.gno.highlighting.GnoSyntaxHighlighter;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralExpression;
import com.github.intellij.gno.psi.GnoPropertyDeclaration;
import org.jetbrains.annotations.NotNull;

import java.util.List;

final class GnoAnnotator implements Annotator {

    // Define strings for the Gno language prefix - used for annotations, line markers, etc.
    public static final String GNO_PREFIX_STR = "gno";
    public static final String GNO_SEPARATOR_STR = ":";

    @Override
    public void annotate(@NotNull final PsiElement element, @NotNull AnnotationHolder holder) {
        // Ensure the PSI Element is an expression
        System.out.println("🚀 GnoAnnotator appelé pour : " + element.getText());
        if (!(element instanceof PsiLiteralExpression literalExpression)) {
            return;
        }

        // Ensure the PSI element contains a string that starts with the prefix and separator
        String value = literalExpression.getValue() instanceof String ? (String) literalExpression.getValue() : null;
        if (value == null || !value.startsWith(GNO_PREFIX_STR + GNO_SEPARATOR_STR)) {
            return;
        }
        System.out.println("✅ Expression détectée : " + value);

        //annotater test
        holder.newAnnotation(HighlightSeverity.WARNING, "🚀 TEST Annotation Gno")
                .range(element)
                .create();


        // Define the text ranges (start is inclusive, end is exclusive)
        // "gno:key"
        //  01234567
        TextRange prefixRange = TextRange.from(element.getTextRange().getStartOffset(), GNO_PREFIX_STR.length() + 1);
        TextRange separatorRange = TextRange.from(prefixRange.getEndOffset(), GNO_SEPARATOR_STR.length());
        TextRange keyRange = new TextRange(separatorRange.getEndOffset(), element.getTextRange().getEndOffset() - 1);

        // Highlight "gno" prefix and ":" separator
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(prefixRange).textAttributes(DefaultLanguageHighlighterColors.KEYWORD).create();
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(separatorRange).textAttributes(GnoSyntaxHighlighter.SEPARATOR).create();

        // Get the list of properties for the given key
        String key = value.substring(GNO_PREFIX_STR.length() + GNO_SEPARATOR_STR.length());
        List<GnoPropertyDeclaration> properties = GnoUtil.findProperties(element.getProject(), key);
        if (properties.isEmpty()) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Unresolved property")
                    .range(keyRange)
                    .highlightType(ProblemHighlightType.LIKE_UNKNOWN_SYMBOL)
                    .withFix(new GnoCreatePropertyQuickFix(key)) // Quick fix for unresolved properties
                    .create();
        } else {
            // Found at least one property, force the text attributes to Gno syntax value character
            holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .range(keyRange).textAttributes(GnoSyntaxHighlighter.VALUE).create();
        }
    }
}
