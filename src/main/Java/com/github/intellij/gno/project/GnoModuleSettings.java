package com.github.intellij.gno.project;

import com.github.intellij.gno.psi.GnoConstants;
import com.github.intellij.gno.configuration.GnoConfigurableProvider;
import com.github.intellij.gno.configuration.GnoModuleSettingsConfigurable;
import com.intellij.codeInsight.daemon.DaemonCodeAnalyzer;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StoragePathMacros;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleServiceManager;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.project.Project;
import com.intellij.psi.impl.source.resolve.ResolveCache;
import com.intellij.util.ThreeState;
import com.intellij.util.messages.Topic;
import com.intellij.util.xmlb.XmlSerializerUtil;
import com.intellij.util.xmlb.annotations.OptionTag;
import com.intellij.util.xmlb.annotations.Property;
import org.jetbrains.annotations.NotNull;

@State(name = GnoConstants.GNO_MODULE_SESTTINGS_SERVICE_NAME)
public class GnoModuleSettings implements PersistentStateComponent<GnoModuleSettings.GnoModuleSettingsState> {
    public static final Topic<BuildTargetListener> TOPIC = Topic.create("build target changed", BuildTargetListener.class);

    @NotNull
    private final GnoModuleSettingsState myState = new GnoModuleSettingsState();
    @NotNull
    private final Module myModule;

    public GnoModuleSettings(@NotNull Module module) {
        myModule = module;
    }

    public static GnoModuleSettings getInstance(@NotNull Module module) {
        return ModuleServiceManager.getService(module, GnoModuleSettings.class);
    }

    @NotNull
    public ThreeState getVendoringEnabled() {
        return myState.vendoring;
    }

    public void setVendoringEnabled(@NotNull ThreeState vendoringEnabled) {
        if (vendoringEnabled != myState.vendoring) {
            cleanResolveCaches();
        }
        myState.vendoring = vendoringEnabled;
    }

    @NotNull
    public GnoBuildTargetSettings getBuildTargetSettings() {
        return myState.buildTargetSettings;
    }

    public void setBuildTargetSettings(@NotNull GnoBuildTargetSettings buildTargetSettings) {
        if (!buildTargetSettings.equals(myState.buildTargetSettings)) {
            XmlSerializerUtil.copyBean(buildTargetSettings, myState.buildTargetSettings);
            if (!myModule.isDisposed()) {
                myModule.getProject().getMessageBus().syncPublisher(TOPIC).changed(myModule);
            }
            cleanResolveCaches();
            myState.buildTargetSettings.incModificationCount();
        }
    }

    private void cleanResolveCaches() {
        Project project = myModule.getProject();
        if (!project.isDisposed()) {
            ResolveCache.getInstance(project).clearCache(true);
            DaemonCodeAnalyzer.getInstance(project).restart();
        }
    }

    @NotNull
    @Override
    public GnoModuleSettingsState getState() {
        return myState;
    }

    @Override
    public void loadState(GnoModuleSettingsState state) {
        XmlSerializerUtil.copyBean(state, myState);
    }

    public interface BuildTargetListener {
        void changed(@NotNull Module module);
    }

    static class GnoModuleSettingsState {
        @OptionTag
        @NotNull
        private ThreeState vendoring = ThreeState.UNSURE;

        @Property(surroundWithTag = false)
        @NotNull
        private GnoBuildTargetSettings buildTargetSettings = new GnoBuildTargetSettings();
    }

    public static void showModulesConfigurable(@NotNull Project project) {
        ApplicationManager.getApplication().assertIsDispatchThread();
        if (!project.isDisposed()) {
            ShowSettingsUtil.getInstance().editConfigurable(project, (Configurable) new GnoConfigurableProvider.GnoProjectSettingsConfigurable(project));
        }
    }

    public static void showModulesConfigurable(@NotNull Module module) {
        ApplicationManager.getApplication().assertIsDispatchThread();
        if (!module.isDisposed()) {
            ShowSettingsUtil.getInstance().editConfigurable(module.getProject(), new GnoModuleSettingsConfigurable(module, true));
        }
    }
}
