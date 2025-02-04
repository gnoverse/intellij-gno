/*
 * Copyright 2013-2016 Sergey Ignatov, Alexander Zolotov, Florin Patan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.intellij.gno.util;

import com.github.intellij.gno.psi.GnoFile;
import com.github.intellij.gno.psi.GnoImportSpec;
import com.github.intellij.gno.psi.GnoNamedElement;
import com.github.intellij.gno.psi.impl.GnoPsiImplUtil;
import com.github.intellij.gno.psi.impl.imports.GnoImportReferenceSet;
import com.github.intellij.gno.sdk.GnoPackageUtil;
import com.github.intellij.gno.sdk.GnoSdkService;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.ObjectUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Set;

public class GnoPathUseScope extends GlobalSearchScope {
  public static GlobalSearchScope create(@NotNull PsiElement declarationContext, boolean filterByImportList) {
    if (declarationContext instanceof GnoNamedElement && ((GnoNamedElement)declarationContext).isBlank()) {
      return GlobalSearchScope.EMPTY_SCOPE;
    }
    PsiFile declarationPsiFile = declarationContext.getContainingFile();
    if (!(declarationPsiFile instanceof GnoFile)) {
      return GlobalSearchScope.fileScope(declarationPsiFile);
    }
    if (GnoPsiImplUtil.isBuiltinFile(declarationPsiFile)) {
      return GlobalSearchScope.allScope(declarationContext.getProject());
    }

    VirtualFile declarationFile = declarationPsiFile.getVirtualFile();
    if (declarationFile == null || declarationFile.getParent() == null) {
      return GlobalSearchScope.fileScope(declarationPsiFile);
    }

    return new GnoPathUseScope(declarationPsiFile.getProject(), declarationFile, filterByImportList);
  }

  @NotNull private final VirtualFile myDeclarationFile;
  private final boolean myFilterByImportList;

  private GnoPathUseScope(@NotNull Project project, @NotNull VirtualFile declarationFile, boolean filterByImportList) {
    super(project);
    myDeclarationFile = declarationFile;
    myFilterByImportList = filterByImportList;
  }

  @Override
  public boolean contains(@NotNull VirtualFile referenceFile) {
    VirtualFile referenceDirectory = referenceFile.isDirectory() ? referenceFile : referenceFile.getParent();
    if (referenceDirectory == null) {
      return false;
    }
    VirtualFile declarationDirectory = myDeclarationFile.getParent();
    if (referenceDirectory.equals(declarationDirectory)) {
      return true;
    }

    Project project = ObjectUtils.assertNotNull(getProject());
    PsiManager psiManager = PsiManager.getInstance(project);
    PsiFile referencePsiFile = psiManager.findFile(referenceFile);
    Module module = referencePsiFile != null ? ModuleUtilCore.findModuleForPsiElement(referencePsiFile) : null;

    GnoPathScopeHelper scopeHelper = GnoPathScopeHelper.fromReferenceFile(project, module, referenceFile);
    if (!scopeHelper.couldBeReferenced(myDeclarationFile, referenceFile)) {
      return false;
    }
    if (!myFilterByImportList) {
      return true;
    }

    if (!(referencePsiFile instanceof GnoFile)) {
      // it's some injection or cross-reference, so we cannot check its imports
      return true;
    }
    PsiFile declarationPsiFile = psiManager.findFile(myDeclarationFile);
    if (declarationPsiFile instanceof GnoFile) {
      String importPath = ((GnoFile)declarationPsiFile).getImportPath(scopeHelper.isVendoringEnabled());
      Map<String, GnoImportSpec> importedPackagesMap = ((GnoFile)referencePsiFile).getImportedPackagesMap();
      if (importedPackagesMap.containsKey(importPath)) {
        return true;
      }
      if (hasRelativeImportOfTargetPackage(importedPackagesMap.keySet(), referenceDirectory, declarationDirectory)) {
        return true;
      }

      for (GnoFile packageFile : GnoPackageUtil.getAllPackageFiles(referencePsiFile.getContainingDirectory(), null)) {
        if (packageFile != referencePsiFile && referencePsiFile.getOriginalFile() != packageFile) {
          Map<String, GnoImportSpec> packagesMap = packageFile.getImportedPackagesMap();
          if (packagesMap.containsKey(importPath)) {
            return true;
          }
          if (hasRelativeImportOfTargetPackage(packagesMap.keySet(), referenceDirectory, declarationDirectory)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  private static boolean hasRelativeImportOfTargetPackage(@NotNull Set<String> paths,
                                                          @NotNull VirtualFile referenceDirectory,
                                                          @NotNull VirtualFile declarationDirectory) {
    for (String pathString : paths) {
      if (GnoImportReferenceSet.isRelativeImport(pathString)) {
        VirtualFile file = referenceDirectory.findFileByRelativePath(pathString);
        if (declarationDirectory.equals(file)) {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public int compare(@NotNull VirtualFile file1, @NotNull VirtualFile file2) {
    return 0;
  }

  @Override
  public boolean isSearchInModuleContent(@NotNull Module aModule) {
    return GnoSdkService.getInstance(ObjectUtils.assertNotNull(getProject())).isGnoModule(aModule);
  }

  @Override
  public boolean isSearchInLibraries() {
    return false;
  }
}
