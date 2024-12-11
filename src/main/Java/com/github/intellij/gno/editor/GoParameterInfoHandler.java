package com.github.intellij.gno.editor;

import com.github.intellij.gno.GoTypes;
import com.github.intellij.gno.psi.*;
import com.intellij.codeInsight.CodeInsightBundle;
import com.intellij.lang.parameterInfo.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.ArrayUtil;
import com.intellij.util.Function;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GoParameterInfoHandler implements ParameterInfoHandlerWithTabActionSupport<GoArgumentList, Object, GoExpression> {
  @NotNull
  @Override
  public GoExpression @NotNull [] getActualParameters(@NotNull GoArgumentList o) {
    return ArrayUtil.toObjectArray(o.getExpressionList(), GoExpression.class);
  }

  @NotNull
  @Override
  public IElementType getActualParameterDelimiterType() {
    return GoTypes.COMMA;
  }

  @NotNull
  @Override
  public IElementType getActualParametersRBraceType() {
    return GoTypes.RPAREN;
  }

  @Override
  public @NotNull Set<Class<?>> getArgumentListAllowedParentClasses() {
    return ContainerUtil.newHashSet();
  }

  @Override
  public @NotNull Set<? extends Class<?>> getArgListStopSearchClasses() {
    return ContainerUtil.newHashSet();
  }

  @NotNull
  @Override
  public Class<GoArgumentList> getArgumentListClass() {
    return GoArgumentList.class;
  }

  @Nullable
  @Override
  public GoArgumentList findElementForParameterInfo(@NotNull CreateParameterInfoContext context) {
    // todo: see ParameterInfoUtils.findArgumentList
    return getList(context);
  }

  @Nullable
  private static GoArgumentList getList(@NotNull ParameterInfoContext context) {
    PsiElement at = context.getFile().findElementAt(context.getOffset());
    return PsiTreeUtil.getParentOfType(at, GoArgumentList.class);
  }

  @Override
  public void showParameterInfo(@NotNull GoArgumentList argList, @NotNull CreateParameterInfoContext context) {
    PsiElement parent = argList.getParent();
    if (!(parent instanceof GoCallExpr)) return;
    GoFunctionType type = findFunctionType(((GoCallExpr)parent).getExpression().getGoType(null));
    if (type != null) {
      context.setItemsToShow(new Object[]{type});
      context.showHint(argList, argList.getTextRange().getStartOffset(), this);
    }
  }

  @Nullable
  private static GoFunctionType findFunctionType(@Nullable GoType type) {
    if (type instanceof GoFunctionType || type == null) return (GoFunctionType)type;
    GoType base = type.getUnderlyingType();
    return base instanceof GoFunctionType ? (GoFunctionType)base : null;
  }

  @Nullable
  @Override
  public GoArgumentList findElementForUpdatingParameterInfo(@NotNull UpdateParameterInfoContext context) {
    return getList(context);
  }

  @Override
  public void updateParameterInfo(@NotNull GoArgumentList list, @NotNull UpdateParameterInfoContext context) {
    context.setCurrentParameter(ParameterInfoUtils.getCurrentParameterIndex(list.getNode(), context.getOffset(), GoTypes.COMMA));
  }

  @Override
  public void updateUI(@Nullable Object p, @NotNull ParameterInfoUIContext context) {
    updatePresentation(p, context);
  }

  static void updatePresentation(@Nullable Object p, @NotNull ParameterInfoUIContext context) {
    if (p == null) {
      context.setUIComponentEnabled(false);
      return;
    }
    GoSignature signature = p instanceof GoSignatureOwner ? ((GoSignatureOwner)p).getSignature() : null;
    if (signature == null) return;
    GoParameters parameters = signature.getParameters();
    List<String> parametersPresentations = getParameterPresentations(parameters, PsiElement::getText);

    StringBuilder builder = new StringBuilder();
    int start = 0;
    int end = 0;
    if (!parametersPresentations.isEmpty()) {
      // Figure out what particular presentation is actually selected. Take in
      // account possibility of the last variadic parameter.
      int selected = isLastParameterVariadic(parameters.getParameterDeclarationList())
                     ? Math.min(context.getCurrentParameterIndex(), parametersPresentations.size() - 1)
                     : context.getCurrentParameterIndex();

      for (int i = 0; i < parametersPresentations.size(); ++i) {
        if (i != 0) {
          builder.append(", ");
        }
        if (i == selected) {
          start = builder.length();
        }
        builder.append(parametersPresentations.get(i));

        if (i == selected) {
          end = builder.length();
        }
      }
    }
    else {
      builder.append(CodeInsightBundle.message("parameter.info.no.parameters"));
    }
    context.setupUIComponentPresentation(builder.toString(), start, end, false, false, false, context.getDefaultParameterColor());
  }

  /**
   * Creates a list of parameter presentations. For clarity we expand parameters declared as `a, b, c int` into `a int, b int, c int`.
   */
  @NotNull
  public static List<String> getParameterPresentations(@NotNull GoParameters parameters,
                                                       @NotNull Function<PsiElement, String> typePresentationFunction) {
    List<GoParameterDeclaration> paramDeclarations = parameters.getParameterDeclarationList();
    List<String> paramPresentations = new ArrayList<>(2 * paramDeclarations.size());
    for (GoParameterDeclaration paramDeclaration : paramDeclarations) {
      boolean isVariadic = paramDeclaration.isVariadic();
      List<GoParamDefinition> paramDefinitionList = paramDeclaration.getParamDefinitionList();
      for (GoParamDefinition paramDefinition : paramDefinitionList) {
        String separator = isVariadic ? " ..." : " ";
        paramPresentations.add(paramDefinition.getText() + separator + typePresentationFunction.fun(paramDeclaration.getType()));
      }
      if (paramDefinitionList.isEmpty()) {
        String separator = isVariadic ? "..." : "";
        paramPresentations.add(separator + typePresentationFunction.fun(paramDeclaration.getType()));
      }
    }
    return paramPresentations;
  }

  private static boolean isLastParameterVariadic(@NotNull List<GoParameterDeclaration> declarations) {
    GoParameterDeclaration lastItem = ContainerUtil.getLastItem(declarations);
    return lastItem != null && lastItem.isVariadic();
  }
}
