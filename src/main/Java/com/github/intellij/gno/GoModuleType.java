package com.github.intellij.gno;

import com.github.intellij.gno.sdk.GoSdkType;
import com.intellij.ide.util.projectWizard.ModuleBuilder;
import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.module.ModuleTypeManager;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class GoModuleType extends ModuleType<ModuleBuilder> {
  public GoModuleType() {
    super(GoConstants.MODULE_TYPE_ID);
  }

  @NotNull
  public static GoModuleType getInstance() {
    return (GoModuleType) ModuleTypeManager.getInstance().findByID(GoConstants.MODULE_TYPE_ID);
  }

  @NotNull
  @Override
  public ModuleBuilder createModuleBuilder() {
    return new ModuleBuilder() {

        @Override
      public ModuleType<?> getModuleType() {
        return GoModuleType.getInstance();
      }

      @Override
      public @NotNull String getBuilderId() {
        return "GO_MODULE_BUILDER";
      }
    };
  }

  @NotNull
  @Override
  public String getName() {
    return "Go Module";
  }

  @NotNull
  @Override
  public String getDescription() {
    return "Go modules are used for developing <b>Go</b> applications.";
  }

  @Nullable
  public Icon getBigIcon() {
    return GoIcons.MODULE_ICON;
  }

  @NotNull
  @Override
  public Icon getNodeIcon(boolean isOpened) {
      assert GoIcons.ICON != null;
      return GoIcons.ICON;
  }

  @NotNull
  @Override
  public ModuleWizardStep @NotNull [] createWizardSteps(@NotNull WizardContext wizardContext,
                                                        @NotNull ModuleBuilder moduleBuilder,
                                                        @NotNull ModulesProvider modulesProvider) {
    return ModuleWizardStep.EMPTY_ARRAY;
  }
}
