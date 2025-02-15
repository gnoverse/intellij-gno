package com.github.intellij.gno.configuration;

import com.intellij.ui.BrowserHyperlinkListener;
import com.intellij.ui.ColorUtil;
import com.intellij.util.ui.UIUtil;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;

public class GnoUIUtil {
    private GnoUIUtil() {}

    @NotNull
    public static JTextPane createDescriptionPane() {
        JTextPane result = new JTextPane();
        result.addHyperlinkListener(new BrowserHyperlinkListener());
        result.setContentType("text/html");
        Font descriptionFont = UIUtil.getLabelFont(UIUtil.FontSize.SMALL);
        HTMLEditorKit editorKit = UIUtil.getHTMLEditorKit();
        editorKit.getStyleSheet().addRule("body, p {" +
                "color:#" + ColorUtil.toHex(UIUtil.getLabelFontColor(UIUtil.FontColor.BRIGHTER)) + ";" +
                "font-family:" + descriptionFont.getFamily() + ";" +
                "font-size:" + descriptionFont.getSize() + "pt;}");
        result.setHighlighter(null);
        result.setEditorKit(editorKit);
        return result;
    }
}
