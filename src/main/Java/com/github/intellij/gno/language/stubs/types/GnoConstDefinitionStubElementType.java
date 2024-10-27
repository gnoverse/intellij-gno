package com.github.intellij.gno.language.stubs.types;

import com.github.intellij.gno.language.psi.GnoConstDefinition;
import com.github.intellij.gno.language.psi.GnoFunctionOrMethodDeclaration;
import com.github.intellij.gno.language.psi.impl.GnoConstDefinitionImpl;
import com.github.intellij.gno.language.stubs.GnoConstDefinitionStub;
import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.ArrayFactory;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class GnoConstDefinitionStubElementType extends GnoNamedStubElementType<GnoConstDefinitionStub, GnoConstDefinition> {
    public static final GnoConstDefinition[] EMPTY_ARRAY = new GnoConstDefinition[0];

    public static final ArrayFactory<GnoConstDefinition> ARRAY_FACTORY = count -> count == 0 ? EMPTY_ARRAY : new GnoConstDefinition[count];

    public GnoConstDefinitionStubElementType(@NotNull String name) {
        super(name);
    }

    @NotNull
    @Override
    public GnoConstDefinition createPsi(@NotNull GnoConstDefinitionStub stub) {
        return new GnoConstDefinitionImpl(stub, this);
    }

    @NotNull
    @Override
    public GnoConstDefinitionStub createStub(@NotNull GnoConstDefinition psi, StubElement parentStub) {
        return new GnoConstDefinitionStub(parentStub, this, psi.getName(), psi.isPublic());
    }

    @Override
    public void serialize(@NotNull GnoConstDefinitionStub stub, @NotNull StubOutputStream dataStream) throws IOException {
        dataStream.writeName(stub.getName());
        dataStream.writeBoolean(stub.isPublic());
    }

    @NotNull
    @Override
    public GnoConstDefinitionStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
        return new GnoConstDefinitionStub(parentStub, this, dataStream.readName(), dataStream.readBoolean());
    }

    @Override
    public boolean shouldCreateStub(@NotNull ASTNode node) {
        return super.shouldCreateStub(node) && PsiTreeUtil.getParentOfType(node.getPsi(), GnoFunctionOrMethodDeclaration.class) == null;
    }
}