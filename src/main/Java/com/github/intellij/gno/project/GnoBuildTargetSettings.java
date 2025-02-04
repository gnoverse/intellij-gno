package com.github.intellij.gno.project;

import com.intellij.openapi.util.SimpleModificationTracker;
import com.intellij.util.ArrayUtil;
import com.intellij.util.ThreeState;
import com.intellij.util.xmlb.annotations.Tag;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

@Tag("buildTags")
public class GnoBuildTargetSettings extends SimpleModificationTracker {
    public static final String ANY_COMPILER = "Any";
    public static final String DEFAULT = "default";

    @NotNull public String os = DEFAULT;
    @NotNull public String arch = DEFAULT;
    @NotNull public ThreeState cgo = ThreeState.UNSURE;
    @NotNull public String compiler = ANY_COMPILER;
    @NotNull public String goVersion = DEFAULT;
    @NotNull public String[] customFlags = ArrayUtil.EMPTY_STRING_ARRAY;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GnoBuildTargetSettings)) return false;

        GnoBuildTargetSettings settings = (GnoBuildTargetSettings)o;

        if (!os.equals(settings.os)) return false;
        if (!arch.equals(settings.arch)) return false;
        if (cgo != settings.cgo) return false;
        if (!compiler.equals(settings.compiler)) return false;
        if (!goVersion.equals(settings.goVersion)) return false;
        return Arrays.equals(customFlags, settings.customFlags);
    }

    @Override
    public int hashCode() {
        int result = os.hashCode();
        result = 31 * result + arch.hashCode();
        result = 31 * result + cgo.hashCode();
        result = 31 * result + compiler.hashCode();
        result = 31 * result + goVersion.hashCode();
        result = 31 * result + Arrays.hashCode(customFlags);
        return result;
    }
}
