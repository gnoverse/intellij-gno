package com.github.intellij.gno.inspections;

import com.github.intellij.gno.psi.*;
import com.github.intellij.gno.psi.impl.GnoPsiImplUtil;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GnoInspectionUtil {
    public static final int UNKNOWN_COUNT = -1;

    private GnoInspectionUtil() {}

    public static int getExpressionResultCount(GnoExpression call) {
        if (call instanceof GnoLiteral || call instanceof GnoStringLiteral || call instanceof GnoBinaryExpr ||
                call instanceof GnoUnaryExpr && ((GnoUnaryExpr)call).getSendChannel() == null || call instanceof GnoBuiltinCallExpr ||
                call instanceof GnoCompositeLit || call instanceof GnoIndexOrSliceExpr || call instanceof GnoFunctionLit) {
            return 1;
        }
        if (call instanceof GnoParenthesesExpr) {
            return getExpressionResultCount(((GnoParenthesesExpr)call).getExpression());
        }
        if (call instanceof GnoTypeAssertionExpr) {
            return getTypeAssertionResultCount((GnoTypeAssertionExpr)call);
        }
        if (GnoPsiImplUtil.isConversionExpression(call)) {
            return 1;
        }
        if (call instanceof GnoCallExpr) {
            return getFunctionResultCount((GnoCallExpr)call);
        }
        if (call instanceof GnoReferenceExpression) {
            // todo: always 1?
            PsiElement resolve = ((GnoReferenceExpression)call).resolve();
            if (resolve instanceof GnoVarDefinition || resolve instanceof GnoParamDefinition || resolve instanceof GnoReceiver) return 1;
        }
        return UNKNOWN_COUNT;
    }

    private static int getTypeAssertionResultCount(@NotNull GnoTypeAssertionExpr expression) { // todo: ???
        PsiElement parent = expression.getParent();
        if (parent instanceof GnoAssignmentStatement) {
            // TODO: get expressions and identifiers of assign statement
            return UNKNOWN_COUNT;
        }

        if (!(parent instanceof GnoVarSpec)) {
            return 1;
        }

        List<GnoVarDefinition> identifiers = ((GnoVarSpec)parent).getVarDefinitionList();
        List<GnoExpression> expressions = ((GnoVarSpec)parent).getRightExpressionsList();
        // if the type assertion is the only expression, and there are two variables.
        // The result of the type assertion is a pair of values with types (T, bool)
        if (identifiers.size() == 2 && expressions.size() == 1) {
            return 2;
        }

        return 1;
    }

    public static int getFunctionResultCount(@NotNull GnoCallExpr call) {
        GnoSignatureOwner signatureOwner = GnoPsiImplUtil.resolveCall(call);
        return signatureOwner == null ? UNKNOWN_COUNT : getFunctionResultCount(signatureOwner);
    }

    public static int getFunctionResultCount(@NotNull GnoSignatureOwner function) {
        int count = 0;
        GnoSignature signature = function.getSignature();
        GnoResult result = signature != null ? signature.getResult() : null;
        GnoParameters parameters = result != null ? result.getParameters() : null;
        if (parameters != null) {
            for (GnoParameterDeclaration p : parameters.getParameterDeclarationList()) {
                count += Math.max(p.getParamDefinitionList().size(), 1);
            }
            return count;
        }
        if (result != null) {
            GnoType type = result.getType();
            if (type instanceof GnoTypeList) return ((GnoTypeList)type).getTypeList().size();
            if (type != null) return 1;
        }
        return count;
    }
}
