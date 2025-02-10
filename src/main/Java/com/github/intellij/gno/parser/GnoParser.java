package com.github.intellij.gno.parser;

import com.github.intellij.gno.psi.GnoTypes;
import com.intellij.lang.ASTNode;
import com.intellij.lang.LightPsiParser;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.lang.parser.GeneratedParserUtilBase;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;

public class GnoParser implements PsiParser, LightPsiParser {
    public static final TokenSet[] EXTENDS_SETS_;

    public GnoParser() {
    }

    public ASTNode parse(IElementType t, PsiBuilder b) {
        this.parseLight(t, b);
        return b.getTreeBuilt();
    }

    public void parseLight(IElementType t, PsiBuilder b) {
        b = GnoParserUtil.adapt_builder_(t, b, this, EXTENDS_SETS_);
        PsiBuilder.Marker m = GnoParserUtil.enter_section_(b, 0, 1, (String)null);
        boolean r = this.parse_root_(t, b);
        GnoParserUtil.exit_section_(b, 0, m, t, r, true, GnoParserUtil.TRUE_CONDITION);
    }

    protected boolean parse_root_(IElementType t, PsiBuilder b) {
        return parse_root_(t, b, 0);
    }

    static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
        return File(b, l + 1);
    }

    public static boolean AssignStatement(PsiBuilder b, int l) {
        if (!GnoParserUtil.recursion_guard_(b, l, "AssignStatement")) {
            return false;
        } else if (!GnoParserUtil.nextTokenIs(b, GnoTypes.LDOUBLE_BRACE)) {
            return false;
        } else {
            PsiBuilder.Marker m = GnoParserUtil.enter_section_(b, l, 0, GnoTypes.ASSIGN_STATEMENT, (String)null);
            boolean r = GnoParserUtil.consumeToken(b, GnoTypes.LDOUBLE_BRACE);
            r = r && VariableExpr(b, l + 1);
            r = r && GnoParserUtil.consumeToken(b, GnoTypes.ASSIGN);
            boolean p = r;
            r = r && GnoParserUtil.report_error_(b, Pipeline(b, l + 1));
            r = p && GnoParserUtil.consumeToken(b, GnoTypes.RDOUBLE_BRACE) && r;
            GnoParserUtil.exit_section_(b, l, m, r, p, null);
            return r || p;
        }
    }

    static boolean BadStatement(PsiBuilder b, int l) {
        if (!GnoParserUtil.recursion_guard_(b, l, "BadStatement")) {
            return false;
        } else if (!GnoParserUtil.nextTokenIs(b, GnoTypes.LDOUBLE_BRACE)) {
            return false;
        } else {
            PsiBuilder.Marker m = GnoParserUtil.enter_section_(b, l, 0);
            boolean r = GnoParserUtil.consumeToken(b, GnoTypes.LDOUBLE_BRACE);
            r = r && GnoParserUtil.badStatementStart(b, l + 1);
            boolean p = r;
            r = r && BadTail(b, l + 1);
            GnoParserUtil.exit_section_(b, l, m, r, p, null);
            return r || p;
        }
    }

    static boolean BadTail(PsiBuilder b, int l) {
        if (!GnoParserUtil.recursion_guard_(b, l, "BadTail")) {
            return false;
        } else {
            PsiBuilder.Marker m = GnoParserUtil.enter_section_(b, l, 0);
            boolean r = GnoParserUtil.unexpectedToken(b, l + 1);
            GnoParserUtil.exit_section_(b, l, m, r, false, GnoParser::Recover);
            return r;
        }
    }

    public static boolean BlockStatement(PsiBuilder b, int l) {
        if (!GnoParserUtil.recursion_guard_(b, l, "BlockStatement")) {
            return false;
        } else if (!GnoParserUtil.nextTokenIs(b, GnoTypes.LDOUBLE_BRACE)) {
            return false;
        } else {
            PsiBuilder.Marker m = GnoParserUtil.enter_section_(b, l, 0, GnoTypes.BLOCK_STATEMENT, (String)null);
            boolean r = GnoParserUtil.consumeTokens(b, 2, GnoTypes.LDOUBLE_BRACE, GnoTypes.BLOCK);
            boolean p = r;
            r = r && GnoParserUtil.report_error_(b, StringLiteral(b, l + 1));
            r = p && GnoParserUtil.report_error_(b, PipelineOrDeclaration(b, l + 1)) && r;
            r = p && GnoParserUtil.report_error_(b, GnoParserUtil.consumeToken(b, GnoTypes.RDOUBLE_BRACE)) && r;
            r = p && GnoParserUtil.report_error_(b, GnoParserUtil.pushEndIsValid(b, l + 1)) && r;
            r = p && GnoParserUtil.report_error_(b, StatementList(b, l + 1)) && r;
            r = p && GnoParserUtil.report_error_(b, GnoParserUtil.pop(b, l + 1)) && r;
            r = p && EndStatement(b, l + 1) && r;
            GnoParserUtil.exit_section_(b, l, m, r, p, (GeneratedParserUtilBase.Parser)null);
            return r || p;
        }
    }

    public static boolean CompositePipeline(PsiBuilder b, int l) {
        if (!GnoParserUtil.recursion_guard_(b, l, "CompositePipeline")) {
            return false;
        } else if (!GnoParserUtil.nextTokenIs(b, GnoTypes.BIT_OR)) {
            return false;
        } else {
            PsiBuilder.Marker m = GnoParserUtil.enter_section_(b, l, 2, GnoTypes.COMPOSITE_PIPELINE, (String)null);
            boolean r = GnoParserUtil.consumeToken(b, GnoTypes.BIT_OR);
            boolean p = r;
            r = r && CompositePipeline_1(b, l + 1);
            GnoParserUtil.exit_section_(b, l, m, r, p, (GeneratedParserUtilBase.Parser)null);
            return r || p;
        }
    }

    private static boolean CompositePipeline_1(PsiBuilder b, int l) {
        if (!GnoParserUtil.recursion_guard_(b, l, "CompositePipeline_1")) {
            return false;
        } else {
            PsiBuilder.Marker m = GnoParserUtil.enter_section_(b);
            boolean r = Expression(b, l + 1);

            while(r) {
                int c = GnoParserUtil.current_position_(b);
                if (!Expression(b, l + 1) || !GnoParserUtil.empty_element_parsed_guard_(b, "CompositePipeline_1", c)) {
                    break;
                }
            }

            GnoParserUtil.exit_section_(b, m, (IElementType)null, r);
            return r;
        }
    }

    public static boolean DefineStatement(PsiBuilder b, int l) {
        if (!GnoParserUtil.recursion_guard_(b, l, "DefineStatement")) {
            return false;
        } else if (!GnoParserUtil.nextTokenIs(b, GnoTypes.LDOUBLE_BRACE)) {
            return false;
        } else {
            PsiBuilder.Marker m = GnoParserUtil.enter_section_(b, l, 0, GnoTypes.DEFINE_STATEMENT, (String)null);
            boolean r = GnoParserUtil.consumeTokens(b, 2, new IElementType[]{GnoTypes.LDOUBLE_BRACE, GnoTypes.DEFINE});
            boolean p = r;
            r = r && GnoParserUtil.report_error_(b, StringLiteral(b, l + 1));
            r = p && GnoParserUtil.report_error_(b, GnoParserUtil.consumeToken(b, GnoTypes.RDOUBLE_BRACE)) && r;
            r = p && GnoParserUtil.report_error_(b, GnoParserUtil.pushEndIsValid(b, l + 1)) && r;
            r = p && GnoParserUtil.report_error_(b, StatementList(b, l + 1)) && r;
            r = p && GnoParserUtil.report_error_(b, GnoParserUtil.pop(b, l + 1)) && r;
            r = p && EndStatement(b, l + 1) && r;
            GnoParserUtil.exit_section_(b, l, m, r, p, (GeneratedParserUtilBase.Parser)null);
            return r || p;
        }
    }

    static boolean DotName(PsiBuilder b, int l) {
        if (!GnoParserUtil.recursion_guard_(b, l, "DotName")) {
            return false;
        } else if (!GnoParserUtil.nextTokenIs(b, GnoTypes.DOT)) {
            return false;
        } else {
            PsiBuilder.Marker m = GnoParserUtil.enter_section_(b);
            boolean r = GnoParserUtil.consumeTokens(b, 0, new IElementType[]{GnoTypes.DOT, GnoTypes.IDENTIFIER});
            GnoParserUtil.exit_section_(b, m, (IElementType)null, r);
            return r;
        }
    }

    public static boolean ElseIfStatement(PsiBuilder b, int l) {
        if (!GnoParserUtil.recursion_guard_(b, l, "ElseIfStatement")) {
            return false;
        } else if (!GnoParserUtil.nextTokenIs(b, GnoTypes.LDOUBLE_BRACE)) {
            return false;
        } else {
            PsiBuilder.Marker m = GnoParserUtil.enter_section_(b, l, 0, GnoTypes.ELSE_IF_STATEMENT, (String)null);
            boolean r = GnoParserUtil.consumeTokens(b, 3, new IElementType[]{GnoTypes.LDOUBLE_BRACE, GnoTypes.ELSE, GnoTypes.IF});
            boolean p = r;
            r = r && GnoParserUtil.report_error_(b, PipelineOrDeclaration(b, l + 1));
            r = p && GnoParserUtil.report_error_(b, GnoParserUtil.consumeToken(b, GnoTypes.RDOUBLE_BRACE)) && r;
            r = p && GnoParserUtil.report_error_(b, GnoParserUtil.pushEndElseIsValid(b, l + 1)) && r;
            r = p && GnoParserUtil.report_error_(b, StatementList(b, l + 1)) && r;
            r = p && GnoParserUtil.report_error_(b, GnoParserUtil.pop(b, l + 1)) && r;
            r = p && EndElseIfElse(b, l + 1) && r;
            GnoParserUtil.exit_section_(b, l, m, r, p, (GeneratedParserUtilBase.Parser)null);
            return r || p;
        }
    }

    public static boolean ElseStatement(PsiBuilder b, int l) {
        if (!GnoParserUtil.recursion_guard_(b, l, "ElseStatement")) {
            return false;
        } else if (!GnoParserUtil.nextTokenIs(b, GnoTypes.LDOUBLE_BRACE)) {
            return false;
        } else {
            PsiBuilder.Marker m = GnoParserUtil.enter_section_(b, l, 0, GnoTypes.ELSE_STATEMENT, (String)null);
            boolean r = GnoParserUtil.consumeTokens(b, 2, new IElementType[]{GnoTypes.LDOUBLE_BRACE, GnoTypes.ELSE, GnoTypes.RDOUBLE_BRACE});
            boolean p = r;
            r = r && GnoParserUtil.report_error_(b, GnoParserUtil.pushEndIsValid(b, l + 1));
            r = p && GnoParserUtil.report_error_(b, StatementList(b, l + 1)) && r;
            r = p && GnoParserUtil.report_error_(b, GnoParserUtil.pop(b, l + 1)) && r;
            r = p && EndStatement(b, l + 1) && r;
            GnoParserUtil.exit_section_(b, l, m, r, p, (GeneratedParserUtilBase.Parser)null);
            return r || p;
        }
    }

    public static boolean EmptyStatement(PsiBuilder b, int l) {
        if (!GnoParserUtil.recursion_guard_(b, l, "EmptyStatement")) {
            return false;
        } else if (!GnoParserUtil.nextTokenIs(b, GnoTypes.LDOUBLE_BRACE)) {
            return false;
        } else {
            PsiBuilder.Marker m = GnoParserUtil.enter_section_(b, l, 0, GnoTypes.EMPTY_STATEMENT, (String)null);
            boolean r = GnoParserUtil.consumeToken(b, GnoTypes.LDOUBLE_BRACE);
            r = r && GnoParserUtil.comment(b, l + 1);
            boolean p = r;
            r = r && GnoParserUtil.consumeToken(b, GnoTypes.RDOUBLE_BRACE);
            GnoParserUtil.exit_section_(b, l, m, r, p, (GeneratedParserUtilBase.Parser)null);
            return r || p;
        }
    }

    static boolean EndElse(PsiBuilder b, int l) {
        if (!GnoParserUtil.recursion_guard_(b, l, "EndElse")) {
            return false;
        } else if (!GnoParserUtil.nextTokenIs(b, GnoTypes.LDOUBLE_BRACE)) {
            return false;
        } else {
            boolean r = EndStatement(b, l + 1);
            if (!r) {
                r = ElseStatement(b, l + 1);
            }

            return r;
        }
    }

    static boolean EndElseIfElse(PsiBuilder b, int l) {
        if (!GnoParserUtil.recursion_guard_(b, l, "EndElseIfElse")) {
            return false;
        } else if (!GnoParserUtil.nextTokenIs(b, GnoTypes.LDOUBLE_BRACE)) {
            return false;
        } else {
            boolean r = EndStatement(b, l + 1);
            if (!r) {
                r = ElseIfStatement(b, l + 1);
            }

            if (!r) {
                r = ElseStatement(b, l + 1);
            }

            return r;
        }
    }

    public static boolean EndStatement(PsiBuilder b, int l) {
        if (!GnoParserUtil.recursion_guard_(b, l, "EndStatement")) {
            return false;
        } else if (!GnoParserUtil.nextTokenIs(b, GnoTypes.LDOUBLE_BRACE)) {
            return false;
        } else {
            PsiBuilder.Marker m = GnoParserUtil.enter_section_(b, l, 0, GnoTypes.END_STATEMENT, (String)null);
            boolean r = GnoParserUtil.consumeTokens(b, 2, new IElementType[]{GnoTypes.LDOUBLE_BRACE, GnoTypes.END, GnoTypes.RDOUBLE_BRACE});
            GnoParserUtil.exit_section_(b, l, m, r, r, (GeneratedParserUtilBase.Parser)null);
            return r || r;
        }
    }

    public static boolean Expression(PsiBuilder b, int l) {
        if (!GnoParserUtil.recursion_guard_(b, l, "Expression")) {
            return false;
        } else {
            PsiBuilder.Marker m = GnoParserUtil.enter_section_(b, l, 1, GnoTypes.EXPRESSION, "<expression>");
            boolean r = FieldChainExpr(b, l + 1);
            if (!r) {
                r = LiteralExpr(b, l + 1);
            }

            if (!r) {
                r = VariableExpr(b, l + 1);
            }

            if (!r) {
                r = ParenthesesExpr(b, l + 1);
            }

            GnoParserUtil.exit_section_(b, l, m, r, false, (GeneratedParserUtilBase.Parser)null);
            return r;
        }
    }

    public static boolean FieldChainExpr(PsiBuilder b, int l) {
        if (!GnoParserUtil.recursion_guard_(b, l, "FieldChainExpr")) {
            return false;
        } else {
            PsiBuilder.Marker m = GnoParserUtil.enter_section_(b, l, 1, GnoTypes.FIELD_CHAIN_EXPR, "<field chain expr>");
            boolean r = SimpleFieldChain(b, l + 1);
            r = r && FieldChainExpr_1(b, l + 1);
            GnoParserUtil.exit_section_(b, l, m, r, false, (GeneratedParserUtilBase.Parser)null);
            return r;
        }
    }

    private static boolean FieldChainExpr_1(PsiBuilder b, int l) {
        if (!GnoParserUtil.recursion_guard_(b, l, "FieldChainExpr_1")) {
            return false;
        } else {
            int c;
            do {
                c = GnoParserUtil.current_position_(b);
            } while(FieldChainExpr_1_0(b, l + 1) && GnoParserUtil.empty_element_parsed_guard_(b, "FieldChainExpr_1", c));

            return true;
        }
    }

    private static boolean FieldChainExpr_1_0(PsiBuilder b, int l) {
        if (!GnoParserUtil.recursion_guard_(b, l, "FieldChainExpr_1_0")) {
            return false;
        } else {
            PsiBuilder.Marker m = GnoParserUtil.enter_section_(b);
            boolean r = GnoParserUtil.notAfterSpace(b, l + 1);
            r = r && QualifiedFieldChain(b, l + 1);
            GnoParserUtil.exit_section_(b, m, (IElementType)null, r);
            return r;
        }
    }

    static boolean File(PsiBuilder b, int l) {
        return StatementList(b, l + 1);
    }

    public static boolean IfStatement(PsiBuilder b, int l) {
        if (!GnoParserUtil.recursion_guard_(b, l, "IfStatement")) {
            return false;
        } else if (!GnoParserUtil.nextTokenIs(b, GnoTypes.LDOUBLE_BRACE)) {
            return false;
        } else {
            PsiBuilder.Marker m = GnoParserUtil.enter_section_(b, l, 0, GnoTypes.IF_STATEMENT, (String)null);
            boolean r = GnoParserUtil.consumeTokens(b, 2, new IElementType[]{GnoTypes.LDOUBLE_BRACE, GnoTypes.IF});
            boolean p = r;
            r = r && GnoParserUtil.report_error_(b, PipelineOrDeclaration(b, l + 1));
            r = p && GnoParserUtil.report_error_(b, GnoParserUtil.consumeToken(b, GnoTypes.RDOUBLE_BRACE)) && r;
            r = p && GnoParserUtil.report_error_(b, GnoParserUtil.pushEndElseIsValid(b, l + 1)) && r;
            r = p && GnoParserUtil.report_error_(b, StatementList(b, l + 1)) && r;
            r = p && GnoParserUtil.report_error_(b, GnoParserUtil.pop(b, l + 1)) && r;
            r = p && EndElseIfElse(b, l + 1) && r;
            GnoParserUtil.exit_section_(b, l, m, r, p, (GeneratedParserUtilBase.Parser)null);
            return r || p;
        }
    }

    public static boolean Literal(PsiBuilder b, int l) {
        if (!GnoParserUtil.recursion_guard_(b, l, "Literal")) {
            return false;
        } else {
            PsiBuilder.Marker m = GnoParserUtil.enter_section_(b, l, 0, GnoTypes.LITERAL, "<literal>");
            boolean r = GnoParserUtil.consumeToken(b, GnoTypes.TRUE);
            if (!r) {
                r = GnoParserUtil.consumeToken(b, GnoTypes.FALSE);
            }

            if (!r) {
                r = GnoParserUtil.consumeToken(b, GnoTypes.NUMBER);
            }

            if (!r) {
                r = GnoParserUtil.consumeToken(b, GnoTypes.IDENTIFIER);
            }

            if (!r) {
                r = GnoParserUtil.consumeToken(b, GnoTypes.NIL);
            }

            if (!r) {
                r = GnoParserUtil.consumeToken(b, GnoTypes.DOT);
            }

            if (!r) {
                r = StringLiteral(b, l + 1);
            }

            if (!r) {
                r = GnoParserUtil.consumeToken(b, GnoTypes.AT);
            }

            if (!r) {
                r = GnoParserUtil.consumeToken(b, GnoTypes.HASH);
            }

            if (!r) {
                r = GnoParserUtil.consumeToken(b, GnoTypes.PERCENT);
            }

            if (!r) {
                r = GnoParserUtil.consumeToken(b, GnoTypes.CHAR);
            }

            GnoParserUtil.exit_section_(b, l, m, r, false, (GeneratedParserUtilBase.Parser)null);
            return r;
        }
    }

    public static boolean LiteralExpr(PsiBuilder b, int l) {
        if (!GnoParserUtil.recursion_guard_(b, l, "LiteralExpr")) {
            return false;
        } else {
            PsiBuilder.Marker m = GnoParserUtil.enter_section_(b, l, 0, GnoTypes.LITERAL_EXPR, "<literal expr>");
            boolean r = Literal(b, l + 1);
            GnoParserUtil.exit_section_(b, l, m, r, false, (GeneratedParserUtilBase.Parser)null);
            return r;
        }
    }

    public static boolean ParenthesesExpr(PsiBuilder b, int l) {
        if (!GnoParserUtil.recursion_guard_(b, l, "ParenthesesExpr")) {
            return false;
        } else if (!GnoParserUtil.nextTokenIs(b, GnoTypes.LPAREN)) {
            return false;
        } else {
            PsiBuilder.Marker m = GnoParserUtil.enter_section_(b, l, 0, GnoTypes.PARENTHESES_EXPR, (String)null);
            boolean r = GnoParserUtil.consumeToken(b, GnoTypes.LPAREN);
            boolean p = r;
            r = r && GnoParserUtil.report_error_(b, Pipeline(b, l + 1));
            r = p && GnoParserUtil.consumeToken(b, GnoTypes.RPAREN) && r;
            GnoParserUtil.exit_section_(b, l, m, r, p, (GeneratedParserUtilBase.Parser)null);
            return r || p;
        }
    }

    public static boolean Pipeline(PsiBuilder b, int l) {
        if (!GnoParserUtil.recursion_guard_(b, l, "Pipeline")) {
            return false;
        } else {
            PsiBuilder.Marker m = GnoParserUtil.enter_section_(b, l, 1, GnoTypes.PIPELINE, "<pipeline>");
            boolean r = SimplePipeline(b, l + 1);
            r = r && Pipeline_1(b, l + 1);
            GnoParserUtil.exit_section_(b, l, m, r, false, (GeneratedParserUtilBase.Parser)null);
            return r;
        }
    }

    private static boolean Pipeline_1(PsiBuilder b, int l) {
        if (!GnoParserUtil.recursion_guard_(b, l, "Pipeline_1")) {
            return false;
        } else {
            int c;
            do {
                c = GnoParserUtil.current_position_(b);
            } while(CompositePipeline(b, l + 1) && GnoParserUtil.empty_element_parsed_guard_(b, "Pipeline_1", c));

            return true;
        }
    }

    static boolean PipelineOrDeclaration(PsiBuilder b, int l) {
        if (!GnoParserUtil.recursion_guard_(b, l, "PipelineOrDeclaration")) {
            return false;
        } else {
            boolean r = VarDeclaration(b, l + 1);
            if (!r) {
                r = Pipeline(b, l + 1);
            }

            return r;
        }
    }

    public static boolean PipelineStatement(PsiBuilder b, int l) {
        if (!GnoParserUtil.recursion_guard_(b, l, "PipelineStatement")) {
            return false;
        } else if (!GnoParserUtil.nextTokenIs(b, GnoTypes.LDOUBLE_BRACE)) {
            return false;
        } else {
            PsiBuilder.Marker m = GnoParserUtil.enter_section_(b, l, 0, GnoTypes.PIPELINE_STATEMENT, (String)null);
            boolean r = GnoParserUtil.consumeToken(b, GnoTypes.LDOUBLE_BRACE);
            r = r && Pipeline(b, l + 1);
            boolean p = r;
            r = r && GnoParserUtil.consumeToken(b, GnoTypes.RDOUBLE_BRACE);
            GnoParserUtil.exit_section_(b, l, m, r, p, (GeneratedParserUtilBase.Parser)null);
            return r || p;
        }
    }

    public static boolean QualifiedFieldChain(PsiBuilder b, int l) {
        if (!GnoParserUtil.recursion_guard_(b, l, "QualifiedFieldChain")) {
            return false;
        } else if (!GnoParserUtil.nextTokenIs(b, GnoTypes.DOT)) {
            return false;
        } else {
            PsiBuilder.Marker m = GnoParserUtil.enter_section_(b, l, 2, GnoTypes.FIELD_CHAIN_EXPR, (String)null);
            boolean r = DotName(b, l + 1);
            GnoParserUtil.exit_section_(b, l, m, r, false, (GeneratedParserUtilBase.Parser)null);
            return r;
        }
    }

    public static boolean RangeStatement(PsiBuilder b, int l) {
        if (!GnoParserUtil.recursion_guard_(b, l, "RangeStatement")) {
            return false;
        } else if (!GnoParserUtil.nextTokenIs(b, GnoTypes.LDOUBLE_BRACE)) {
            return false;
        } else {
            PsiBuilder.Marker m = GnoParserUtil.enter_section_(b, l, 0, GnoTypes.RANGE_STATEMENT, (String)null);
            boolean r = GnoParserUtil.consumeTokens(b, 2, new IElementType[]{GnoTypes.LDOUBLE_BRACE, GnoTypes.RANGE});
            boolean p = r;
            r = r && GnoParserUtil.report_error_(b, RangeStatement_2(b, l + 1));
            r = p && GnoParserUtil.report_error_(b, GnoParserUtil.consumeToken(b, GnoTypes.RDOUBLE_BRACE)) && r;
            r = p && GnoParserUtil.report_error_(b, GnoParserUtil.pushEndElseIsValid(b, l + 1)) && r;
            r = p && GnoParserUtil.report_error_(b, StatementList(b, l + 1)) && r;
            r = p && GnoParserUtil.report_error_(b, GnoParserUtil.pop(b, l + 1)) && r;
            r = p && EndElse(b, l + 1) && r;
            GnoParserUtil.exit_section_(b, l, m, r, p, (GeneratedParserUtilBase.Parser)null);
            return r || p;
        }
    }

    private static boolean RangeStatement_2(PsiBuilder b, int l) {
        if (!GnoParserUtil.recursion_guard_(b, l, "RangeStatement_2")) {
            return false;
        } else {
            boolean r = RangeVarDeclaration(b, l + 1);
            if (!r) {
                r = Pipeline(b, l + 1);
            }

            return r;
        }
    }

    public static boolean RangeVarDeclaration(PsiBuilder b, int l) {
        if (!GnoParserUtil.recursion_guard_(b, l, "RangeVarDeclaration")) {
            return false;
        } else if (!GnoParserUtil.nextTokenIs(b, GnoTypes.VARIABLE)) {
            return false;
        } else {
            PsiBuilder.Marker m = GnoParserUtil.enter_section_(b);
            boolean r = VarDefinition(b, l + 1);
            r = r && RangeVarDeclaration_1(b, l + 1);
            r = r && GnoParserUtil.consumeToken(b, GnoTypes.VAR_ASSIGN);
            r = r && Pipeline(b, l + 1);
            GnoParserUtil.exit_section_(b, m, GnoTypes.RANGE_VAR_DECLARATION, r);
            return r;
        }
    }

    private static boolean RangeVarDeclaration_1(PsiBuilder b, int l) {
        if (!GnoParserUtil.recursion_guard_(b, l, "RangeVarDeclaration_1")) {
            return false;
        } else {
            RangeVarDeclaration_1_0(b, l + 1);
            return true;
        }
    }

    private static void RangeVarDeclaration_1_0(PsiBuilder b, int l) {
        if (!GnoParserUtil.recursion_guard_(b, l, "RangeVarDeclaration_1_0")) {
        } else {
            PsiBuilder.Marker m = GnoParserUtil.enter_section_(b);
            boolean r = GnoParserUtil.consumeToken(b, GnoTypes.COMMA);
            r = r && VarDefinition(b, l + 1);
            GnoParserUtil.exit_section_(b, m, (IElementType)null, r);
        }
    }

    static boolean Recover(PsiBuilder b, int l) {
        if (!GnoParserUtil.recursion_guard_(b, l, "Recover")) {
            return false;
        } else {
            PsiBuilder.Marker m = GnoParserUtil.enter_section_(b, l, 16);
            boolean r = !Recover_0(b, l + 1);
            GnoParserUtil.exit_section_(b, l, m, r, false, (GeneratedParserUtilBase.Parser)null);
            return r;
        }
    }

    private static boolean Recover_0(PsiBuilder b, int l) {
        if (!GnoParserUtil.recursion_guard_(b, l, "Recover_0")) {
            return false;
        } else {
            boolean r = GnoParserUtil.consumeToken(b, GnoTypes.LDOUBLE_BRACE);
            if (!r) {
                r = GnoParserUtil.consumeToken(b, GnoTypes.TEXT);
            }

            return r;
        }
    }

    public static boolean SimpleFieldChain(PsiBuilder b, int l) {
        if (!GnoParserUtil.recursion_guard_(b, l, "SimpleFieldChain")) {
            return false;
        } else {
            PsiBuilder.Marker m = GnoParserUtil.enter_section_(b, l, 0, GnoTypes.FIELD_CHAIN_EXPR, "<simple field chain>");
            boolean r = SimpleFieldChain_0(b, l + 1);
            r = r && DotName(b, l + 1);
            GnoParserUtil.exit_section_(b, l, m, r, false, (GeneratedParserUtilBase.Parser)null);
            return r;
        }
    }

    private static boolean SimpleFieldChain_0(PsiBuilder b, int l) {
        if (!GnoParserUtil.recursion_guard_(b, l, "SimpleFieldChain_0")) {
            return false;
        } else {
            SimpleFieldChain_0_0(b, l + 1);
            return true;
        }
    }

    private static void SimpleFieldChain_0_0(PsiBuilder b, int l) {
        if (!GnoParserUtil.recursion_guard_(b, l, "SimpleFieldChain_0_0")) {
        } else {
            PsiBuilder.Marker m = GnoParserUtil.enter_section_(b);
            boolean r = SimpleFieldChain_0_0_0(b, l + 1);
            r = r && GnoParserUtil.notAfterSpace(b, l + 1);
            GnoParserUtil.exit_section_(b, m, (IElementType)null, r);
        }
    }

    private static boolean SimpleFieldChain_0_0_0(PsiBuilder b, int l) {
        if (!GnoParserUtil.recursion_guard_(b, l, "SimpleFieldChain_0_0_0")) {
            return false;
        } else {
            boolean r = VariableExpr(b, l + 1);
            if (!r) {
                r = ParenthesesExpr(b, l + 1);
            }

            return r;
        }
    }

    public static boolean SimplePipeline(PsiBuilder b, int l) {
        if (!GnoParserUtil.recursion_guard_(b, l, "SimplePipeline")) {
            return false;
        } else {
            PsiBuilder.Marker m = GnoParserUtil.enter_section_(b, l, 0, GnoTypes.SIMPLE_PIPELINE, "<simple pipeline>");
            boolean r = Expression(b, l + 1);
            r = r && SimplePipeline_1(b, l + 1);
            GnoParserUtil.exit_section_(b, l, m, r, false, (GeneratedParserUtilBase.Parser)null);
            return r;
        }
    }

    private static boolean SimplePipeline_1(PsiBuilder b, int l) {
        if (!GnoParserUtil.recursion_guard_(b, l, "SimplePipeline_1")) {
            return false;
        } else {
            int c;
            do {
                c = GnoParserUtil.current_position_(b);
            } while(Expression(b, l + 1) && GnoParserUtil.empty_element_parsed_guard_(b, "SimplePipeline_1", c));

            return true;
        }
    }

    public static boolean StatementList(PsiBuilder b, int l) {
        if (!GnoParserUtil.recursion_guard_(b, l, "StatementList")) {
            return false;
        } else {
            PsiBuilder.Marker m = GnoParserUtil.enter_section_(b, l, 0, GnoTypes.STATEMENT_LIST, "<statement list>");

            int c;
            do {
                c = GnoParserUtil.current_position_(b);
            } while(StatementsGroup(b, l + 1) && GnoParserUtil.empty_element_parsed_guard_(b, "StatementList", c));

            GnoParserUtil.exit_section_(b, l, m, true, false, (GeneratedParserUtilBase.Parser)null);
            return true;
        }
    }

    static boolean StatementsGroup(PsiBuilder b, int l) {
        if (!GnoParserUtil.recursion_guard_(b, l, "StatementsGroup")) {
            return false;
        } else if (!GnoParserUtil.nextTokenIs(b, "", new IElementType[]{GnoTypes.LDOUBLE_BRACE, GnoTypes.TEXT})) {
            return false;
        } else {
            PsiBuilder.Marker m = GnoParserUtil.enter_section_(b);
            boolean r = StatementsGroup_0(b, l + 1);
            if (!r) {
                r = IfStatement(b, l + 1);
            }

            if (!r) {
                r = BlockStatement(b, l + 1);
            }

            if (!r) {
                r = TemplateStatement(b, l + 1);
            }

            if (!r) {
                r = RangeStatement(b, l + 1);
            }

            if (!r) {
                r = WithStatement(b, l + 1);
            }

            if (!r) {
                r = DefineStatement(b, l + 1);
            }

            if (!r) {
                r = VarDeclarationStatement(b, l + 1);
            }

            if (!r) {
                r = AssignStatement(b, l + 1);
            }

            if (!r) {
                r = PipelineStatement(b, l + 1);
            }

            if (!r) {
                r = EmptyStatement(b, l + 1);
            }

            if (!r) {
                r = BadStatement(b, l + 1);
            }

            GnoParserUtil.exit_section_(b, m, (IElementType)null, r);
            return r;
        }
    }

    private static boolean StatementsGroup_0(PsiBuilder b, int l) {
        if (!GnoParserUtil.recursion_guard_(b, l, "StatementsGroup_0")) {
            return false;
        } else {
            PsiBuilder.Marker m = GnoParserUtil.enter_section_(b);
            boolean r = GnoParserUtil.consumeToken(b, GnoTypes.TEXT);

            while(r) {
                int c = GnoParserUtil.current_position_(b);
                if (!GnoParserUtil.consumeToken(b, GnoTypes.TEXT) || !GnoParserUtil.empty_element_parsed_guard_(b, "StatementsGroup_0", c)) {
                    break;
                }
            }

            GnoParserUtil.exit_section_(b, m, (IElementType)null, r);
            return r;
        }
    }

    public static boolean StringLiteral(PsiBuilder b, int l) {
        if (!GnoParserUtil.recursion_guard_(b, l, "StringLiteral")) {
            return false;
        } else if (!GnoParserUtil.nextTokenIs(b, "<string literal>", new IElementType[]{GnoTypes.RAW_STRING, GnoTypes.STRING})) {
            return false;
        } else {
            PsiBuilder.Marker m = GnoParserUtil.enter_section_(b, l, 0, GnoTypes.STRING_LITERAL, "<string literal>");
            boolean r = GnoParserUtil.consumeToken(b, GnoTypes.RAW_STRING);
            if (!r) {
                r = GnoParserUtil.consumeToken(b, GnoTypes.STRING);
            }

            GnoParserUtil.exit_section_(b, l, m, r, false, (GeneratedParserUtilBase.Parser)null);
            return r;
        }
    }

    public static boolean TemplateStatement(PsiBuilder b, int l) {
        if (!GnoParserUtil.recursion_guard_(b, l, "TemplateStatement")) {
            return false;
        } else if (!GnoParserUtil.nextTokenIs(b, GnoTypes.LDOUBLE_BRACE)) {
            return false;
        } else {
            PsiBuilder.Marker m = GnoParserUtil.enter_section_(b, l, 0, GnoTypes.TEMPLATE_STATEMENT, (String)null);
            boolean r = GnoParserUtil.consumeTokens(b, 2, new IElementType[]{GnoTypes.LDOUBLE_BRACE, GnoTypes.TEMPLATE});
            boolean p = r;
            r = r && GnoParserUtil.report_error_(b, StringLiteral(b, l + 1));
            r = p && GnoParserUtil.report_error_(b, TemplateStatement_3(b, l + 1)) && r;
            r = p && GnoParserUtil.consumeToken(b, GnoTypes.RDOUBLE_BRACE) && r;
            GnoParserUtil.exit_section_(b, l, m, r, p, (GeneratedParserUtilBase.Parser)null);
            return r || p;
        }
    }

    private static boolean TemplateStatement_3(PsiBuilder b, int l) {
        if (!GnoParserUtil.recursion_guard_(b, l, "TemplateStatement_3")) {
            return false;
        } else {
            PipelineOrDeclaration(b, l + 1);
            return true;
        }
    }

    public static boolean VarDeclaration(PsiBuilder b, int l) {
        if (!GnoParserUtil.recursion_guard_(b, l, "VarDeclaration")) {
            return false;
        } else if (!GnoParserUtil.nextTokenIs(b, GnoTypes.VARIABLE)) {
            return false;
        } else {
            PsiBuilder.Marker m = GnoParserUtil.enter_section_(b, l, 0, GnoTypes.VAR_DECLARATION, (String)null);
            boolean r = VarDefinition(b, l + 1);
            r = r && GnoParserUtil.consumeToken(b, GnoTypes.VAR_ASSIGN);
            boolean p = r;
            r = r && Pipeline(b, l + 1);
            GnoParserUtil.exit_section_(b, l, m, r, p, (GeneratedParserUtilBase.Parser)null);
            return r || p;
        }
    }

    public static boolean VarDeclarationStatement(PsiBuilder b, int l) {
        if (!GnoParserUtil.recursion_guard_(b, l, "VarDeclarationStatement")) {
            return false;
        } else if (!GnoParserUtil.nextTokenIs(b, GnoTypes.LDOUBLE_BRACE)) {
            return false;
        } else {
            PsiBuilder.Marker m = GnoParserUtil.enter_section_(b, l, 0, GnoTypes.VAR_DECLARATION_STATEMENT, (String)null);
            boolean r = GnoParserUtil.consumeToken(b, GnoTypes.LDOUBLE_BRACE);
            r = r && VarDeclaration(b, l + 1);
            boolean p = r;
            r = r && GnoParserUtil.consumeToken(b, GnoTypes.RDOUBLE_BRACE);
            GnoParserUtil.exit_section_(b, l, m, r, p, null);
            return r || p;
        }
    }

    public static boolean VarDefinition(PsiBuilder b, int l) {
        if (!GnoParserUtil.recursion_guard_(b, l, "VarDefinition")) {
            return false;
        } else if (!GnoParserUtil.nextTokenIs(b, GnoTypes.VARIABLE)) {
            return false;
        } else {
            PsiBuilder.Marker m = GnoParserUtil.enter_section_(b);
            boolean r = GnoParserUtil.consumeToken(b, GnoTypes.VARIABLE);
            GnoParserUtil.exit_section_(b, m, GnoTypes.VAR_DEFINITION, r);
            return r;
        }
    }

    public static boolean VariableExpr(PsiBuilder b, int l) {
        if (!GnoParserUtil.recursion_guard_(b, l, "VariableExpr")) {
            return false;
        } else if (!GnoParserUtil.nextTokenIs(b, GnoTypes.VARIABLE)) {
            return false;
        } else {
            PsiBuilder.Marker m = GnoParserUtil.enter_section_(b);
            boolean r = GnoParserUtil.consumeToken(b, GnoTypes.VARIABLE);
            GnoParserUtil.exit_section_(b, m, GnoTypes.VARIABLE_EXPR, r);
            return r;
        }
    }

    public static boolean WithStatement(PsiBuilder b, int l) {
        if (!GnoParserUtil.recursion_guard_(b, l, "WithStatement")) {
            return false;
        } else if (!GnoParserUtil.nextTokenIs(b, GnoTypes.LDOUBLE_BRACE)) {
            return false;
        } else {
            PsiBuilder.Marker m = GnoParserUtil.enter_section_(b, l, 0, GnoTypes.WITH_STATEMENT, (String)null);
            boolean r = GnoParserUtil.consumeTokens(b, 2, GnoTypes.LDOUBLE_BRACE, GnoTypes.WITH);
            boolean p = r;
            r = r && GnoParserUtil.report_error_(b, PipelineOrDeclaration(b, l + 1));
            r = p && GnoParserUtil.report_error_(b, GnoParserUtil.consumeToken(b, GnoTypes.RDOUBLE_BRACE)) && r;
            r = p && GnoParserUtil.report_error_(b, GnoParserUtil.pushEndElseIsValid(b, l + 1)) && r;
            r = p && GnoParserUtil.report_error_(b, StatementList(b, l + 1)) && r;
            r = p && GnoParserUtil.report_error_(b, GnoParserUtil.pop(b, l + 1)) && r;
            r = p && EndElse(b, l + 1) && r;
            GnoParserUtil.exit_section_(b, l, m, r, p, null);
            return r || p;
        }
    }

    static {
        EXTENDS_SETS_ = new TokenSet[]{GnoParserUtil.create_token_set_(GnoTypes.RANGE_VAR_DECLARATION, GnoTypes.VAR_DECLARATION), GnoParserUtil.create_token_set_(new IElementType[]{GnoTypes.COMPOSITE_PIPELINE, GnoTypes.PIPELINE, GnoTypes.SIMPLE_PIPELINE}), GnoParserUtil.create_token_set_(new IElementType[]{GnoTypes.EXPRESSION, GnoTypes.FIELD_CHAIN_EXPR, GnoTypes.LITERAL_EXPR, GnoTypes.PARENTHESES_EXPR, GnoTypes.VARIABLE_EXPR}), GnoParserUtil.create_token_set_(new IElementType[]{GnoTypes.ASSIGN_STATEMENT, GnoTypes.BLOCK_STATEMENT, GnoTypes.DEFINE_STATEMENT, GnoTypes.ELSE_IF_STATEMENT, GnoTypes.ELSE_STATEMENT, GnoTypes.EMPTY_STATEMENT, GnoTypes.END_STATEMENT, GnoTypes.IF_STATEMENT, GnoTypes.PIPELINE_STATEMENT, GnoTypes.RANGE_STATEMENT, GnoTypes.TEMPLATE_STATEMENT, GnoTypes.VAR_DECLARATION_STATEMENT, GnoTypes.WITH_STATEMENT})};
    }
}
