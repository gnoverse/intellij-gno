package com.github.intellij.gno.parser;

import com.github.intellij.gno.psi.GnoTokenSets;
import com.github.intellij.gno.psi.GnoTypes;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.lang.parser.GeneratedParserUtilBase;
import com.intellij.openapi.util.Key;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.util.ArrayUtil;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GnoParserUtil extends GeneratedParserUtilBase {
    private static final Key<BadStatementSettings> BAD_STATEMENT_SETTINGS = Key.create("go.template.parser.bad.statement.settings");

    public GnoParserUtil() {
    }

    public static PsiBuilder adapt_builder_(IElementType root, PsiBuilder builder, PsiParser parser, TokenSet[] tokenSets) {
        PsiBuilder result = GeneratedParserUtilBase.adapt_builder_(root, builder, parser, (TokenSet[])ArrayUtil.mergeArrays(tokenSets, GnoParser.EXTENDS_SETS_));
        ErrorState.get(result).braces = GnoBraceMatcher.PAIRS;
        return result;
    }

    public static boolean notAfterSpace(@NotNull PsiBuilder builder_, int level) {
        if (builder_ == null) {
            $$$reportNull$$$0(0);
        }

        return !builder_.eof() && builder_.rawLookup(-1) != TokenType.WHITE_SPACE;
    }

    public static boolean comment(PsiBuilder builder, int level) {
        int tokenIdx;
        for(tokenIdx = 0; builder.rawLookup(tokenIdx) == TokenType.WHITE_SPACE; ++tokenIdx) {
        }

        return builder.rawLookup(tokenIdx) == GnoTokenSets.COMMENT;
    }

    public static boolean unexpectedToken(@NotNull PsiBuilder builder_, int level) {
        if (builder_ == null) {
            $$$reportNull$$$0(1);
        }

        String text = builder_.getTokenText();
        return true;
    }

    public static boolean badStatementStart(@NotNull PsiBuilder b, int level) {
        if (b == null) {
            $$$reportNull$$$0(2);
        }

        BadStatementSettings settings = (BadStatementSettings)b.getUserData(BAD_STATEMENT_SETTINGS);
        return settings == null || settings.canStartBadStatement(b.getTokenType());
    }

    public static boolean pushEndIsValid(@NotNull PsiBuilder b, int level) {
        if (b == null) {
            $$$reportNull$$$0(3);
        }

        b.putUserData(BAD_STATEMENT_SETTINGS, new BadStatementSettings(false, true, (BadStatementSettings)b.getUserData(BAD_STATEMENT_SETTINGS)));
        return true;
    }

    public static boolean pushEndElseIsValid(@NotNull PsiBuilder b, int level) {
        if (b == null) {
            $$$reportNull$$$0(4);
        }

        b.putUserData(BAD_STATEMENT_SETTINGS, new BadStatementSettings(true, true, (BadStatementSettings)b.getUserData(BAD_STATEMENT_SETTINGS)));
        return true;
    }

    public static boolean pop(@NotNull PsiBuilder b, int level) {
        if (b == null) {
            $$$reportNull$$$0(5);
        }

        BadStatementSettings current = (BadStatementSettings)Objects.requireNonNull((BadStatementSettings)b.getUserData(BAD_STATEMENT_SETTINGS));
        b.putUserData(BAD_STATEMENT_SETTINGS, current.myParent);
        return true;
    }

    private static final class BadStatementSettings {
        private final boolean myElseIsValid;
        private final boolean myEndIsValid;
        private final BadStatementSettings myParent;

        private BadStatementSettings(boolean elseIsValid, boolean endIsValid, @Nullable @Nullable BadStatementSettings parent) {
            this.myElseIsValid = elseIsValid;
            this.myEndIsValid = endIsValid;
            this.myParent = parent;
        }

        private boolean canStartBadStatement(@Nullable IElementType token) {
            if (token == GnoTypes.ELSE) {
                return !this.myElseIsValid;
            } else if (token == GnoTypes.END) {
                return !this.myEndIsValid;
            } else {
                return true;
            }
        }
    }
}
