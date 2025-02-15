package com.github.intellij.gno.psi.impl;

import com.github.intellij.gno.language.GnoLanguage;
import com.github.intellij.gno.psi.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.PsiParserFacade;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("ConstantConditions")
public class GnoElementFactory {
  private GnoElementFactory() {
  }

  @NotNull
  private static GnoFile createFileFromText(@NotNull Project project, @NotNull String text) {
    return (GnoFile)PsiFileFactory.getInstance(project).createFileFromText("a.go", GnoLanguage.INSTANCE, text);
  }

  @NotNull
  public static PsiElement createReturnStatement(@NotNull Project project) {
    GnoFile file = createFileFromText(project, "package main\nfunc _() { return; }");
    return PsiTreeUtil.findChildOfType(file, GnoReturnStatement.class);
  }

  @NotNull
  public static PsiElement createSelectStatement(@NotNull Project project) {
    GnoFile file = createFileFromText(project, "package main\nfunc _() { select {\n} }");
    return PsiTreeUtil.findChildOfType(file, GnoSelectStatement.class);
  }

  @NotNull
  public static PsiElement createIdentifierFromText(@NotNull Project project, String text) {
    GnoFile file = createFileFromText(project, "package " + text);
    return file.getPackage().getIdentifier();
  }

  @NotNull
  public static GnoIfStatement createIfStatement(@NotNull Project project,
                                                @NotNull String condition,
                                                @NotNull String thenBranch,
                                                @Nullable String elseBranch) {
    String elseText = elseBranch != null ? " else {\n" + elseBranch + "\n}" : "";
    GnoFile file = createFileFromText(project, "package a; func _() {\n" +
            "if " + condition + " {\n" +
            thenBranch + "\n" +
            "}" + elseText + "\n" +
            "}");
    return PsiTreeUtil.findChildOfType(file, GnoIfStatement.class);
  }

  @NotNull
  public static GnoImportDeclaration createEmptyImportDeclaration(@NotNull Project project) {
    return PsiTreeUtil.findChildOfType(createFileFromText(project, "package main\nimport (\n\n)"), GnoImportDeclaration.class);
  }

  @NotNull
  public static GnoImportDeclaration createImportDeclaration(@NotNull Project project, @NotNull String importString,
                                                            @Nullable String alias, boolean withParens) {
    importString = GnoPsiImplUtil.isQuotedImportString(importString) ? importString : StringUtil.wrapWithDoubleQuote(importString);
    alias = alias != null ? alias + " " : "";
    GnoFile file = createFileFromText(project, withParens
            ? "package main\nimport (\n" + alias + importString + "\n)"
            : "package main\nimport " + alias + importString);
    return PsiTreeUtil.findChildOfType(file, GnoImportDeclaration.class);
  }

  @NotNull
  public static GnoImportSpec createImportSpec(@NotNull Project project, @NotNull String importString, @Nullable String alias) {
    GnoImportDeclaration importDeclaration = createImportDeclaration(project, importString, alias, true);
    return ContainerUtil.getFirstItem(importDeclaration.getImportSpecList());
  }

  @NotNull
  public static GnoImportString createImportString(@NotNull Project project, @NotNull String importString) {
    GnoImportSpec importSpec = createImportSpec(project, importString, null);
    return importSpec.getImportString();
  }

  @NotNull
  public static PsiElement createNewLine(@NotNull Project project) {
    return PsiParserFacade.getInstance(project).createWhiteSpaceFromText("\n");
  }

  @NotNull
  public static PsiElement createComma(@NotNull Project project) {
    return createFileFromText(project, "package foo; var a,b = 1,2").getVars().get(0).getNextSibling();
  }

  @NotNull
  public static GnoPackageClause createPackageClause(@NotNull Project project, @NotNull String name) {
    return createFileFromText(project, "package " + name).getPackage();
  }

  @NotNull
  public static GnoBlock createBlock(@NotNull Project project) {
    GnoFunctionDeclaration function = ContainerUtil.getFirstItem(createFileFromText(project, "package a; func t() {\n}").getFunctions());
    assert function != null : "Impossible situation! Parser is broken.";
    return function.getBlock();
  }

