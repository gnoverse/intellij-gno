/*
 * Copyright 2013-2016 Sergey Ignatov, Alexander Zolotov, Florin Patan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.intellij.gno.psi.impl;

import com.github.intellij.gno.psi.GnoConstants;
import com.github.intellij.gno.psi.*;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class GnoTypeUtil {
  /**
   * https://golang.org/ref/spec#For_statements
   * The expression on the right in the "range" clause is called the range expression,
   * which may be an array, pointer to an array, slice, string, map, or channel permitting receive operations.
   */
  public static boolean isIterable(@Nullable GnoType type) {
    type = type != null ? type.getUnderlyingType() : null;
    return type instanceof GnoArrayOrSliceType ||
           type instanceof GnoPointerType && isArray(((GnoPointerType)type).getType()) ||
           type instanceof GnoMapType ||
           type instanceof GnoChannelType ||
           isString(type);
  }

  private static boolean isArray(@Nullable GnoType type) {
    type = type != null ? type.getUnderlyingType() : null;
    return type instanceof GnoArrayOrSliceType && ((GnoArrayOrSliceType)type).getExpression() != null;
  }

  public static boolean isString(@Nullable GnoType type) {
    return isBuiltinType(type, "string");
  }

  public static boolean isBoolean(@Nullable GnoType type) {
    return isBuiltinType(type, "bool");
  }

  private static boolean isBuiltinType(@Nullable GnoType type, @Nullable String builtinTypeName) {
    if (builtinTypeName == null) return false;
    type = type != null ? type.getUnderlyingType() : null;
    return type != null &&
           !(type instanceof GnoCType) &&
           type.textMatches(builtinTypeName) && GnoPsiImplUtil.builtin(type);
  }

  @NotNull
  public static List<GnoType> getExpectedTypes(@NotNull GnoExpression expression) {
    PsiElement parent = expression.getParent();
    if (parent == null) return Collections.emptyList();
    if (parent instanceof GnoAssignmentStatement) {
      return getExpectedTypesFromAssignmentStatement(expression, (GnoAssignmentStatement)parent);
    }
    if (parent instanceof GnoRangeClause) {
      return Collections.singletonList(getGnoType(null, parent));
    }
    if (parent instanceof GnoRecvStatement) {
      return getExpectedTypesFromRecvStatement((GnoRecvStatement)parent);
    }
    if (parent instanceof GnoVarSpec) {
      return getExpectedTypesFromVarSpec(expression, (GnoVarSpec)parent);
    }
    if (parent instanceof GnoArgumentList) {
      return getExpectedTypesFromArgumentList(expression, (GnoArgumentList)parent);
    }
    if (parent instanceof GnoUnaryExpr) {
      GnoUnaryExpr unaryExpr = (GnoUnaryExpr)parent;
      if (unaryExpr.getSendChannel() != null) {
        GnoType type = ContainerUtil.getFirstItem(getExpectedTypes(unaryExpr));
        GnoType chanType = GnoElementFactory.createType(parent.getProject(), "chan " + getInterfaceIfNull(type, parent).getText());
        return Collections.singletonList(chanType);
      }
      else {
        return Collections.singletonList(getGnoType(null, parent));
      }
    }
    if (parent instanceof GnoSendStatement || parent instanceof GnoLeftHandExprList && parent.getParent() instanceof GnoSendStatement) {
      GnoSendStatement sendStatement = (GnoSendStatement)(parent instanceof GnoSendStatement ? parent : parent.getParent());
      return getExpectedTypesFromGnoSendStatement(expression, sendStatement);
    }
    if (parent instanceof GnoExprCaseClause) {
      return getExpectedTypesFromExprCaseClause((GnoExprCaseClause)parent);
    }
    return Collections.emptyList();
  }

  @NotNull
  private static List<GnoType> getExpectedTypesFromExprCaseClause(@NotNull GnoExprCaseClause exprCaseClause) {
    GnoExprSwitchStatement switchStatement = PsiTreeUtil.getParentOfType(exprCaseClause, GnoExprSwitchStatement.class);
    assert switchStatement != null;

    GnoExpression switchExpr = switchStatement.getExpression();
    if (switchExpr != null) {
      return Collections.singletonList(getGnoType(switchExpr, exprCaseClause));
    }

    GnoStatement statement = switchStatement.getStatement();
    if (statement == null) {
      return Collections.singletonList(getInterfaceIfNull(GnoPsiImplUtil.getBuiltinType("bool", exprCaseClause), exprCaseClause));
    }

    GnoLeftHandExprList leftHandExprList = statement instanceof GnoSimpleStatement ? ((GnoSimpleStatement)statement).getLeftHandExprList() : null;
    GnoExpression expr = leftHandExprList != null ? ContainerUtil.getFirstItem(leftHandExprList.getExpressionList()) : null;
    return Collections.singletonList(getGnoType(expr, exprCaseClause));
  }

  @NotNull
  private static List<GnoType> getExpectedTypesFromGnoSendStatement(@NotNull GnoExpression expression, @NotNull GnoSendStatement statement) {
    GnoLeftHandExprList leftHandExprList = statement.getLeftHandExprList();
    GnoExpression channel = ContainerUtil.getFirstItem(leftHandExprList != null ? leftHandExprList.getExpressionList() : statement.getExpressionList());
    GnoExpression sendExpr = statement.getSendExpression();
    assert channel != null;
    if (expression.isEquivalentTo(sendExpr)) {
      GnoType chanType = channel.getGnoType(null);
      if (chanType instanceof GnoChannelType) {
        return Collections.singletonList(getInterfaceIfNull(((GnoChannelType)chanType).getType(), statement));
      }
    }
    if (expression.isEquivalentTo(channel)) {
      GnoType type = sendExpr != null ? sendExpr.getGnoType(null) : null;
      GnoType chanType = GnoElementFactory.createType(statement.getProject(), "chan " + getInterfaceIfNull(type, statement).getText());
      return Collections.singletonList(chanType);
    }
    return Collections.singletonList(getInterfaceIfNull(null, statement));
  }

  @NotNull
  private static List<GnoType> getExpectedTypesFromArgumentList(@NotNull GnoExpression expression, @NotNull GnoArgumentList argumentList) {
    PsiElement parentOfParent = argumentList.getParent();
    assert parentOfParent instanceof GnoCallExpr;
    PsiReference reference = ((GnoCallExpr)parentOfParent).getExpression().getReference();
    if (reference != null) {
      PsiElement resolve = reference.resolve();
      if (resolve instanceof GnoFunctionOrMethodDeclaration) {
        GnoSignature signature = ((GnoFunctionOrMethodDeclaration)resolve).getSignature();
        if (signature != null) {
          List<GnoExpression> exprList = argumentList.getExpressionList();
          List<GnoParameterDeclaration> paramsList = signature.getParameters().getParameterDeclarationList();
          if (exprList.size() == 1) {
            List<GnoType> typeList = ContainerUtil.newSmartList();
            for (GnoParameterDeclaration parameterDecl : paramsList) {
              for (GnoParamDefinition parameter : parameterDecl.getParamDefinitionList()) {
                typeList.add(getGnoType(parameter, argumentList));
              }
              if (parameterDecl.getParamDefinitionList().isEmpty()) {
                typeList.add(getInterfaceIfNull(parameterDecl.getType(), argumentList));
              }
            }
            List<GnoType> result = ContainerUtil.newSmartList();
            if (paramsList.size() > 1) {
              assert paramsList.get(0) != null;
              result.add(getInterfaceIfNull(paramsList.get(0).getType(), argumentList));
            }
            return result;
          }
          else {
            int position = exprList.indexOf(expression);
            if (position >= 0) {
              int i = 0;
              for (GnoParameterDeclaration parameterDecl : paramsList) {
                int paramDeclSize = Math.max(1, parameterDecl.getParamDefinitionList().size());
                if (i + paramDeclSize > position) {
                  return Collections.singletonList(getInterfaceIfNull(parameterDecl.getType(), argumentList));
                }
                i += paramDeclSize;
              }
            }
          }
        }
      }
    }
    return Collections.singletonList(getInterfaceIfNull(null, argumentList));
  }

  @NotNull
  private static List<GnoType> getExpectedTypesFromRecvStatement(@NotNull GnoRecvStatement recvStatement) {
    List<GnoType> typeList = ContainerUtil.newSmartList();
    for (GnoExpression expr : recvStatement.getLeftExpressionsList()) {
      typeList.add(getGnoType(expr, recvStatement));
    }
    return Collections.singletonList(createGnoTypeListOrGnoType(typeList, recvStatement));
  }

  @NotNull
  private static List<GnoType> getExpectedTypesFromVarSpec(@NotNull GnoExpression expression, @NotNull GnoVarSpec varSpec) {
    List<GnoType> result = ContainerUtil.newSmartList();
    GnoType type = getInterfaceIfNull(varSpec.getType(), varSpec);
    if (varSpec.getRightExpressionsList().size() == 1) {
      List<GnoType> typeList = ContainerUtil.newSmartList();
      int defListSize = varSpec.getVarDefinitionList().size();
      for (int i = 0; i < defListSize; i++) {
        typeList.add(type);
      }
      result.add(createGnoTypeListOrGnoType(typeList, expression));
      if (defListSize > 1) {
        result.add(getInterfaceIfNull(type, varSpec));
      }
      return result;
    }
    result.add(type);
    return result;
  }

  @NotNull
  private static List<GnoType> getExpectedTypesFromAssignmentStatement(@NotNull GnoExpression expression,
                                                                      @NotNull GnoAssignmentStatement assignment) {
    List<GnoExpression> leftExpressions = assignment.getLeftHandExprList().getExpressionList();
    if (assignment.getExpressionList().size() == 1) {
      List<GnoType> typeList = ContainerUtil.newSmartList();
      for (GnoExpression expr : leftExpressions) {
        GnoType type = expr.getGnoType(null);
        typeList.add(type);
      }
      List<GnoType> result = ContainerUtil.newSmartList();
      if (leftExpressions.size() > 1) {
        result.add(getGnoType(leftExpressions.get(0), assignment));
      }
      return result;
    }

    int position = assignment.getExpressionList().indexOf(expression);
    GnoType leftExpression = leftExpressions.size() > position ? leftExpressions.get(position).getGnoType(null) : null;
    return Collections.singletonList(getInterfaceIfNull(leftExpression, assignment));
  }

  @NotNull
  private static GnoType createGnoTypeListOrGnoType(@NotNull List<GnoType> types, @NotNull PsiElement context) {
    if (types.size() < 2) {
      return getInterfaceIfNull(ContainerUtil.getFirstItem(types), context);
    }
    return GnoElementFactory.createTypeList(context.getProject(), StringUtil.join(types, type -> type == null ? GnoConstants.INTERFACE_TYPE : type.getText(), ", "));
  }

  @NotNull
  private static GnoType getInterfaceIfNull(@Nullable GnoType type, @NotNull PsiElement context) {
    return type == null ? GnoElementFactory.createType(context.getProject(), GnoConstants.INTERFACE_TYPE) : type;
  }

  @NotNull
  private static GnoType getGnoType(@Nullable GnoTypeOwner element, @NotNull PsiElement context) {
    return getInterfaceIfNull(element != null ? element.getGnoType(null) : null, context);
  }

  public static boolean isFunction(@Nullable GnoType goType) {
    return goType != null && goType.getUnderlyingType() instanceof GnoFunctionType;
  }
}
