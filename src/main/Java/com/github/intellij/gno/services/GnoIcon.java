package com.github.intellij.gno.services;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.util.IconLoader;
import com.intellij.ui.LayeredIcon;
import com.intellij.util.PlatformIcons;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

@SuppressWarnings("ConstantConditions")
public interface GnoIcon {
    Icon ICON = IconLoader.getIcon("/icon/gno-icon.svg", GnoIcon.class);
    Icon TYPE = IconLoader.getIcon("/icon/gno-icon.svg", GnoIcon.class);
    Icon APPLICATION_RUN = Helper.createIconWithShift(ICON, AllIcons.Nodes.RunnableMark);
    Icon TEST_RUN = Helper.createIconWithShift(ICON, AllIcons.Nodes.JunitTestMark);
    Icon METHOD = AllIcons.Nodes.Method;
    Icon FUNCTION = AllIcons.Nodes.Function;
    Icon VARIABLE = AllIcons.Nodes.Variable;
    Icon CONSTANT = IconLoader.getIcon("/icon/gno-icon.svg", GnoIcon.class);
    Icon PARAMETER = AllIcons.Nodes.Parameter;
    Icon FIELD = AllIcons.Nodes.Field;
    Icon LABEL = IconLoader.getIcon("/icon/gno-icon.svg", GnoIcon.class);
    Icon RECEIVER = AllIcons.Nodes.Parameter;
    Icon PACKAGE = AllIcons.Nodes.Package;
    Icon MODULE_ICON = IconLoader.getIcon("/icon/gno-icon.svg", GnoIcon.class);
    Icon DEBUG = ICON;
    Icon DIRECTORY = PlatformIcons.FOLDER_ICON;

    class Helper {
        private Helper() {}

        @NotNull
        public static LayeredIcon createIconWithShift(@NotNull Icon base, Icon mark) {
            LayeredIcon icon = new LayeredIcon(2) {
                @Override
                public int getIconHeight() {
                    return base.getIconHeight();
                }
            };
            icon.setIcon(base, 0);
            icon.setIcon(mark, 1, 0, base.getIconWidth() / 2);
            return icon;
        }
    }
}