  @NotNull
  public static GnoStringLiteral createStringLiteral(@NotNull Project project, @NotNull String stringLiteral) {
    GnoFile f = createFileFromText(project, "package a; var b = " + stringLiteral);
    return PsiTreeUtil.getNextSiblingOfType(ContainerUtil.getFirstItem(f.getVars()), GnoStringLiteral.class);
  }

  @NotNull
  public static GnoSignature createFunctionSignatureFromText(@NotNull Project project, @NotNull String text) {
    GnoFile file = createFileFromText(project, "package a; func t(" + text + ") {\n}");
    return ContainerUtil.getFirstItem(file.getFunctions()).getSignature();
  }

  @NotNull
  public static GnoStatement createShortVarDeclarationStatement(@NotNull Project project,
                                                               @NotNull String name,
                                                               @NotNull GnoExpression initializer) {
    GnoFile file = createFileFromText(project, "package a; func a() {\n " + name + " := " + initializer.getText() + "}");
    return PsiTreeUtil.findChildOfType(file, GnoSimpleStatement.class);
  }

  @NotNull
  public static GnoStatement createShortVarDeclarationStatement(@NotNull Project project,
                                                               @NotNull String leftSide,
                                                               @NotNull String rightSide) {
    GnoFile file = createFileFromText(project, "package a; func a() {\n " + leftSide + " := " + rightSide + "}");
    return PsiTreeUtil.findChildOfType(file, GnoSimpleStatement.class);
  }

  @NotNull
  public static GnoRangeClause createRangeClause(@NotNull Project project, @NotNull String leftSide, @NotNull String rightSide) {
    GnoFile file = createFileFromText(project, "package a; func a() {\n for " + leftSide + " := range " + rightSide + "{\n}\n}");
    return PsiTreeUtil.findChildOfType(file, GnoRangeClause.class);
  }

  @NotNull
  public static GnoRangeClause createRangeClauseAssignment(@NotNull Project project, @NotNull String leftSide, @NotNull String rightSide) {
    GnoFile file = createFileFromText(project, "package a; func a() {\n for " + leftSide + " = range " + rightSide + "{\n}\n}");
    return PsiTreeUtil.findChildOfType(file, GnoRangeClause.class);
  }

  @NotNull
  public static GnoRecvStatement createRecvStatement(@NotNull Project project, @NotNull String leftSide, @NotNull String rightSide) {
    GnoFile file = createFileFromText(project, "package a; func a() {\n select { case " + leftSide + " := " + rightSide + ":\n}\n}");
    return PsiTreeUtil.findChildOfType(file, GnoRecvStatement.class);
  }

  @NotNull
  public static GnoRecvStatement createRecvStatementAssignment(@NotNull Project project, @NotNull String left, @NotNull String right) {
    GnoFile file = createFileFromText(project, "package a; func a() {\n select { case " + left + " = " + right + ":\n}\n}");
    return PsiTreeUtil.findChildOfType(file, GnoRecvStatement.class);
  }

  public static GnoAssignmentStatement createAssignmentStatement(@NotNull Project project, @NotNull String left, @NotNull String right) {
    GnoFile file = createFileFromText(project, "package a; func a() {\n " + left + " = " + right + "}");
    return PsiTreeUtil.findChildOfType(file, GnoAssignmentStatement.class);
  }

  @NotNull
  public static GnoDeferStatement createDeferStatement(@NotNull Project project, @NotNull String expressionText) {
    GnoFile file = createFileFromText(project, "package a; func a() {\n  defer " + expressionText + "}");
    return PsiTreeUtil.findChildOfType(file, GnoDeferStatement.class);
  }

  @NotNull
  public static GnoGoStatement createGnoStatement(@NotNull Project project, @NotNull String expressionText) {
    GnoFile file = createFileFromText(project, "package a; func a() {\n  go " + expressionText + "}");
    return PsiTreeUtil.findChildOfType(file, GnoGoStatement.class);
  }

