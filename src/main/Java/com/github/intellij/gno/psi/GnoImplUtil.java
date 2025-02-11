package com.github.intellij.gno.psi;

import com.github.intellij.gno.utils.GnoStringUtil;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.ResolveState;
import com.intellij.psi.impl.source.resolve.reference.ReferenceProvidersRegistry;
import com.intellij.psi.impl.source.tree.LeafElement;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.psi.tree.ILeafElementType;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.ObjectUtils;
import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class GnoImplUtil {
    public GnoImplUtil() {
    }

    public static boolean processDeclarations(@NotNull GnoStatementList o, @NotNull PsiScopeProcessor processor, @NotNull ResolveState state, @Nullable PsiElement lastParent, @NotNull PsiElement place) {


        if (lastParent instanceof GnoStatement statement) {
            while((statement = PsiTreeUtil.getPrevSiblingOfType(statement, GnoStatement.class)) != null) {
                if (!statement.processDeclarations(processor, state, lastParent, place)) {
                    return false;
                }
            }
        }

        return true;
    }

    public static @Nullable GnoExpression getQualifier(@NotNull GnoFieldChainExpr chain) {

        return ObjectUtils.tryCast(chain.getDot().getPrevSibling(), GnoExpression.class);
    }

    public static @NotNull PsiElement getDot(@NotNull GnoFieldChainExpr chain) {

        PsiElement var10000 = Objects.requireNonNull(chain.getNode().findChildByType(GnoTypes.DOT)).getPsi();
        if (var10000 == null) {
            throw new NullPointerException("WHITESPACES is null");
        }
        return var10000;
    }

    public static @NotNull PsiElement getIdentifier(@NotNull GnoFieldChainExpr chain) {

        PsiElement var10000 = (Objects.requireNonNull(chain.getNode().findChildByType(GnoTypes.IDENTIFIER))).getPsi();
        if (var10000 == null) {
            throw new NullPointerException("WHITESPACES is null");
        }
        return var10000;
    }

    public static @NotNull List<GnoExpression> getExpressionList(@NotNull GnoPipeline pipeline) {

        return PsiTreeUtil.getChildrenOfTypeAsList(pipeline, GnoExpression.class);
    }

    public static boolean processDeclarations(@NotNull GnoStatement o, @NotNull PsiScopeProcessor processor, @NotNull ResolveState state, @Nullable PsiElement lastParent, @NotNull PsiElement place) {

        if (o instanceof GnoVarDeclarationStatement) {
            return processor.execute(o, state);
        } else if (o instanceof GnoRangeStatement && PsiTreeUtil.isAncestor(o, place, false)) {
            GnoRangeVarDeclaration declaration = ((GnoRangeStatement)o).getRangeVarDeclaration();
            return declaration == null || processor.execute(declaration, state);
        } else if (o instanceof GnoDeclarationOwner) {
            GnoVarDeclaration declaration = ((GnoDeclarationOwner)o).getVarDeclaration();
            return declaration == null || processor.execute(declaration, state);
        } else {
            return true;
        }
    }

    public static @NotNull PsiReference getReference(@NotNull GnoVariableExpr variableExpr) {

        return new GnoVariableReference(variableExpr);
    }

    public static PsiReference @NotNull [] getReferences(@NotNull GnoStringLiteral literal) {

        return ReferenceProvidersRegistry.getReferencesFromProviders(literal);
    }

    public static @NotNull GnoStringLiteral updateText(@NotNull GnoStringLiteral o, @NotNull String text) {

        if (text.length() > 2 && o.getString() != null) {
            StringBuilder outChars = new StringBuilder("\"");
            GnoStringUtil.escapeString(text.substring(1, text.length() - 1), outChars);
            outChars.append('"');
            text = outChars.toString();
        }

        ASTNode valueNode = o.getNode().getFirstChildNode();

        assert valueNode instanceof LeafElement;

        ((LeafElement)valueNode).replaceWithText(text);

        return o;
    }

    public static GnoTokenType createIdentifierTokenType(@NotNull String name) {

        return !"IDENTIFIER".equals(name) && !"VARIABLE".equals(name) ? new GnoTokenType(name) : new GnoReferenceTokenType(name);
    }

    private static final class GnoReferenceTokenType extends GnoTokenType implements ILeafElementType {
        private GnoReferenceTokenType(@NotNull String debugName) {

            super(debugName);
        }

        public @NotNull ASTNode createLeafNode(@NotNull CharSequence leafText) {

            return new LeafPsiElement(this, leafText) {
                public PsiReference @NotNull [] getReferences() {

                    return ReferenceProvidersRegistry.getReferencesFromProviders(this);
                }
            };
        }
    }
}
