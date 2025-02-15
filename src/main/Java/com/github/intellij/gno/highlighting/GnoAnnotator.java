package com.github.intellij.gno.highlighting;

import com.github.intellij.gno.psi.GnoConstants;
import com.github.intellij.gno.GnoTypes;
import com.github.intellij.gno.inspections.GnoInspectionUtil;
import com.github.intellij.gno.psi.*;
import com.github.intellij.gno.psi.impl.GnoCType;
import com.github.intellij.gno.psi.impl.GnoPsiImplUtil;
import com.github.intellij.gno.psi.impl.GnoTypeUtil;
import com.github.intellij.gno.quickfix.GnoDeleteRangeQuickFix;
import com.github.intellij.gno.quickfix.GnoEmptySignatureQuickFix;
import com.github.intellij.gno.quickfix.GnoReplaceWithReturnStatementQuickFix;
import com.google.common.collect.Sets;
import com.intellij.lang.ASTNode;
import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.TokenSet;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;

public class GnoAnnotator implements Annotator {
  private static final Set<String> INT_TYPE_NAMES = Sets.newHashSet(
    "int", "int8", "int16", "int32", "int64", "uint", "uint8", "uint16", "uint32", "uint64", "uintptr",
    "rune", "float32", "float64"
  ); // todo: unify with DlvApi.Variable.Kind

  @Override
  public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
    if (!(element instanceof GnoCompositeElement) || !element.isValid()) return;

    if (element instanceof GnoPackageClause) {
      PsiElement identifier = ((GnoPackageClause)element).getIdentifier();
      if (identifier != null && identifier.textMatches("_")) {
        holder.createErrorAnnotation(identifier, "Invalid package name");
        return;
      }
    }

