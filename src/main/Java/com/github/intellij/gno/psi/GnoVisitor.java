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

// This is a generated file. Not intended for manual editing.
package com.github.intellij.gno.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiLanguageInjectionHost;

public class GnoVisitor extends PsiElementVisitor {

  public void visitAddExpr(@NotNull GnoAddExpr o) {
    visitBinaryExpr(o);
  }

  public void visitAndExpr(@NotNull GnoAndExpr o) {
    visitBinaryExpr(o);
  }

  public void visitAnonymousFieldDefinition(@NotNull GnoAnonymousFieldDefinition o) {
    visitNamedElement(o);
  }

  public void visitArgumentList(@NotNull GnoArgumentList o) {
    visitCompositeElement(o);
  }

  public void visitArrayOrSliceType(@NotNull GnoArrayOrSliceType o) {
    visitType(o);
  }

  public void visitAssignmentStatement(@NotNull GnoAssignmentStatement o) {
    visitStatement(o);
  }

  public void visitBinaryExpr(@NotNull GnoBinaryExpr o) {
    visitExpression(o);
  }

  public void visitBlock(@NotNull GnoBlock o) {
    visitCompositeElement(o);
  }

  public void visitBreakStatement(@NotNull GnoBreakStatement o) {
    visitStatement(o);
  }

  public void visitBuiltinArgumentList(@NotNull GnoBuiltinArgumentList o) {
    visitArgumentList(o);
  }

  public void visitBuiltinCallExpr(@NotNull GnoBuiltinCallExpr o) {
    visitExpression(o);
  }

  public void visitCallExpr(@NotNull GnoCallExpr o) {
    visitExpression(o);
  }

  public void visitChannelType(@NotNull GnoChannelType o) {
    visitType(o);
  }

  public void visitCommCase(@NotNull GnoCommCase o) {
    visitCompositeElement(o);
  }

  public void visitCommClause(@NotNull GnoCommClause o) {
    visitCompositeElement(o);
  }

  public void visitCompositeLit(@NotNull GnoCompositeLit o) {
    visitExpression(o);
  }

  public void visitConditionalExpr(@NotNull GnoConditionalExpr o) {
    visitBinaryExpr(o);
  }

  public void visitConstDeclaration(@NotNull GnoConstDeclaration o) {
    visitTopLevelDeclaration(o);
  }

  public void visitConstDefinition(@NotNull GnoConstDefinition o) {
    visitNamedElement(o);
  }

  public void visitConstSpec(@NotNull GnoConstSpec o) {
    visitCompositeElement(o);
  }

  public void visitContinueStatement(@NotNull GnoContinueStatement o) {
    visitStatement(o);
  }

  public void visitConversionExpr(@NotNull GnoConversionExpr o) {
    visitBinaryExpr(o);
  }

  public void visitDeferStatement(@NotNull GnoDeferStatement o) {
    visitStatement(o);
  }

  public void visitElement(@NotNull GnoElement o) {
    visitCompositeElement(o);
  }

  public void visitElseStatement(@NotNull GnoElseStatement o) {
    visitStatement(o);
  }

  public void visitExprCaseClause(@NotNull GnoExprCaseClause o) {
    visitCaseClause(o);
  }

  public void visitExprSwitchStatement(@NotNull GnoExprSwitchStatement o) {
    visitSwitchStatement(o);
  }

  public void visitExpression(@NotNull GnoExpression o) {
    visitTypeOwner(o);
  }

  public void visitFallthroughStatement(@NotNull GnoFallthroughStatement o) {
    visitStatement(o);
  }

  public void visitFieldDeclaration(@NotNull GnoFieldDeclaration o) {
    visitCompositeElement(o);
  }

  public void visitFieldDefinition(@NotNull GnoFieldDefinition o) {
    visitNamedElement(o);
  }

  public void visitFieldName(@NotNull GnoFieldName o) {
    visitReferenceExpressionBase(o);
  }

  public void visitForClause(@NotNull GnoForClause o) {
    visitCompositeElement(o);
  }

  public void visitForStatement(@NotNull GnoForStatement o) {
    visitStatement(o);
  }

  public void visitFunctionDeclaration(@NotNull GnoFunctionDeclaration o) {
    visitFunctionOrMethodDeclaration(o);
  }

