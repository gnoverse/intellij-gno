package com.github.intellij.gno.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;

public class GnoVisitor extends PsiElementVisitor {
    public GnoVisitor() {
    }

    public void visitAssignStatement(@NotNull GnoAssignStatement o) {
        if (o == null) {
            $$$reportNull$$$0(0);
        }

        this.visitStatement(o);
    }

    public void visitBlockStatement(@NotNull GnoBlockStatement o) {
        if (o == null) {
            $$$reportNull$$$0(1);
        }

        this.visitStatement(o);
    }

    public void visitCompositePipeline(@NotNull GnoCompositePipeline o) {
        if (o == null) {
            $$$reportNull$$$0(2);
        }

        this.visitPipeline(o);
    }

    public void visitDefineStatement(@NotNull GnoDefineStatement o) {
        if (o == null) {
            $$$reportNull$$$0(3);
        }

        this.visitStatement(o);
    }

    public void visitElseIfStatement(@NotNull GnoElseIfStatement o) {
        if (o == null) {
            $$$reportNull$$$0(4);
        }

        this.visitStatement(o);
    }

    public void visitElseStatement(@NotNull GnoElseStatement o) {
        if (o == null) {
            $$$reportNull$$$0(5);
        }

        this.visitStatement(o);
    }

    public void visitEmptyStatement(@NotNull GnoEmptyStatement o) {
        if (o == null) {
            $$$reportNull$$$0(6);
        }

        this.visitStatement(o);
    }

    public void visitEndStatement(@NotNull GnoEndStatement o) {
        if (o == null) {
            $$$reportNull$$$0(7);
        }

        this.visitStatement(o);
    }

    public void visitExpression(@NotNull GnoExpression o) {
        if (o == null) {
            $$$reportNull$$$0(8);
        }

        this.visitPsiElement(o);
    }

    public void visitFieldChainExpr(@NotNull GnoFieldChainExpr o) {
        if (o == null) {
            $$$reportNull$$$0(9);
        }

        this.visitExpression(o);
    }

    public void visitIfStatement(@NotNull GnoIfStatement o) {
        if (o == null) {
            $$$reportNull$$$0(10);
        }

        this.visitStatement(o);
    }

    public void visitLiteral(@NotNull GnoLiteral o) {
        if (o == null) {
            $$$reportNull$$$0(11);
        }

        this.visitPsiElement(o);
    }

    public void visitLiteralExpr(@NotNull GnoLiteralExpr o) {
        if (o == null) {
            $$$reportNull$$$0(12);
        }

        this.visitExpression(o);
    }

    public void visitParenthesesExpr(@NotNull GnoParenthesesExpr o) {
        if (o == null) {
            $$$reportNull$$$0(13);
        }

        this.visitExpression(o);
    }

    public void visitPipeline(@NotNull GnoPipeline o) {
        if (o == null) {
            $$$reportNull$$$0(14);
        }

        this.visitPsiElement(o);
    }

    public void visitPipelineStatement(@NotNull GnoPipelineStatement o) {
        if (o == null) {
            $$$reportNull$$$0(15);
        }

        this.visitStatement(o);
    }

    public void visitRangeStatement(@NotNull GnoRangeStatement o) {
        if (o == null) {
            $$$reportNull$$$0(16);
        }

        this.visitStatement(o);
    }

    public void visitRangeVarDeclaration(@NotNull GnoRangeVarDeclaration o) {
        if (o == null) {
            $$$reportNull$$$0(17);
        }

        this.visitVarDeclaration(o);
    }

    public void visitSimplePipeline(@NotNull GnoSimplePipeline o) {
        if (o == null) {
            $$$reportNull$$$0(18);
        }

        this.visitPipeline(o);
    }

    public void visitStatement(@NotNull GnoStatement o) {
        if (o == null) {
            $$$reportNull$$$0(19);
        }

        this.visitPsiElement(o);
    }

    public void visitStatementList(@NotNull GnoStatementList o) {
        if (o == null) {
            $$$reportNull$$$0(20);
        }

        this.visitPsiElement(o);
    }

    public void visitStringLiteral(@NotNull GnoStringLiteral o) {
        if (o == null) {
            $$$reportNull$$$0(21);
        }

        this.visitPsiElement(o);
    }

    public void visitTemplateStatement(@NotNull GnoTemplateStatement o) {
        if (o == null) {
            $$$reportNull$$$0(22);
        }

        this.visitStatement(o);
    }

    public void visitVarDeclaration(@NotNull GnoVarDeclaration o) {
        if (o == null) {
            $$$reportNull$$$0(23);
        }

        this.visitPsiElement(o);
    }

    public void visitVarDeclarationStatement(@NotNull GnoVarDeclarationStatement o) {
        if (o == null) {
            $$$reportNull$$$0(24);
        }

        this.visitStatement(o);
    }

    public void visitVarDefinition(@NotNull GnoVarDefinition o) {
        if (o == null) {
            $$$reportNull$$$0(25);
        }

        this.visitNamedElement(o);
    }

    public void visitVariableExpr(@NotNull GnoVariableExpr o) {
        if (o == null) {
            $$$reportNull$$$0(26);
        }

        this.visitExpression(o);
    }

    public void visitWithStatement(@NotNull GnoWithStatement o) {
        if (o == null) {
            $$$reportNull$$$0(27);
        }

        this.visitStatement(o);
    }

    public void visitNamedElement(@NotNull GnoNamedElement o) {
        if (o == null) {
            $$$reportNull$$$0(28);
        }

        this.visitPsiElement(o);
    }

    public void visitPsiElement(@NotNull PsiElement o) {
        if (o == null) {
            $$$reportNull$$$0(29);
        }

        this.visitElement(o);
    }
}
