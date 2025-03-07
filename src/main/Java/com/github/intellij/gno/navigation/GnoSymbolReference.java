package com.github.intellij.gno.navigation;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReferenceBase;
import com.intellij.psi.PsiNamedElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GnoSymbolReference extends PsiReferenceBase<PsiElement> {

    public GnoSymbolReference(@NotNull PsiElement element) {
        super(element);
    }

    @Override
    public @Nullable PsiElement resolve() {
        if (myElement instanceof PsiNamedElement) {
            String name = ((PsiNamedElement) myElement).getName();
            if (name != null) {
                PsiElement target = myElement.getContainingFile().findElementAt(myElement.getTextOffset());
                if (target != null) {
                    return target;
                }
            }
        }
        return null;
    }

    @Override
    public @NotNull Object[] getVariants() {
        return new Object[0];
    }
}