  public void visitFunctionLit(@NotNull GnoFunctionLit o) {
    visitExpression(o);
    // visitSignatureOwner(o);
  }

  public void visitFunctionType(@NotNull GnoFunctionType o) {
    visitType(o);
    // visitSignatureOwner(o);
  }

  public void visitGnoStatement(@NotNull GnoGoStatement o) {
    visitStatement(o);
  }

  public void visitGnotoStatement(@NotNull GnoGnotoStatement o) {
    visitStatement(o);
  }

  public void visitIfStatement(@NotNull GnoIfStatement o) {
    visitStatement(o);
  }

  public void visitImportDeclaration(@NotNull GnoImportDeclaration o) {
    visitCompositeElement(o);
  }

  public void visitImportList(@NotNull GnoImportList o) {
    visitCompositeElement(o);
  }

  public void visitImportSpec(@NotNull GnoImportSpec o) {
    visitNamedElement(o);
  }

  public void visitImportString(@NotNull GnoImportString o) {
    visitCompositeElement(o);
  }

  public void visitIncDecStatement(@NotNull GnoIncDecStatement o) {
    visitStatement(o);
  }

  public void visitIndexOrSliceExpr(@NotNull GnoIndexOrSliceExpr o) {
    visitExpression(o);
  }

  public void visitInterfaceType(@NotNull GnoInterfaceType o) {
    visitType(o);
  }

  public void visitKey(@NotNull GnoKey o) {
    visitCompositeElement(o);
  }

  public void visitLabelDefinition(@NotNull GnoLabelDefinition o) {
    visitNamedElement(o);
  }

  public void visitLabelRef(@NotNull GnoLabelRef o) {
    visitCompositeElement(o);
  }

  public void visitLabeledStatement(@NotNull GnoLabeledStatement o) {
    visitStatement(o);
  }

  public void visitLeftHandExprList(@NotNull GnoLeftHandExprList o) {
    visitCompositeElement(o);
  }

  public void visitLiteral(@NotNull GnoLiteral o) {
    visitExpression(o);
  }

  public void visitLiteralTypeExpr(@NotNull GnoLiteralTypeExpr o) {
    visitExpression(o);
  }

  public void visitLiteralValue(@NotNull GnoLiteralValue o) {
    visitCompositeElement(o);
  }

  public void visitMapType(@NotNull GnoMapType o) {
    visitType(o);
  }

  public void visitMethodDeclaration(@NotNull GnoMethodDeclaration o) {
    visitFunctionOrMethodDeclaration(o);
  }

  public void visitMethodSpec(@NotNull GnoMethodSpec o) {
    visitNamedSignatureOwner(o);
  }

  public void visitMulExpr(@NotNull GnoMulExpr o) {
    visitBinaryExpr(o);
  }

  public void visitOrExpr(@NotNull GnoOrExpr o) {
    visitBinaryExpr(o);
  }

  public void visitPackageClause(@NotNull GnoPackageClause o) {
    visitCompositeElement(o);
  }

  public void visitParType(@NotNull GnoParType o) {
    visitType(o);
  }

  public void visitParamDefinition(@NotNull GnoParamDefinition o) {
    visitNamedElement(o);
  }

  public void visitParameterDeclaration(@NotNull GnoParameterDeclaration o) {
    visitCompositeElement(o);
  }

  public void visitParameters(@NotNull GnoParameters o) {
    visitCompositeElement(o);
  }

  public void visitParenthesesExpr(@NotNull GnoParenthesesExpr o) {
    visitExpression(o);
  }

  public void visitPointerType(@NotNull GnoPointerType o) {
    visitType(o);
  }

  public void visitRangeClause(@NotNull GnoRangeClause o) {
    visitVarSpec(o);
  }

  public void visitReceiver(@NotNull GnoReceiver o) {
    visitNamedElement(o);
  }

  public void visitRecvStatement(@NotNull GnoRecvStatement o) {
    visitVarSpec(o);
  }

  public void visitReferenceExpression(@NotNull GnoReferenceExpression o) {
    visitExpression(o);
    // visitReferenceExpressionBase(o);
  }

  public void visitResult(@NotNull GnoResult o) {
    visitCompositeElement(o);
  }

  public void visitReturnStatement(@NotNull GnoReturnStatement o) {
    visitStatement(o);
  }

