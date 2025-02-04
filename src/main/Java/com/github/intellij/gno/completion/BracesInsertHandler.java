package com.github.intellij.gno.completion;

import com.intellij.codeInsight.completion.InsertHandler;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.template.Template;
import com.intellij.codeInsight.template.TemplateManager;
import com.intellij.openapi.actionSystem.IdeActions;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import com.intellij.openapi.editor.actionSystem.EditorActionManager;
import com.intellij.openapi.editor.ex.EditorEx;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.NotNull;

public class BracesInsertHandler implements InsertHandler<LookupElement> {
    public static final BracesInsertHandler ONE_LINER = new BracesInsertHandler(true);
    public static final BracesInsertHandler INSTANCE = new BracesInsertHandler(false);

    private final boolean myOneLine;

    private BracesInsertHandler(boolean oneLine) {
        myOneLine = oneLine;
    }

    @Override
    public void handleInsert(@NotNull InsertionContext context, LookupElement item) {
        Editor editor = context.getEditor();
        CharSequence documentText = context.getDocument().getImmutableCharSequence();
        int offset = skipWhiteSpaces(editor.getCaretModel().getOffset(), documentText);
        if (documentText.charAt(offset) != '{') {
            Project project = context.getProject();
            Template template = TemplateManager.getInstance(project).createTemplate("braces", "gno", myOneLine ? "{$END$}" : " {\n$END$\n}");
            template.setToReformat(true);
            TemplateManager.getInstance(project).startTemplate(editor, template);
        }
        else {
            editor.getCaretModel().moveToOffset(offset);
            ApplicationManager.getApplication().runWriteAction(() -> {
                EditorActionHandler enterAction = EditorActionManager.getInstance().getActionHandler(IdeActions.ACTION_EDITOR_START_NEW_LINE);
                enterAction.execute(editor, editor.getCaretModel().getCurrentCaret(), ((EditorEx)editor).getDataContext());
            });
        }
    }

    private static int skipWhiteSpaces(int offset, @NotNull CharSequence documentText) {
        while (offset < documentText.length() && StringUtil.isWhiteSpace(documentText.charAt(offset))) {
            offset += 1;
        }
        return Math.min(documentText.length() - 1, offset);
    }
}