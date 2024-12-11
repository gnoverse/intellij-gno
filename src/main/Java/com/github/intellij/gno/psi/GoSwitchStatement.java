package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;

public interface GoSwitchStatement extends GoStatement {

    @Nullable
    GoSwitchStart getSwitchStart();

    @Nullable
    GoSwitchStatement getSwitchStatement();

}