  public void visitSelectStatement(@NotNull GnoSelectStatement o) {
    visitStatement(o);
  }

  public void visitSelectorExpr(@NotNull GnoSelectorExpr o) {
    visitBinaryExpr(o);
  }

  public void visitSendStatement(@NotNull GnoSendStatement o) {
    visitStatement(o);
  }

  public void visitShortVarDeclaration(@NotNull GnoShortVarDeclaration o) {
    visitVarSpec(o);
  }

  public void visitSignature(@NotNull GnoSignature o) {
    visitCompositeElement(o);
  }

  public void visitSimpleStatement(@NotNull GnoSimpleStatement o) {
    visitStatement(o);
  }

  public void visitSpecType(@NotNull GnoSpecType o) {
    visitType(o);
  }

  public void visitStatement(@NotNull GnoStatement o) {
    visitCompositeElement(o);
  }

  public void visitStringLiteral(@NotNull GnoStringLiteral o) {
    visitExpression(o);
    // visitPsiLanguageInjectionHost(o);
  }

  public void visitStructType(@NotNull GnoStructType o) {
    visitType(o);
  }

  public void visitSwitchStart(@NotNull GnoSwitchStart o) {
    visitCompositeElement(o);
  }

  public void visitSwitchStatement(@NotNull GnoSwitchStatement o) {
    visitStatement(o);
  }

  public void visitTag(@NotNull GnoTag o) {
    visitCompositeElement(o);
  }

  public void visitType(@NotNull GnoType o) {
    visitCompositeElement(o);
  }

  public void visitTypeAssertionExpr(@NotNull GnoTypeAssertionExpr o) {
    visitExpression(o);
  }

  public void visitTypeCaseClause(@NotNull GnoTypeCaseClause o) {
    visitCaseClause(o);
  }

  public void visitTypeDeclaration(@NotNull GnoTypeDeclaration o) {
    visitTopLevelDeclaration(o);
  }

  public void visitTypeGuard(@NotNull GnoTypeGuard o) {
    visitCompositeElement(o);
  }

  public void visitTypeList(@NotNull GnoTypeList o) {
    visitType(o);
  }

  public void visitTypeReferenceExpression(@NotNull GnoTypeReferenceExpression o) {
    visitReferenceExpressionBase(o);
  }

  public void visitTypeSpec(@NotNull GnoTypeSpec o) {
    visitNamedElement(o);
  }

  public void visitTypeSwitchGuard(@NotNull GnoTypeSwitchGuard o) {
    visitCompositeElement(o);
  }

  public void visitTypeSwitchStatement(@NotNull GnoTypeSwitchStatement o) {
    visitSwitchStatement(o);
  }

  public void visitUnaryExpr(@NotNull GnoUnaryExpr o) {
    visitExpression(o);
  }

  public void visitValue(@NotNull GnoValue o) {
    visitCompositeElement(o);
  }

  public void visitVarDeclaration(@NotNull GnoVarDeclaration o) {
    visitTopLevelDeclaration(o);
  }

  public void visitVarDefinition(@NotNull GnoVarDefinition o) {
    visitNamedElement(o);
  }

  public void visitVarSpec(@NotNull GnoVarSpec o) {
    visitCompositeElement(o);
  }

  public void visitAssignOp(@NotNull GnoAssignOp o) {
    visitCompositeElement(o);
  }

  public void visitCaseClause(@NotNull GnoCaseClause o) {
    visitCompositeElement(o);
  }

  public void visitFunctionOrMethodDeclaration(@NotNull GnoFunctionOrMethodDeclaration o) {
    visitCompositeElement(o);
  }

  public void visitNamedElement(@NotNull GnoNamedElement o) {
    visitCompositeElement(o);
  }

  public void visitNamedSignatureOwner(@NotNull GnoNamedSignatureOwner o) {
    visitCompositeElement(o);
  }

  public void visitReferenceExpressionBase(@NotNull GnoReferenceExpressionBase o) {
    visitCompositeElement(o);
  }

  public void visitTopLevelDeclaration(@NotNull GnoTopLevelDeclaration o) {
    visitCompositeElement(o);
  }

  public void visitTypeOwner(@NotNull GnoTypeOwner o) {
    visitCompositeElement(o);
  }

  public void visitCompositeElement(@NotNull GnoCompositeElement o) {
    visitElement(o);
  }

}
