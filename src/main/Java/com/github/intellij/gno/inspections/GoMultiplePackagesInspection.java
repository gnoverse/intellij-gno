package com.github.intellij.gno.inspections;

import com.github.intellij.gno.GoConstants;
import com.github.intellij.gno.psi.GoFile;
import com.github.intellij.gno.psi.GoPackageClause;
import com.github.intellij.gno.quickfix.GoMultiplePackagesQuickFix;
import com.github.intellij.gno.sdk.GoPackageUtil;
import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.ide.scratch.ScratchUtil;
import com.intellij.psi.PsiDirectory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public class GoMultiplePackagesInspection extends GoInspectionBase {
  @Override
  protected void checkFile(@NotNull GoFile file, @NotNull ProblemsHolder problemsHolder) {
    if (ScratchUtil.isScratch(file.getVirtualFile())) return;
    GoPackageClause packageClause = file.getPackage();
    if (packageClause != null) {
      String packageName = file.getPackageName();
      if (packageName == null || packageName.equals(GoConstants.DOCUMENTATION)) return;
      PsiDirectory dir = file.getContainingDirectory();
      Collection<String> packages = GoPackageUtil.getAllPackagesInDirectory(dir, null, true);
      packages.remove(GoConstants.DOCUMENTATION);
      if (packages.size() > 1) {
        Collection<LocalQuickFix> fixes = new ArrayList<>();
        if (problemsHolder.isOnTheFly()) {
          fixes.add(new GoMultiplePackagesQuickFix(packageClause, packageName, packages, true));
        }
        else {
          for (String name : packages) {
            fixes.add(new GoMultiplePackagesQuickFix(packageClause, name, packages, false));
          }
        }
        problemsHolder.registerProblem(packageClause, "Multiple packages in directory", fixes.toArray(new LocalQuickFix[0]));
      }
    }
  }
}
