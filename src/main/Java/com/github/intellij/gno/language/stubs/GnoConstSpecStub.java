package com.github.intellij.gno.language.stubs;

import com.github.intellij.gno.language.psi.GnoConstSpec;
import com.github.intellij.gno.language.psi.GnoExpression;
import com.github.intellij.gno.language.psi.impl.GnoElementFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubBase;
import com.intellij.psi.stubs.StubElement;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.io.StringRef;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GnoConstSpecStub extends StubBase<GnoConstSpec> {
    private final StringRef myExpressionsRef;
    private List<GnoExpression> myList;

    public GnoConstSpecStub(StubElement parent, IStubElementType elementType, StringRef ref) {
        super(parent, elementType);
        myExpressionsRef = ref;
    }

    @Nullable
    public String getExpressionsText() {
        return myExpressionsRef == null? null : myExpressionsRef.getString();
    }

    @NotNull
    public List<GnoExpression> getExpressionList() {
        if (myList == null) {
            String text = getExpressionsText();
            if (!StringUtil.isNotEmpty(text)) return myList = ContainerUtil.emptyList();
            Project project = getPsi().getProject();
            List<String> split = StringUtil.split(text, ";");
            myList = ContainerUtil.map(split, s -> GnoElementFactory.createExpression(project, s));
        }
        return myList;
    }
}