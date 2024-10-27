package com.github.intellij.gno.language.psi;

import com.github.intellij.gno.language.psi.impl.GnoExpressionImpl;
import com.github.intellij.gno.language.psi.impl.GnoPsiImplUtil;
import com.github.intellij.gno.language.util.GnoStringLiteralEscaper;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import static com.github.intellij.gno.language.GnoTypes.*;

public class GnoStringLiteralImpl extends GnoExpressionImpl implements GnoStringLiteral {

    public GnoStringLiteralImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GnoVisitor visitor) {
        visitor.visitStringLiteral(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GnoVisitor) accept((GnoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @Nullable
    public PsiElement getRawString() {
        return findChildByType(RAW_STRING);
    }

    @Override
    @Nullable
    public PsiElement getString() {
        return findChildByType(STRING);
    }

    public boolean isValidHost() {
        return GnoPsiImplUtil.isValidHost(this);
    }

    @NotNull
    public GnoStringLiteralImpl updateText(String text) {
        return GnoPsiImplUtil.updateText(this, text);
    }

    @NotNull
    public GnoStringLiteralEscaper createLiteralTextEscaper() {
        return GnoPsiImplUtil.createLiteralTextEscaper(this);
    }

    @NotNull
    public String getDecodedText() {
        return GnoPsiImplUtil.getDecodedText(this);
    }

}