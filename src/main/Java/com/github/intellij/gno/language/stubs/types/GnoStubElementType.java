package com.github.intellij.gno.language.stubs.types;

import com.github.intellij.gno.language.psi.GnoCompositeElement;
import com.github.intellij.gno.services.GnoLanguage;
import com.github.intellij.gno.language.psi.GnoBlock;
import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.IndexSink;
import com.intellij.psi.stubs.StubBase;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class GnoStubElementType<S extends StubBase<T>, T extends GnoCompositeElement> extends IStubElementType<S, T> {
    public GnoStubElementType(@NonNls @NotNull String debugName) {
        super(debugName, GnoLanguage.INSTANCE);
    }

    public GnoStubElementType(@NotNull @NonNls String debugName, @Nullable Language language) {
        super(debugName, language);
    }

    @Override
    @NotNull
    public String getExternalId() {
        return "gno." + super.toString();
    }

    @Override
    public void indexStub(@NotNull S stub, @NotNull IndexSink sink) {
    }

    @Override
    public boolean shouldCreateStub(ASTNode node) {
        return super.shouldCreateStub(node) && shouldCreateStubInBlock(node);
    }

    protected boolean shouldCreateStubInBlock(ASTNode node) {
        return PsiTreeUtil.getParentOfType(node.getPsi(), GnoBlock.class) == null;
    }
}