  @NotNull
  public static GnoForStatement createForStatement(@NotNull Project project, @NotNull String text) {
    GnoFile file = createFileFromText(project, "package a; func a() {\n for {\n" + text +  "\n}\n}");
    return PsiTreeUtil.findChildOfType(file, GnoForStatement.class);
  }

  @NotNull
  public static GnoExpression createExpression(@NotNull Project project, @NotNull String text) {
    GnoFile file = createFileFromText(project, "package a; func a() {\n a := " + text + "}");
    return PsiTreeUtil.findChildOfType(file, GnoExpression.class);
  }

  @NotNull
  public static GnoReferenceExpression createReferenceExpression(@NotNull Project project, @NotNull String name) {
    GnoFile file = createFileFromText(project, "package a; var a = " + name);
    return PsiTreeUtil.findChildOfType(file, GnoReferenceExpression.class);
  }

  @NotNull
  public static GnoSimpleStatement createComparison(@NotNull Project project, @NotNull String text) {
    GnoFile file = createFileFromText(project, "package a; func a() {\n " + text + "}");
    return PsiTreeUtil.findChildOfType(file, GnoSimpleStatement.class);
  }

  @NotNull
  public static GnoConstDeclaration createConstDeclaration(@NotNull Project project, @NotNull String text) {
    GnoFile file = createFileFromText(project, "package a; const " + text);
    return PsiTreeUtil.findChildOfType(file, GnoConstDeclaration.class);
  }

  @NotNull
  public static GnoConstSpec createConstSpec(@NotNull Project project, @NotNull String name, @Nullable String type, @Nullable String value) {
    GnoConstDeclaration constDeclaration = createConstDeclaration(project, prepareVarOrConstDeclarationText(name, type, value));
    return ContainerUtil.getFirstItem(constDeclaration.getConstSpecList());
  }

  @NotNull
  public static GnoVarDeclaration createVarDeclaration(@NotNull Project project, @NotNull String text) {
    GnoFile file = createFileFromText(project, "package a; var " + text);
    return PsiTreeUtil.findChildOfType(file, GnoVarDeclaration.class);
  }

  @NotNull
  public static GnoVarSpec createVarSpec(@NotNull Project project, @NotNull String name, @Nullable String type, @Nullable String value) {
    GnoVarDeclaration varDeclaration = createVarDeclaration(project, prepareVarOrConstDeclarationText(name, type, value));
    return ContainerUtil.getFirstItem(varDeclaration.getVarSpecList());
  }

  @NotNull
  private static String prepareVarOrConstDeclarationText(@NotNull String name, @Nullable String type, @Nullable String value) {
    type = StringUtil.trim(type);
    value = StringUtil.trim(value);
    type = StringUtil.isNotEmpty(type) ? " " + type : "";
    value = StringUtil.isNotEmpty(value) ? " = " + value : "";
    return name + type + value;
  }

  public static GnoTypeList createTypeList(@NotNull Project project, @NotNull String text) {
    GnoFile file = createFileFromText(project, "package a; func _() (" + text + "){}");
    return PsiTreeUtil.findChildOfType(file, GnoTypeList.class);
  }

  public static GnoType createType(@NotNull Project project, @NotNull String text) {
    GnoFile file = createFileFromText(project, "package a; var a " + text);
    return PsiTreeUtil.findChildOfType(file, GnoType.class);
  }

  public static PsiElement createLiteralValueElement(@NotNull Project project, @NotNull String key, @NotNull String value) {
    GnoFile file = createFileFromText(project, "package a; var _ = struct { a string } { " + key + ": " + value + " }");
    return PsiTreeUtil.findChildOfType(file, GnoElement.class);
  }

  @NotNull
  public static GnoTypeDeclaration createTypeDeclaration(@NotNull Project project, @NotNull String name, @NotNull GnoType type) {
    GnoFile file = createFileFromText(project, "package a; type " + name + " " + type.getText());
    return PsiTreeUtil.findChildOfType(file, GnoTypeDeclaration.class);
  }
}