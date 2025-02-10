package com.github.intellij.gno.psi;

import com.github.intellij.gno.psi.impl.GnoAssignStatementImpl;
import com.github.intellij.gno.psi.impl.GnoBlockStatementImpl;
import com.github.intellij.gno.psi.impl.GnoCompositePipelineImpl;
import com.github.intellij.gno.psi.impl.GnoDefineStatementImpl;
import com.github.intellij.gno.psi.impl.GnoElseIfStatementImpl;
import com.github.intellij.gno.psi.impl.GnoElseStatementImpl;
import com.github.intellij.gno.psi.impl.GnoEmptyStatementImpl;
import com.github.intellij.gno.psi.impl.GnoEndStatementImpl;
import com.github.intellij.gno.psi.impl.GnoFieldChainExprImpl;
import com.github.intellij.gno.psi.impl.GnoIfStatementImpl;
import com.github.intellij.gno.psi.impl.GnoLiteralExprImpl;
import com.github.intellij.gno.psi.impl.GnoLiteralImpl;
import com.github.intellij.gno.psi.impl.GnoParenthesesExprImpl;
import com.github.intellij.gno.psi.impl.GnoPipelineStatementImpl;
import com.github.intellij.gno.psi.impl.GnoRangeStatementImpl;
import com.github.intellij.gno.psi.impl.GnoRangeVarDeclarationImpl;
import com.github.intellij.gno.psi.impl.GnoSimplePipelineImpl;
import com.github.intellij.gno.psi.impl.GnoStatementListImpl;
import com.github.intellij.gno.psi.impl.GnoStringLiteralImpl;
import com.github.intellij.gno.psi.impl.GnoTemplateStatementImpl;
import com.github.intellij.gno.psi.impl.GnoVarDeclarationImpl;
import com.github.intellij.gno.psi.impl.GnoVarDeclarationStatementImpl;
import com.github.intellij.gno.psi.impl.GnoVarDefinitionImpl;
import com.github.intellij.gno.psi.impl.GnoVariableExprImpl;
import com.github.intellij.gno.psi.impl.GnoWithStatementImpl;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;

public interface GnoTypes {
    IElementType ASSIGN_STATEMENT = new GnoCompositeElementType("ASSIGN_STATEMENT");
    IElementType BLOCK_STATEMENT = new GnoCompositeElementType("BLOCK_STATEMENT");
    IElementType COMPOSITE_PIPELINE = new GnoCompositeElementType("COMPOSITE_PIPELINE");
    IElementType DEFINE_STATEMENT = new GnoCompositeElementType("DEFINE_STATEMENT");
    IElementType ELSE_IF_STATEMENT = new GnoCompositeElementType("ELSE_IF_STATEMENT");
    IElementType ELSE_STATEMENT = new GnoCompositeElementType("ELSE_STATEMENT");
    IElementType EMPTY_STATEMENT = new GnoCompositeElementType("EMPTY_STATEMENT");
    IElementType END_STATEMENT = new GnoCompositeElementType("END_STATEMENT");
    IElementType EXPRESSION = new GnoCompositeElementType("EXPRESSION");
    IElementType FIELD_CHAIN_EXPR = new GnoCompositeElementType("FIELD_CHAIN_EXPR");
    IElementType IF_STATEMENT = new GnoCompositeElementType("IF_STATEMENT");
    IElementType LITERAL = new GnoCompositeElementType("LITERAL");
    IElementType LITERAL_EXPR = new GnoCompositeElementType("LITERAL_EXPR");
    IElementType PARENTHESES_EXPR = new GnoCompositeElementType("PARENTHESES_EXPR");
    IElementType PIPELINE = new GnoCompositeElementType("PIPELINE");
    IElementType PIPELINE_STATEMENT = new GnoCompositeElementType("PIPELINE_STATEMENT");
    IElementType RANGE_STATEMENT = new GnoCompositeElementType("RANGE_STATEMENT");
    IElementType RANGE_VAR_DECLARATION = new GnoCompositeElementType("RANGE_VAR_DECLARATION");
    IElementType SIMPLE_PIPELINE = new GnoCompositeElementType("SIMPLE_PIPELINE");
    IElementType STATEMENT_LIST = new GnoCompositeElementType("STATEMENT_LIST");
    IElementType STRING_LITERAL = new GnoCompositeElementType("STRING_LITERAL");
    IElementType TEMPLATE_STATEMENT = new GnoCompositeElementType("TEMPLATE_STATEMENT");
    IElementType VARIABLE_EXPR = new GnoCompositeElementType("VARIABLE_EXPR");
    IElementType VAR_DECLARATION = new GnoCompositeElementType("VAR_DECLARATION");
    IElementType VAR_DECLARATION_STATEMENT = new GnoCompositeElementType("VAR_DECLARATION_STATEMENT");
    IElementType VAR_DEFINITION = new GnoCompositeElementType("VAR_DEFINITION");
    IElementType WITH_STATEMENT = new GnoCompositeElementType("WITH_STATEMENT");
    IElementType ASSIGN = GnoImplUtil.createIdentifierTokenType("=");
    IElementType AT = GnoImplUtil.createIdentifierTokenType("@");
    IElementType BIT_OR = GnoImplUtil.createIdentifierTokenType("|");
    IElementType BLOCK = GnoImplUtil.createIdentifierTokenType("block");
    IElementType CHAR = GnoImplUtil.createIdentifierTokenType("CHAR");
    IElementType COMMA = GnoImplUtil.createIdentifierTokenType(",");
    IElementType DEFINE = GnoImplUtil.createIdentifierTokenType("define");
    IElementType DOT = GnoImplUtil.createIdentifierTokenType(".");
    IElementType ELSE = GnoImplUtil.createIdentifierTokenType("else");
    IElementType END = GnoImplUtil.createIdentifierTokenType("end");
    IElementType FALSE = GnoImplUtil.createIdentifierTokenType("FALSE");
    IElementType HASH = GnoImplUtil.createIdentifierTokenType("#");
    IElementType IDENTIFIER = GnoImplUtil.createIdentifierTokenType("IDENTIFIER");
    IElementType IF = GnoImplUtil.createIdentifierTokenType("if");
    IElementType LDOUBLE_BRACE = GnoImplUtil.createIdentifierTokenType("{{");
    IElementType LPAREN = GnoImplUtil.createIdentifierTokenType("(");
    IElementType NIL = GnoImplUtil.createIdentifierTokenType("NIL");
    IElementType NUMBER = GnoImplUtil.createIdentifierTokenType("NUMBER");
    IElementType PERCENT = GnoImplUtil.createIdentifierTokenType("%");
    IElementType RANGE = GnoImplUtil.createIdentifierTokenType("range");
    IElementType RAW_STRING = GnoImplUtil.createIdentifierTokenType("RAW_STRING");
    IElementType RDOUBLE_BRACE = GnoImplUtil.createIdentifierTokenType("}}");
    IElementType RPAREN = GnoImplUtil.createIdentifierTokenType(")");
    IElementType STRING = GnoImplUtil.createIdentifierTokenType("STRING");
    IElementType TEMPLATE = GnoImplUtil.createIdentifierTokenType("template");
    IElementType TEXT = GnoImplUtil.createIdentifierTokenType("TEXT");
    IElementType TRUE = GnoImplUtil.createIdentifierTokenType("TRUE");
    IElementType VARIABLE = GnoImplUtil.createIdentifierTokenType("VARIABLE");
    IElementType VAR_ASSIGN = GnoImplUtil.createIdentifierTokenType(":=");
    IElementType WITH = GnoImplUtil.createIdentifierTokenType("with");

