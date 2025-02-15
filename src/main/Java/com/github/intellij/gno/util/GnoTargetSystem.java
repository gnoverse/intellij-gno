/*
 * Copyright 2013-2016 Sergey Ignatov, Alexander Zolotov, Florin Patan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.intellij.gno.util;

import com.github.intellij.gno.project.GnoBuildTargetSettings;
import com.github.intellij.gno.project.GnoModuleSettings;
import com.github.intellij.gno.sdk.GnoSdkService;
import com.intellij.openapi.module.Module;
import com.intellij.psi.util.CachedValueProvider;
import com.intellij.psi.util.CachedValuesManager;
import com.intellij.util.ArrayUtil;
import com.intellij.util.ThreeState;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.Set;

public class GnoTargetSystem {
  private static final String GAE_BUILD_FLAG = "appengine";

  @NotNull public final String os;
  @NotNull public final String arch;
  @Nullable public final String goVersion;
  @Nullable public final String compiler;

  @NotNull public final ThreeState cgoEnabled;
  private final Set<String> customFlags = ContainerUtil.newHashSet();

  public GnoTargetSystem(@NotNull String os, @NotNull String arch, @Nullable String goVersion, @Nullable String compiler,
                        @NotNull ThreeState cgoEnabled, @NotNull String... customFlags) {
    this.os = os;
    this.arch = arch;
    this.goVersion = goVersion;
    this.compiler = compiler;
    this.cgoEnabled = cgoEnabled;
    Collections.addAll(this.customFlags, customFlags);
  }

  public boolean supportsFlag(@NotNull String flag) {
    return customFlags.contains(flag);
  }

  @NotNull
  public static GnoTargetSystem forModule(@NotNull Module module) {
    return CachedValuesManager.getManager(module.getProject()).getCachedValue(module, () -> {
      GnoBuildTargetSettings settings = GnoModuleSettings.getInstance(module).getBuildTargetSettings();
      String os = realValue(settings.os, GnoUtil.systemOS());
      String arch = realValue(settings.arch, GnoUtil.systemArch());
      ThreeState cgo = settings.cgo == ThreeState.UNSURE ? GnoUtil.systemCgo(os, arch) : settings.cgo;
      String moduleSdkVersion = GnoSdkService.getInstance(module.getProject()).getSdkVersion(module);
      String[] customFlags = GnoSdkService.getInstance(module.getProject()).isAppEngineSdk(module)
                             ? ArrayUtil.prepend(GAE_BUILD_FLAG, settings.customFlags)
                             : settings.customFlags;
      String compiler = GnoBuildTargetSettings.ANY_COMPILER.equals(settings.compiler) ? null : settings.compiler;
      GnoTargetSystem result = new GnoTargetSystem(os, arch, realValue(settings.goVersion, moduleSdkVersion), compiler, cgo, customFlags);
      return CachedValueProvider.Result.create(result, settings);
    });
  }

  @Contract("_,null->!null")
  private static String realValue(@NotNull String value, @Nullable String defaultValue) {
    return GnoBuildTargetSettings.DEFAULT.equals(value) ? defaultValue : value;
  }
}
