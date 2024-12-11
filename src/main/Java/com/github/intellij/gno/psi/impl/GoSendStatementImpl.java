package com.github.intellij.gno.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.github.intellij.gno.psi.GoPsiTreeUtil;
import static com.github.intellij.gno.GoTypes.*;
import com.github.intellij.gno.psi.*;

public class GoSendStatementImpl extends GoStatementImpl implements GoSendStatement {

    public GoSendStatementImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull GoVisitor visitor) {
        visitor.visitSendStatement(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof GoVisitor) accept((GoVisitor)visitor);
        else super.accept(visitor);
    }

    @Override
    @NotNull
    public List<GoExpression> getExpressionList() {
        return GoPsiTreeUtil.getChildrenOfTypeAsList(this, GoExpression.class);
    }

    @Override
    @Nullable
    public GoLeftHandExprList getLeftHandExprList() {
        return GoPsiTreeUtil.getChildOfType(this, GoLeftHandExprList.class);
    }

    @Override
    @NotNull
    public PsiElement getSendChannel() {
        return notNullChild(findChildByType(SEND_CHANNEL));
    }

    @Nullable
    public GoExpression getSendExpression() {
        return GoPsiImplUtil.getSendExpression(this);
    }

}