    public static class Factory {
        public Factory() {
        }

        public static PsiElement createElement(ASTNode node) {
            IElementType type = node.getElementType();
            if (type == GnoTypes.ASSIGN_STATEMENT) {
                return new GnoAssignStatementImpl(node);
            } else if (type == GnoTypes.BLOCK_STATEMENT) {
                return new GnoBlockStatementImpl(node);
            } else if (type == GnoTypes.COMPOSITE_PIPELINE) {
                return new GnoCompositePipelineImpl(node);
            } else if (type == GnoTypes.DEFINE_STATEMENT) {
                return new GnoDefineStatementImpl(node);
            } else if (type == GnoTypes.ELSE_IF_STATEMENT) {
                return new GnoElseIfStatementImpl(node);
            } else if (type == GnoTypes.ELSE_STATEMENT) {
                return new GnoElseStatementImpl(node);
            } else if (type == GnoTypes.EMPTY_STATEMENT) {
                return new GnoEmptyStatementImpl(node);
            } else if (type == GnoTypes.END_STATEMENT) {
                return new GnoEndStatementImpl(node);
            } else if (type == GnoTypes.FIELD_CHAIN_EXPR) {
                return new GnoFieldChainExprImpl(node);
            } else if (type == GnoTypes.IF_STATEMENT) {
                return new GnoIfStatementImpl(node);
            } else if (type == GnoTypes.LITERAL) {
                return new GnoLiteralImpl(node);
            } else if (type == GnoTypes.LITERAL_EXPR) {
                return new GnoLiteralExprImpl(node);
            } else if (type == GnoTypes.PARENTHESES_EXPR) {
                return new GnoParenthesesExprImpl(node);
            } else if (type == GnoTypes.PIPELINE_STATEMENT) {
                return new GnoPipelineStatementImpl(node);
            } else if (type == GnoTypes.RANGE_STATEMENT) {
                return new GnoRangeStatementImpl(node);
            } else if (type == GnoTypes.RANGE_VAR_DECLARATION) {
                return new GnoRangeVarDeclarationImpl(node);
            } else if (type == GnoTypes.SIMPLE_PIPELINE) {
                return new GnoSimplePipelineImpl(node);
            } else if (type == GnoTypes.STATEMENT_LIST) {
                return new GnoStatementListImpl(node);
            } else if (type == GnoTypes.STRING_LITERAL) {
                return new GnoStringLiteralImpl(node);
            } else if (type == GnoTypes.TEMPLATE_STATEMENT) {
                return new GnoTemplateStatementImpl(node);
            } else if (type == GnoTypes.VARIABLE_EXPR) {
                return new GnoVariableExprImpl(node);
            } else if (type == GnoTypes.VAR_DECLARATION) {
                return new GnoVarDeclarationImpl(node);
            } else if (type == GnoTypes.VAR_DECLARATION_STATEMENT) {
                return new GnoVarDeclarationStatementImpl(node);
            } else if (type == GnoTypes.VAR_DEFINITION) {
                return new GnoVarDefinitionImpl(node);
            } else if (type == GnoTypes.WITH_STATEMENT) {
                return new GnoWithStatementImpl(node);
            } else {
                throw new AssertionError("Unknown element type: " + String.valueOf(type));
            }
        }
    }
}
