package com.github.intellij.gno.navigation;

import com.intellij.psi.PsiReferenceContributor;
import com.intellij.psi.PsiReferenceRegistrar;
import org.jetbrains.annotations.NotNull;

public class GnoImplicitReferenceProvider extends PsiReferenceContributor {

    @Override
    public void registerReferenceProviders(@NotNull PsiReferenceRegistrar registrar) {
        registrar.registerReferenceProvider(GnoPattern.identifierPattern(), new GnoReferenceProvider());
    }
}
