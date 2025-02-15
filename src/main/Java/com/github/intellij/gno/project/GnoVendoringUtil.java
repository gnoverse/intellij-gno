package com.github.intellij.gno.project;

import com.github.intellij.gno.sdk.GnoSdkService;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.ThreeState;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

public class GnoVendoringUtil {
    public static boolean supportsVendoringByDefault(@Nullable String sdkVersion) {
        if (sdkVersion == null || sdkVersion.length() < 3) {
            return false;
        }
        return StringUtil.parseDouble(sdkVersion.substring(0, 3), 0) >= 1.6;
    }

    public static boolean vendoringCanBeDisabled(@Nullable String sdkVersion) {
        if (sdkVersion == null || sdkVersion.length() < 3) {
            return true;
        }
        return StringUtil.parseDouble(sdkVersion.substring(0, 3), 0) < 1.7;
    }

    public static boolean supportsInternalPackages(@Nullable String sdkVersion) {
        if (sdkVersion == null || sdkVersion.length() < 3) {
            return false;
        }
        return StringUtil.parseDouble(sdkVersion.substring(0, 3), 0) >= 1.5;
    }

    public static boolean supportsSdkInternalPackages(@Nullable String sdkVersion) {
        if (sdkVersion == null || sdkVersion.length() < 3) {
            return false;
        }
        return StringUtil.parseDouble(sdkVersion.substring(0, 3), 0) >= 1.4;
    }

    public static boolean supportsVendoring(@Nullable String sdkVersion) {
        if (sdkVersion == null || sdkVersion.length() < 3) {
            return false;
        }
        return StringUtil.parseDouble(sdkVersion.substring(0, 3), 0) >= 1.4;
    }

    @Contract("null -> false")
    public static boolean isVendoringEnabled(@Nullable Module module) {
        if (module == null) {
            return false;
        }

        String version = GnoSdkService.getInstance(module.getProject()).getSdkVersion(module);
        if (!vendoringCanBeDisabled(version)) {
            return true;
        }
        ThreeState vendorSupportEnabled = GnoModuleSettings.getInstance(module).getVendoringEnabled();
        if (vendorSupportEnabled == ThreeState.UNSURE) {
            return supportsVendoring(version) && supportsVendoringByDefault(version);
        }
        return vendorSupportEnabled.toBoolean();
    }
}
