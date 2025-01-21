package com.github.intellij.gno.language;

import com.github.intellij.gno.psi.GnoPropertyDeclaration;
import com.intellij.navigation.ChooseByNameContributorEx;
import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.project.Project;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.Processor;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.indexing.FindSymbolParameters;
import com.intellij.util.indexing.IdFilter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

final class GnoChooseByNameContributor implements ChooseByNameContributorEx {

    @Override
    public void processNames(@NotNull Processor<? super String> processor,
                             @NotNull GlobalSearchScope scope,
                             @Nullable IdFilter filter) {
        Project project = Objects.requireNonNull(scope.getProject());
        List<GnoPropertyDeclaration> properties = GnoUtil.findProperties(project);

        System.out.println("🔍 processNames - Nombre de propriétés trouvées : " + properties.size());

        List<String> propertyKeys = ContainerUtil.map(properties, GnoPropertyDeclaration::getKey);
        ContainerUtil.process(propertyKeys, processor);
    }


    @Override
    public void processElementsWithName(@NotNull String name,
                                        @NotNull Processor<? super NavigationItem> processor,
                                        @NotNull FindSymbolParameters parameters) {
        List<GnoPropertyDeclaration> properties = GnoUtil.findProperties(parameters.getProject(), name);

        System.out.println("🔍 processElementsWithName - Recherche du symbole : " + name);
        System.out.println("📌 Nombre de propriétés trouvées : " + properties.size());

        List<NavigationItem> navigationItems = ContainerUtil.map(properties, property -> (NavigationItem) property);
        ContainerUtil.process(navigationItems, processor);
    }


}