    if (element instanceof GnoContinueStatement) {
      if (!(PsiTreeUtil.getParentOfType(element, GnoForStatement.class, GnoFunctionLit.class) instanceof GnoForStatement)) {
        Annotation annotation = holder.createErrorAnnotation(element, "Continue statement not inside a for loop");
        annotation.registerFix(new GnoReplaceWithReturnStatementQuickFix(element));
      }
    }
    else if (element instanceof GnoBreakStatement) {
      if (GnoPsiImplUtil.getBreakStatementOwner(element) == null) {
        Annotation annotation = holder.createErrorAnnotation(element, "Break statement not inside a for loop, select or switch");
        annotation.registerFix(new GnoReplaceWithReturnStatementQuickFix(element));
      }
    }
    else if (element instanceof GnoReferenceExpression) {
      GnoReferenceExpression reference = (GnoReferenceExpression)element;
      PsiElement resolvedReference = reference.resolve();
      if (resolvedReference instanceof PsiDirectory || resolvedReference instanceof GnoImportSpec) {
        // It's a package reference. It should either be inside a package clause or part of a larger reference expression.
        if (!(element.getParent() instanceof GnoReferenceExpression) &&
            PsiTreeUtil.getParentOfType(reference, GnoPackageClause.class) == null) {
          holder.createErrorAnnotation(element, "Use of package " + element.getText() + " without selector");
        }
      }
      if (resolvedReference instanceof GnoTypeSpec && isIllegalUseOfTypeAsExpression(reference)) {
        holder.createErrorAnnotation(element, "Type " + element.getText() + " is not an expression");
      }
      if (resolvedReference instanceof GnoConstDefinition &&
          resolvedReference.getParent() instanceof GnoConstSpec &&
          PsiTreeUtil.getParentOfType(element, GnoConstDeclaration.class) != null) {
        checkSelfReference((GnoReferenceExpression)element, resolvedReference, holder);
      }
      if (resolvedReference instanceof GnoVarDefinition &&
          resolvedReference.getParent() instanceof GnoVarSpec &&
          PsiTreeUtil.getParentOfType(element, GnoVarDeclaration.class) != null) {
        checkSelfReference((GnoReferenceExpression)element, resolvedReference, holder);
      }
    }
    else if (element instanceof GnoLiteralTypeExpr) {
      if (isIllegalUseOfTypeAsExpression(element)) {
        holder.createErrorAnnotation(element, "Type " + element.getText() + " is not an expression");
      }
    }
    else if (element instanceof GnoCompositeLit) {
      GnoCompositeLit literal = (GnoCompositeLit)element;
      if (literal.getType() instanceof GnoMapType) {
        GnoLiteralValue literalValue = literal.getLiteralValue();
        if (literalValue != null) {
          for (GnoElement literalElement : literalValue.getElementList()) {
            if (literalElement.getKey() == null) {
              holder.createErrorAnnotation(literalElement, "Missing key in map literal");
            }
          }
        }
      }
    }
    else if (element instanceof GnoTypeAssertionExpr) {
      GnoType type = ((GnoTypeAssertionExpr)element).getExpression().getGnoType(null);
      if (type != null) {
        GnoType underlyingType = type.getUnderlyingType();
        if (!(underlyingType instanceof GnoInterfaceType)) {
          String message =
            String.format("Invalid type assertion: %s, (non-interface type %s on left)", element.getText(), type.getText());
          holder.createErrorAnnotation(((GnoTypeAssertionExpr)element).getExpression(), message);
        }
      }
    }
    else if (element instanceof GnoBuiltinCallExpr) {
      GnoBuiltinCallExpr call = (GnoBuiltinCallExpr)element;
      if ("make".equals(call.getReferenceExpression().getText())) {
        checkMakeCall(call, holder);
      }
    }
    else if (element instanceof GnoCallExpr) {
      GnoCallExpr call = (GnoCallExpr)element;
      GnoExpression callExpression = call.getExpression();
      if (GnoInspectionUtil.getFunctionResultCount(call) == 0) {
        PsiElement parent = call.getParent();
        boolean simpleStatement = parent instanceof GnoLeftHandExprList && parent.getParent() instanceof GnoSimpleStatement;
        boolean inDeferOrGno = parent instanceof GnoDeferStatement || parent instanceof GnoGoStatement;
        if (!simpleStatement && !inDeferOrGno) {
          holder.createErrorAnnotation(call, call.getText() + " used as value");
        }
      }
      if (callExpression instanceof GnoReferenceExpression) {
        GnoReferenceExpression reference = (GnoReferenceExpression)callExpression;
        if (reference.textMatches("cap")) {
          if (GnoPsiImplUtil.builtin(reference.resolve())) {
            checkCapCall(call, holder);
          }
        }
      }
    }
    else if (element instanceof GnoTopLevelDeclaration) {
      if (element.getParent() instanceof GnoFile) {
        if (element instanceof GnoTypeDeclaration) {
          for (GnoTypeSpec spec : ((GnoTypeDeclaration)element).getTypeSpecList()) {
            if (spec.getIdentifier().textMatches(GnoConstants.INIT)) {
              holder.createErrorAnnotation(spec, "Cannot declare init, must be a function");
            }
          }
        }
        else if (element instanceof GnoVarDeclaration) {
          for (GnoVarSpec spec : ((GnoVarDeclaration)element).getVarSpecList()) {
            for (GnoVarDefinition definition : spec.getVarDefinitionList()) {
              if (definition.getIdentifier().textMatches(GnoConstants.INIT)) {
                holder.createErrorAnnotation(spec, "Cannot declare init, must be a function");
              }
            }
          }
        }
        else if (element instanceof GnoConstDeclaration) {
          for (GnoConstSpec spec : ((GnoConstDeclaration)element).getConstSpecList()) {
            for (GnoConstDefinition definition : spec.getConstDefinitionList()) {
              if (definition.getIdentifier().textMatches(GnoConstants.INIT)) {
                holder.createErrorAnnotation(spec, "Cannot declare init, must be a function");
              }
            }
          }
        }
        else if (element instanceof GnoFunctionDeclaration) {
          GnoFunctionDeclaration declaration = (GnoFunctionDeclaration)element;
          if (declaration.getIdentifier().textMatches(GnoConstants.INIT) ||
              declaration.getIdentifier().textMatches(GnoConstants.MAIN) &&
              GnoConstants.MAIN.equals(declaration.getContainingFile().getPackageName())) {
            GnoSignature signature = declaration.getSignature();
            if (signature != null) {
              GnoResult result = signature.getResult();
              if (result != null && !result.isVoid()) {
                Annotation annotation = holder.createErrorAnnotation(result, declaration.getName() +
                                                                             " function must have no arguments and no return values");
                annotation.registerFix(new GnoEmptySignatureQuickFix(declaration));
              }
              GnoParameters parameters = signature.getParameters();
              if (!parameters.getParameterDeclarationList().isEmpty()) {
                Annotation annotation = holder.createErrorAnnotation(parameters, declaration.getName() +
                                                                                 " function must have no arguments and no return values");
                annotation.registerFix(new GnoEmptySignatureQuickFix(declaration));
              }
            }
          }
        }
      }
    }
    else if (element instanceof GnoIndexOrSliceExpr) {
      GnoIndexOrSliceExpr slice = (GnoIndexOrSliceExpr)element;
      GnoExpression expr = slice.getExpression();
      GnoExpression thirdIndex = slice.getIndices().third;
      if (expr == null || thirdIndex == null) {
        return;
      }
      if (GnoTypeUtil.isString(expr.getGnoType(null))) {
        ASTNode[] colons = slice.getNode().getChildren(TokenSet.create(GnoTypes.COLON));
        if (colons.length == 2) {
          PsiElement secondColon = colons[1].getPsi();
          TextRange r = TextRange.create(secondColon.getTextRange().getStartOffset(), thirdIndex.getTextRange().getEndOffset());
          Annotation annotation = holder.createErrorAnnotation(r, "Invalid operation " + slice.getText() + " (3-index slice of string)");
          annotation.registerFix(new GnoDeleteRangeQuickFix(secondColon, thirdIndex, "Delete third index"));
        }
      }
    }
  }

  private static void checkCapCall(@NotNull GnoCallExpr capCall, @NotNull AnnotationHolder holder) {
    List<GnoExpression> exprs = capCall.getArgumentList().getExpressionList();
    if (exprs.size() != 1) return;
    GnoExpression first = ContainerUtil.getFirstItem(exprs);
    //noinspection ConstantConditions
    GnoType exprType = first.getGnoType(null); // todo: context
    if (exprType == null) return;
    GnoType baseType = GnoPsiImplUtil.unwrapPointerIfNeeded(exprType.getUnderlyingType());
    if (baseType instanceof GnoArrayOrSliceType || baseType instanceof GnoChannelType) return;
    holder.createErrorAnnotation(first, "Invalid argument for cap");
  }

  private static void checkMakeCall(@NotNull GnoBuiltinCallExpr call, @NotNull AnnotationHolder holder) {
    GnoBuiltinArgumentList args = call.getBuiltinArgumentList();
    if (args == null) {
      holder.createErrorAnnotation(call, "Missing argument to make");
      return;
    }
    GnoType type = args.getType();
    if (type == null) {
      GnoExpression first = ContainerUtil.getFirstItem(args.getExpressionList());
      if (first != null) {
        holder.createErrorAnnotation(call, first.getText() + " is not a type");
      }
      else {
        holder.createErrorAnnotation(args, "Missing argument to make");
      }
    }
    else {
      // We have a type, is it valid?
      GnoType baseType = type.getUnderlyingType();
      if (canMakeType(baseType)) {
        // We have a type and we can make the type, are the parameters to make valid?
        checkMakeArgs(call, baseType, args.getExpressionList(), holder);
      }
      else {
        holder.createErrorAnnotation(type, "Cannot make " + type.getText());
      }
    }
  }

  private static boolean canMakeType(@Nullable GnoType type) {
    if (type instanceof GnoArrayOrSliceType) {
      // Only slices (no size expression) can be make()'d.
      return ((GnoArrayOrSliceType)type).getExpression() == null;
    }
    return type instanceof GnoChannelType || type instanceof GnoMapType;
  }

  private static void checkMakeArgs(@NotNull GnoBuiltinCallExpr call,
                                    @Nullable GnoType baseType,
                                    @NotNull List<GnoExpression> list,
                                    @NotNull AnnotationHolder holder) {
    if (baseType instanceof GnoArrayOrSliceType) {
      if (list.isEmpty()) {
        holder.createErrorAnnotation(call, "Missing len argument to make");
        return;
      }
      else if (list.size() > 2) {
        holder.createErrorAnnotation(call, "Too many arguments to make");
        return;
      }
    }

    if (baseType instanceof GnoChannelType || baseType instanceof GnoMapType) {
      if (list.size() > 1) {
        holder.createErrorAnnotation(call, "Too many arguments to make");
        return;
      }
    }

    for (int i = 0; i < list.size(); i++) {
      GnoExpression expression = list.get(i);
      GnoType type = expression.getGnoType(null); // todo: context
      if (type != null) {
        GnoType expressionBaseType = type.getUnderlyingType();
        if (!(isIntegerConvertibleType(expressionBaseType) || isCType(type))) {
          String argName = i == 0 ? "size" : "capacity";
          holder.createErrorAnnotation(expression, "Non-integer " + argName + " argument to make");
        }
      }
    }
  }

  private static boolean isCType(@Nullable GnoType type) {
    return type instanceof GnoCType;
  }

  private static boolean isIntegerConvertibleType(@Nullable GnoType type) {
    if (type == null) return false;
    GnoTypeReferenceExpression ref = type.getTypeReferenceExpression();
    if (ref == null) return false;
    return INT_TYPE_NAMES.contains(ref.getText()) && GnoPsiImplUtil.builtin(ref.resolve());
  }

  private static void checkSelfReference(@NotNull GnoReferenceExpression o, PsiElement definition, AnnotationHolder holder) {
    GnoExpression value = null;
    if (definition instanceof GnoVarDefinition) {
      value = ((GnoVarDefinition)definition).getValue();
    }
    else if (definition instanceof GnoConstDefinition) {
      value = ((GnoConstDefinition)definition).getValue();
    }

    if (value != null && value.equals(GnoPsiImplUtil.getNonStrictTopmostParentOfType(o, GnoExpression.class))) {
      holder.createErrorAnnotation(o, "Cyclic definition detected");
    }
  }

  /**
   * Returns {@code true} if the given element is in an invalid location for a type literal or type reference.
   */
  private static boolean isIllegalUseOfTypeAsExpression(@NotNull PsiElement e) {
    PsiElement parent = PsiTreeUtil.skipParentsOfType(e, GnoParenthesesExpr.class, GnoUnaryExpr.class);
    // Part of a selector such as T.method
    if (parent instanceof GnoReferenceExpression || parent instanceof GnoSelectorExpr) return false;
    // A situation like T("foo").
    return !(parent instanceof GnoCallExpr);
  }
}
