package com.github.intellij.gno.runconfig.testing;

import com.github.intellij.gno.psi.GnoConstants;
import com.github.intellij.gno.psi.impl.GnoPsiImplUtil;
import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

public enum GnoTestFunctionType {
    TEST(GnoConstants.TEST_PREFIX, "T"),
    TEST_MAIN(GnoConstants.TEST_PREFIX, "M"),
    BENCHMARK(GnoConstants.BENCHMARK_PREFIX, "B"),
    EXAMPLE(GnoConstants.EXAMPLE_PREFIX, null);

    private final String myPrefix;
    private final String myParamType;

    GnoTestFunctionType(String prefix, String paramType) {
        myPrefix = prefix;
        myParamType = paramType;
    }

    @Nullable
    public String getParamType() {
        return myParamType;
    }

    @NotNull
    public String getPrefix() {
        return myPrefix;
    }

    @NotNull
    public String getQualifiedParamType(@Nullable String testingQualifier) {
        return myParamType != null ? "*" + GnoPsiImplUtil.getFqn(testingQualifier, myParamType) : "";
    }

    @NotNull
    public String getSignature(@Nullable String testingQualifier) {
        if (myParamType == null) {
            return "";
        }
        return myParamType.toLowerCase(Locale.US) + " " + getQualifiedParamType(testingQualifier);
    }

    @Nullable
    public static GnoTestFunctionType fromName(@Nullable String functionName) {
        if (StringUtil.isEmpty(functionName)) return null;
        if (GnoConstants.TEST_MAIN.equals(functionName)) return TEST_MAIN;
        for (GnoTestFunctionType type : values()) {
            if (checkPrefix(functionName, type.myPrefix)) return type;
        }
        return null;
    }

    private static boolean checkPrefix(@Nullable String name, @NotNull String prefix) {
        // https://github.com/golang/go/blob/master/src/cmd/go/test.go#L1161 – isTest()
        if (name == null || !name.startsWith(prefix)) return false;
        if (prefix.length() == name.length()) return true;
        char c = name.charAt(prefix.length());
        return !Character.isLetter(c) || !Character.isLowerCase(c);
    }
}
