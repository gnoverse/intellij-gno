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
        if (o == null) {
            $$$reportNull$$$0(0);
        }

        if (processor == null) {
            $$$reportNull$$$0(1);
        }

        if (state == null) {
            $$$reportNull$$$0(2);
        }

        if (place == null) {
            $$$reportNull$$$0(3);
        }

        if (lastParent instanceof GnoStatement statement) {
            while((statement = (GnoStatement)PsiTreeUtil.getPrevSiblingOfType(statement, GnoStatement.class)) != null) {
                if (!statement.processDeclarations(processor, state, lastParent, place)) {
                    return false;
                }
            }
        }

        return true;
    }

    public static @Nullable GnoExpression getQualifier(@NotNull GnoFieldChainExpr chain) {
        if (chain == null) {
            $$$reportNull$$$0(4);
        }

        return (GnoExpression)ObjectUtils.tryCast(chain.getDot().getPrevSibling(), GnoExpression.class);
    }

    public static @NotNull PsiElement getDot(@NotNull GnoFieldChainExpr chain) {
        if (chain == null) {
            $$$reportNull$$$0(5);
        }

        PsiElement var10000 = ((ASTNode)Objects.requireNonNull(chain.getNode().findChildByType(GnoTypes.DOT))).getPsi();
        if (var10000 == null) {
            $$$reportNull$$$0(6);
        }

        return var10000;
    }

    public static @NotNull PsiElement getIdentifier(@NotNull GnoFieldChainExpr chain) {
        if (chain == null) {
            $$$reportNull$$$0(7);
        }

        PsiElement var10000 = ((ASTNode)Objects.requireNonNull(chain.getNode().findChildByType(GnoTypes.IDENTIFIER))).getPsi();
        if (var10000 == null) {
            $$$reportNull$$$0(8);
        }

        return var10000;
    }

    public static @NotNull List<GnoExpression> getExpressionList(@NotNull GnoPipeline pipeline) {
        if (pipeline == null) {
            $$$reportNull$$$0(9);
        }

        List var10000 = PsiTreeUtil.getChildrenOfTypeAsList(pipeline, GnoExpression.class);
        if (var10000 == null) {
            $$$reportNull$$$0(10);
        }

        return var10000;
    }

    public static boolean processDeclarations(@NotNull GnoStatement o, @NotNull PsiScopeProcessor processor, @NotNull ResolveState state, @Nullable PsiElement lastParent, @NotNull PsiElement place) {
        if (o == null) {
            $$$reportNull$$$0(11);
        }

        if (processor == null) {
            $$$reportNull$$$0(12);
        }

        if (state == null) {
            $$$reportNull$$$0(13);
        }

        if (place == null) {
            $$$reportNull$$$0(14);
        }

        if (o instanceof GnoVarDeclarationStatement) {
            return processor.execute(o, state);
        } else if (o instanceof GnoRangeStatement && PsiTreeUtil.isAncestor(o, place, false)) {
            GnoRangeVarDeclaration declaration = ((GnoRangeStatement)o).getRangeVarDeclaration();
            return declaration == null || processor.execute(declaration, state);
        } else if (o instanceof GnoDeclarationOwner && (o instanceof GnoStatement || PsiTreeUtil.isAncestor(o, place, false))) {
            GnoVarDeclaration declaration = ((GnoDeclarationOwner)o).getVarDeclaration();
            return declaration == null || processor.execute(declaration, state);
        } else {
            return true;
        }
    }

    public static @NotNull PsiReference getReference(@NotNull GnoVariableExpr variableExpr) {
        if (variableExpr == null) {
            $$$reportNull$$$0(15);
        }

        return new GnoVariableReference(variableExpr);
    }

    public static PsiReference @NotNull [] getReferences(@NotNull GnoStringLiteral literal) {
        if (literal == null) {
            $$$reportNull$$$0(16);
        }

        PsiReference[] var10000 = ReferenceProvidersRegistry.getReferencesFromProviders(literal);
        if (var10000 == null) {
            $$$reportNull$$$0(17);
        }

        return var10000;
    }

    public static @NotNull GnoStringLiteral updateText(@NotNull GnoStringLiteral o, @NotNull String text) {
        if (o == null) {
            $$$reportNull$$$0(18);
        }

        if (text == null) {
            $$$reportNull$$$0(19);
        }

        if (text.length() > 2 && o.getString() != null) {
            StringBuilder outChars = new StringBuilder("\"");
            GnoStringUtil.escapeString(text.substring(1, text.length() - 1), outChars);
            outChars.append('"');
            text = outChars.toString();
        }

        ASTNode valueNode = o.getNode().getFirstChildNode();

        assert valueNode instanceof LeafElement;

        ((LeafElement)valueNode).replaceWithText(text);
        if (o == null) {
            $$$reportNull$$$0(20);
        }

        return o;
    }

    public static GnoTokenType createIdentifierTokenType(@NotNull String name) {
        if (name == null) {
            $$$reportNull$$$0(21);
        }

        return (GnoTokenType)(!"IDENTIFIER".equals(name) && !"VARIABLE".equals(name) ? new GnoTokenType(name) : new GnoReferenceTokenType(name));
    }

    private static final class GnoReferenceTokenType extends GnoTokenType implements ILeafElementType {
        private GnoReferenceTokenType(@NotNull String debugName) {
            if (debugName == null) {
                $$$reportNull$$$0(0);
            }

            super(debugName);
        }

        public @NotNull ASTNode createLeafNode(@NotNull CharSequence leafText) {
            if (leafText == null) {
                $$$reportNull$$$0(1);
            }

            return new LeafPsiElement(this, leafText) {
                public PsiReference @NotNull [] getReferences() {
                    PsiReference[] var10000 = ReferenceProvidersRegistry.getReferencesFromProviders(this);
                    if (var10000 == null) {
                        $$$reportNull$$$0(0);
                    }

                    return var10000;
                }
            };
        }
    }
}
