package com.github.intellij.gno.language;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.util.IconLoader;
import com.intellij.ui.LayeredIcon;
import com.intellij.util.PlatformIcons;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

@SuppressWarnings("ConstantConditions")
public interface GnoIcons {
  Icon ICON = IconLoader.findIcon("/icon/gno-icon.png");
  Icon TYPE = IconLoader.findIcon("/icon/gno-icon.png");
  Icon DIRECTORY = IconLoader.findIcon("/icon/gno-icon.png");
  Icon APPLICATION_RUN = Helper.createIconWithShift(ICON, AllIcons.Nodes.RunnableMark);
  Icon TEST_RUN = Helper.createIconWithShift(ICON, AllIcons.Nodes.JunitTestMark);
  Icon METHOD = AllIcons.Nodes.Method;
  Icon FUNCTION = AllIcons.Nodes.Function;
  Icon VARIABLE = AllIcons.Nodes.Variable;
  Icon CONSTANT = IconLoader.findIcon("/icon/gno-icon.png");
  Icon PARAMETER = AllIcons.Nodes.Parameter;
  Icon FIELD = AllIcons.Nodes.Field;
  Icon LABEL = null; // todo: we need an icon here!
  Icon RECEIVER = AllIcons.Nodes.Parameter;
  Icon PACKAGE = AllIcons.Nodes.Package;
  Icon MODULE_ICON = IconLoader.findIcon("/icon/gno-icon.png");
  Icon DEBUG = ICON;

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
