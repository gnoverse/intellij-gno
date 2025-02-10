package com.github.intellij.gno.psi;

import java.util.List;
import org.jetbrains.annotations.NotNull;

public interface GnoCompositePipeline extends GnoPipeline {
    @NotNull List<GnoExpression> getExpressionList();

    @NotNull GnoPipeline getPipeline();
}
