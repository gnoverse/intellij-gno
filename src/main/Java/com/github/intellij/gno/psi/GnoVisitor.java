package com.github.intellij.gno.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;

public class GnoVisitor extends PsiElementVisitor {
    public GnoVisitor() {
    }

    public void visitAssignStatement(@NotNull GnoAssignStatement o) {

        this.visitStatement(o);
    }

    public void visitBlockStatement(@NotNull GnoBlockStatement o) {

        this.visitStatement(o);
    }

    public void visitCompositePipeline(@NotNull GnoCompositePipeline o) {
        this.visitPipeline(o);
    }

    public void visitDefineStatement(@NotNull GnoDefineStatement o) {
        this.visitStatement(o);
    }

    public void visitElseIfStatement(@NotNull GnoElseIfStatement o) {
        this.visitStatement(o);
    }

    public void visitElseStatement(@NotNull GnoElseStatement o) {
        this.visitStatement(o);
    }

    public void visitEmptyStatement(@NotNull GnoEmptyStatement o) {
        this.visitStatement(o);
    }

    public void visitEndStatement(@NotNull GnoEndStatement o) {
        this.visitStatement(o);
    }

    public void visitExpression(@NotNull GnoExpression o) {
        this.visitPsiElement(o);
    }

    public void visitFieldChainExpr(@NotNull GnoFieldChainExpr o) {
        this.visitExpression(o);
    }

    public void visitIfStatement(@NotNull GnoIfStatement o) {
        this.visitStatement(o);
    }

    public void visitLiteral(@NotNull GnoLiteral o) {
        this.visitPsiElement(o);
    }

    public void visitLiteralExpr(@NotNull GnoLiteralExpr o) {
        this.visitExpression(o);
    }

    public void visitParenthesesExpr(@NotNull GnoParenthesesExpr o) {
        this.visitExpression(o);
    }

    public void visitPipeline(@NotNull GnoPipeline o) {
        this.visitPsiElement(o);
    }

    public void visitPipelineStatement(@NotNull GnoPipelineStatement o) {
        this.visitStatement(o);
    }

    public void visitRangeStatement(@NotNull GnoRangeStatement o) {
        this.visitStatement(o);
    }

    public void visitRangeVarDeclaration(@NotNull GnoRangeVarDeclaration o) {
        this.visitVarDeclaration(o);
    }

    public void visitSimplePipeline(@NotNull GnoSimplePipeline o) {
        this.visitPipeline(o);
    }

    public void visitStatement(@NotNull GnoStatement o) {
        this.visitPsiElement(o);
    }

    public void visitStatementList(@NotNull GnoStatementList o) {
        this.visitPsiElement(o);
    }

    public void visitStringLiteral(@NotNull GnoStringLiteral o) {
        this.visitPsiElement(o);
    }

    public void visitTemplateStatement(@NotNull GnoTemplateStatement o) {
        this.visitStatement(o);
    }

    public void visitVarDeclaration(@NotNull GnoVarDeclaration o) {
        this.visitPsiElement(o);
    }

    public void visitVarDeclarationStatement(@NotNull GnoVarDeclarationStatement o) {
        this.visitStatement(o);
    }

    public void visitVarDefinition(@NotNull GnoVarDefinition o) {
        this.visitNamedElement(o);
    }

    public void visitVariableExpr(@NotNull GnoVariableExpr o) {
        this.visitExpression(o);
    }

    public void visitWithStatement(@NotNull GnoWithStatement o) {
        this.visitStatement(o);
    }

    public void visitNamedElement(@NotNull GnoNamedElement o) {
        this.visitPsiElement(o);
    }

    public void visitPsiElement(@NotNull PsiElement o) {
        this.visitElement(o);
    }
}
